package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/6/15.
 * 文本消息
 */
public class TextForVisitorMsg extends VisitorStaffChatMessage {
    private long staffId;//发送消息的客服ID
    private String visitorId;//接收消息的访客ID
    private String staffNickName;//如果是插入的对话，次参数不为空
    private String content;//发送的消息内容

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getStaffNickName() {
        return staffNickName;
    }

    public void setStaffNickName(String staffNickName) {
        this.staffNickName = staffNickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getMt() {
        return MsgType.Visitor.CHAT_MSG_TEXT;
    }
}
