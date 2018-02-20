package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;

class ProgressBar extends Sprite{
    public Picture basePic;
    public void init(SpriteComponent sc) throws IOException{
        basePic = new Picture("ProgressBar_3.png");
        setPicture(basePic);
        setX(438);
        this.sc = sc;
        sc.addSprite(this);
    };
    SpriteComponent sc;
}