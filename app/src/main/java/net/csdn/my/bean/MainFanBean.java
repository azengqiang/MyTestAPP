package net.csdn.my.bean;

/**
 * Created by lenovo on 2016/5/19.
 */
public class MainFanBean {
    MainFanBean(){

    }

    public MainFanBean(String imgUrl, String title, String summy) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.summy = summy;
    }

    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummy() {
        return summy;
    }

    public void setSummy(String summy) {
        this.summy = summy;
    }

    private String title;
    private String summy;
}
