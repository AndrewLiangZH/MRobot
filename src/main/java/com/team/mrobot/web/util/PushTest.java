package com.team.mrobot.web.util;

/**
 * Project: TaaS-test
 * Author: AndrewLiang
 * Date: 2017/9/28
 * Description:
 */

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

/**
 * Created by liudengchao on 17/9/20.
 */
public class PushTest {
    protected final static Logger LOG = LoggerFactory.getLogger(PushTest.class);
    protected static final String APP_KEY = "69385dcff8debe1ffda23caf";
    protected static final String MASTER_SECRET = "3b555f4e1cd77c51e212ccfa";
    //    public static final String TITLE = "Test from API example";
    public static final String ALERT = "Test from API Example - alert";


    public static JPushClient jpushClient = null;

    public static void testSendPush(String msg,String url,String name,String content) throws APIConnectionException, APIRequestException {
        jpushClient = new JPushClient(MASTER_SECRET,APP_KEY,null, ClientConfig.getInstance());
        PushPayload payload=buildsend_Custom_Message(msg,url,name,content);
//        jpushClient.sendMessageAll(operating);
//        Message message = Message.newBuilder().addExtra(key, value).build();
//        PushPayload payload = buildsend_Custom_Message_WithTag(message);
        try {
            System.out.println(payload.toString());
            PushResult result = jpushClient.sendPush(payload);
//            PushResult result =  jpushClient.sendMessageAll(operating);

            System.out.println(result+"................................");

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
    public static PushPayload buildPushObject_android_tag_alertWithTitle(String alert ,String title) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(alert, title, null))
                .build();
    }

    public static PushPayload buildsend_Custom_Message(String msg_content,String url,String name,String content){
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent(msg_content)
                        .addExtra("url",url)
                        .addExtra("name",name)
                        .addExtra("label_content",content)
                        .build())
                .build();

    }

}

