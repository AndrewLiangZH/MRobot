package com.team.mrobot.web.vo;

/**
 * Project: TaaS
 * Author: AndrewLiang
 * Date: 2017/9/24
 * Description:
 */
public class TClient {

    private static final String appKey = "69385dcff8debe1ffda23caf";
    private static final String masterSecret = "3b555f4e1cd77c51e212ccfa";
    private String operating;

    protected TClient(){
    }

    public TClient(String operating) {
        this.operating = operating;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public String getOperating() {
        return operating;
    }

    public void setOperating(String operating) {
        this.operating = operating;
    }
}
