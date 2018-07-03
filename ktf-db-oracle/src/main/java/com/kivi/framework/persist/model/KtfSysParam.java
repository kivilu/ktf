package com.kivi.framework.persist.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "KTF_SYS_PARAM")
public class KtfSysParam {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT SEQ_KTF_COMMON.NEXTVAL AS ID FROM DUAL")
    private Long id;

    @Column(name = "PID")
    private Long pid;

    @Column(name = "CUSTOM_CODE")
    private String customCode;

    @Column(name = "BIZ_CODE")
    private String bizCode;

    @Column(name = "PARAM_CODE")
    private String paramCode;

    @Column(name = "PARAM_NAME")
    private String paramName;

    @Column(name = "PARAM_TYPE")
    private String paramType;

    @Column(name = "PARAM_VALUE")
    private String paramValue;

    @Column(name = "LAST_UPDATE_USER")
    private String lastUpdateUser;

    @Column(name = "GMT_CREATE")
    private Date gmtCreate;

    @Column(name = "GMT_UPDATE")
    private Date gmtUpdate;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return PID
     */
    public Long getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * @return CUSTOM_CODE
     */
    public String getCustomCode() {
        return customCode;
    }

    /**
     * @param customCode
     */
    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    /**
     * @return BIZ_CODE
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * @param bizCode
     */
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * @return PARAM_CODE
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * @param paramCode
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    /**
     * @return PARAM_NAME
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * @param paramName
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * @return PARAM_TYPE
     */
    public String getParamType() {
        return paramType;
    }

    /**
     * @param paramType
     */
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    /**
     * @return PARAM_VALUE
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * @param paramValue
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * @return LAST_UPDATE_USER
     */
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * @param lastUpdateUser
     */
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /**
     * @return GMT_CREATE
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return GMT_UPDATE
     */
    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    /**
     * @param gmtUpdate
     */
    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}