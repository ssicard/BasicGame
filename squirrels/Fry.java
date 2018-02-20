package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fry extends Sprite{
    public Picture basePic;
    SpriteComponent sc;
    boolean update;
    Random rand = new Random();
    double VELX = -1;
    
    public void init(SpriteComponent sc) throws IOException{
        basePic = new Picture("pup.png");
        setPicture(basePic);
        setX(700);
        setY(rand.nextInt(450)+75);
        setVelX(VELX);
        this.sc = sc;
        sc.addSprite(this);
    };
    
    @Override
    public void processEvent(SpriteCollisionEvent se) {
        if(se.xlo){
            setY(rand.nextInt(450)+75);
            setX(800);
        }
    }
}