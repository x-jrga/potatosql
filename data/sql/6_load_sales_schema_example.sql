CALL public.DatabaseSchema_Insert('Sales_Schema');
CALL public.DatabaseTable_Insert(1,'Product');
CALL public.DatabaseTable_Insert(1,'Customer');
CALL public.TableKey_Insert(1,3,'ProductId','ProductId',true,1,0,0,1);
CALL public.TableKey_Insert(1,3,'ProductName','ProductName',false,1,0,0,2);
CALL public.TableKey_Insert(1,4,'CustomerId','CustomerId',true,1,0,0,1);
CALL public.TableKey_Insert(1,4,'CustomeName','CustomeName',false,3,8000,0,2);
CALL public.DatabaseTable_Insert(1,'ProductSales');
CALL public.TableKey_Insert(1,5,'CustomerId','CustomerId',true,1,0,0,1);
CALL public.TableKey_Insert(1,5,'ProductId','ProductId',true,1,0,0,2);
CALL public.TableKey_Insert(1,5,'Quantity','Quantity',false,2,0,0,3);
CALL public.TableKey_Insert(1,5,'UnitPrice','UnitPrice',false,2,0,0,4);
CALL public.TableKey_Insert(1,5,'SalesDate','SalesDate',false,4,0,0,5);
CALL public.Relationship_Insert(1,3,5,0);
CALL public.RelationshipKeyPair_Insert(1,3,5,2,8,13);
CALL public.Relationship_Insert(1,4,5,0);
CALL public.RelationshipKeyPair_Insert(1,4,5,3,10,12)
