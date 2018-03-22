package com.team.mrobot.web.vo;

/**
 * Project: TaaS
 * Author: AndrewLiang
 * Date: 2017/9/23
 * Description: Menu
 */
public class Menu {
    private String name;//name of menu
    private String url;//url of menu

    protected Menu(){
    }

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
