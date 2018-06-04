package com.kivi.framework.persist.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ktf_error_code")
public class KtfErrorCode extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 错误代码
     */
    @Column(name = "err_code")
    private String errCode;

    /**
     * 错误代码英文别名
     */
    @Column(name = "err_alias")
    private String errAlias;

    /**
     * 错误代码中文说明
     */
    @Column(name = "err_desc")
    private String errDesc;

    /**
     * 错误代码友好提示
     */
    @Column(name = "err_tip")
    private String errTip;

    /**
     * 错误代码分组
     */
    @Column(name = "err_group")
    private String errGroup;

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
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取错误代码
     *
     * @return err_code - 错误代码
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * 设置错误代码
     *
     * @param errCode 错误代码
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * 获取错误代码英文别名
     *
     * @return err_alias - 错误代码英文别名
     */
    public String getErrAlias() {
        return errAlias;
    }

    /**
     * 设置错误代码英文别名
     *
     * @param errAlias 错误代码英文别名
     */
    public void setErrAlias(String errAlias) {
        this.errAlias = errAlias;
    }

    /**
     * 获取错误代码中文说明
     *
     * @return err_desc - 错误代码中文说明
     */
    public String getErrDesc() {
        return errDesc;
    }

    /**
     * 设置错误代码中文说明
     *
     * @param errDesc 错误代码中文说明
     */
    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    /**
     * 获取错误代码友好提示
     *
     * @return err_tip - 错误代码友好提示
     */
    public String getErrTip() {
        return errTip;
    }

    /**
     * 设置错误代码友好提示
     *
     * @param errTip 错误代码友好提示
     */
    public void setErrTip(String errTip) {
        this.errTip = errTip;
    }

    /**
     * 获取错误代码分组
     *
     * @return err_group - 错误代码分组
     */
    public String getErrGroup() {
        return errGroup;
    }

    /**
     * 设置错误代码分组
     *
     * @param errGroup 错误代码分组
     */
    public void setErrGroup(String errGroup) {
        this.errGroup = errGroup;
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
        sb.append(", errCode=").append(errCode);
        sb.append(", errAlias=").append(errAlias);
        sb.append(", errDesc=").append(errDesc);
        sb.append(", errTip=").append(errTip);
        sb.append(", errGroup=").append(errGroup);
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
        KtfErrorCode other = (KtfErrorCode) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getErrCode() == null ? other.getErrCode() == null : this.getErrCode().equals(other.getErrCode()))
            && (this.getErrAlias() == null ? other.getErrAlias() == null : this.getErrAlias().equals(other.getErrAlias()))
            && (this.getErrDesc() == null ? other.getErrDesc() == null : this.getErrDesc().equals(other.getErrDesc()))
            && (this.getErrTip() == null ? other.getErrTip() == null : this.getErrTip().equals(other.getErrTip()))
            && (this.getErrGroup() == null ? other.getErrGroup() == null : this.getErrGroup().equals(other.getErrGroup()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtUpdate() == null ? other.getGmtUpdate() == null : this.getGmtUpdate().equals(other.getGmtUpdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getErrCode() == null) ? 0 : getErrCode().hashCode());
        result = prime * result + ((getErrAlias() == null) ? 0 : getErrAlias().hashCode());
        result = prime * result + ((getErrDesc() == null) ? 0 : getErrDesc().hashCode());
        result = prime * result + ((getErrTip() == null) ? 0 : getErrTip().hashCode());
        result = prime * result + ((getErrGroup() == null) ? 0 : getErrGroup().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtUpdate() == null) ? 0 : getGmtUpdate().hashCode());
        return result;
    }
}