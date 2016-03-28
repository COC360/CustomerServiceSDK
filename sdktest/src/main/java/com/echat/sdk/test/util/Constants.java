package com.echat.sdk.test.util;

/**
 * Created by xiaoyu on 2016/3/16.
 */
public class Constants {
    /**
     * 请求对话的API渠道
     */
    public interface ClientFrom {
        public static final int WEB = 1;
        public static final int SDK = 2;
    }

    /**
     * SDK注册的访客身份是否是会员，如果是会员那么使用其自定义的ID
     */
    public static interface VisitorVIPType {
        public static final int VIP = 1;//会员
        public static final int NOVIP = 0;//非会员
    }

    /**
     * 系统消息类型应用在647消息中
     */
    public static interface SystemMsgTypeForVisitor {
        public static final int WAITMSG = 1;
        public static final int WELCOMEMSG = 2;
        public static final int CHATENDMSG = 3;
        public static final int TIMEOUTMSG = 4;
        public static final int AUTOREPLY = 5;
    }

    /*
   对话结束原因
    */
    public static interface ChatCloseReson {
        public static final byte unknow = 0;//未知
        public static final byte chaterClose = 1;//访客结束对话
        public static final byte staffClose = 2;//客服结束对话
        public static final byte chaterTimeout = 3;//访客超时
        //        public static final byte staffTimeout = 4;//客服超时
        public static final byte staffLogout = 5;//客服退出
        public static final byte systemClose = 6;//系统关闭
        public static final byte transferClose = 7;//转接结束
        public static final byte chaterUnsendTimeOut = 8;//访客长时间未回复
    }

    public static interface CompanyStatus {
        public static final int ONLINE = 1;//表示有客服在，可以接对话
        public static final int OFFLINE = 2;
    }

    /*
    客户端的媒介，客服访客通用
    */
    public interface ClientMedia {
        public static final byte PC = 1;//pc网页
        public static final byte phone = 2;//手机网页
        public static final byte iphone = 3;//iphone sdk
        public static final byte android = 4;//android sdk
        public static final byte wechat = 5;//微信
    }
}


