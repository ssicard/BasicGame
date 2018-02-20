package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;
import java.util.Random;

public class Background extends Sprite{
    public Picture basePic;
    SpriteComponent sc;
    
    public void init(SpriteComponent sc, String scene) throws IOException{
        if(scene.equals("main"))
            basePic = new Picture("TitleScreen.png");
        else if(scene.equals("park"))
            basePic = new Picture("bk_grass.png");
        else if(scene.equals("street"))
            basePic = new Picture("bk_street.png");
        else if(scene.equals("i1"))
            basePic = new Picture("instruct_1.png");
        else if(scene.equals("congratsmale"))
            basePic = new Picture("congrats_screen_b.png");
        else if(scene.equals("congratsfemale"))
            basePic = new Picture("congrats_screen_g.png");
        else if(scene.equals("congratsdragon"))
            basePic = new Picture("congrats_screen_d.png");
        setPicture(basePic);
        this.sc = sc;
        sc.addSprite(this);
    };
    
    public void changePic(int instructNum)throws IOException{
        if(instructNum == 2)
            setPicture(new Picture("instruct_2.png"));
        else if(instructNum ==3)
            setPicture(new Picture("instruct_3.png"));
        else if(instructNum == 4)
            setPicture(new Picture("instruct_4.png"));
        else if(instructNum == 5)
            setPicture(new Picture("instruct_5.png"));
    }
}
