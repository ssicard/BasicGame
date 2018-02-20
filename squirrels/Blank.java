package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;

/* This class creates a sprite that hides any sprites on the progress bar to show it blank.
*/
public class Blank extends Sprite{
    public Picture basePic;
    SpriteComponent sc;
    
    public void init(SpriteComponent sc, int position) throws IOException{
        basePic = new Picture("blank.png");
        setPicture(basePic);
        setX(441+(position-1)*75);
        setY(2);
        this.sc = sc;
        sc.addSprite(this);
    }
}
