package com.abir.lazaar.ocrssactivity.model;

import java.util.Date;

/**
 * Created by SP-AbirLazaar on 22/12/2017.
 */

public class News implements Comparable<News>{

    public String description;
    public String title;
    public String link;
    public Date date;

    public News(String description, String title, String link, Date date) {
        this.description = description;
        this.title = title;
        this.link = link;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(News news) {
        return date.compareTo(news.date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
