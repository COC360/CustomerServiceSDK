package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/6/15.
 */
public class FileForVisitorMsg extends VisitorStaffChatMessage {
    private long staffId;//发送消息的客服ID
    private String staffNickName;//如果是插入的对话，次参数不为空
    private String fileUrl;//文件的路径
    private String fileName;//文件名称
    private String visitorId;//接收消息的访客ID
    private long fileSize;//文件大小byte
    private String fileIdentity = null;//文件身份标识：即上传的token

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileIdentity() {
        return fileIdentity;
    }

    public void setFileIdentity(String fileIdentity) {
        this.fileIdentity = fileIdentity;
    }

    @Override
    public String getMt() {
        return MsgType.Visitor.CHAT_MSG_FILE;
    }
}
