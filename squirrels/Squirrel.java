package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;

public class Squirrel extends Sprite{
    public Picture basePic;
    SpriteComponent sc;
    int count = 0;
    
    public void init(SpriteComponent sc) throws IOException{
        basePic = new Picture("s1.png");
        setPicture(basePic);
        setX(700);
        setY(200);
        this.sc = sc;
        sc.addSprite(this);
    };
    
    @Override
    public void preMove(){
        count++;
        if(count == 100)
            setPicture(basePic.rotate(.1));
        else if(count == 200){
            setPicture(basePic.rotate(-.1));
            count = 0;
        }
    }
}
