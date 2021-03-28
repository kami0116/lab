package per.gh.study.mysql.asciitable;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcUtil {
    List<ColumnDataStructure> schema;
    List<List<String>> data;
    String tableName;
    String url = "jdbc:mysql://tc/test";
    String user = "root";
    String password = "E9B69FDF118D1F46E3E358CF039B921F";

    public JdbcUtil(String fileName) {
        try {
            init(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(String fileName) throws IOException {
        AsciiTableParser parser = new AsciiTableParser().load(fileName).parse();
        this.schema = parser.getSchema();
        this.tableName = parser.getTableName();
        this.data = parser.getData();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropTableIfExists() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement("drop table if exists " + tableName);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void createTable() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            String sql = generateCreateSql();
            System.out.println(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

    String generateCreateSql() {
        StringBuilder sql = new StringBuilder("create table ").append(tableName).append("(");
        for (ColumnDataStructure cds : schema) {
            String mysqlDataType;
            if (Integer.class.equals(cds.type) || Double.class.equals(cds.type)) {
                mysqlDataType = "int";
            } else {
                mysqlDataType = "varchar(255)";
            }
            sql.append(String.format("%s %s,", cds.name, mysqlDataType));
        }
        sql.delete(sql.length() - 1, sql.length());
        sql.append(")");
        return sql.toString();
    }

    public void insertAll() {
        String sql = generateInsertSql();
        System.out.println(sql);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            for (List<String> column : data) {
                int i = 1;
//                Iterator<String> cit = column.iterator();
//                Iterator<ColumnDataStructure> sit = schema.iterator();
//                while (cit.hasNext()) {
//                    String cell = cit.next();
//                    ColumnDataStructure cds = sit.next();
//                    if (cds.type == Integer.class) {
//                        ps.setInt(i++, (Integer) cds.converter.apply(cell));
//                    } else if (cds.type == Double.class) {
//                        ps.setDouble(i++, (Double) cds.converter.apply(cell));
//                    } else {
//                        ps.setString(i++, cell);
//                    }
//                }
                for (String s : column) {
                    ps.setString(i++, s);
                }
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    String generateInsertSql() {
        StringBuilder sql = new StringBuilder("insert into " + tableName + "(");
        int count = 0;
        for (ColumnDataStructure cds : schema) {
            sql.append(cds.name).append(",");
            count++;
        }
        sql.delete(sql.length() - 1, sql.length());
        sql.append(") values(");
        for (int i = 0; i < count; i++) {
            sql.append("?,");
        }
        sql.delete(sql.length() - 1, sql.length());
        sql.append(")");
        return sql.toString();
    }


}
