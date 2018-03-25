package com.team.mrobot.web.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.team.mrobot.web.vo.TClient;

public class PushClientUtil {
    protected final static Logger LOG = LoggerFactory.getLogger(PushClientUtil.class);
    public static final String ALERT = "Test from API Example - alert";


    public static JPushClient jpushClient = null;

    public static void testSendPush(TClient tClient) throws APIConnectionException, APIRequestException {

        jpushClient = new JPushClient(tClient.getMasterSecret(), tClient.getAppKey(), null, ClientConfig.getInstance());
        jpushClient.sendMessageAll(tClient.getOperating());

        try {
            PushResult result = jpushClient.sendMessageAll(tClient.getOperating());
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }

    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle(String alert, String title) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(alert, title, null))
                .build();
    }

    public static PushPayload buildsend_Custom_Message_WithTag(Message message) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setMessage(message)
                .build();

    }

}


