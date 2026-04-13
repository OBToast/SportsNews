package com.example.sportsnews;

public class NewsItem {
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
