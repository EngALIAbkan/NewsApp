package com.example.android.newsapp;



public class News {
    private String Title;
    private String Story;
    private String Url;
    private String Date;
    private String NameFirst;
    private String NameLast;

    public News(String title, String story, String url, String date, String nameFirst, String nameLast) {
        this.Title = title;
        this.Story = story;
        this.Url = url;
        this.Date = date;
        this. NameFirst = nameFirst;
        this.NameLast = nameLast;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStory() {
        return Story;
    }

    public void setStory(String story) {
        Story = story;
    }
    public String getUrl() { return Url; }

    public void setUrl(String url) { Url = url; }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNameFirst() {
        return NameFirst;
    }

    public void setNameFirst(String nameFirst) {
        NameFirst = nameFirst;
    }

    public String getNameLast() {
        return NameLast;
    }

    public void setNameLast(String nameLast) {
        NameLast = nameLast;
    }
}


