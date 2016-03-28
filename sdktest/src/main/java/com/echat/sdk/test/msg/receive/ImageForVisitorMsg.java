package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/6/15.
 */
public class ImageForVisitorMsg extends VisitorStaffChatMessage {
    private long staffId;//发送消息的客服ID
    private String staffNickName;//如果是插入的对话，次参数不为空
    private String smallImg;//图片的小图路径
    private String bigImg;//图片的大图路径
    private String visitorId;//接收消息的访客ID
    private String fileIdentity;//文件身份标识：即上传的token
    private String fileName;//真实文件名

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

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

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public String getBigImg() {
        return bigImg;
    }

    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    public String getFileIdentity() {
        return fileIdentity;
    }

    public void setFileIdentity(String fileIdentity) {
        this.fileIdentity = fileIdentity;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getMt() {
        return MsgType.Visitor.CHAT_MSG_IMAGE;
    }
}
