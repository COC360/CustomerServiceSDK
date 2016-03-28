package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/9/18.
 */
public abstract class VisitorStaffChatMessage extends Message {
    private String talkId;

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }


}
