package com.example.lzy.baicizhan.excise.bean;

/**
 * Created by lzy on 2020/3/2.
 */

public class ArticleInfo {
    private String name;
    private String music;
    private String article;

    public void setName(String name) {
        this.name = name;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getName() {

        return name;
    }

    public String getMusic() {
        return music;
    }

    public String getArticle() {
        return article;
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "name='" + name + '\'' +
                ", music='" + music + '\'' +
                ", article='" + article + '\'' +
                '}';
    }
}
