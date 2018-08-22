package com.kivi.framework.persist.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "ktf_application")
public class KtfApplication extends BaseModel implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * service唯一标签
     */
    private String sid;

    /**
     * service名字
     */
    private String name;

    /**
     * 主机IP
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 服务地址
     */
    private String url;

    /**
     * 通信密钥
     */
    @Column(name = "auth_key")
    private String authKey;

    /**
     * 业务代码
     */
    @Column(name = "biz_code")
    private String bizCode;

    /**
     * 服务序号
     */
    @Column(name = "slot_id")
    private Short slotId;

    /**
     * 状态，00：离线 01：在线
     */
    private String status;

    /**
     * 是否为内部服务器，0：否，1是
     */
    private Byte internal;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_update")
    private Date gmtUpdate;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取service唯一标签
     *
     * @return sid - service唯一标签
     */
    public String getSid() {
        return sid;
    }

    /**
     * 设置service唯一标签
     *
     * @param sid service唯一标签
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 获取service名字
     *
     * @return name - service名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置service名字
     *
     * @param name service名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取主机IP
     *
     * @return host - 主机IP
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置主机IP
     *
     * @param host 主机IP
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 获取端口
     *
     * @return port - 端口
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 设置端口
     *
     * @param port 端口
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 获取服务地址
     *
     * @return url - 服务地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置服务地址
     *
     * @param url 服务地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取通信密钥
     *
     * @return auth_key - 通信密钥
     */
    public String getAuthKey() {
        return authKey;
    }

    /**
     * 设置通信密钥
     *
     * @param authKey 通信密钥
     */
    public void setAuthKey(String authKey) {
        this.authKey = authKey;
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
     * 获取服务序号
     *
     * @return slot_id - 服务序号
     */
    public Short getSlotId() {
        return slotId;
    }

    /**
     * 设置服务序号
     *
     * @param slotId 服务序号
     */
    public void setSlotId(Short slotId) {
        this.slotId = slotId;
    }

    /**
     * 获取状态，00：离线 01：在线
     *
     * @return status - 状态，00：离线 01：在线
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态，00：离线 01：在线
     *
     * @param status 状态，00：离线 01：在线
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取是否为内部服务器，0：否，1是
     *
     * @return internal - 是否为内部服务器，0：否，1是
     */
    public Byte getInternal() {
        return internal;
    }

    /**
     * 设置是否为内部服务器，0：否，1是
     *
     * @param internal 是否为内部服务器，0：否，1是
     */
    public void setInternal(Byte internal) {
        this.internal = internal;
    }

    /**
     * @return gmt_create
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
     * @return gmt_update
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sid=").append(sid);
        sb.append(", name=").append(name);
        sb.append(", host=").append(host);
        sb.append(", port=").append(port);
        sb.append(", url=").append(url);
        sb.append(", authKey=").append(authKey);
        sb.append(", bizCode=").append(bizCode);
        sb.append(", slotId=").append(slotId);
        sb.append(", status=").append(status);
        sb.append(", internal=").append(internal);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtUpdate=").append(gmtUpdate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
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
        KtfApplication other = (KtfApplication) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getHost() == null ? other.getHost() == null : this.getHost().equals(other.getHost()))
            && (this.getPort() == null ? other.getPort() == null : this.getPort().equals(other.getPort()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getAuthKey() == null ? other.getAuthKey() == null : this.getAuthKey().equals(other.getAuthKey()))
            && (this.getBizCode() == null ? other.getBizCode() == null : this.getBizCode().equals(other.getBizCode()))
            && (this.getSlotId() == null ? other.getSlotId() == null : this.getSlotId().equals(other.getSlotId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getInternal() == null ? other.getInternal() == null : this.getInternal().equals(other.getInternal()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtUpdate() == null ? other.getGmtUpdate() == null : this.getGmtUpdate().equals(other.getGmtUpdate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getHost() == null) ? 0 : getHost().hashCode());
        result = prime * result + ((getPort() == null) ? 0 : getPort().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getAuthKey() == null) ? 0 : getAuthKey().hashCode());
        result = prime * result + ((getBizCode() == null) ? 0 : getBizCode().hashCode());
        result = prime * result + ((getSlotId() == null) ? 0 : getSlotId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getInternal() == null) ? 0 : getInternal().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtUpdate() == null) ? 0 : getGmtUpdate().hashCode());
        return result;
    }
}