package lunarlander;
import basicgraphics.Sprite;
import basicgraphics.BasicFrame;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Sarah Sicard
 */
class Lander extends Sprite{
    public Picture basePic;
    
    public void init(SpriteComponent sc) throws IOException{
        basePic = new Picture("landeranim.gif");
        setPicture(basePic);
        setX(10);
        setY(10);
        setVelX(0);
        setVelY(0.002);
        this.sc = sc;
        sc.addSprite(this);
    };
    SpriteComponent sc;
    boolean update;
    
    @Override
    public void preMove() {
        
            setVelY(getVelY() + .002);
        
    }
    
    @Override
    public void processEvent(SpriteCollisionEvent se) {
        if (se.xlo) {
            setX(sc.getSize().width-getWidth());
        }
        if (se.xhi) {
            setX(0);
        }
        if (se.ylo) {
           setActive(false);
           JOptionPane.showMessageDialog(sc, "You lose! Game Over!");
           System.exit(0);
        }
        if (se.yhi) {
            setActive(false);
            JOptionPane.showMessageDialog(sc, "You went off screen! Game Over!");
            System.exit(0);
        }
        if(se.sprite2 instanceof LandingPad){
            if((Math.abs(getVelX()) > .1)||(Math.abs(getVelY())>.4)){
                setActive(false);
                String format = java.lang.String.format("You crashed! Try again! %nX vel: %.4f %nY vel: %.4f", getVelX(), getVelY());
                JOptionPane.showMessageDialog(sc, format);
                System.exit(0);
            }else{
                setActive(false);
                String format = java.lang.String.format("Good Job,you landed safely! %nX vel: %.4f %nY vel: %.4f", getVelX(), getVelY());
                JOptionPane.showMessageDialog(sc, format);
                System.exit(0);
            }
        }
    }
}

class LandingPad extends Sprite{
    public Picture basePic;
    
    public void init(SpriteComponent sc) throws IOException{
        basePic = new Picture("landingpad.png");
        setPicture(basePic);
        setX(350);
        setY(380);
        this.sc = sc;
        sc.addSprite(this);
    }
    SpriteComponent sc;
    boolean update;
}

public class LunarLander {
    public static void main(String[] args) throws IOException{
        SpriteComponent sc = new SpriteComponent();
        sc.setPreferredSize(new Dimension(800,400));
        BasicFrame bf = new BasicFrame("Lunar Lander");
        bf.add("Lunar Lander", sc, 0, 0, 1, 1);
        bf.show();
        
        Lander rocket = new Lander();
         LandingPad pad = new LandingPad();
        pad.init(sc);
        
        bf.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent key) {
                if(key.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rocket.setVelX(rocket.getVelX() + .02);
                    rocket.update = true;
                } else if(key.getKeyCode() == KeyEvent.VK_LEFT) {
                    rocket.setVelX(rocket.getVelX() - .02);
                    rocket.update = true;
                } else if(key.getKeyCode()== KeyEvent.VK_UP){
                    rocket.setVelY(rocket.getVelY() - .04);
                    rocket.update = true;
                }
            }
        });
        
        rocket.init(sc);
        sc.start(0,10);
    }
}
