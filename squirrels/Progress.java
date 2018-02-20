package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;

public class Progress extends Sprite{
    public Picture basePic;
    SpriteComponent sc;
    int blankCount = 0;
    int progNum;
    
    public void init(SpriteComponent sc, int progressNum) throws IOException{
        progNum = progressNum;
        basePic = new Picture("progress_1.png");
        setPicture(basePic);
        setX(441+75*(progressNum-1));
        setY(2);
        this.sc = sc;
        sc.addSprite(this);
    }
    
    public void hide() throws IOException{
        if(progNum > 0){
            Blank b1 = new Blank();
            b1.init(sc,1);
        }
        if(progNum > 1){
            Blank b2 = new Blank();
            b2.init(sc, 2);
        }
        if(progNum > 2){
            Blank b3 = new Blank();
            b3.init(sc, 3);
        }
        if(progNum > 3){
            Blank b4 = new Blank();
            b4.init(sc, 4);
        }
        if(progNum > 4){
            Blank b5 = new Blank();
            b5.init(sc, 5);
        }
    }
}