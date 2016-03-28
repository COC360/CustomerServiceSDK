package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/6/15.
 * 客服正在输入消息
 */
public class StaffInputingMsg extends Message {
    private long staffId;//发送消息的客服ID
    private String staffNickName;//如果是插入的对话，次参数不为空

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

    @Override
    public String getMt() {
        return MsgType.Visitor.CHAT_MSG_INPUTING;
    }
}
