package com.echat.sdk.test.msg.receive;


import com.yichat.util.JsonUtil;
import org.eclipse.jetty.util.ajax.JSON;

import java.io.IOException;

/**
 * Created by xiaoyu on 2015/6/11.
 */
public abstract class Message implements JSON.Generator {
    private String mt = null;//此消息的消息类型
    private Long tm = 0L;//此消息饿时间戳

    public Message() {
        tm = System.currentTimeMillis();
    }

    public Long getTm() {
        return tm;
    }

    public void setTm(Long tm) {
        this.tm = tm;
    }

    public abstract String getMt();

    public void setMt(String mt) {
        this.mt = mt;
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
