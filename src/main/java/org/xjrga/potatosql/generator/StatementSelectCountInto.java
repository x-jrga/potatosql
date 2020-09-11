package org.xjrga.potatosql.generator;

public class StatementSelectCountInto implements Code {
    private final Table table;
    private final StringBuilder sqlbuild;

    public StatementSelectCountInto(Table table) {
        this.table = table;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {
        if (!table.isEmpty()) {
            sqlbuild.append("SELECT COUNT(*)");
            sqlbuild.append(" ");
            sqlbuild.append("INTO v_count");
            sqlbuild.append("\n");
            sqlbuild.append("FROM");
            sqlbuild.append("\n");
            sqlbuild.append(table.getName());
            sqlbuild.append(";");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
