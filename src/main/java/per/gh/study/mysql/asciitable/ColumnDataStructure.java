package per.gh.study.mysql.asciitable;

import java.util.function.Function;

public class ColumnDataStructure {
    String name;
    Class type;
    Function<String, Object> converter;
}
