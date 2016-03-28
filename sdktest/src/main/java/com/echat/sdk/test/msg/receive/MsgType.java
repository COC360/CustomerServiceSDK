package com.echat.sdk.test.msg.receive;

/**
 * Created by qjl on 2015/6/11.
 * 管理消息的类型 即：消息对象中的mt属性的值
 */
public class MsgType {
    public static interface Staff {

        /*
         * 对话相关
         * 客服收到的消息，在刷新对话消息列表的时候可能会用到
         */
        public static final String CHATMSG_TEXT = "864";//文本对话消息
        public static final String CHATMSG_IMAGE = "865";//图片对话消息
        public static final String CHATMSG_FILE = "866";//文件对话消息
        public static final String VISITOR_EVALUATE = "868";//访客评价消息
        public static final String CHATDETAILLIST = "874";//对话记录消息

    }

    public static interface Visitor {
        public static final String COMPANY_STATUS = "603";//公司状态，增加对话状态
        public static final String CHAT_START = "604";//对话开始
        public static final String CHAT_END = "605";//对话结束
        public static final String CHAT_STAFF_DETAIL = "606";//客服详情消息
        public static final String CHAT_MSG_TEXT = "640";//文本消息
        public static final String CHAT_MSG_IMAGE = "641";//图片消息
        public static final String CHAT_MSG_FILE = "642";//文件消息
        public static final String CHAT_MSG_INPUTING = "644";//客服正在输入
        public static final String HASNEW_CHATMSG = "645";//有新的对话消息
        public static final String SYSTEMCHATMSG = "647";//系统提示语
        public static final String WAITPOSITION_MSG = "648";//排队消息
        public static final String FILETOKENMSG = "651";//文件token 消息
        public static final String ERRORMSG = "652";//相关错误警告类消息
        public static final String SCREENSHOT = "653";//返回截图消息
    }
    /**
     * 系统消息类型
     */
    public static interface System {
        //sdk相关的系统消息11打头
        public static final String SDK_VISITOR_CONFIG = "11001";//SDK访客接收的系统配置消息
    }
}
