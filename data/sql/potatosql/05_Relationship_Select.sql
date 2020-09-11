CREATE PROCEDURE PUBLIC.RELATIONSHIP_SELECT (IN V_SCHEMAID INTEGER,IN V_TABLEID INTEGER)
--
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
--
DECLARE RESULT CURSOR
FOR
SELECT SCHEMAID,
       PARENT_TABLEID,
       B.NAME AS PARENT,
       CHILD_TABLEID,
       C.NAME AS CHILD,
       RELATIONSHIPTYPEID,
       RELATIONSHIPID
FROM PUBLIC.RELATIONSHIP A,
     PUBLIC.DATABASETABLE B,
     PUBLIC.DATABASETABLE C
WHERE SCHEMAID = V_SCHEMAID
AND   A.PARENT_TABLEID = V_TABLEID
AND   A.SCHEMAID = B.SCHEMAID
AND   A.SCHEMAID = C.SCHEMAID
AND   A.PARENT_TABLEID = B.TABLEID
AND   A.CHILD_TABLEID = C.TABLEID
ORDER BY PARENT,
         CHILD;
         --
OPEN RESULT;
--
END;
/
