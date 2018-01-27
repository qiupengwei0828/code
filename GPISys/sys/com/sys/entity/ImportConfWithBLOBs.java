package com.sys.entity;

public class ImportConfWithBLOBs extends ImportConf {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tmpCrtSql;

    private String cleanSql;

    private String querySql;

    private String impDataSql;

    /** 备份表名：原表名+序号 */
    private String bakSql;

    /**
     * @return  T_SYS_IMPORT_CONF.TMP_CRT_SQL
     */
    public String getTmpCrtSql() {
        return tmpCrtSql;
    }

    /**
     * @param tmpCrtSql ： T_SYS_IMPORT_CONF.TMP_CRT_SQL
     */
    public void setTmpCrtSql(String tmpCrtSql) {
        this.tmpCrtSql = tmpCrtSql == null ? null : tmpCrtSql.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.CLEAN_SQL
     */
    public String getCleanSql() {
        return cleanSql;
    }

    /**
     * @param cleanSql ： T_SYS_IMPORT_CONF.CLEAN_SQL
     */
    public void setCleanSql(String cleanSql) {
        this.cleanSql = cleanSql == null ? null : cleanSql.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.QUERY_SQL
     */
    public String getQuerySql() {
        return querySql;
    }

    /**
     * @param querySql ： T_SYS_IMPORT_CONF.QUERY_SQL
     */
    public void setQuerySql(String querySql) {
        this.querySql = querySql == null ? null : querySql.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.IMP_DATA_SQL
     */
    public String getImpDataSql() {
        return impDataSql;
    }

    /**
     * @param impDataSql ： T_SYS_IMPORT_CONF.IMP_DATA_SQL
     */
    public void setImpDataSql(String impDataSql) {
        this.impDataSql = impDataSql == null ? null : impDataSql.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.BAK_SQL
     */
    public String getBakSql() {
        return bakSql;
    }

    /**
     * @param bakSql ： T_SYS_IMPORT_CONF.BAK_SQL
     */
    public void setBakSql(String bakSql) {
        this.bakSql = bakSql == null ? null : bakSql.trim();
    }
}