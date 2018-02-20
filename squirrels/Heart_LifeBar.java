package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;

public class Heart_LifeBar extends Sprite{
    public Picture basePic;
    SpriteComponent sc;
    
    public void init(SpriteComponent sc, int position) throws IOException{
        basePic = new Picture("heart_1.png");
        setPicture(basePic);
        if(position==3){
            setX(101);
        } 
        else if(position==2){
            setX(51);
        }
        this.sc = sc;
        sc.addSprite(this);
    }
}
