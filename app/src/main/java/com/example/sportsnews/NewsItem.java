package com.example.sportsnews;

public class NewsItem {
    //Create the class for a news item
    //Create fields for an id, title, image, description, category, and whether or not the article is bookmarked or not.
    private int id;
    private String title;
    private int image;
    private String descrpition;
    private String category;
    public boolean bookmarked;
    public NewsItem(int id, String title, int image, String descrpition, String category, boolean bookmarked)
    {
        this.id = id;
        this.title = title;
        this.image = image;
        this.descrpition = descrpition;
        this.category = category;
        this.bookmarked = bookmarked;
    }

    //Methods for getting data about the news article
    public void ToggleBookMarked()
    {
        bookmarked = !bookmarked;
    }
    public boolean GetBookmarked()
    {
        return bookmarked;
    }
    public String GetTitle()
    {
        return title;
    }
    public int GetImage()
    {
        return image;
    }
    public String GetDescription()
    {
        return descrpition;
    }
    public String GetCategory()
    {
        return category;
    }
    public int GetId()
    {
        return id;
    }

}
