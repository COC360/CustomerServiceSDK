package com.echat.sdk.test.msg.send;

import com.echat.sdk.test.util.Constants;

/**
 * Created by qjl on 2016/3/16.
 * sdk注册访客信息的消息，此消息会在订阅self频道的时候提交给服务器做验证，验证通过后会收到
 * 11001消息
 */
public class RegistSDKVisitorInfoMsg extends SendMessage {
    private int from = Constants.ClientFrom.SDK;
    private String token;
    private String vId;
    private int vType;
    private String chatFlag;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getChatFlag() {
        return chatFlag;
    }

    public void setChatFlag(String chatFlag) {
        this.chatFlag = chatFlag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId;
    }

    public int getvType() {
        return vType;
    }

    public void setvType(int vType) {
        this.vType = vType;
    }


    @Override
    public String getEt() {
        return null;
    }
}
