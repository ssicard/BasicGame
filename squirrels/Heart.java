package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;
import java.util.Random;

public class Heart extends Sprite{
    public Picture basePic;
    SpriteComponent sc;
    Random rand = new Random();
    double VELX = -1;
    int respawnCount = 0;
    boolean offScreen = true;
    boolean canGetHit = true;
    int heartsRespawned = 0;
    int hitlessPeriod = 0;
    
    
    public void init(SpriteComponent sc) throws IOException{
        basePic = new Picture("heart_1.png");
        setPicture(basePic);
        setX(700);
        setY(rand.nextInt(450)+75);
        setVelX(VELX);
        this.sc = sc;
        sc.addSprite(this);
    };
    
    @Override
    public void preMove() {
        if(offScreen = true){
            respawnCount++;
        }
        if(heartsRespawned < 5){
            if(respawnCount==1000){
                offScreen=false;
                respawn();
                respawnCount=0;
            }
        }
        if(canGetHit==false){
            hitlessPeriod++;
            if(hitlessPeriod == 300){
                canGetHit = true;
                hitlessPeriod = 0;
            }
        }
    }
    
    public void respawn(){
        setY(rand.nextInt(525)+75);
        setX(rand.nextInt(200) + 800);
        heartsRespawned++;
    }
    
    @Override
    public void processEvent(SpriteCollisionEvent se) {
        if(se.xlo){
            offScreen = true;
            canGetHit = false;
        }
    }
}
