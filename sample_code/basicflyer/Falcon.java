/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicflyer;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Dimension;
import java.io.IOException;

/**
 *
 * @author sbrandt
 */
public class Falcon extends Sprite {
    public Picture initialPic;
    public void init(SpriteComponent sc) throws IOException {
        initialPic = new Picture("mfalcon.png");
        setPicture(initialPic);
        Dimension d = sc.getSize();
        setX(d.width/2);
        setY(d.height/2);
        setVelX(Math.cos(heading));
        setVelY(Math.sin(heading));
        this.sc = sc;
        sc.addSprite(this);
    }
    SpriteComponent sc;
    double heading = 0;
    boolean update;
    @Override
    public void preMove() {
        if(update) {
            setVelX(Math.cos(heading));
            setVelY(Math.sin(heading));
            setPicture(initialPic.rotate(heading));
            update = false;
        }
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
            setY(sc.getSize().height-getHeight());
        }
        if (se.yhi) {
            setY(0);
        }
    }
}
