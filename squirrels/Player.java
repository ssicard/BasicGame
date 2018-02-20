package squirrels;

import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Player extends Sprite{
    Picture basePic;
    Picture blinkPic;
    SpriteComponent sc;
    String difMode;
    boolean update;
    boolean posOne = false;
    boolean posTwo = false;
    boolean endGame = false;
    int count = 0;
    int progress = 0;
    int xStart = 100;
    int numOfInvisibles = 0;
    int heartsRespawned = 0;
    int hitlessPeriod = 0;
    int blink = 0;
    Blank b1 = new Blank();
    Blank b2 = new Blank();
    Blank b3 = new Blank();
    Blank b4 = new Blank();
    Progress p1 = new Progress();
    Progress p2 = new Progress();
    Progress p3 = new Progress();
    Progress p4 = new Progress();
    Progress p5 = new Progress();
    Progress[] progresses =  {p1,p2,p3,p4,p5};
    ProgressBar pb = new ProgressBar();
    Heart_LifeBar h1 = new Heart_LifeBar();
    Heart_LifeBar h2 = new Heart_LifeBar();
    Heart_LifeBar h3 = new Heart_LifeBar();
    Heart_LifeBar h4 = new Heart_LifeBar();
    Invisible i1 = new Invisible();
    Invisible i2 = new Invisible();
    Invisible i3 = new Invisible();
    boolean canGetHit = true;
    boolean girl = false;
    boolean boy = false;
    boolean dragon = false;
    private static int lives = 3;
    private boolean life = true;
    private boolean oneLife = true;
    BasicFrame bf;
    
    public void init(SpriteComponent sc, String difMode, BasicFrame bf_game) throws IOException{
        bf = bf_game;
        setActive(true);
        this.difMode = difMode;
        if(difMode.equals("male")){
            basePic = new Picture("prun_1.png");
            blinkPic = new Picture("prun_1B.png");
            boy = true;
        }
        if(difMode.equals("female")){
            basePic = new Picture("girlrun.png");
            blinkPic = new Picture("girlrunB.png");
            girl = true;
        }
        if(difMode.equals("dragon")){
            basePic = new Picture("dragon.png");
            blinkPic = new Picture("dragonB.png");
            dragon = true;
        }
        setPicture(basePic);
        setX(xStart);
        setY(200);
        this.sc = sc;
        sc.addSprite(this);
    };
    
    public String getDifMode(){
        if(girl)
            return "girl";
        else if(boy)
            return "boy";
        else
            return "dragon";
    }
    
    public void blink(){
        if(blink%2 == 0)
            setPicture(basePic);
        else
            setPicture(blinkPic);
    }
    
    @Override
    public void preMove() {
        count++;
        blink++;
        if(count == 100)
            setPicture(basePic.rotate(.1));
        else if(count == 200){
            setPicture(basePic.rotate(-.1));
            count = 0;
        }
        if(canGetHit==false){
            hitlessPeriod++;
            blink();
            if(hitlessPeriod == 300){
                canGetHit = true;
                hitlessPeriod = 0;
            }
        }
    }
    
    public void loseProgress() throws IOException{
        setX(xStart);
        for (int i = 1; i <= progress; i++) {
            progresses[i].hide();
        }
        progress = 0; 
    }
    
    private boolean isLife() { 
        return life; 
    }
    
    private void gainLife() throws IOException{
        if(lives == 2){
            i1.hide(3);
        }else{
            i2.hide(2);
        }
        lives++;
        oneLife = true;
    }
    
    
    private void loseLife() throws IOException{ 
        if(lives>0)
            lives--;
        loseProgress();
        if(lives == 2){
            i1.init(sc, lives);
            numOfInvisibles++;
        }else{
            i2.init(sc, 1);
            numOfInvisibles++;
            if(oneLife == false){
                numOfInvisibles++;
                i3.init(sc, 0);
                life = false;
            }
            oneLife = false;
        }
    }
    
    public void setCongratsMode(){
        setX(400);
        setY(300);
    }
    
    public void hitFry() throws IOException{
        if(getX()<700)
            setX(getX() + 125);
        progress++;
        progresses[progress].init(sc,progress);
    }
    
    @Override
    public void processEvent(SpriteCollisionEvent se){
        if(se.sprite2 instanceof Obstacle){
            if(canGetHit){
                canGetHit=false;
                try {
                    loseLife();
                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(isLife() == true){
                        String message = java.lang.String.format("You lost a life");
                        JOptionPane.showMessageDialog(sc, message);
                }else{
                    String message = java.lang.String.format("You have lost all of your lives. Game over.");
                    JOptionPane.showMessageDialog(sc, message);
                    System.exit(0);
                }
            }
        }else if(se.sprite2 instanceof Fry){
            if(canGetHit)
                try {
                    hitFry();
                    canGetHit = false;
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(se.sprite2 instanceof Heart){
            if(canGetHit){
                    try {
                        if(lives<3){
                            gainLife();
                            canGetHit=false;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }else if(se.sprite2 instanceof Squirrel){
            try {
                setActive(false);
                if(update){
                    bf.hide();
                    SpriteComponent sc_congrats;
                    sc_congrats = new SpriteComponent(){
                        @Override
                        public void paintBackground (Graphics g){
                            Dimension d = getSize();
                            g.setColor(Color.white);
                            g.fillRect(0, 0, d.width, d.height);
                        }
                    };
                    sc_congrats.setPreferredSize(new Dimension(800,600));
                    BasicFrame bf_c = new BasicFrame("Congratulations");
                    bf_c.add("Congratulations", sc_congrats, 0, 0, 1, 1);

                    Background congrats = new Background();
                    congrats.init(sc_congrats, "congrats"+difMode);
                    
                    Player main = new Player();
                    main.init(sc, difMode, bf_c);
                    main.setCongratsMode();
                    
                    MouseAdapter ma = new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.exit(0);
                        }
                    };
                    sc_congrats.addMouseListener(ma);
                    bf_c.show();
                    sc_congrats.start(0,10);
                    update=false;
                }
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
