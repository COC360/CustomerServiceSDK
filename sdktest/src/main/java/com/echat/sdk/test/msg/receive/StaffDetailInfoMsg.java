package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/11/24.
 * 对话客服详细信息，用于手机端对话客服头像 电话号码  简介信息发送
 */
public class StaffDetailInfoMsg extends Message {
    private String staffPhone;//客服电话号码 暂时只针对手机端传送此信息
    private String staffHead;//客服头像
    private String staffInfo;//客服介绍信息


    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffHead() {
        return staffHead;
    }

    public void setStaffHead(String staffHead) {
        this.staffHead = staffHead;
    }

    public String getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(String staffInfo) {
        this.staffInfo = staffInfo;
    }
    @Override
    public String getMt() {
        return MsgType.Visitor.CHAT_STAFF_DETAIL;
    }
}
