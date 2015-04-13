PRAGMA foreign_keys = ON;
PRAGMA auto_vacuum  = FULL;
CREATE TABLE WAREHOUSE (WHSE VARCHAR2(100),NAME VARCHAR2(240) PRIMARY KEY, LINE1 VARCHAR2(240), LINE2 VARCHAR2(240),LINE3 VARCHAR2(240),LINE4 VARCHAR2(240),CITY VARCHAR2(30),PROVINCE VARCHAR2(30),STATE VARCHAR2(120),ZIP VARCHAR2(30),COUNTRY VARCHAR2(60),LOCATORCONTROL VARCHAR2(30),ISWMS VARCHAR2(1),DIRECTSHIP VARCHAR2(250),ATTRIBUTE1 VARCHAR2(150),ATTRIBUTE2 VARCHAR2(150));
INSERT INTO WAREHOUSE (WHSE,NAME) VALUES ("From SQLite Table","1");
CREATE TABLE CATEGORYSET(CATGSETNAME VARCHAR2(240),STRUCTUREID VARCHAR2(240),DESCRIPTION VARCHAR2(240),DEFCATGCODE VARCHAR2(240),CONTROLLEVEL VARCHAR2(240),MULTICATGASSIGN VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO CATEGORYSET(CATGSETNAME,STRUCTUREID) VALUES('TEST CATEGORY SET','100');
CREATE TABLE SUBINVENTORY(WHSE VARCHAR2(240),SUBINV VARCHAR2(240),DESCRIPTION VARCHAR2(240),LOCATORCONTROL VARCHAR2(240),LPNCONTROL VARCHAR2(240),DEFLOCATOR VARCHAR2(240),DEFCOSTGRP VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO SUBINVENTORY(WHSE,SUBINV) VALUES('TEST SUB INVENTORY','101');
CREATE TABLE LOCATOR(WHSE VARCHAR2(240),SUBINV VARCHAR2(240),LOCATOR VARCHAR2(240),ALIAS VARCHAR2(240),DESCRIPTION VARCHAR2(240),LOCATORTYPE VARCHAR2(240),XDIM VARCHAR2(10),YDIM VARCHAR2(10),ZDIM VARCHAR2(10),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));INSERT INTO LOCATOR(SUBINV,LOCATOR) VALUES('TEST LOCATOR','102');
CREATE TABLE CATEGORYCODE(CATGCODE VARCHAR2(240),STRUCTUREID VARCHAR2(240),DESCRIPTION VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO CATEGORYCODE(CATGCODE,STRUCTUREID) VALUES('TEST CATEGORY CODE','103');
CREATE TABLE UOM(UOMCODE VARCHAR2(240),UOM VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO UOM(UOMCODE,UOM) VALUES('TEST UOM','104');
CREATE TABLE COSTGROUP (WHSE VARCHAR2(240),COSTGROUP VARCHAR2(240),DESCRIPTION VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO COSTGROUP(WHSE,COSTGROUP) VALUES('TEST COST GROUP','105');
CREATE TABLE TRANSACTIONTYPE(TRANSACTIONTYPE VARCHAR2(240),DESCRIPTION VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO TRANSACTIONTYPE(TRANSACTIONTYPE,DESCRIPTION) VALUES('TEST TRANSACTION TYPE','106');
CREATE TABLE TRANSACTIONREASON(REASONNAME VARCHAR2(240),DESCRIPTION VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO TRANSACTIONREASON(REASONNAME,DESCRIPTION) VALUES('TEST TRANSACTION REASON','107');
CREATE TABLE CARRIER (WHSE VARCHAR2(240),CARRIERNAME VARCHAR2(240),FREIGHTCODE VARCHAR2(240),SCAC VARCHAR2(240),SHIPMETHOD VARCHAR2(240),DESCRIPTION VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO CARRIER(WHSE,CARRIERNAME) VALUES('TEST CARRIER','108');
CREATE TABLE ACCOUNTALIAS (WHSE VARCHAR2(240),ACCOUNTALIAS VARCHAR2(240),DESCRIPTION VARCHAR2(240),ACCOUNTSEGMENT VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO ACCOUNTALIAS(ACCOUNTALIAS,DESCRIPTION) VALUES('TEST ACCOUNT ALIAS','109');
CREATE TABLE PICKRULE (WHSE VARCHAR2(240),RULENAME VARCHAR2(240),DOCSETNAME VARCHAR2(240),RELEASESEQNAME VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO PICKRULE(WHSE,RULENAME) VALUES('TEST PICK RULE','110');
CREATE TABLE COUNTTYPE (WHSE VARCHAR2(240),COUNTTYPE VARCHAR2(240),COUNTNAME VARCHAR2(240),DESCRIPTION VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO COUNTTYPE (WHSE,COUNTTYPE) VALUES('TEST COUNT TYPE','111');
CREATE TABLE PHYSICALCOUNT (COUNTNAME VARCHAR2(240),TAGNUMBER VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO PHYSICALCOUNT (COUNTNAME,TAGNUMBER) VALUES('TEST PHYSICAL COUNT','111');
CREATE TABLE KANBANCARD (WHSE VARCHAR2(240),KANBANCARDNUM VARCHAR2(240),CARDSTATUS VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO KANBANCARD (WHSE,KANBANCARDNUM) VALUES('TEST KANBAN CARD','112');
CREATE TABLE DEPARTMENT (ORGCODE VARCHAR2(10),DEPTID VARCHAR2(10) PRIMARY KEY, DEPTDESC VARCHAR2(240), OPENJOB VARCHAR2(10),DEPTNAME VARCHAR2(100));

CREATE TABLE  RESOURCE (WHSE VARCHAR2(240),RESOURCECODE VARCHAR2(240),DEPTCODE VARCHAR2(240),DESCRIPTION VARCHAR2(240),RESOURCETYPE VARCHAR2(240),UOM VARCHAR2(240),CHARGETYPE VARCHAR2(240),BASIS VARCHAR2(240),INSTANCE VARCHAR2(250),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO RESOURCE (WHSE,RESOURCECODE) VALUES('TEST RESOURCE','114');
CREATE TABLE SYNCPREFERENCES(LOVID VARCHAR2(50) PRIMARY KEY,LOVNAME VARCHAR2(50),LOVDESCRIPTION VARCHAR2(200),SYNCCOUNT VARCHAR2(50),LASTSYNCDATETIME VARCHAR2(100),LOVCLASSNAME VARCHAR2(50),LOVCOLLECTVAR VARCHAR2(50));
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('1','Warehouse','Warehouse Description','0','2014-12-01 14:00:00','WarehouseDC','s_warehouse');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('2','Subinventory','Subinventory Description','0','2014-12-01 14:10:00','SubinventoryDC','s_subInventories');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('3','Locator','Locator Description','0','2014-12-02 14:10:00','LocatorDC','s_locator');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('4','UOM','UOM Description','0','2014-12-02 14:15:00','UOMDC','s_uom');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('5','Category Set Name','Category Set Name Description','0','2014-12-02 14:16:00','CategorySetDC','s_categorySets');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('6','Category Code','Category Code Description','0','2014-12-02 14:17:00','CategoryCodeDC','s_categorycode');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('7','Cost Groups','Cost Groups Description','0','2014-12-03 14:23:00','CostGroupDC','s_costGroups');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('8','Account Alias','Account Alias Description','0','2014-12-03 14:25:00','AccountAliasDC','s_accountAlias');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('9','Transaction Type','Transaction Type Description','0','2014-12-03 14:27:00','TransactionTypeDC','s_transactionType');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('10','Transaction Reason','Transaction Reason Description','0','2014-12-03 14:30:00','TransactionReasonDC','s_transactionReason');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('11','Resources',' ResourcesDescription','0','2014-12-04 14:40:00','ResourceDC','s_resources');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('12','Carrier','Carrier Description','0','2014-12-04 14:42:00','CarrierDC','s_carrier');
INSERT INTO SYNCPREFERENCES(LOVID,LOVNAME,LOVDESCRIPTION,SYNCCOUNT,LASTSYNCDATETIME,LOVCLASSNAME,LOVCOLLECTVAR) VALUES('13','Picking Rule','Picking Rule Description','0','2014-12-04 14:43:00','PickRuleDC','s_pickrule');
CREATE TABLE CONCURRENTPROGRAM(CONCPROGID INTEGER,PROGRAMSHORTNAME VARCHAR2(255),APPLICATIONSHORTNAME VARCHAR2(255),PROGRAMNAME VARCHAR2(255),ISNEWENTITY VARCHAR2(10),ROWOPERATION VARCHAR2(50));
INSERT INTO CONCURRENTPROGRAM(CONCPROGID,PROGRAMSHORTNAME,APPLICATIONSHORTNAME,PROGRAMNAME,ISNEWENTITY,ROWOPERATION) VALUES(1,'AHC','ONT','Audit History Consolidator','Y','C');
INSERT INTO CONCURRENTPROGRAM(CONCPROGID,PROGRAMSHORTNAME,APPLICATIONSHORTNAME,PROGRAMNAME,ISNEWENTITY,ROWOPERATION) VALUES(2,'BP','ONT','Batch Pricing','Y','C');
INSERT INTO CONCURRENTPROGRAM(CONCPROGID,PROGRAMSHORTNAME,APPLICATIONSHORTNAME,PROGRAMNAME,ISNEWENTITY,ROWOPERATION) VALUES(3,'CCP','ONT','Credit Check Processor','Y','C');
CREATE TABLE SUBINVENTORYTXN(TRXNID INTEGER PRIMARY KEY AUTOINCREMENT, ISNEWENTITY VARCHAR2(1), ROWOPERATION VARCHAR2(20), FROMINVENTORY VARCHAR2(240), TOINVENTORY VARCHAR2(240), ITEMNUMBER VARCHAR2(240), ITEMNAME VARCHAR2(240), QUANTITY VARCHAR2(240), TXNTIME VARCHAR2(240), COMPLETEFLAG VARCHAR2(1), LPN VARCHAR2(240), FROMLOC VARCHAR2(240), TOLOC VARCHAR2(240), STATUS VARCHAR2(240),TXNUOM VARCHAR2(240), SERIALCONTROL VARCHAR2(240), LOTCONTROL VARCHAR2(240));
CREATE TABLE SERIAL(SERIALID INTEGER,TRXNID INTEGER,FROMSERIAL VARCHAR2(30),TOSERIAL VARCHAR2(30),SERIALQTY INTEGER, TRXTYPE VARCHAR2(30),PRIMARY KEY(SERIALID,TRXTYPE));
CREATE TABLE LOT(LOTID INTEGER,TRXNID INTEGER,LOTNO VARCHAR2(30),LOTQTY INTEGER, TRXTYPE VARCHAR2(30),PRIMARY KEY(LOTID,TRXTYPE));
CREATE TABLE MISCTXN(TRXNID INTEGER PRIMARY KEY AUTOINCREMENT,  FROMINVENTORY VARCHAR2(240), LOCATOR VARCHAR2(240), ITEMNUMBER VARCHAR2(240), ITEMNAME VARCHAR2(240), QUANTITY VARCHAR2(240), TXNTIME VARCHAR2(240), COMPLETEFLAG VARCHAR2(1), TRXTYPE VARCHAR2(240), ACCOUNTALIAS VARCHAR2(240), STATUS VARCHAR2(240), TXNUOM VARCHAR2(240), SERIALCONTROL VARCHAR2(240), LOTCONTROL VARCHAR2(240));
CREATE TABLE LPNTXN(TRXNID INTEGER PRIMARY KEY AUTOINCREMENT,  SUBINVENTORY VARCHAR2(240), LOCATOR VARCHAR2(240), LPNFROM VARCHAR2(240), LPNTO VARCHAR2(240), TRXTIME VARCHAR2(240), ORGCODE VARCHAR2(1), TRXTYPE VARCHAR2(240));
CREATE TABLE ITEMTXN(ITEMID INTEGER,TRXNID INTEGER, ITEMNUMBER VARCHAR2(240),ITEMNAME VARCHAR2(240), UOM VARCHAR2(240),QUANTITY INTEGER, TRXTYPE VARCHAR2(240), SERIALCONTROL VARCHAR2(240), LOTCONTROL VARCHAR2(240),PRIMARY KEY(ITEMID,TRXTYPE));

CREATE TABLE JOBOPERATION(JOBNUMBER VARCHAR2(10), JOBOPS VARCHAR2(50), JOBDESC VARCHAR2(50),JOBSTATUS VARCHAR2(10), READYSTATUS VARCHAR2(10), ASSEMBLY VARCHAR2(30), ASSEMBLYDESC VARCHAR2(30), SCHSTARTDATE VARCHAR2(30), NEXTOPSEQ VARCHAR2(10), COMPSUBINV VARCHAR2(20), COMPLOCATOR VARCHAR2(20), ASSEMBLYUOM VARCHAR2(10), LASTDEPT VARCHAR2(20), LASTOPSEQ VARCHAR2(10), NEXTDEPT VARCHAR2(20), OPSEQ VARCHAR2(10), PRIMARY KEY(JOBNUMBER));
CREATE TABLE LINES(ROWLINEIDX INTEGER,LINES VARCHAR2(100),SUBINV VARCHAR2(100),LOCATOR VARCHAR2(100),QUANTITY VARCHAR2(100),UOM VARCHAR2(100),LPN VARCHAR2(100),ISNEWENTITY VARCHAR2(100),RECEIVETXNID INTEGER,DOCREFLINE VARCHAR2(10),SHIPMENTLINE VARCHAR2(10),SERIALCONTROL VARCHAR2(10),LOTCONTROL VARCHAR2(10));
CREATE TABLE SHIPMENT(RECEIVETXNID INTEGER,ORGCODE VARCHAR2(100),DOCTYPE VARCHAR2(100),DOCREF VARCHAR2(100),VENDOR VARCHAR2(100),CUSTOMER VARCHAR2(100),CARRIER VARCHAR2(100),PACKINGSLIP VARCHAR2(100),BOL VARCHAR2(100),WAYAIRBILL VARCHAR2(100),SHIPMENTNUM VARCHAR2(100),SHIPPEDDATE VARCHAR2(100),COMMENTS VARCHAR2(100));

CREATE TABLE ITEMTYPE(TYPECODE VARCHAR2(240),MEANING VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('AOC','ATO Option Class');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('AI','ATO item');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('ATO','ATO model');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('AA','Asset Activity');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('AG','Asset Group');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('CONSULTING','Contracts - Consulting');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('EDU','Contracts - Education');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('KIT','Contracts - KIT');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('MEDIA','Contracts - Media');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('SW LIC','Contracts - Software License');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('TRAIN','Contracts - Training');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('DISCOUNT','Discount');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('DISCOUNT_MODEL','Discount Model');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('EQ','Equipment');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('FG','Finished good');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('FRT','Freight');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('I','Inventory Type');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('K','Kit');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('M','Model');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('NRI','Non-Recurring');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('NS','Non-Stocked item');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('OC','Option Class');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('OP','Outside Processing Item');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('POC','PTO Option Class');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('PTO','PTO model');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('PH','Phantom item');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('PL','Planning');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('PF','Product Family');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('PROMOTION','Promotion');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('P','Purchased item');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('RB','Rebuildable');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('RI','Recurring');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('REF','Reference item');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('SA','Subassembly');
INSERT INTO ITEMTYPE(TYPECODE,MEANING) VALUES('SI','Supply item');

CREATE TABLE ITEMSTATUS(STATUS VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO ITEMSTATUS(STATUS) VALUES('Active');
INSERT INTO ITEMSTATUS(STATUS) VALUES('ERESReject');
INSERT INTO ITEMSTATUS(STATUS) VALUES('Inactive');
INSERT INTO ITEMSTATUS(STATUS) VALUES('OPM');
INSERT INTO ITEMSTATUS(STATUS) VALUES('Obsolete');
INSERT INTO ITEMSTATUS(STATUS) VALUES('Pending');
INSERT INTO ITEMSTATUS(STATUS) VALUES('Prototype');

CREATE TABLE CATALOG(CATALOG VARCHAR2(240),ATTRIBUTE1 VARCHAR2(240),ATTRIBUTE2 VARCHAR2(240));
INSERT INTO CATALOG(CATALOG) VALUES('DCOMS Item Catalog');
INSERT INTO CATALOG(CATALOG) VALUES('Adhesives');
INSERT INTO CATALOG(CATALOG) VALUES('Adhesives-Hot Melt');
INSERT INTO CATALOG(CATALOG) VALUES('Cabinets');
INSERT INTO CATALOG(CATALOG) VALUES('Doors');
INSERT INTO CATALOG(CATALOG) VALUES('Drawers');
INSERT INTO CATALOG(CATALOG) VALUES('Cabinet Assemblies');
INSERT INTO CATALOG(CATALOG) VALUES('Shells');
INSERT INTO CATALOG(CATALOG) VALUES('Trays');
INSERT INTO CATALOG(CATALOG) VALUES('Shelves');
INSERT INTO CATALOG(CATALOG) VALUES('Frames');
INSERT INTO CATALOG(CATALOG) VALUES('Cabinet Boxes');
INSERT INTO CATALOG(CATALOG) VALUES('Compressors');
INSERT INTO CATALOG(CATALOG) VALUES('Countertops');
INSERT INTO CATALOG(CATALOG) VALUES('Packaging');
INSERT INTO CATALOG(CATALOG) VALUES('Packaging–Bags');
INSERT INTO CATALOG(CATALOG) VALUES('Packaging-Corrugated');
INSERT INTO CATALOG(CATALOG) VALUES('Pallets');
INSERT INTO CATALOG(CATALOG) VALUES('Paper');
INSERT INTO CATALOG(CATALOG) VALUES('Paper-Rolls');
INSERT INTO CATALOG(CATALOG) VALUES('Paper-Sheets');
INSERT INTO CATALOG(CATALOG) VALUES('Tubes');
INSERT INTO CATALOG(CATALOG) VALUES('Tubes-Compound');
INSERT INTO CATALOG(CATALOG) VALUES('Buildups');
INSERT INTO CATALOG(CATALOG) VALUES('Components-Other');
INSERT INTO CATALOG(CATALOG) VALUES('Ends');
INSERT INTO CATALOG(CATALOG) VALUES('Ends-Metal');
INSERT INTO CATALOG(CATALOG) VALUES('Ends-Fiber');
INSERT INTO CATALOG(CATALOG) VALUES('Ends-Paper');
INSERT INTO CATALOG(CATALOG) VALUES('Caps');
INSERT INTO CATALOG(CATALOG) VALUES('Caps-Plastic');
INSERT INTO CATALOG(CATALOG) VALUES('Metals');
INSERT INTO CATALOG(CATALOG) VALUES('Compounds');
INSERT INTO CATALOG(CATALOG) VALUES('Coatings');
INSERT INTO CATALOG(CATALOG) VALUES('Inks and Dyes');
INSERT INTO CATALOG(CATALOG) VALUES('Lubricants');
INSERT INTO CATALOG(CATALOG) VALUES('Tape');
INSERT INTO CATALOG(CATALOG) VALUES('Cans');
