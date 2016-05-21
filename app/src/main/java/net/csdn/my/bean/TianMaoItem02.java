package net.csdn.my.bean;

/**
 * Created by lenovo on 2016/5/15.
 */
public class TianMaoItem02 {
    private String title;
    private String content;
    private int imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public TianMaoItem02(String title, String content, int imgUrl) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
    }

    public TianMaoItem02() {
    }

}
