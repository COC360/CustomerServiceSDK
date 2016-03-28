package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/6/15.
 *公司状态变更
 * 根据公司状态及时处理邀请窗口和图标的状态
 */
public class CompanyStatusMsg extends Message{

    /**
     * 当前公司状态
     1：公司在线
     2：公司不在线
     */
    private int status;

    private String visitorId;//注册对话或者访客的时候会返回次字段来定于频道

    private byte chatStatus;//对话状态 0,没有对话 1：请求对话中 2：对话进行中

    public byte getChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(byte chatStatus) {
        this.chatStatus = chatStatus;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMt() {
        return MsgType.Visitor.COMPANY_STATUS;
    }
}
