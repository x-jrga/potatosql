package org.xjrga.potatosql.generator;

public class MethodProcedureSelectAll implements Code
{

    private Table table;
    private ProcedureStuff procedureStuff;
    private JavaStuff javaStuff;

    public MethodProcedureSelectAll(Table table)
    {

        this.table = table;
        this.procedureStuff = new ProcedureStuff(table);
        this.javaStuff = new JavaStuff(table);
    }


    @Override
    public String getCode()
    {
        String procedureKind = "Select_All";
        String methodName = table.getName() + "_" + procedureKind;
        String methodParameters = "";
        String sql = procedureStuff.getProcedureSqlStringNone(procedureKind);
        String setParameters = "";
        StringBuilder sqlbuild = new StringBuilder();
        String rowput = javaStuff.getResultSetObjectString();
        String method = "public List<Map<String, Object>> " + methodName + "() throws SQLException\n" +
                "    {\n" +
                "        LinkedList<Map<String, Object>> list = new LinkedList<>();\n" +
                "        CallableStatement proc;\n" +
                "        proc = connection.prepareCall(" + sql + ");\n" +
                "        ResultSet rs = proc.executeQuery();\n" +
                "        while (rs.next())\n" +
                "        {\n" +
                "            Map<String, Object> row = new HashMap<>();\n" +
                "                " + rowput + "\n" +
                "            list.add(row);\n" +
                "        }\n" +
                "        proc.close();\n" +
                "        return list;\n" +
                "    }\n";
        sqlbuild.append(method);
        return sqlbuild.toString();
    }
}