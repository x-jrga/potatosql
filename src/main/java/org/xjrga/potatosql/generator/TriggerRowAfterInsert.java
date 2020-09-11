package org.xjrga.potatosql.generator;

public class TriggerRowAfterInsert implements Code {
    private final Table table;
    private final TriggerStuff triggerStuff;
    private final StringBuilder sqlbuild;

    public TriggerRowAfterInsert(Table table, TriggerStuff triggerStuff) {
        this.table = table;
        this.triggerStuff = triggerStuff;
        sqlbuild = new StringBuilder();
    }

    @Override
    public String getCode() {
        if (!table.isEmpty()) {
            sqlbuild.append("CREATE TRIGGER");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append("_RowLevelAfterInsert_Trigger");
            sqlbuild.append("\n");
            sqlbuild.append("AFTER INSERT ON");
            sqlbuild.append(" ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName());
            sqlbuild.append(" ");
            sqlbuild.append("REFERENCING NEW ROW AS newrow");
            sqlbuild.append("\n");
            sqlbuild.append("FOR EACH ROW");
            sqlbuild.append("\n");
            sqlbuild.append("BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append("INSERT INTO ");
            sqlbuild.append(table.getSchema());
            sqlbuild.append(".");
            sqlbuild.append(table.getName() + "2");
            sqlbuild.append(" VALUES (");
            sqlbuild.append(triggerStuff.getTriggerNewRowFields());
            sqlbuild.append(");");
            sqlbuild.append("\n");
            sqlbuild.append("END;");
            sqlbuild.append("\n");
        }
        return sqlbuild.toString();
    }
}
