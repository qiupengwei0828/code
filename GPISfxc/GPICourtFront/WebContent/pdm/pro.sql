--1创建表
declare
  num    number(1);
  conum  number(1);
begin
--判断表是否存在，如果存在则删除
select count(1) into num from all_tables  where TABLE_NAME = 'SFCK_QUERY_INFO' ; 
if   num=1   then 
    execute immediate 'drop table SFCK_QUERY_INFO'; 
end   if; 
select count(*) into num from all_tables where TABLE_NAME = 'SFCK_CHECK_WSINFO' ; 
if   num=1   then 
    execute immediate 'drop table SFCK_CHECK_WSINFO'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'SFCK_RESULT_ZHXX_VIEW' ; 
if   num=1   then 
execute immediate 'drop table SFCK_RESULT_ZHXX_VIEW'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'SFCK_RESULT_JYXX_VIEW' ; 
if   num=1   then 
execute immediate 'drop table SFCK_RESULT_JYXX_VIEW'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'SFCK_RESULT_BXGXX_VIEW' ; 
if   num=1   then 
execute immediate 'drop table SFCK_RESULT_BXGXX_VIEW'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'SFCK_RESULT_POSXX_VIEW' ; 
if   num=1   then 
execute immediate 'drop table SFCK_RESULT_POSXX_VIEW'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'SFCK_RESULT_DLRZXX_VIEW' ; 
if   num=1   then 
execute immediate 'drop table SFCK_RESULT_DLRZXX_VIEW'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'SFCK_RESULT_JJXX_VIEW' ; 
if   num=1   then 
execute immediate 'drop table SFCK_RESULT_JJXX_VIEW'; 
end   if;
end;   
/

/*==============================================================*/
/* Table: SFCK_QUERY_INFO      协查请求内容                                 */
/*==============================================================*/
create table SFCK_QUERY_INFO 
(
   QUERY_ID             VARCHAR2(20)         not null,
   BANK_TYPE            VARCHAR2(2),
   QUERY_QU             VARCHAR2(1),
   QUERY_NAME           VARCHAR2(100),
   NATION_AREA          VARCHAR2(15),
   CERT_TYPE            VARCHAR2(15),
   CERT_NO              VARCHAR2(30),
   ORG_NO               VARCHAR2(30),
   SSUCTF_AHR_LO        VARCHAR2(50),
   APPLY_NAME           VARCHAR2(50),
   PROCURATOR_NAME      VARCHAR2(20),
   CASE_ID              VARCHAR2(50),
   START_DT             VARCHAR2(19),
   END_DT               VARCHAR2(19),
   LATE_BACK_DT         VARCHAR2(19),
   QUERY_STATE          VARCHAR2(3),
   REQUEST_DT           VARCHAR2(19),
   REQUEST_TIME         VARCHAR2(9),
   CHECK_IDEA           VARCHAR2(1000),
   UPDATE_USER          VARCHAR2(30),
   LOAD_DT              VARCHAR2(10),
   RET_MSG              VARCHAR2(100),
   WS_STATE             VARCHAR2(3),
   WS_RET_MSG           VARCHAR2(100),
   constraint PK_SFCK_QUERY_INFO primary key (QUERY_ID)
);
/


/*==============================================================*/
/* Table: SFCK_CHECK_WSINFO         协查请求文件                            */
/*==============================================================*/
create table SFCK_CHECK_WSINFO 
(
   KEYID                NUMBER(30)           not null,
   QUERY_TIME           VARCHAR2(20),
   QUERY_STATE          VARCHAR2(2),
   XH                   VARCHAR2(10),
   WJMC                 VARCHAR2(50),
   WJLX                 VARCHAR2(30),
   CHECK_FILE           VARCHAR2(50),
   QUERY_ID             VARCHAR2(20)         not null,
   constraint PK_SFCK_CHECK_WSINFO primary key (KEYID)
);
/

/*==============================================================*/
/* Table: SFCK_RESULT_ZHXX_VIEW   具体账号信息                              */
/*==============================================================*/
create table SFCK_RESULT_ZHXX_VIEW 
(
   KEYID                NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   ZHXH                 VARCHAR2(8),
   KHZH                 VARCHAR2(30),
   YE                   VARCHAR2(20),
   FKSJ                 VARCHAR2(19),
   ZHLB                 VARCHAR2(20),
   ZHZT                 VARCHAR2(20),
   KHWD                 VARCHAR2(100),
   BZ                   VARCHAR2(20),
   TXDZ                 VARCHAR2(100),
   YZBM                 VARCHAR2(20),
   LXDH                 VARCHAR2(20),
   BEIZ                 VARCHAR2(100),
   SFTZ                 VARCHAR2(1),
   KHRQ                 VARCHAR2(20),
   XHRQ                 VARCHAR2(20),
   constraint PK_SFCK_RESULT_ZHXX_VIEW primary key (KEYID)
);
/

/*==============================================================*/
/* Table: SFCK_RESULT_JYXX_VIEW               交易信息表                  */
/*==============================================================*/
create table SFCK_RESULT_JYXX_VIEW 
(
   KEYID                NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   KHZH                 VARCHAR2(30),
   JYLSH                VARCHAR2(50),
   JYLX                 VARCHAR2(50),
   JYFS                 VARCHAR2(50),
   JYWD                 VARCHAR2(50),
   JJBH                 VARCHAR2(50),
   JYSJ                 VARCHAR2(19),
   DFJE                 VARCHAR2(20),
   JFJE                 VARCHAR2(20),
   BZ                   VARCHAR2(20),
   SFFMC                VARCHAR2(100),
   SFFZH                VARCHAR2(30),
   SFFLX                VARCHAR2(20),
   SFFDW                VARCHAR2(50),
   YE                   VARCHAR2(20),
   ZYXX                 VARCHAR2(100),
   FKSJ                 VARCHAR2(19),
   constraint PK_SFCK_RESULT_JYXX_VIEW primary key (KEYID)
);
/

/*==============================================================*/
/* Table: SFCK_RESULT_GXGXX_VIEW         保险柜信息表                       */
/*==============================================================*/
create table SFCK_RESULT_GXGXX_VIEW 
(
   KEYID                NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   BXGXH                NUMBER(15),
   BXGH                 VARCHAR2(30),
   FKSJ                 VARCHAR2(19),
   BXGZT                VARCHAR2(20),
   SZWD                 VARCHAR2(100),
   ZYKSSJ               VARCHAR2(19),
   ZYJSSJ               VARCHAR2(19),
   BEIZ                 VARCHAR2(100),
   constraint PK_SFCK_RESULT_GXGXX_VIEW primary key (KEYID)
);
/
/*==============================================================*/
/* Table: SFCK_RESULT_POSXX_VIEW   POS机商户信息                             */
/*==============================================================*/
create table SFCK_RESULT_POSXX_VIEW 
(
   KEYID                NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   POSXH                VARCHAR2(20),
   SH                   VARCHAR2(100),
   KHZH                 VARCHAR2(30),
   DLDZ                 VARCHAR2(60),
   TXFS                 VARCHAR2(30),
   TXSJ                 VARCHAR2(100),
   HM                   VARCHAR2(40),
   FKSJ                 VARCHAR2(19),
   constraint PK_SFCK_RESULT_POSXX_VIEW primary key (KEYID)
);
/

/*==============================================================*/
/* Table: SFCK_RESULT_DLRZXX_VIEW              网银登录日志信息                 */
/*==============================================================*/
create table SFCK_RESULT_DLRZXX_VIEW 
(
   KEYID                NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   RZXH                 NUMBER(15),
   DLSJ                 VARCHAR2(19),
   KHZH                 VARCHAR2(30),
   DLDZ                 VARCHAR2(60),
   DLCZ                 VARCHAR2(30),
   DLMS                 VARCHAR2(60),
   FKSJ                 VARCHAR2(19),
   constraint PK_SFCK_RESULT_DLRZXX_VIEW primary key (KEYID)
);
/
/*==============================================================*/
/* Table: SFCK_RESULT_JJXX_VIEW                自动机具信息               */
/*==============================================================*/
create table SFCK_RESULT_JJXX_VIEW 
(
   KEYID                NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   RZXH                 NUMBER(15),
   DLSJ                 VARCHAR2(19),
   KHZH                 VARCHAR2(30),
   DLDZ                 VARCHAR2(60),
   DLCZ                 VARCHAR2(30),
   DLMS                 VARCHAR2(60),
   FKSJ                 VARCHAR2(19),
   constraint PK_SFCK_RESULT_JJXX_VIEW primary key (KEYID)
);
/