CALL public.DatabaseSchema_Insert('Sales_Dimensional_Schema');
CALL public.DatabaseTable_Insert(2,'DailySalesFact');
CALL public.TableKey_Insert(2,6,'DateKey','DateKey',true,1,0,0,1);
CALL public.TableKey_Insert(2,6,'ProductKey','ProductKey',true,1,0,0,2);
CALL public.TableKey_Insert(2,6,'StoreKey','StoreKey',true,1,0,0,3);
CALL public.TableKey_Insert(2,6,'QuantitySold','QuantitySold',false,2,0,0,4);
CALL public.TableKey_Insert(2,6,'DollarSalesAmount','DollarSalesAmount',false,2,0,0,5);
CALL public.DatabaseTable_Insert(2,'ProductDimension');
CALL public.TableKey_Insert(2,7,'ProductKey','ProductKey',true,1,0,0,1);
CALL public.TableKey_Insert(2,7,'ProductDescription','ProductDescription',false,3,8000,0,2);
CALL public.TableKey_Insert(2,7,'CategoryDescription','CategoryDescription',false,3,8000,0,3);
CALL public.DatabaseTable_Insert(2,'StoreDimension');
CALL public.TableKey_Insert(2,8,'StoreKey','StoreKey',true,1,0,0,1);
CALL public.TableKey_Insert(2,8,'StoreNumber','StoreNumber',false,1,0,0,2);
CALL public.TableKey_Insert(2,8,'StoreZip','StoreZip',false,1,0,0,3);
CALL public.DatabaseTable_Insert(2,'DateDimension');
CALL public.TableKey_Insert(2,9,'DateKey','DateKey',true,1,0,0,1);
CALL public.TableKey_Insert(2,9,'TheDate','TheDate',false,4,0,0,2);
CALL public.TableKey_Insert(2,9,'TheMonth','TheMonth',false,1,0,0,3);
CALL public.TableKey_Insert(2,9,'TheYear','TheYear',false,1,0,0,4);
CALL public.Relationship_Insert(2,7,6,0);
CALL public.RelationshipKeyPair_Insert(2,7,6,4,22,18);
CALL public.Relationship_Insert(2,8,6,0);
CALL public.RelationshipKeyPair_Insert(2,8,6,5,25,19);
CALL public.Relationship_Insert(2,9,6,0);
CALL public.RelationshipKeyPair_Insert(2,9,6,6,28,17);
