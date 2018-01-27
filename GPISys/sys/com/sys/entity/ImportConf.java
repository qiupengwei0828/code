package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;

@Alias("ImportConf")
public class ImportConf extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cfgCode;

    private String cfgName;

    private String aimTable;

    /** XLS/TXT/等 */
    private String cfgType;

    /** 文件存储名，含路径 */
    private String cfgFile;

    /** 表名：指定表名+序号 */
    private String tmpTable;

    /** 1.SQL, 2.过程 */
    private String cleanType;

    /** 表名：指定表名+序号 */
    private String midTable;

    private String isBak;

    private String remark;
    private String clumName;

    /**
     * @return  T_SYS_IMPORT_CONF.CFG_CODE
     */
    public String getCfgCode() {
        return cfgCode;
    }

    /**
     * @param cfgCode ： T_SYS_IMPORT_CONF.CFG_CODE
     */
    public void setCfgCode(String cfgCode) {
        this.cfgCode = cfgCode == null ? null : cfgCode.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.CFG_NAME
     */
    public String getCfgName() {
        return cfgName;
    }

    /**
     * @param cfgName ： T_SYS_IMPORT_CONF.CFG_NAME
     */
    public void setCfgName(String cfgName) {
        this.cfgName = cfgName == null ? null : cfgName.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.AIM_TABLE
     */
    public String getAimTable() {
        return aimTable;
    }

    /**
     * @param aimTable ： T_SYS_IMPORT_CONF.AIM_TABLE
     */
    public void setAimTable(String aimTable) {
        this.aimTable = aimTable == null ? null : aimTable.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.CFG_TYPE
     */
    public String getCfgType() {
        return cfgType;
    }

    /**
     * @param cfgType ： T_SYS_IMPORT_CONF.CFG_TYPE
     */
    public void setCfgType(String cfgType) {
        this.cfgType = cfgType == null ? null : cfgType.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.CFG_FILE
     */
    public String getCfgFile() {
        return cfgFile;
    }

    /**
     * @param cfgFile ： T_SYS_IMPORT_CONF.CFG_FILE
     */
    public void setCfgFile(String cfgFile) {
        this.cfgFile = cfgFile == null ? null : cfgFile.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.TMP_TABLE
     */
    public String getTmpTable() {
        return tmpTable;
    }

    /**
     * @param tmpTable ： T_SYS_IMPORT_CONF.TMP_TABLE
     */
    public void setTmpTable(String tmpTable) {
        this.tmpTable = tmpTable == null ? null : tmpTable.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.CLEAN_TYPE
     */
    public String getCleanType() {
        return cleanType;
    }

    /**
     * @param cleanType ： T_SYS_IMPORT_CONF.CLEAN_TYPE
     */
    public void setCleanType(String cleanType) {
        this.cleanType = cleanType == null ? null : cleanType.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.MID_TABLE
     */
    public String getMidTable() {
        return midTable;
    }

    /**
     * @param midTable ： T_SYS_IMPORT_CONF.MID_TABLE
     */
    public void setMidTable(String midTable) {
        this.midTable = midTable == null ? null : midTable.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.IS_BAK
     */
    public String getIsBak() {
        return isBak;
    }

    /**
     * @param isBak ： T_SYS_IMPORT_CONF.IS_BAK
     */
    public void setIsBak(String isBak) {
        this.isBak = isBak == null ? null : isBak.trim();
    }

    /**
     * @return  T_SYS_IMPORT_CONF.REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark ： T_SYS_IMPORT_CONF.REMARK
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getClumName() {
		return clumName;
	}

	public void setClumName(String clumName) {
		this.clumName = clumName;
	}
}