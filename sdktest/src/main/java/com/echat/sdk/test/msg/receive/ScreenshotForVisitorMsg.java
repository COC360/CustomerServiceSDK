package com.echat.sdk.test.msg.receive;

/**
 * Created by wangyitao on 2015/9/10.
 */
public class ScreenshotForVisitorMsg extends ImageForVisitorMsg {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getMt() {
        return MsgType.Visitor.SCREENSHOT;
    }
}
