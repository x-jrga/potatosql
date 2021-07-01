package io.github.xjrga.potatosql.generator;

public class MethodProcedureSelectPrint implements Code {

    private Table table;
    private JavaStuff javaStuff;

    public MethodProcedureSelectPrint(Table table) {
        this.table = table;
        this.javaStuff = new JavaStuff(table);
    }

    @Override
    public String getCode() {
        String methodName1 = table.getName() + "_Select_Print";
        String methodName2 = table.getName() + "_Select";
        String methodParameters = javaStuff.getMethodParametersPrimaryKeyOnly();
        String methodVariables = javaStuff.getMethodVariablesPrimaryKeyOnly();
        String rowGetNonPrimaryKeys = javaStuff.getRowGetNonPrimaryKeys();
        StringBuilder sqlbuild = new StringBuilder();
        String sout = javaStuff.getSout();
        String method = "public void " + methodName1 + "(" + methodParameters + ") throws SQLException\n"
                + "    {\n"
                + "        List list = (LinkedList)" + methodName2 + "(" + methodVariables + ");\n"
                + "        Iterator it = list.listIterator();\n"
                + "        while(it.hasNext()){\n"
                + "            Map row = (HashMap) it.next();\n"
                + "            " + rowGetNonPrimaryKeys + "\n"
                + "            " + sout + "\n"
                + "        }\n"
                + "    }";
        sqlbuild.append(method);
        sqlbuild.append("\n");
        sqlbuild.append("\n");
        return sqlbuild.toString();
    }

}
