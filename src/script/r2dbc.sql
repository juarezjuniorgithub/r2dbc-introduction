CREATE TABLE customers (
	id NUMBER(20,0),
	name VARCHAR2(20 BYTE),
	region VARCHAR2(20 BYTE)
);
COMMIT;

INSERT INTO CUSTOMERS (id, name, region) VALUES (1, 'Juarez', 'Dublin');
INSERT INTO CUSTOMERS (id, name, region) VALUES (2, 'David', 'Cork');
INSERT INTO CUSTOMERS (id, name, region) VALUES (3, 'Joe', 'Limerick');
INSERT INTO CUSTOMERS (id, name, region) VALUES (4, 'Karl', 'Belfast');
INSERT INTO CUSTOMERS (id, name, region) VALUES (5, 'Patrick', 'Galway');
COMMIT;

SELECT * FROM CUSTOMERS;