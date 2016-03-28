package com.echat.sdk.test.msg.send;


import com.yichat.util.JsonUtil;
import org.eclipse.jetty.util.ajax.JSON;

import java.io.IOException;

/**
 * Created by xiaoyu on 2015/6/11.
 */
public abstract class SendMessage implements JSON.Generator {
    private String et = null;//此消息的消息类型
    private Long tm = 0L;//此消息饿时间戳

    public SendMessage() {
        tm = System.currentTimeMillis();
    }

    public Long getTm() {
        return tm;
    }

    public void setTm(Long tm) {
        this.tm = tm;
    }

    public abstract String getEt();

    public void setEt(String et) {
        this.et = et;
    }

    @Override
    public void addJSON(Appendable appendable) {
        try {
            appendable.append(JsonUtil.toJSON(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toJson() {
        return JsonUtil.toJSON(this);
    }
}
