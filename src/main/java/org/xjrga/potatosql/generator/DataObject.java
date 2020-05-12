package org.xjrga.potatosql.generator;

public class DataObject implements Code {

    private final Table table;
    private final DataObjectStuff dataObjectStuff;
    private final StringBuilder sqlbuild;


    public DataObject(Table table, DataObjectStuff dataObjectStuff) {
        this.table = table;
        this.dataObjectStuff = dataObjectStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode() {
        if (!table.isEmpty()) {
            sqlbuild.append("public class ");
            sqlbuild.append(table.getName() + "Object");
            sqlbuild.append("\n");
            sqlbuild.append("{");
            sqlbuild.append("\n");
            sqlbuild.append("\n");
            sqlbuild.append(dataObjectStuff.getFields());
            sqlbuild.append("\n");
            sqlbuild.append("\n");
            sqlbuild.append(dataObjectStuff.getConstructor());
            sqlbuild.append("\n");
            sqlbuild.append("\n");
            sqlbuild.append(dataObjectStuff.getSetters());
            sqlbuild.append("\n");
            sqlbuild.append("\n");
            sqlbuild.append(dataObjectStuff.getGetters());
            sqlbuild.append("\n");
            sqlbuild.append("\n");
            sqlbuild.append(dataObjectStuff.getToString());
            sqlbuild.append("\n");
            sqlbuild.append("\n");
            sqlbuild.append("}");


        }
        return sqlbuild.toString();
    }

}
