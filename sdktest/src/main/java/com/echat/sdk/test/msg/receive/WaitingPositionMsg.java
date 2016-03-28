package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/8/9.
 * 当前访客的排队消息
 */
public class WaitingPositionMsg extends Message {
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getMt() {
        return MsgType.Visitor.WAITPOSITION_MSG;
    }
}
