package per.gh.study.mysql.asciitable;

import per.gh.study.mysql.asciitable.JdbcUtil;

import java.io.IOException;

public class AsciiTableTest {
    public static void main(String[] args) throws IOException {
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.dropTableIfExists();
        jdbcUtil.createTable();
        jdbcUtil.insertAll();
    }
}
