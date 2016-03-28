package com.echat.sdk.test.msg.send;

/**
 * Created by xiaoyu on 2016/3/17.
 */
public class EndChatMsg extends  SendMessage {
    @Override
    public String getEt() {
        return EventType.endChat;
    }
}
