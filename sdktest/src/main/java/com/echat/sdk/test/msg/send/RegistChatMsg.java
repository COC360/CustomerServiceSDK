package com.echat.sdk.test.msg.send;

/**
 * Created by xiaoyu on 2016/3/17.
 */
public class RegistChatMsg extends SendMessage {
    private int media;
    private String screenResolution;
    private String osName;
    private String chatFlag;

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getChatFlag() {
        return chatFlag;
    }

    public void setChatFlag(String chatFlag) {
        this.chatFlag = chatFlag;
    }

    @Override
    public String getEt() {
        return EventType.registChat;
    }
}
