package per.gh.study.mysql.asciitable;

import org.junit.Test;

import java.io.IOException;

public class AsciiTableTest {
    @Test
    public void parserTest() throws IOException {
        AsciiTableParser parser = new AsciiTableParser().load("ascii_table.txt").parse();
        System.out.println("tableName:" + parser.getTableName());
        for (ColumnDataStructure columnDataStructure : parser.getSchema()) {
            System.out.print(columnDataStructure.name + "(" + columnDataStructure.type.getSimpleName() + ")\t");
        }
    }

    @Test
    public void jdbcUtilTest() {
        JdbcUtil util = new JdbcUtil("ascii_table.txt");
        util.dropTableIfExists();
        util.createTable();
        util.insertAll();
        util.dropTableIfExists();
    }
}
