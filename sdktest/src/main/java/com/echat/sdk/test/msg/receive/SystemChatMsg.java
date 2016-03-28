package com.echat.sdk.test.msg.receive;



/**
 * Created by qjl on 2015/8/9.
 * 系统对话提示语
 */
public class SystemChatMsg extends VisitorStaffChatMessage {
    private String content;//消息内容
    private int type;//消息类型
    private String eventId;//事件ID，在转留言的时候需要使用
    private long staffId;//发送消息的客服ID
    private String visitorId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    @Override
    public String getMt() {
        return MsgType.Visitor.SYSTEMCHATMSG;
    }
}
