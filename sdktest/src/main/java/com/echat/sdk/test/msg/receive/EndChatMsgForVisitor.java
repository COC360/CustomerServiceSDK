package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/6/15.
 * 对话结束
 */
public class EndChatMsgForVisitor extends Message{
    private String content;//对话结束语
    private byte closeReason;
    private int toLeaveWord;//是否转到留言 1:转到留言 0：直接结束对话
    private String talkId;//对话ID

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public int getToLeaveWord() {
        return toLeaveWord;
    }

    public void setToLeaveWord(int toLeaveWord) {
        this.toLeaveWord = toLeaveWord;
    }

    public byte getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(byte closeReason) {
        this.closeReason = closeReason;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getMt() {
        return MsgType.Visitor.CHAT_END;
    }
}
