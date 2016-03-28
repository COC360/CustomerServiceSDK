package com.echat.sdk.test.msg.send;

/**
 * Created by qjl on 2016/3/17.
 */
public class SendInputingMsg extends SendMessage {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getEt() {
        return EventType.typing;
    }
}
