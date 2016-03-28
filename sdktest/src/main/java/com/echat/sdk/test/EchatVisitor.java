package com.echat.sdk.test;

import com.echat.sdk.test.msg.receive.SDKVisitorConfigMsg;
import com.echat.sdk.test.msg.send.RegistChatMsg;
import com.echat.sdk.test.msg.send.RegistSDKVisitorInfoMsg;
import com.echat.sdk.test.msg.send.SendMessage;
import com.echat.sdk.test.util.Constants;
import com.echat.sdk.test.util.StringUtil;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.ClientTransport;
import org.cometd.client.transport.LongPollingTransport;
import org.cometd.websocket.client.JettyWebSocketTransport;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qjl on 2016/3/16.
 * 测试java api接入对话
 */
public class EchatVisitor {
    //    private static final String appToken = "AesEG3R";
    //访客请求对话的uri
    private static final String visitorCometdURI = "/mychat/visitor";
    //接入对话的服务器地址
    private String serverUrl;
    //应用bayeux协议的客户端，通过此客户端与服务器进行通信
    private BayeuxClient client;
    private String appToken;
    private RegistSDKVisitorInfoMsg registSDKVisitorInfoMsg;
    private EchatMessageListener messageListener;
    private SDKVisitorConfigMsg configMsg;
    private WebSocketClient webSocketClient = null;
    /**
     * @param serverUrl 需要接入对话的服务器地址
     * @param appToken  分配给此APP使用的token 此token用于标示所对应的的企业账号
     *                  初始化一个echat 访客
     */
    public EchatVisitor(String serverUrl, String appToken) {
        if (StringUtil.isEmpty(serverUrl) || StringUtil.isEmpty(appToken)) {
            throw new RuntimeException("init visitor must with serverurl and apptoken");
        }
        this.appToken = appToken;
        this.serverUrl = serverUrl;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public void initReqestInfo(RegistSDKVisitorInfoMsg registSDKVisitorInfoMsg) {
        this.registSDKVisitorInfoMsg = registSDKVisitorInfoMsg;
    }

    /**
     * 开始对话流程
     */
    public void startToChat() throws Exception {
        if (registSDKVisitorInfoMsg == null) {
            throw new RuntimeException("registSDKVisitorInfoMsg must be inited befor start chat ");
        }
        initBayeuxClient();
        messageListener = new EchatMessageListener(this, new Object());
        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("data",registSDKVisitorInfoMsg);
        client.handshake(datas, messageListener);
    }

    public SDKVisitorConfigMsg getConfigMsg() {
        return configMsg;
    }

    public void setConfigMsg(SDKVisitorConfigMsg configMsg) {
        this.configMsg = configMsg;
    }

    /**
     * 初始化连接客户端
     */
    public void initBayeuxClient() throws Exception {
        if (client == null) {
            webSocketClient = new WebSocketClient();
            webSocketClient.start();
            HttpClient httpClient = new HttpClient();
            ClientTransport wsTransport = new JettyWebSocketTransport(null, null,
                    webSocketClient);
            ClientTransport longPollingTransport = new LongPollingTransport(null,
                    httpClient);
            client = new BayeuxClient(serverUrl + visitorCometdURI,
                    wsTransport, longPollingTransport);
        }
    }

    public void disconnect() {
        if (client != null && client.isHandshook()) {
            client.disconnect();
        }
        if (webSocketClient != null && webSocketClient.isRunning()) {
            webSocketClient.destroy();;
        }
    }

    /**
     * 订阅相关的频道，待所有频道都订阅成功后在注册对话
     */
    public void subscribeMsgChannel() {
        client.startBatch();
        client.getChannel(VisitorChannel.visitor_SelfMsg).subscribe(messageListener,messageListener);
        client.getChannel(VisitorChannel.getVisitorChatCompanyChannel(configMsg.getCompanyId())).subscribe(messageListener,messageListener);
        client.getChannel(VisitorChannel.getVisitorChatChannel(configMsg.getCompanyId(), configMsg.getVisitorId())).subscribe(messageListener,messageListener);
        client.endBatch();
    }

    /**
     * 向服务器push注册对话事件
     */
    public void registChatAction() {
        RegistChatMsg registChatMsg = new RegistChatMsg();
        registChatMsg.setChatFlag(registSDKVisitorInfoMsg.getChatFlag());
        registChatMsg.setMedia(Constants.ClientMedia.android);
        registChatMsg.setOsName("android");
        registChatMsg.setScreenResolution("1356,1120");
        client.getChannel(VisitorChannel.service_visitor_event).publish(registChatMsg,messageListener);
    }

    /**
     * @param sendMessage
     * 向服务器发送消息
     */
    public void sendMsg(SendMessage sendMessage) {
        if (sendMessage != null) {
            client.getChannel(VisitorChannel.service_visitor_event).publish(sendMessage, messageListener);
        }
    }

    public static void main(String[] strings) throws Exception {
        EchatVisitor echatVisitor = new EchatVisitor("http://hospital.feelchat.net", "ABdeE3R");
        RegistSDKVisitorInfoMsg registSDKVisitorInfoMsg1 = new RegistSDKVisitorInfoMsg();
        registSDKVisitorInfoMsg1.setChatFlag("test by qjl");
        registSDKVisitorInfoMsg1.setToken(echatVisitor.getAppToken());
        echatVisitor.initReqestInfo(registSDKVisitorInfoMsg1);
        echatVisitor.startToChat();
    }

}
