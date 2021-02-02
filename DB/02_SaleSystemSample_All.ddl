conn SALESAMPLE/SALESAMPLE

DROP TABLE TR_SALES_DETAIL CASCADE CONSTRAINTS;
DROP TABLE TR_SALES_OUTLINE CASCADE CONSTRAINTS;
DROP TABLE MT_CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE MT_ITEM CASCADE CONSTRAINTS;
DROP TABLE MT_ITEM_GENRE CASCADE CONSTRAINTS;
DROP TABLE MT_USER CASCADE CONSTRAINTS;
DROP TABLE MT_ROLE CASCADE CONSTRAINTS;

/**********************************/
/* テーブル名: ロールマスタ */
/**********************************/
CREATE TABLE MT_ROLE(
        ROLE_CODE         VARCHAR2(2)         NOT NULL         PRIMARY KEY,
        ROLE_NAME         VARCHAR2(30)        NOT NULL,
        EXPLAIN           VARCHAR2(30)        NULL 
);

/**********************************/
/* テーブル名: ユーザマスタ */
/**********************************/
CREATE TABLE MT_USER(
        USER_CODE         VARCHAR2(8)         NOT NULL         PRIMARY KEY,
        USER_NAME         VARCHAR2(45)        NOT NULL,
        PASSWORD          VARCHAR2(100)       NOT NULL,
        ROLE_CODE         VARCHAR2(2)         NOT NULL,
        ENABLED           NUMBER(1)           NOT NULL,
  FOREIGN KEY (ROLE_CODE) REFERENCES MT_ROLE (ROLE_CODE)
);


/**********************************/
/* テーブル名: 商品区分マスタ */
/**********************************/
CREATE TABLE MT_ITEM_GENRE(
        ITEM_GENRE_CODE   VARCHAR2(8)         NOT NULL         PRIMARY KEY,
        ITEM_GENRE_NAME   VARCHAR2(30)        NOT NULL
);

/**********************************/
/* テーブル名: 商品マスタ */
/**********************************/
CREATE TABLE MT_ITEM(
        ITEM_CODE         VARCHAR2(8)         NOT NULL         PRIMARY KEY,
        ITEM_NAME         VARCHAR2(60)       NOT NULL,
        ITEM_GENRE_CODE   VARCHAR2(8)         NOT NULL,
        SPEC              VARCHAR2(30)        NULL ,
        PRICE             NUMBER(7)           NOT NULL,
  FOREIGN KEY (ITEM_GENRE_CODE) REFERENCES MT_ITEM_GENRE (ITEM_GENRE_CODE)
);


/**********************************/
/* テーブル名: 顧客マスタ */
/**********************************/
CREATE TABLE MT_CUSTOMER(
        CUSTOMER_CODE     VARCHAR2(8)         NOT NULL         PRIMARY KEY,
        CUSTOMER_NAME     VARCHAR2(60)        NOT NULL
);


/**********************************/
/* テーブル名: 売上概要 */
/**********************************/
CREATE TABLE TR_SALES_OUTLINE(
        SALES_ID          NUMBER(8) DEFAULT 1 NOT NULL         PRIMARY KEY,
        CUSTOMER_CODE     VARCHAR2(8)         NOT NULL,
        USER_CODE         VARCHAR2(8)         NOT NULL,
        SALE_DATE         DATE                NOT NULL,
  FOREIGN KEY (USER_CODE) REFERENCES MT_USER (USER_CODE),
  FOREIGN KEY (CUSTOMER_CODE) REFERENCES MT_CUSTOMER (CUSTOMER_CODE)
);

DROP SEQUENCE TR_SALES_OUTLINE_SALES_ID_SEQ;

CREATE SEQUENCE TR_SALES_OUTLINE_SALES_ID_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER TR_SALES_OUTLINE_SALES_ID_TRG
BEFORE INSERT ON TR_SALES_OUTLINE
FOR EACH ROW
BEGIN
IF :NEW.SALES_ID IS NULL THEN
  SELECT TR_SALES_OUTLINE_SALES_ID_SEQ.NEXTVAL INTO :NEW.SALES_ID FROM DUAL;
END IF;
END;
/


/**********************************/
/* テーブル名: 売上明細 */
/**********************************/
CREATE TABLE TR_SALES_DETAIL(
        DETAIL_ID         NUMBER(10) DEFAULT 1 NOT NULL         PRIMARY KEY,
        SALES_ID          NUMBER(8)           NOT NULL,
        ITEM_CODE         VARCHAR2(8)         NOT NULL,
        QUANTITY          NUMBER(4)           NOT NULL,
        SALES_PRICE       NUMBER(7)           NOT NULL,
  FOREIGN KEY (SALES_ID) REFERENCES TR_SALES_OUTLINE (SALES_ID),
  FOREIGN KEY (ITEM_CODE) REFERENCES MT_ITEM (ITEM_CODE)
);

DROP SEQUENCE TR_SALES_DETAIL_DETAIL_ID_SEQ;

CREATE SEQUENCE TR_SALES_DETAIL_DETAIL_ID_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

CREATE TRIGGER TR_SALES_DETAIL_DETAIL_ID_TRG
BEFORE INSERT ON TR_SALES_DETAIL
FOR EACH ROW
BEGIN
IF :NEW.DETAIL_ID IS NULL THEN
  SELECT TR_SALES_DETAIL_DETAIL_ID_SEQ.NEXTVAL INTO :NEW.DETAIL_ID FROM DUAL;
END IF;
END;
/

