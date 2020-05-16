package org.xjrga.potatosql.generator;

public class MethodProcedureSelectAllPrint implements Code {

    private final Table table;
    private final JavaStuff javaStuff;

    public MethodProcedureSelectAllPrint(Table table) {

        this.table = table;
        this.javaStuff = new JavaStuff(table);
    }


    @Override
    public String getCode() {
        String methodName1 = table.getName() + "_Select_All_Print";
        String methodName2 = table.getName() + "_Select_All";
        String rowGetAllKeys = javaStuff.getRowGetAllKeys();
        StringBuilder sqlbuild = new StringBuilder();
        String sout = javaStuff.getSout();
        String methodVariables = javaStuff.getDataObjectName();
        String methodType = javaStuff.getMethodType();
        String objectName = javaStuff.getDataObjectName();

        String method = "public void " + methodName1 + "() throws SQLException\n" +
                "    {\n" +
                "        LinkedList<" + methodType + "> list = (LinkedList) this." + methodName2 + "();\n" +
                "        Iterator it = list.listIterator();\n" +
                "        while(it.hasNext()){\n" +
                "            " + methodType + " " + objectName + " = (" + methodType + ") it.next();\n" +
                "            " + rowGetAllKeys + "\n" +
                "            " + sout + "\n" +
                "        }\n" +
                "    }";
        sqlbuild.append(method);
        return sqlbuild.toString();
    }

}