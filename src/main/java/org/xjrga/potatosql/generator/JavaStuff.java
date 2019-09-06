package org.xjrga.potatosql.generator;

import java.util.Iterator;

public class JavaStuff
{
    private Table table;

    public JavaStuff(Table table)
    {
        this.table = table;
    }

    public String getMethodParametersAllMinusIdent()
    {
        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (!column.isIdentity())
            {
                sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
                sb.append(" ");
                sb.append(column.getName());
                sb.append(",");
                sb.append(" ");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public String getMethodParametersAll()
    {
        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
            sb.append(" ");
            sb.append(column.getName());
            sb.append(",");
            sb.append(" ");
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public String getMethodParametersPrimaryKeyOnly()
    {
        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (column.isPrimaryKey())
            {
                sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
                sb.append(" ");
                sb.append(column.getName());
                sb.append(",");
                sb.append(" ");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    public String getRowGetAllKeys()
    {

        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
            sb.append(" ");
            sb.append(column.getName());
            sb.append(" = ");
            sb.append("(");
            sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
            sb.append(")");
            sb.append("row.get(");
            sb.append("\"");
            sb.append(column.getName().toUpperCase());
            sb.append("\"");
            sb.append(");\n");
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public String getRowGetNonPrimaryKeys()
    {

        HsqldbTypeToJavaType hsqldbTypeToJavaType = new HsqldbTypeToJavaType();
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            if (!column.isPrimaryKey())
            {
                sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
                sb.append(" ");
                sb.append(column.getName());
                sb.append(" = ");
                sb.append("(");
                sb.append(hsqldbTypeToJavaType.getJavaType(column.getTypeName()));
                sb.append(")");
                sb.append("row.get(");
                sb.append("\"");
                sb.append(column.getName().toUpperCase());
                sb.append("\"");
                sb.append(");\n");
            }
        }

        if (sb.length() > 0)
        {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }

    public String getSout()
    {

        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        sb.append("System.out.println(");

        while (it.hasNext())
        {
            Column column = (Column) it.next();
            sb.append(column.getName());
            sb.append("+");
            sb.append("\",\"");
            sb.append("+");
        }

        sb.setLength(sb.length() - 5);
        sb.append(");");

        return sb.toString();
    }

    public String getMethodVariablesPrimaryKeyOnly()
    {
        StringBuilder sb = new StringBuilder();
        Iterator it = table.getIterator();

        while (it.hasNext())
        {
            Column column = (Column) it.next();

            if (column.isPrimaryKey())
            {
                sb.append(column.getName());
                sb.append(",");
            }
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    public String getResultSetObjectString()
    {
        StringBuilder sb = new StringBuilder();
        int count = table.getCountKey();
        for (int i = 0; i < count; i++)
        {
            Column column = table.getColumn(i);
            sb.append("row.put(");
            sb.append(getParam(i + 1, column));
            sb.append(");");
            sb.append("\n");
        }
        return sb.toString();
    }

    private String getParam(int count, Column column)
    {
        StringBuilder sb = new StringBuilder();

        /*
        *   call keytype_insert(0,'IDENTITY',false,false);
            call keytype_insert(1,'INTEGER',false,false);
            call keytype_insert(2,'DOUBLE',false,false);
            call keytype_insert(3,'VARCHAR',true,false);
            call keytype_insert(4,'DATE',false,false);
            call keytype_insert(5,'TIME',false,false);
            call keytype_insert(6,'TIMESTAMP',false,false);
            call keytype_insert(7,'DECIMAL',true,true);
            call keytype_insert(8,'LONGVARCHAR',false,false);
            call keytype_insert(9,'BLOB',false,false);
            call keytype_insert(10,'CLOB',false,false);
        * */
        switch (column.getTypeName())
        {
            case "INTEGER":
            case "IDENTITY":
                sb.append("rs.getInt(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "DOUBLE":
                sb.append("rs.getDouble(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "VARCHAR":
            case "LONGVARCHAR":
                sb.append("rs.getString(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "DATE":
                sb.append("rs.getDate(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "TIME":
                sb.append("rs.getTime(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "TIMESTAMP":
                sb.append("rs.getTimestamp(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "DECIMAL":
                sb.append("rs.getBigDecimal(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "BLOB":
                sb.append("rs.getBlob(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            case "CLOB":
                sb.append("rs.getClob(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
            default:
                sb.append("rs.getObject(");
                sb.append(count);
                sb.append(",");
                sb.append(column.getName());
                sb.append(")");
                break;
        }

        return sb.toString();
    }
}
