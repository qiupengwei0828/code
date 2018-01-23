package com.sys.entity;

import org.apache.ibatis.type.Alias;

import com.base.entity.BaseEntity;


@Alias("Menu")
public class Menu extends BaseEntity{
    /** @Fields serialVersionUID :           */
	private static final long serialVersionUID = 1L;

	private Long menuId;

    private String appCode;

    private String menuName;

    private String actUrl;

    private Long pMenuId;
    
    private String pMenuName;

    private Integer dispNo;

    private String tip;

    private String icon;
    
    /** 授权时使用的角色编号 **/
    private String roleCode;
    
    /** 接收授权列表 **/
    private String menuIds;
    /**
     * @return  T_SYS_APP_MENU.MENU_ID
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * @param menuId ： T_SYS_APP_MENU.MENU_ID
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * @return  T_SYS_APP_MENU.APP_CODE
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * @param appCode ： T_SYS_APP_MENU.APP_CODE
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    /**
     * @return  T_SYS_APP_MENU.MENU_NAME
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName ： T_SYS_APP_MENU.MENU_NAME
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * @return  T_SYS_APP_MENU.ACT_URL
     */
    public String getActUrl() {
        return actUrl;
    }

    /**
     * @param actUrl ： T_SYS_APP_MENU.ACT_URL
     */
    public void setActUrl(String actUrl) {
        this.actUrl = actUrl == null ? null : actUrl.trim();
    }

    /**
     * @return  T_SYS_APP_MENU.P_MENU_ID
     */
    public Long getpMenuId() {
        return pMenuId;
    }

    /**
     * @param pMenuId ： T_SYS_APP_MENU.P_MENU_ID
     */
    public void setpMenuId(Long pMenuId) {
        this.pMenuId = pMenuId;
    }

    /**
     * @return  T_SYS_APP_MENU.DISP_NO
     */
    public Integer getDispNo() {
        return dispNo;
    }

    /**
     * @param dispNo ： T_SYS_APP_MENU.DISP_NO
     */
    public void setDispNo(Integer dispNo) {
        this.dispNo = dispNo;
    }

    /**
     * @return  T_SYS_APP_MENU.TIP
     */
    public String getTip() {
        return tip;
    }

    /**
     * @param tip ： T_SYS_APP_MENU.TIP
     */
    public void setTip(String tip) {
        this.tip = tip == null ? null : tip.trim();
    }

    /**
     * @return  T_SYS_APP_MENU.ICON
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon ： T_SYS_APP_MENU.ICON
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

	/**@return: String */
	public String getpMenuName() {
		return pMenuName;
	}

	/**@param String*/
	public void setpMenuName(String pMenuName) {
		this.pMenuName = pMenuName;
	}

	/**@return: String */
	public String getRoleCode() {
		return roleCode;
	}

	/**@param String*/
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**@return: String */
	public String getMenuIds() {
		return menuIds;
	}

	/**@param String*/
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
}