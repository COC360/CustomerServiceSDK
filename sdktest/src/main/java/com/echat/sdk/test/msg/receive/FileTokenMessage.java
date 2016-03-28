package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/8/3.
 */
public class FileTokenMessage extends Message {
    private String token = null;
    private int fileType;

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public FileTokenMessage(String token,int fileType) {
        this.setToken(token);
        this.setFileType(fileType);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getMt() {
        return MsgType.Visitor.FILETOKENMSG;
    }
}
