package squirrels;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Obstacle extends Sprite{
    public Picture basePic;
    SpriteComponent sc;
    boolean update;
    Random rand = new Random();
    double[] velX_b = {-.55, -.65, -.75, -.85, -.95};
    double[] velX_g = {-1, -1.1, -1.2, -1.3, -1.4, -1.5};
    double[] velX_d ={-1.5, -1.6, -1.7};
    ArrayList<Picture> obs = new ArrayList<>();
    
    public void init(SpriteComponent sc, Player main, String scene) throws IOException{
        basePic = new Picture("trashcan.png");
        setPicture(basePic);
        setX(700);
        setY(rand.nextInt(525)+75);
        if(main.getDifMode().equals("boy"))
            setVelX(velX_b[rand.nextInt(5)]);
        else if(main.getDifMode().equals("girl"))
            setVelX(velX_g[rand.nextInt(6)]);
        else
            setVelX(velX_d[rand.nextInt(3)]);
        if(scene.equals("park")){
            obs.add(new Picture("tree_1.png"));
            obs.add(new Picture("trashcan.png"));
            obs.add(new Picture("tree_2.png"));
            obs.add(new Picture("flower.png"));
            obs.add(new Picture("bush.png"));
            obs.add(new Picture("log.png"));
            obs.add(new Picture("rock.png"));
        }else if(scene.equals("street")){
            obs.add(new Picture("car.png"));
            obs.add(new Picture("bike.png"));
            obs.add(new Picture("bike2.png"));
            obs.add(new Picture("motorcycle.png"));
        }
        this.sc = sc;
        sc.addSprite(this);
    };
    
    public void respawn(){
        int index = rand.nextInt(obs.size());
        setPicture(obs.get(index));
            setY(rand.nextInt(475)+75);
            setX(rand.nextInt(200) + 800);
    }
    
    public void setSpeed(double velX){
        setVelX(velX);
    }
    
    public double getSpeed(){
        return getVelX();
    }
    
    @Override
    public void processEvent(SpriteCollisionEvent se) {
        if(se.xlo){
            respawn();
        }else if(se.sprite2 instanceof Obstacle){
            if(getY()>600)
                setY(rand.nextInt(475)+75);
        }
    }
}