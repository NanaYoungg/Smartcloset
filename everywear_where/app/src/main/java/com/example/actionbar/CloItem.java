package com.example.actionbar;

public class CloItem {


    //내옷목록
    private String kind;
  //  private String color;
    private String image;
    private String quality;

    public CloItem() {
    }

    public CloItem(String kind, String quality,String image) {
        this.kind =kind;
      //  this.color = color;
        this.quality = quality;
        this.image = image;
    }
    public String getKind(){return kind;}
    public void setKind(String kind) {
        this.kind = kind;
    }

  /*  public String getColor(){return color;}
    public void setColor(String color) {
        this.color=color;
    }*/

    public String getQuality(){return quality;}
    public void setQuality(String quality) {
        this.quality= quality;
    }


    public String getImage(){return image;}
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BookReplyItem{" +
                ", kind='" + kind + '\''+
               // ", color='" + color+ '\'' +
                ", quality='" + quality + '\'' +
                ",image='"+image + '\'' +
                '}';
    }
}