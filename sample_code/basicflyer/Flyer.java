/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicflyer;

import basicgraphics.BasicFrame;
import basicgraphics.SpriteComponent;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 *
 * @author sbrandt
 */
public class Flyer {
    public static void main(String[] args) throws IOException {
        BasicFrame bf = new BasicFrame("Flyer");
        SpriteComponent sc = new SpriteComponent();
        sc.setPreferredSize(new Dimension(800,400));
        bf.add("Flyer",sc,0,0,1,1);
        bf.show();
        final Falcon f = new Falcon();
        final double INCR = Math.PI*2/100.0;
        bf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                    f.heading += INCR;
                    f.update = true;
                } else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
                    f.heading -= INCR;
                    f.update = true;
                } else if(ke.getKeyChar() == ' ') {
                    Plasma pl = new Plasma();
                    pl.setVelX(f.getVelX()*2);
                    pl.setVelY(f.getVelY()*2);
                    pl.setX(f.getX()+f.getWidth()*3./7.);
                    pl.setY(f.getY()+f.getHeight()*3./6.5);
                    pl.init(sc);
                    sc.addSprite(pl);
                }
            }
        });
        f.init(sc);
        sc.start(0,10);
    }
}
