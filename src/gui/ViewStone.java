package gui;

import javafx.scene.image.Image;

public class ViewStone{
    public int square_id;
    public int coordX;
    public int coordY;
    public int type;
    public Image image;
    public ViewStone( int square_id, int coordX, int coordY,int type, Image image){
        this.square_id= square_id;
        this.coordX= coordX;
        this.coordY= coordY;
        this.type= type;
        this.image= image;
    }
}