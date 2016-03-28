package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/6/15.
 * 对话开始
 * 对话开始后通知浏览中的访客，单一推送不广播给所有定于此频道的访客，根据userid进行publish
 */
public class StartChatMsg extends Message {
    private long staffId;//接通对话的客服ID
    private String staffNickName;//接受对话的客服昵称
    private String talkId;//当前对话的ID，在对话结束后访客对其进行评价，可以追踪到评价的对话是哪一个


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

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    @Override
    public String getMt() {
        return MsgType.Visitor.CHAT_START;
    }
}
