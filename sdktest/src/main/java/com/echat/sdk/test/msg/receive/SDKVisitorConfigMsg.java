package com.echat.sdk.test.msg.receive;


/**
 * Created by qjl on 2016/3/14.
 */
public class SDKVisitorConfigMsg extends Message {
    private long companyId;//公司ID
    private String visitorId;//明文vid用于订阅频道
    private String encryptVID;//密文vid用于保存在客户端用户下次访问的时候通知服务器
    private int status;//公司状态

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getEncryptVID() {
        return encryptVID;
    }

    public void setEncryptVID(String encryptVID) {
        this.encryptVID = encryptVID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMt() {
        return MsgType.System.SDK_VISITOR_CONFIG;
    }
}
