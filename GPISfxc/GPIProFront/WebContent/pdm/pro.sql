--创建表空间
CREATE TABLESPACE GPI_DATA
datafile 'C:\app\yangj\oradata\sfxc\GPI_DATA_01.DBF' size 1024m reuse autoextend off
nologging
online
permanent
extent management local autoallocate
segment space management auto;
commit; 
/
create temporary tablespace GPI_TEMP  
tempfile 'C:\app\yangj\oradata\sfxc\GPI_TEMP_01.DBF' 
size 1024m  
autoextend on  
next 50m maxsize 1024m  
extent management local;  

commit;  
/
---- create the user 在本地建库用了之前创建的GPI_DATA和GPI_TEMP
create USER gpisfxc
    identified by gpisfxc
    default tablespace GPI_DATA
    temporary tablespace GPI_TEMP
    profile default;
    
-- Grant/Revoke role privileges 
grant connect to gpisfxc;
grant resource to gpisfxc;
-- Grant/Revoke system privileges 
grant create any procedure to gpisfxc;
grant create any synonym to gpisfxc;
grant create any view to gpisfxc;
grant debug connect session to gpisfxc;
grant unlimited tablespace to gpisfxc;
grant create  public synonym to gpisfxc;

--用gpisys登录
grant select on S_LOG_KEY to gpisfxc;
grant select on T_SYS_APP_INFO to gpisfxc;
grant select on T_SYS_APP_MENU to gpisfxc;
grant select, insert, delete on T_SYS_ATT_CFG to gpisfxc;
grant select, insert, delete on T_SYS_ATT_INFO to gpisfxc;

grant select on T_SYS_DIC_INFO to gpisfxc;
grant select on T_SYS_DIC_TYPE to gpisfxc;
grant select, insert, delete on T_SYS_IMPORT_CONF to gpisfxc;
grant select, insert, update, delete on T_SYS_OP_LOG to gpisfxc;
grant select on T_SYS_ORG_INFO to gpisfxc;
grant select on T_SYS_POSITION_INFO to gpisfxc;
grant select on T_SYS_ROLE to gpisfxc;
grant select on T_SYS_ROLE_MENU to gpisfxc;
grant select, insert, update, delete on T_SYS_USER_INFO to gpisfxc;
grant select on T_SYS_USER_ROLE to gpisfxc;
--       
/

--1创建表
declare
  num    number(1);
  conum  number(1);
begin
--判断表是否存在，如果存在则删除
select count(1) into num from all_tables  where TABLE_NAME = 'PRO_QUERY_INFO' ; 
if   num=1   then 
    execute immediate 'drop table PRO_QUERY_INFO'; 
end   if; 
select count(*) into num from all_tables where TABLE_NAME = 'PRO_QUERY_RESULT' ; 
if   num=1   then 
    execute immediate 'drop table PRO_QUERY_RESULT'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'PRO_CHECK_WSINFO' ; 
if   num=1   then 
execute immediate 'drop table PRO_CHECK_WSINFO'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'PRO_DEAL_INFO' ; 
if   num=1   then 
execute immediate 'drop table PRO_DEAL_INFO'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'PRO_RESULT_ZHXX' ; 
if   num=1   then 
execute immediate 'drop table PRO_RESULT_ZHXX'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'PRO_RESULT_JYXX' ; 
if   num=1   then 
execute immediate 'drop table PRO_RESULT_JYXX'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'PRO_RESULT_BXGXX' ; 
if   num=1   then 
execute immediate 'drop table PRO_RESULT_BXGXX'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'PRO_RESULT_POSXX' ; 
if   num=1   then 
execute immediate 'drop table PRO_RESULT_POSXX'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'PRO_RESULT_DLRZXX' ; 
if   num=1   then 
execute immediate 'drop table PRO_RESULT_DLRZXX'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'PRO_RESULT_JJXX' ; 
if   num=1   then 
execute immediate 'drop table PRO_RESULT_JJXX'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'COURT_QUERY_INFO' ; 
if   num=1   then 
execute immediate 'drop table COURT_QUERY_INFO'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'COURT_DEAL_INFO' ; 
if   num=1   then 
execute immediate 'drop table COURT_DEAL_INFO'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'COURT_CHECK_WSINFO' ; 
if   num=1   then 
execute immediate 'drop table COURT_CHECK_WSINFO'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'COURT_QUERY_RESULT' ; 
if   num=1   then 
execute immediate 'drop table COURT_QUERY_RESULT'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'COURT_RESULT_ZHXX' ; 
if   num=1   then 
execute immediate 'drop table COURT_RESULT_ZHXX'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'COURT_RESULT_DJXX' ; 
if   num=1   then 
execute immediate 'drop table COURT_RESULT_DJXX'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'COURT_RESULT_GLZHXX' ; 
if   num=1   then 
execute immediate 'drop table COURT_RESULT_GLZHXX'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'COURT_RESULT_ZJWLXX' ; 
if   num=1   then 
execute immediate 'drop table COURT_RESULT_ZJWLXX'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'PRO_QUERY_RESULT' ; 
if   num=1   then 
execute immediate 'drop table PRO_QUERY_RESULT'; 
end   if;
select count(*) into num from all_tables where TABLE_NAME = 'COURT_QUERY_RESULT' ; 
if   num=1   then 
execute immediate 'drop table COURT_QUERY_RESULT'; 
end   if;
end;   
/

/*==============================================================*/
/* Table: PRO_QUERY_INFO                                        */
/*==============================================================*/
create table PRO_QUERY_INFO 
(
   QUERY_ID             VARCHAR2(20)         not null,
   BANK_TYPE            VARCHAR2(2),
   QUERY_QU             VARCHAR2(10),
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
   constraint PK_PRO_QUERY_INFO primary key (QUERY_ID)
);
/

/*==============================================================*/
/* Table: PRO_QUERY_RESULT                                            */
/*==============================================================*/
create table PRO_QUERY_RESULT
(
   KEYID                NUMBER(30)           not null,
   QUERY_ID             VARCHAR2(20)         not null,
   RESULTMSG             VARCHAR2(20),
   MSG                VARCHAR2(200),
   constraint PK_PRO_QUERY_RESULT primary key (KEYID)
);
/

/*==============================================================*/
/* Table: PRO_CHECK_WSINFO                                      */
/*==============================================================*/
create table PRO_CHECK_WSINFO 
(
   KEYID                NUMBER(30)           not null,
   XH                   VARCHAR2(10),
   WJMC                 VARCHAR2(50),
   WJLX                 VARCHAR2(30),
   FILEPATH             VARCHAR2(500),
   QUERY_ID             VARCHAR2(20)         not null,
   constraint PK_PRO_CHECK_WSINFO primary key (KEYID)
);
/
/*==============================================================*/
/* Table: PRO_DEAL_INFO                                         */
/*==============================================================*/
create table PRO_DEAL_INFO 
(
   KEYID                NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   RECEIVEDTIME       VARCHAR2(30),
   RECEIVER           VARCHAR2(30),
   EXAMINER           VARCHAR2(30),
   EXAMINETIME        VARCHAR2(30),
   BACKER             VARCHAR2(30),
   BACKTIME           VARCHAR2(30),
   DEALSTAUS          VARCHAR2(2),
   REMARK             VARCHAR2(100),
   constraint PK_PRO_DEAL_INFO primary key (KEYID)
);
/

/*==============================================================*/
/* Table: PRO_RESULT_ZHXX                                       */
/*==============================================================*/
create table PRO_RESULT_ZHXX 
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
   PROVIDER           VARCHAR2(30),
   PROVIDETIME        VARCHAR2(30),
   constraint PK_PRO_RESULT_ZHXX primary key (KEYID)
);
/

/*==============================================================*/
/* Table: PRO_RESULT_JYXX                                       */
/*==============================================================*/
create table PRO_RESULT_JYXX 
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
   PROVIDER           VARCHAR2(30),
   PROVIDETIME        VARCHAR2(30),
   constraint PK_PRO_RESULT_JYXX primary key (KEYID)
);
/

/*==============================================================*/
/* Table: PRO_RESULT_BXGXX                                      */
/*==============================================================*/
create table PRO_RESULT_BXGXX 
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
   PROVIDER           VARCHAR2(30),
   PROVIDETIME        VARCHAR2(30),
   constraint PK_PRO_RESULT_BXGXX primary key (KEYID)
);
/

/*==============================================================*/
/* Table: PRO_RESULT_POSXX                                      */
/*==============================================================*/
create table PRO_RESULT_POSXX 
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
   PROVIDER           VARCHAR2(30),
   PROVIDETIME        VARCHAR2(30),
   constraint PK_PRO_RESULT_POSXX primary key (KEYID)
);
/
/*==============================================================*/
/* Table: PRO_RESULT_DLRZXX                                     */
/*==============================================================*/
create table PRO_RESULT_DLRZXX 
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
   PROVIDER           VARCHAR2(30),
   PROVIDETIME        VARCHAR2(30),
   constraint PK_PRO_RESULT_DLRZXX primary key (KEYID)
);

/

/*==============================================================*/
/* Table: PRO_RESULT_JJXX                                       */
/*==============================================================*/
create table PRO_RESULT_JJXX 
(
   KEYID                NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   JJXH                 NUMBER(15),
   JJDZ                 VARCHAR2(60),
   JD                   VARCHAR2(30),
   WD                   VARCHAR2(30),
   WDH                  VARCHAR2(30),
   JGH                  VARCHAR2(60),
   JJBH                 VARCHAR2(60),
   WDMC                 VARCHAR2(30),
   LXDH                 VARCHAR2(30),
   JJLX                 VARCHAR2(20),
   FKSJ                 VARCHAR2(19),
   KHZH                 VARCHAR2(30),
   PROVIDER           VARCHAR2(30),
   PROVIDETIME        VARCHAR2(30),
   constraint PK_PRO_RESULT_JJXX primary key (KEYID)
);
/
/*==============================================================*/
/* Table: COURT_QUERY_INFO                                      */
/*==============================================================*/
create table COURT_QUERY_INFO 
(
   QUERY_ID             VARCHAR2(20)         not null,
   BANK_TYPE            VARCHAR2(2),
   QUERY_QU             VARCHAR2(10),
   QUERY_NAME           VARCHAR2(100),
   NATION_AREA          VARCHAR2(30),
   CERT_TYPE            VARCHAR2(15),
   CERT_NO              VARCHAR2(30),
   ISSUCTF_AHR_LO       VARCHAR2(50),
   COURT_NAME           VARCHAR2(50),
   JUDGE_NAME           VARCHAR2(20),
   CASE_ID              VARCHAR2(50),
   START_DT             VARCHAR2(19),
   END_DT               VARCHAR2(19),
   constraint PK_COURT_QUERY_INFO primary key (QUERY_ID)
);
/
/*==============================================================*/
/* Table: COURT_DEAL_INFO                                       */
/*==============================================================*/
create table COURT_DEAL_INFO 
(
   KEYID                NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   RECEIVEDTIME       VARCHAR2(30),
   RECEIVER           VARCHAR2(30),
   EXAMINER           VARCHAR2(30),
   EXAMINETIME        VARCHAR2(30),
   BACKER             VARCHAR2(30),
   BACKTIME           VARCHAR2(30),
   DEALSTAUS          VARCHAR2(2),
   REMARK             VARCHAR2(100),
   constraint PK_COURT_DEAL_INFO primary key (KEYID)
);
/
/*==============================================================*/
/* Table: COURT_CHECK_WSINFO                                    */
/*==============================================================*/
create table COURT_CHECK_WSINFO 
(
   KEYID                NUMBER(30)           not null,
   XH                   VARCHAR2(10),
   WJMC                 VARCHAR2(50),
   WJLX                 VARCHAR2(30),
   FILEPATH             VARCHAR2(500),
   QUERY_ID             VARCHAR2(20)         not null,
   constraint PK_COURT_CHECK_WSINFO primary key (KEYID)
);
/
/*==============================================================*/
/* Table: COURT_QUERY_RESULT                                          */
/*==============================================================*/
create table COURT_QUERY_RESULT 
(
   KEYID                NUMBER(30)           not null,
   QUERY_ID             VARCHAR2(20)         not null,
   RESULTMSG             VARCHAR2(20),
   MSG                VARCHAR2(200),
   constraint PK_COURT_QUERY_RESULT primary key (KEYID)
);
/
/*==============================================================*/
/* Table: COURT_RESULT_ZHXX                                     */
/*==============================================================*/
create table COURT_RESULT_ZHXX 
(
   ID                   NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   CCXH                 NUMBER(15),
   KHZH                 VARCHAR2(30),
   YE                   VARCHAR2(20),
   FKSJ                 VARCHAR2(19),
   CCLB                 VARCHAR2(20),
   ZHZT                 VARCHAR2(20),
   KHWD                 VARCHAR2(100),
   BZ                   VARCHAR2(20),
   TXDZ                 VARCHAR2(100),
   YZBM                 VARCHAR2(20),
   LXDH                 VARCHAR2(20),
   BEIZ                 VARCHAR2(100),
   SFTZ                 VARCHAR2(1),
   PROVIDER           VARCHAR2(30),
   PROVIDETIME        VARCHAR2(30),
   constraint PK_COURT_RESULT_ZHXX primary key (ID)
);
/
/*==============================================================*/
/* Table: COURT_RESULT_DJXX                                     */
/*==============================================================*/
create table COURT_RESULT_DJXX 
(
   ID                   NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   CCXH                 NUMBER(15),
   CSXH                 NUMBER(15),
   SFBDJ                VARCHAR2(1),
   DJJZRQ               VARCHAR2(30),
   DJJG                 VARCHAR2(50),
   DJWH                 VARCHAR2(50),
   DJJE                 VARCHAR2(20),
   PROVIDER           VARCHAR2(30),
   PROVIDETIME       VARCHAR2(30),
   constraint PK_COURT_RESULT_DJXX primary key (ID)
);
/

/*==============================================================*/
/* Table: COURT_RESULT_GLZHXX                                   */
/*==============================================================*/
create table COURT_RESULT_GLZHXX 
(
   ID                   NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   CCXH                 NUMBER(15),
   GLXH                 NUMBER(15),
   GLZHLB               VARCHAR2(20),
   GLZHHM               VARCHAR2(30),
   GLZHMC               VARCHAR2(100),
   PROVIDER           VARCHAR2(30),
   PROVIDETIME        VARCHAR2(30),
   constraint PK_COURT_RESULT_GLZHXX primary key (ID)
);
/
/*==============================================================*/
/* Table: COURT_RESULT_ZJWLXX                                   */
/*==============================================================*/
create table COURT_RESULT_ZJWLXX 
(
   ID                   NUMBER(30)           not null,
   BDHM                 VARCHAR2(20)         not null,
   CCXH                 NUMBER(15),
   WLXH                 NUMBER(15),
   ZJLX                 VARCHAR2(20),
   ZCKZH                VARCHAR2(30),
   ZCKZXM               VARCHAR2(60),
   BZ                   VARCHAR2(20),
   JE                   VARCHAR2(20),
   JYSJ                 VARCHAR2(19),
   PROVIDER           VARCHAR2(30),
   PROVIDETIME        VARCHAR2(30),
   constraint PK_COURT_RESULT_ZJWLXX primary key (ID)
);
/
