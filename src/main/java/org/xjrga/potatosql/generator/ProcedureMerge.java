package org.xjrga.potatosql.generator;

public class ProcedureMerge implements Code
{

    private Table table;
    private Code statementMerge;
    private SqlStuff sqlStuff;
    private StringBuilder sqlbuild;


    public ProcedureMerge(Table table, SqlStuff sqlStuff)
    {

        this.table = table;
        statementMerge = new StatementMerge(table, sqlStuff);
        this.sqlStuff = sqlStuff;
        sqlbuild = new StringBuilder();
    }


    @Override
    public String getCode()
    {

        if (table.containsNonPrimaryKeys())
        {
            sqlbuild.append("CREATE PROCEDURE");
            sqlbuild.append(" ");
            sqlbuild.append(table.getName());
            sqlbuild.append("_Merge (");
            sqlbuild.append("\n");
            sqlbuild.append(sqlStuff.getSqlProcedureVariablesAll());
            sqlbuild.append("\n");
            sqlbuild.append(")");
            sqlbuild.append("\n");
            sqlbuild.append("MODIFIES SQL DATA BEGIN ATOMIC");
            sqlbuild.append("\n");
            sqlbuild.append(statementMerge.getCode());
            sqlbuild.append("END;");
            sqlbuild.append("\n");
            sqlbuild.append("/");
        }
        return sqlbuild.toString();
    }

}
