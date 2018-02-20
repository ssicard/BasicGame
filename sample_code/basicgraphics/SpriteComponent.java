/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicgraphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author sbrandt
 */
public class SpriteComponent extends JComponent {
    Timer t = null;
    private final List<Sprite> sprites = new ArrayList<>();
    
    public SpriteComponent() {
        setPreferredSize(new Dimension(100, 100));
    }
    public void addSprite(Sprite sp) {
        sprites.add(sp);
    }
    public void removeSprite(Sprite sp) {
        sprites.remove(sp);
    }
    public void start(int delay,int period) {
        if(t != null)
            throw new GuiException("SpriteComponent already started");
        t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        moveSprites();
                    }
                });
            }
        };
        t.schedule(tt, delay, period);
    }
    public void paintBackground(Graphics g) {
        Dimension d = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
    }
    
    public final void paintSprites(Graphics g) {
        for(Sprite sprite : sprites) {
            int xv = (int) (sprite.getX());
            int yv = (int) (sprite.getY());
            g.drawImage(sprite.getPicture().getImage(),xv,yv,null);
        }
    }
    
    Image image;
    Dimension sz;
    
    @Override
    public void paintComponent(Graphics g) {
        Dimension d = getSize();
        if(image == null || sz.width != d.width || sz.height != d.height) {
            image = createImage(d.width,d.height);
            sz = d;
        }
        Graphics gi = image.getGraphics();
        paintBackground(gi);
        paintSprites(gi);
        g.drawImage(image, 0, 0, this);
    }
    /**
     * Simple version of the collision detector. It is
     * O(n^2) where n is the number of sprites. Not ideal
     * if there are lots of sprites.
     */
    public void detectCollisions1() {
        int n = sprites.size();
        for(int i=0;i<n;i++) {
            Sprite sp1 = sprites.get(i);
            for(int j=i+1;j<n;j++) {
                Sprite sp2 = sprites.get(j);
                if(Sprite.overlap(sp1,sp2)) {
                    sp1.processEvent(new SpriteCollisionEvent(sp2));
                    sp2.processEvent(new SpriteCollisionEvent(sp1));
                }
            }
        }
    }
    
    public static Comparator<Sprite> COMPX = new Comparator<Sprite>() {
        @Override
        public int compare(Sprite a,Sprite b) {
            double d = a.getX() - b.getX();
            if(d < 0) return -1;
            if(d > 0) return 1;
            return 0;
        }
    };
    
    /**
     * This should be a faster version of detecting
     * collisions. It's still O(n^2), unfortunately.
     */
    public void detectCollisions() {
        int n = sprites.size();
        Collections.sort(sprites,COMPX);
        for(int i=0;i<n;i++) {
            Sprite sp1 = sprites.get(i);
            for(int ii=i+1;ii<n;ii++) {
                Sprite sp2 = sprites.get(ii);
                if(Sprite.overlapx(sp1,sp2)) {
                    if(Sprite.overlapy(sp1,sp2)) {
                        sp1.processEvent(new SpriteCollisionEvent(sp2));
                        sp2.processEvent(new SpriteCollisionEvent(sp1));
                    }
                } else {
                    break;
                }
            }
        }
    }
    public void moveSprites() {
        Dimension d = getSize();
        if(d.width == 0 || d.height == 0)
            return;
        for(Iterator<Sprite> iter = sprites.iterator();iter.hasNext();) {
            Sprite sp = iter.next();
            if(!sp.isActive()) {
                iter.remove();
            }
        }
        for(Sprite sp : sprites) {
            sp.preMove();
            sp.move(d);
        }
        detectCollisions();
        for(Sprite sp : sprites) {
            sp.postMove();
        }
        repaint();
    }
}
