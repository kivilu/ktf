package com.kivi.framework.persist.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ktf_sys_param")
public class KtfSysParam extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父ID
     */
    @Column(name = "PID")
    private Long pid;

    /**
     * 客户代码
     */
    @Column(name = "custom_code")
    private String customCode;

    /**
     * 业务代码
     */
    @Column(name = "biz_code")
    private String bizCode;

    /**
     * 参数代码
     */
    @Column(name = "param_code")
    private String paramCode;

    /**
     * 参数名称
     */
    @Column(name = "param_name")
    private String paramName;

    /**
     * 参数类型，0：字符串，1：数字，2：金额，默认：0
     */
    @Column(name = "param_type")
    private String paramType;

    /**
     * 参数值
     */
    @Column(name = "param_value")
    private String paramValue;

    /**
     * 上次更新用户
     */
    @Column(name = "LAST_UPDATE_USER")
    private String lastUpdateUser;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @Column(name = "gmt_update")
    private Date gmtUpdate;

    /**
     * 获取主键ID
     *
     * @return ID - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取父ID
     *
     * @return PID - 父ID
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父ID
     *
     * @param pid 父ID
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取客户代码
     *
     * @return custom_code - 客户代码
     */
    public String getCustomCode() {
        return customCode;
    }

    /**
     * 设置客户代码
     *
     * @param customCode 客户代码
     */
    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    /**
     * 获取业务代码
     *
     * @return biz_code - 业务代码
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * 设置业务代码
     *
     * @param bizCode 业务代码
     */
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * 获取参数代码
     *
     * @return param_code - 参数代码
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置参数代码
     *
     * @param paramCode 参数代码
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    /**
     * 获取参数名称
     *
     * @return param_name - 参数名称
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 设置参数名称
     *
     * @param paramName 参数名称
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * 获取参数类型，0：字符串，1：数字，2：金额，默认：0
     *
     * @return param_type - 参数类型，0：字符串，1：数字，2：金额，默认：0
     */
    public String getParamType() {
        return paramType;
    }

    /**
     * 设置参数类型，0：字符串，1：数字，2：金额，默认：0
     *
     * @param paramType 参数类型，0：字符串，1：数字，2：金额，默认：0
     */
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    /**
     * 获取参数值
     *
     * @return param_value - 参数值
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置参数值
     *
     * @param paramValue 参数值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * 获取上次更新用户
     *
     * @return LAST_UPDATE_USER - 上次更新用户
     */
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * 设置上次更新用户
     *
     * @param lastUpdateUser 上次更新用户
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /**
     * 获取创建时间
     *
     * @return gmt_create - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取更新时间
     *
     * @return gmt_update - 更新时间
     */
    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    /**
     * 设置更新时间
     *
     * @param gmtUpdate 更新时间
     */
    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", customCode=").append(customCode);
        sb.append(", bizCode=").append(bizCode);
        sb.append(", paramCode=").append(paramCode);
        sb.append(", paramName=").append(paramName);
        sb.append(", paramType=").append(paramType);
        sb.append(", paramValue=").append(paramValue);
        sb.append(", lastUpdateUser=").append(lastUpdateUser);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtUpdate=").append(gmtUpdate);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        KtfSysParam other = (KtfSysParam) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCustomCode() == null ? other.getCustomCode() == null : this.getCustomCode().equals(other.getCustomCode()))
            && (this.getBizCode() == null ? other.getBizCode() == null : this.getBizCode().equals(other.getBizCode()))
            && (this.getParamCode() == null ? other.getParamCode() == null : this.getParamCode().equals(other.getParamCode()))
            && (this.getParamName() == null ? other.getParamName() == null : this.getParamName().equals(other.getParamName()))
            && (this.getParamType() == null ? other.getParamType() == null : this.getParamType().equals(other.getParamType()))
            && (this.getParamValue() == null ? other.getParamValue() == null : this.getParamValue().equals(other.getParamValue()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtUpdate() == null ? other.getGmtUpdate() == null : this.getGmtUpdate().equals(other.getGmtUpdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCustomCode() == null) ? 0 : getCustomCode().hashCode());
        result = prime * result + ((getBizCode() == null) ? 0 : getBizCode().hashCode());
        result = prime * result + ((getParamCode() == null) ? 0 : getParamCode().hashCode());
        result = prime * result + ((getParamName() == null) ? 0 : getParamName().hashCode());
        result = prime * result + ((getParamType() == null) ? 0 : getParamType().hashCode());
        result = prime * result + ((getParamValue() == null) ? 0 : getParamValue().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtUpdate() == null) ? 0 : getGmtUpdate().hashCode());
        return result;
    }
}