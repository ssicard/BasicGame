package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;

/* This class creates a sprite that hides the heart sprites on the life bar. 
*/
class Invisible extends Sprite{
    public Picture basePic;
    public void init(SpriteComponent sc, int livesLeft) throws IOException{
        basePic = new Picture("invisible.png");
        setPicture(basePic);
        setX(50*livesLeft + 1);
        this.sc = sc;
        sc.addSprite(this);
    }
    SpriteComponent sc;
    
    
    public void hide(int pos) throws IOException{
        Heart_LifeBar h1 = new Heart_LifeBar();
        h1.init(sc,pos);
    }
    
}