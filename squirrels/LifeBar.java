package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;

class LifeBar extends Sprite{
    public Picture basePic;
    SpriteComponent sc;
    boolean update;
    Player temp;
    
    public void init(SpriteComponent sc) throws IOException{
        basePic = new Picture("LifeBar.png");
        setPicture(basePic);
        this.sc = sc;
        sc.addSprite(this);
    };
}