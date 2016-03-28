package com.echat.sdk.test;

import com.echat.sdk.test.msg.receive.*;
import com.echat.sdk.test.msg.send.SendInputingMsg;
import com.echat.sdk.test.msg.send.SendTextMsg;
import com.echat.sdk.test.util.Constants;
import com.echat.sdk.test.util.StringUtil;
import com.yichat.util.JsonUtil;
import org.cometd.bayeux.Channel;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;

/**
 * Created by qjl on 2016/3/16.
 * 访客消息处理类
 */
public class EchatMessageListener implements ClientSessionChannel.MessageListener {
    private EchatVisitor echatVisitor;//echat访客实例
    private Object appActivity;//app的某个界面对象
    private int channelSubStatus = 0;//订阅频道的状态，所有频道都订阅成功后进行对话的注册
    private int subChannelSuccess = 3;
    private StartChatMsg startChatMsg;
    private StaffDetailInfoMsg staffDetailInfoMsg;
    public EchatMessageListener(EchatVisitor echatVisitor, Object appActivity) {
        this.echatVisitor = echatVisitor;
        this.appActivity = appActivity;
    }

    @Override
    public void onMessage(ClientSessionChannel channel, Message message) {
        if (channel.getChannelId().toString()
                .equals(Channel.META_HANDSHAKE)) {
            if (message.isSuccessful()) {
                handleHandShakeSuccess(message);
            } else {
                echatVisitor.disconnect();
                System.out.println(">>>>>>>>>>>与服务器握手失败，重试或者报错");
            }
        } else if (channel.getChannelId().toString().equals(Channel.META_SUBSCRIBE)) {
            handleSubscribeAction(message);
        } else if (!channel.getChannelId().isMeta()) {
            handleChatMessage(message);
        }
    }

    /**
     * @param message 处理对话消息
     */
    private void handleChatMessage(Message message) {
        if (message.getDataAsMap() != null) {
            String messageType = (String) message.getDataAsMap().get("mt");
            if (StringUtil.isNotEmpty(messageType)) {
                if (messageType.equals(MsgType.Visitor.CHAT_START)) {
                    handleChatStartMsg((StartChatMsg) JsonUtil.toBean(message.getDataAsMap(), StartChatMsg.class));
                } else if (messageType.equals(MsgType.Visitor.CHAT_END)) {
                    handleChatEndMsg((EndChatMsgForVisitor) JsonUtil.toBean(message.getDataAsMap(), EndChatMsgForVisitor.class));
                } else if (messageType.equals(MsgType.Visitor.CHAT_MSG_TEXT)) {
                    handleTextMsg((TextForVisitorMsg) JsonUtil.toBean(message.getDataAsMap(), TextForVisitorMsg.class));
                } else if (messageType.equals(MsgType.Visitor.CHAT_MSG_FILE)) {
                    handleFileMsg((FileForVisitorMsg) JsonUtil.toBean(message.getDataAsMap(), FileForVisitorMsg.class));
                } else if (messageType.equals(MsgType.Visitor.CHAT_MSG_IMAGE)) {
                    handleImageMsg((ImageForVisitorMsg) JsonUtil.toBean(message.getDataAsMap(), ImageForVisitorMsg.class));
                } else if (messageType.equals(MsgType.Visitor.CHAT_MSG_INPUTING)) {
                    handleInputingMsg((StaffInputingMsg) JsonUtil.toBean(message.getDataAsMap(), StaffInputingMsg.class));
                } else if (messageType.equals(MsgType.Visitor.SYSTEMCHATMSG)) {
                    handleSystemChatMsg((SystemChatMsg) JsonUtil.toBean(message.getDataAsMap(), SystemChatMsg.class));
                } else if (messageType.equals(MsgType.Visitor.WAITPOSITION_MSG)) {
                    handleWaitingPositionMsg((WaitingPositionMsg) JsonUtil.toBean(message.getDataAsMap(), WaitingPositionMsg.class));
                }else if (messageType.equals(MsgType.Visitor.COMPANY_STATUS)) {
                    handleCompanyStatusMsg((CompanyStatusMsg) JsonUtil.toBean(message.getDataAsMap(), CompanyStatusMsg.class));
                }else if (messageType.equals(MsgType.Visitor.CHAT_STAFF_DETAIL)) {
                    handleStaffDetailMsg((StaffDetailInfoMsg) JsonUtil.toBean(message.getDataAsMap(), StaffDetailInfoMsg.class));
                } else {
                    System.out.println("***********未处理消息：" + message.getJSON() + "**********");
                }
            }
        }
    }

    /**
     * @param staffDetailInfoMsg
     * 处理对话客服详细信息消息
     */
    private void handleStaffDetailMsg(StaffDetailInfoMsg staffDetailInfoMsg) {
        this.staffDetailInfoMsg = staffDetailInfoMsg;
    }

    /**
     * @param companyStatusMsg
     * 公司状态消息
     */
    private void handleCompanyStatusMsg(CompanyStatusMsg companyStatusMsg) {
        if (companyStatusMsg.getStatus() == Constants.CompanyStatus.OFFLINE) {
            if (startChatMsg != null) {
                System.out.println("系统：对话结束!");
            }else {
                System.out.print("客服不在线进入留言!");
            }
        }
    }

    /**
     * @param textForVisitorMsg 处理文本消息
     */
    private void handleTextMsg(TextForVisitorMsg textForVisitorMsg) {
        System.out.println(startChatMsg.getStaffNickName() + ":" + textForVisitorMsg.getContent());
        SendTextMsg sendTextMsg = new SendTextMsg();
        sendTextMsg.setContent("收到您发来的消息：" + textForVisitorMsg.getContent());
        echatVisitor.sendMsg(sendTextMsg);

    }

    /**
     * @param waitingPositionMsg 处理排队位置变化消息
     */
    private void handleWaitingPositionMsg(WaitingPositionMsg waitingPositionMsg) {
        System.out.println("系统;当前等待队列位置为->" + waitingPositionMsg.getPosition());
    }

    /**
     * @param systemChatMsg 处理系统提示语消息
     */
    private void handleSystemChatMsg(SystemChatMsg systemChatMsg) {
        int type = systemChatMsg.getType();
        if (type == Constants.SystemMsgTypeForVisitor.WAITMSG) {
            System.out.println("系统:客服正忙-->" + systemChatMsg.getContent());
        } else if (type == Constants.SystemMsgTypeForVisitor.WELCOMEMSG) {
            System.out.println("系统:对话接通-->" + systemChatMsg.getContent());
        } else if (type == Constants.SystemMsgTypeForVisitor.CHATENDMSG) {
            System.out.println("系统:对话结束-->" + systemChatMsg.getContent());
        } else if (type == Constants.SystemMsgTypeForVisitor.TIMEOUTMSG) {
            System.out.println("系统:上时间未回复-->" + systemChatMsg.getContent());
        } else if (type == Constants.SystemMsgTypeForVisitor.AUTOREPLY) {
            System.out.println("系统:自动应答-->" + systemChatMsg.getContent());
        }
        SendTextMsg sendTextMsg = new SendTextMsg();
        sendTextMsg.setContent("收到系统消息：" + systemChatMsg.getContent());
        echatVisitor.sendMsg(sendTextMsg);

    }

    /**
     * @param staffInputingMsg 处理正在输入消息
     */
    private void handleInputingMsg(StaffInputingMsg staffInputingMsg) {
        System.out.println("系统:客服正在输入。。。");
        SendInputingMsg inputingMsg = new SendInputingMsg();
        inputingMsg.setContent("客服正在输入消息。。。。");
        echatVisitor.sendMsg(inputingMsg);
    }

    /**
     * @param imageForVisitorMsg 处理图片消息
     */
    private void handleImageMsg(ImageForVisitorMsg imageForVisitorMsg) {
        System.out.println(startChatMsg.getStaffNickName() + ":图片[" + imageForVisitorMsg.getSmallImg() + "]");
        SendTextMsg sendTextMsg = new SendTextMsg();
        sendTextMsg.setContent("收到图片消息：" + imageForVisitorMsg.getSmallImg());
        echatVisitor.sendMsg(sendTextMsg);
    }

    /**
     * @param fileForVisitorMsg 处理接收到的文件消息
     */
    private void handleFileMsg(FileForVisitorMsg fileForVisitorMsg) {
        System.out.println(startChatMsg.getStaffNickName() + ":文件[" + fileForVisitorMsg.getFileName() + "]");
        SendTextMsg sendTextMsg = new SendTextMsg();
        sendTextMsg.setContent("收到文件消息：" + fileForVisitorMsg.getFileName());
        echatVisitor.sendMsg(sendTextMsg);
    }

    /**
     * @param endChatMsgForVisitor 处理对话结束消息
     */
    private void handleChatEndMsg(EndChatMsgForVisitor endChatMsgForVisitor) {
        System.out.println("系统：对话结束!");
        echatVisitor.disconnect();
    }

    /**
     * @param startChatMsg 处理对话开始消息604
     */
    private void handleChatStartMsg(StartChatMsg startChatMsg) {
        this.startChatMsg = startChatMsg;
        System.out.println("系统：对话开始!");
    }

    private void handleSubscribeAction(Message message) {
        if (message.isSuccessful()) {
            channelSubStatus++;
            if (channelSubStatus == subChannelSuccess) {
                echatVisitor.registChatAction();
            }
        } else {
            echatVisitor.disconnect();
            System.out.println(">>>>>>>>>>>订阅频道错误");
        }
    }

    private void handleHandShakeSuccess(Message message) {
        //如果握手成功，则注册self频道来提交注册访客信息
        //获得返回的对话配置消息
        if (message != null && message.getDataAsMap() != null) {
            SDKVisitorConfigMsg configMsg = (SDKVisitorConfigMsg) JsonUtil.toBean(message.getDataAsMap(), SDKVisitorConfigMsg.class);
            if (configMsg != null && configMsg.getCompanyId() > 0 && StringUtil.isNotEmpty(configMsg.getVisitorId())) {
                echatVisitor.setConfigMsg(configMsg);
                if (configMsg.getStatus() == Constants.CompanyStatus.OFFLINE) {
                    //如果没客服在线，则留言
                    echatVisitor.disconnect();
                } else if (configMsg.getStatus() == Constants.CompanyStatus.ONLINE) {
                    //如果有客服在线则订阅频道后注册对话
                    echatVisitor.subscribeMsgChannel();
                } else {
                    //error
                    echatVisitor.disconnect();
                }
            }
        }
    }
}
