package com.example.myapplication;

public class ScreenItem {

    String Title;
    int Screenimg;

    public ScreenItem(String title, int screenimg) {
        Title = title;
        Screenimg = screenimg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setScreenimg(int screenimg) {
        Screenimg = screenimg;
    }

    public String getTitle() {
        return Title;
    }

    public int getScreenimg() {
        return Screenimg;
    }
}
