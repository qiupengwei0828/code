package com.posrot.entity;

import org.apache.ibatis.type.Alias;
import com.base.entity.BaseEntity;
import com.sys.entity.Org;

/*
 * 履历
 */
@Alias("Staff")
public class Staff extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String pwd;
	private String salt;
	private String hrNo;
	private String orgNo;
	// 业务部门
	private String depCode;
	private String sex;
	private String certTyp;
	private String certNo;
	private String nativePlace;
	private String nation;
	private String userClass;
	private String birthday;
	// 参加工作时间
	private String beginWorkDate;
	// 政治面貌
	private String politicalStatus;
	// 学历
	private String stLevel;
	// 职务
	private String post;
	// 职级
	private String rank;
	// 职称
	private String posTitle;
	// 岗位
	private String pos;
	// 到岗时间
	private String posDate;
	private String tel;
	private String mobile;
	private String email;
	private String mobile2;
	private String status;
	private String crtUser;
	private String crtTime;

	/*
	 * 只用于传参
	 */
	private Org orgInfo; // 用户所在机构对象
	// 角色编码
	private String roleCode;
	// 机构名称
	private String orgName;
	private String orgLevel;
	// ***********************************************
	// ***********************************************
	// 轮岗系统
	private String posCode;
	private String posName;
	// 当前岗位
	private String posNow;
	// 轮岗期限
	private String limitDate;
	// 用于计算出到期天数
	private String limitDay;
	// 表示到期天数的
	private String limitDayDisp;

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	/**
	 * 
	 * 證書管理部分用於傳參的字段
	 */
	/**
	 * @return T_SYS_USER_INFO.USER_ID
	 */

	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            ： T_SYS_USER_INFO.USER_ID
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.USER_NAME
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            ： T_SYS_USER_INFO.USER_NAME
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.PWD
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd
	 *            ： T_SYS_USER_INFO.PWD
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.HR_NO
	 */
	public String getHrNo() {
		return hrNo;
	}

	/**
	 * @param hrNo
	 *            ： T_SYS_USER_INFO.HR_NO
	 */
	public void setHrNo(String hrNo) {
		this.hrNo = hrNo == null ? null : hrNo.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.ORG_NO
	 */
	public String getOrgNo() {
		return orgNo;
	}

	/**
	 * @param orgNo
	 *            ： T_SYS_USER_INFO.ORG_NO
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo == null ? null : orgNo.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.DEP_CODE
	 */
	public String getDepCode() {
		return depCode;
	}

	/**
	 * @param depCode
	 *            ： T_SYS_USER_INFO.DEP_CODE
	 */
	public void setDepCode(String depCode) {
		this.depCode = depCode == null ? null : depCode.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.SEX
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            ： T_SYS_USER_INFO.SEX
	 */
	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.CERT_TYP
	 */
	public String getCertTyp() {
		return certTyp;
	}

	/**
	 * @param certTyp
	 *            ： T_SYS_USER_INFO.CERT_TYP
	 */
	public void setCertTyp(String certTyp) {
		this.certTyp = certTyp == null ? null : certTyp.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.CERT_NO
	 */
	public String getCertNo() {
		return certNo;
	}

	/**
	 * @param certNo
	 *            ： T_SYS_USER_INFO.CERT_NO
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo == null ? null : certNo.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.NATIVE_PLACE
	 */
	public String getNativePlace() {
		return nativePlace;
	}

	/**
	 * @param nativePlace
	 *            ： T_SYS_USER_INFO.NATIVE_PLACE
	 */
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace == null ? null : nativePlace.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.NATION
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * @param nation
	 *            ： T_SYS_USER_INFO.NATION
	 */
	public void setNation(String nation) {
		this.nation = nation == null ? null : nation.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.USER_CLASS
	 */
	public String getUserClass() {
		return userClass;
	}

	/**
	 * @param userClass
	 *            ： T_SYS_USER_INFO.USER_CLASS
	 */
	public void setUserClass(String userClass) {
		this.userClass = userClass == null ? null : userClass.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.BIRTHDAY
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            ： T_SYS_USER_INFO.BIRTHDAY
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday == null ? null : birthday.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.BEGIN_WORK_DATE
	 */
	public String getBeginWorkDate() {
		return beginWorkDate;
	}

	/**
	 * @param beginWorkDate
	 *            ： T_SYS_USER_INFO.BEGIN_WORK_DATE
	 */
	public void setBeginWorkDate(String beginWorkDate) {
		this.beginWorkDate = beginWorkDate == null ? null : beginWorkDate.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.POLITICAL_STATUS
	 */
	public String getPoliticalStatus() {
		return politicalStatus;
	}

	/**
	 * @param politicalStatus
	 *            ： T_SYS_USER_INFO.POLITICAL_STATUS
	 */
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus == null ? null : politicalStatus.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.ST_LEVEL
	 */
	public String getStLevel() {
		return stLevel;
	}

	/**
	 * @param stLevel
	 *            ： T_SYS_USER_INFO.ST_LEVEL
	 */
	public void setStLevel(String stLevel) {
		this.stLevel = stLevel == null ? null : stLevel.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.POST
	 */
	public String getPost() {
		return post;
	}

	/**
	 * @param post
	 *            ： T_SYS_USER_INFO.POST
	 */
	public void setPost(String post) {
		this.post = post == null ? null : post.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.RANK
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            ： T_SYS_USER_INFO.RANK
	 */
	public void setRank(String rank) {
		this.rank = rank == null ? null : rank.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.POS_TITLE
	 */
	public String getPosTitle() {
		return posTitle;
	}

	/**
	 * @param posTitle
	 *            ： T_SYS_USER_INFO.POS_TITLE
	 */
	public void setPosTitle(String posTitle) {
		this.posTitle = posTitle == null ? null : posTitle.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.POS
	 */
	public String getPos() {
		return pos;
	}

	/**
	 * @param pos
	 *            ： T_SYS_USER_INFO.POS
	 */
	public void setPos(String pos) {
		this.pos = pos == null ? null : pos.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.POS_DATE
	 */
	public String getPosDate() {
		return posDate;
	}

	/**
	 * @param posDate
	 *            ： T_SYS_USER_INFO.POS_DATE
	 */
	public void setPosDate(String posDate) {
		this.posDate = posDate == null ? null : posDate.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.TEL
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            ： T_SYS_USER_INFO.TEL
	 */
	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.MOBILE
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            ： T_SYS_USER_INFO.MOBILE
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.EMAIL
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            ： T_SYS_USER_INFO.EMAIL
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.MOBILE2
	 */
	public String getMobile2() {
		return mobile2;
	}

	/**
	 * @param mobile2
	 *            ： T_SYS_USER_INFO.MOBILE2
	 */
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2 == null ? null : mobile2.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.STATUS
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            ： T_SYS_USER_INFO.STATUS
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.CRT_USER
	 */
	public String getCrtUser() {
		return crtUser;
	}

	/**
	 * @param crtUser
	 *            ： T_SYS_USER_INFO.CRT_USER
	 */
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser == null ? null : crtUser.trim();
	}

	/**
	 * @return T_SYS_USER_INFO.CRT_TIME
	 */
	public String getCrtTime() {
		return crtTime;
	}

	/**
	 * @param crtTime
	 *            ： T_SYS_USER_INFO.CRT_TIME
	 */
	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime == null ? null : crtTime.trim();
	}

	/** @return: String */
	public String getSalt() {
		return salt;
	}

	/** @param String */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/*
	 * 只用于传参
	 */
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/** @return: Org */
	public Org getOrgInfo() {
		return orgInfo;
	}

	/** @param Org */
	public void setOrgInfo(Org orgInfo) {
		this.orgInfo = orgInfo;
	}

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getPosNow() {
		return posNow;
	}

	public void setPosNow(String posNow) {
		this.posNow = posNow;
	}

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	public String getLimitDay() {
		return limitDay;
	}

	public void setLimitDay(String limitDay) {
		this.limitDay = limitDay;
	}

	public String getLimitDayDisp() {
		return limitDayDisp;
	}

	public void setLimitDayDisp(String limitDayDisp) {
		this.limitDayDisp = limitDayDisp;
	}

}
