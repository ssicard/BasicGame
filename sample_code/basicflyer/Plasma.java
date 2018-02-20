/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicflyer;

import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author sbrandt
 */
class Plasma extends Sprite {
    
    static Picture makeBall(Color color,int size) {
        Image im = BasicFrame.createImage(size, size);
        Graphics g = im.getGraphics();
        g.setColor(color);
        g.fillOval(0, 0, size, size);
        return new Picture(im);
    }
    
    public void init(SpriteComponent sc) {
        setPicture(makeBall(Color.red,10));
    }
    
    @Override
    public void processEvent(SpriteCollisionEvent se) {
        if(se.sprite2 != null) {
        } else {
            setActive(false);
        }
    }
}
