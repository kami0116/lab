package per.gh.study.mysql.asciitable;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Data
public class AsciiTableParser {

    private List<ColumnDataStructure> schema;
    private List<List<String>> data;
    private String tableName;

    public AsciiTableParser parse() throws IOException {
        init();
        String table = load("ascii_table.txt");
        String[] lines = table.split("\n");
        tableName = lines[0].trim();
        String[] names = lines[2].split("\\|");
        String[] firstColumn = lines[4].split("\\|");
        //schema
        for (int i = 1; i < names.length - 1; i++) {
            ColumnDataStructure cds = new ColumnDataStructure();
            cds.name = names[i];
            String cell = firstColumn[i].trim();
            //type
            if (isNumber(cell)) {
                if (cell.contains(".")) {
                    cds.type = Double.class;
                    cds.converter = Double::parseDouble;
                } else {
                    cds.type = Integer.class;
                    cds.converter = Integer::parseInt;
                }
            } else {
                cds.type = String.class;
                cds.converter = s -> s;
            }
            schema.add(cds);
        }
        //data
        for (int i = 4; i < lines.length; i++) {
            String[] col = lines[i].split("\\|");
            List<String> column = new LinkedList<>();
            for (int j = 1; j < col.length - 1; j++) {
                column.add(col[j].trim());
            }
            data.add(column);
        }
        return this;
    }

    void init() {
        schema = new LinkedList<>();
        data = new LinkedList<>();
    }

    String load(String path) throws IOException {
        StringBuilder table = new StringBuilder();
        InputStream in = null;
        try {
            in = this.getClass().getClassLoader().getResourceAsStream(path);
            byte[] buf = new byte[4096];
            int length;
            for (; (length = in.read(buf)) > 0; ) {
                table.append(new String(buf, 0, length));
            }
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
        return table.toString();
    }

    boolean isNumber(String c) {
        boolean flag = true;
        for (byte b : c.getBytes()) {
            if (b == '.') {
                if (flag) {
                    flag = false;
                } else {
                    return false;
                }
            } else if (b < '0' || b > '9') {
                return false;
            }
        }
        return true;
    }
}