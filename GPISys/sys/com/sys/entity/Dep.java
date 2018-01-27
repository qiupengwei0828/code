package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;


/**
 * 系统应用表
 */

@Alias("Dep")
public class Dep extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String depCode;

    private String depName;

    private String remark;

    private String status;

    /**
     * @return  T_SYS_DEP_INFO.DEP_CODE
     */
    public String getDepCode() {
        return depCode;
    }

    /**
     * @param depCode ： T_SYS_DEP_INFO.DEP_CODE
     */
    public void setDepCode(String depCode) {
        this.depCode = depCode == null ? null : depCode.trim();
    }

    /**
     * @return  T_SYS_DEP_INFO.DEP_NAME
     */
    public String getDepName() {
        return depName;
    }

    /**
     * @param depName ： T_SYS_DEP_INFO.DEP_NAME
     */
    public void setDepName(String depName) {
        this.depName = depName == null ? null : depName.trim();
    }

    /**
     * @return  T_SYS_DEP_INFO.REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark ： T_SYS_DEP_INFO.REMARK
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return  T_SYS_DEP_INFO.STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status ： T_SYS_DEP_INFO.STATUS
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}