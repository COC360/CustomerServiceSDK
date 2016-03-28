package com.echat.sdk.test;

/**
 * Created by qjl on 2016/3/16.
 */
public class VisitorChannel {
    public static final String service_visitor_event = "/service/visitor/event";
    public static final String visitor_SelfMsg = "/visitor/selfmsg";
    public static final String visitorChat = "/visitor/chat";

    public static String getVisitorChatChannel(long companyId, String visitorId) {
        return String.format("%s/%d/%s", visitorChat, companyId, visitorId);
    }

    /**
     * @param companyId
     * @return 如果公司状态变更了通过此通配频道发送给此公司所有的访客
     */
    public static String getVisitorChatCompanyChannel(long companyId) {
        return String.format("%s/%d", visitorChat, companyId);
    }
}
