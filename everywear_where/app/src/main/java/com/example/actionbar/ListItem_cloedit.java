package com.example.actionbar;



//LanCare  세탁물관리 리스트 아이템값


public class ListItem_cloedit {


    private String kind;
    private String image;
    private String quality;

    public ListItem_cloedit() {
    }

    public ListItem_cloedit(String kind, String color, String quality, String image) {
        this.kind = kind;
        this.quality = quality;
        this.image = image;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }


    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BookReplyItem{" +
                ", kind='" + kind + '\'' +
                ", quality='" + quality + '\'' +
                ",image='" + image + '\'' +
                '}';
    }
}