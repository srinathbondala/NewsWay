package com.example.newsway;

public class Articles {
    private String title;
    private String description;
    private String urlToImage;
    private String url;
    private String content;
    private source source;

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public com.example.newsway.source getSource() {
        return source;
    }

    public void setSource(com.example.newsway.source source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToimg(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Articles(String title, String description, String urlToImage, String url, String content,source source) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.url = url;
        this.content = content;
        this.source=source;
    }
}
