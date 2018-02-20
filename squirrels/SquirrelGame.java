package squirrels;

import lunarlander.Timer;
import basicgraphics.BasicFrame;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Sarah Sicard
 */

public class SquirrelGame{
    BasicFrame bf = new BasicFrame("Catch the Squirrel");
    static int iScreenCount = 1;
    static double velYIncrease = .05;
    
    public static void runPlayerMenu(String setting, BasicFrame bf_setting, BasicFrame bf_mainmenu){
        SpriteComponent sc2 = new SpriteComponent();
        sc2.setPreferredSize(new Dimension(800,400));
        BasicFrame bf_playerChoice = new BasicFrame("Choose Gender");
        JComponent jc = new JComponent() {
            @Override
            public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawLine(0,0,d.width,d.height);
            }
        };
        jc.setPreferredSize(new Dimension(500,500));
        jc.setMinimumSize(jc.getPreferredSize());
        try {
            bf_playerChoice.add("title", new JLabel("Pick a Mode"), 0, 0, 3, 1);
            SquirrelGame g = new SquirrelGame();
            JButton male = new Picture("prun_1.png").makeButton();
            male.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        g.runGame("male", setting, bf_playerChoice, bf_setting, bf_mainmenu);
                        bf_playerChoice.hide();
                    } catch (IOException ex) {
                        Logger.getLogger(SquirrelGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            bf_playerChoice.add("row1", new JLabel("Easy"), 0, 1, 1, 1);
            bf_playerChoice.add("opt1", male, 0,2,1,1);
            
            JButton female = new Picture("girlrun.png").makeButton();
            female.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        g.runGame("female", setting, bf_playerChoice, bf_setting, bf_mainmenu);
                        bf_playerChoice.hide();
                    } catch (IOException ex) {
                        Logger.getLogger(SquirrelGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            bf_playerChoice.add("row2", new JLabel("Medium"), 1, 1, 1, 1);
            bf_playerChoice.add("opt2", female, 1,2,1,1);

            JButton dragon = new Picture("dragon.png").makeButton();
            dragon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        g.runGame("dragon", setting, bf_playerChoice, bf_setting, bf_mainmenu);
                        bf_playerChoice.hide();
                    } catch (IOException ex) {
                        Logger.getLogger(SquirrelGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            bf_playerChoice.add("row3", new JLabel("Hard"), 2, 1, 1, 1);
            bf_playerChoice.add("opt3", dragon, 2,2,1,1);
        }catch(IOException ex){
            Logger.getLogger(SquirrelGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bf_playerChoice.show();
        sc2.start(0,10);
    }
    
    public void runGame(String gender, String scene, BasicFrame bf_difMenu, BasicFrame bf_setting, BasicFrame bf_mainmenu) throws IOException{
        SpriteComponent sc = new SpriteComponent(){
        @Override
            public void paintBackground (Graphics g){
                Dimension d = getSize();
                if(scene.equals("street"))
                    g.setColor(Color.yellow);
                if(scene.equals("park"))
                    g.setColor(Color.green);
                g.fillRect(0, 0, d.width, d.height);
            }
        };
        sc.setPreferredSize(new Dimension(798,600));
        bf.add("Catch the Squirrel", sc, 0, 0, 1, 1);
        bf.show();
        
        if(scene.equals("street")){
            Background street_bk = new Background();
            street_bk.init(sc, scene);
        }else if(scene.equals("park")){
            Background park_bk = new Background();
            park_bk.init(sc, scene);
        }
        
        Player mainPerson = new Player();
        Squirrel fryStealer = new Squirrel();
        ProgressBar progressBar = new ProgressBar();
        Timer timer = new Timer();
        LifeBar lives = new LifeBar();
        Fry speedUp = new Fry();
        Heart heart = new Heart();
        Obstacle obs1 = new Obstacle();
        Obstacle obs2 = new Obstacle();
        Obstacle obs3 = new Obstacle();
        Obstacle obs4 = new Obstacle();
        if(gender.equals("male"))
            mainPerson.init(sc,"male", bf);
        else if(gender.equals("female"))
            mainPerson.init(sc,"female", bf);
        else
            mainPerson.init(sc,"dragon", bf);
        fryStealer.init(sc);
        progressBar.init(sc);
        lives.init(sc);
        obs1.init(sc, mainPerson, scene);
        obs2.init(sc, mainPerson, scene);
        obs3.init(sc, mainPerson, scene);
        obs4.init(sc, mainPerson, scene);
        speedUp.init(sc);
        heart.init(sc);
        
        bf.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent key) {
                if(key.getKeyCode() == KeyEvent.VK_UP) {
                    if(mainPerson.getY()>75){
                        if(mainPerson.getDifMode().equals("boy"))
                            mainPerson.setY(mainPerson.getY()-15);
                        if(mainPerson.getDifMode().equals("girl"))
                            mainPerson.setY(mainPerson.getY()-10);
                        if(mainPerson.getDifMode().equals("dragon"))
                            mainPerson.setY(mainPerson.getY() -5);
                        mainPerson.update = true;
                    }
                } else if(key.getKeyCode() == KeyEvent.VK_DOWN) {
                    if(mainPerson.getY()<550){
                        if(mainPerson.getDifMode().equals("boy")){
                            mainPerson.setY(mainPerson.getY()+15);
                        }
                        else if(mainPerson.getDifMode().equals("girl")){
                            mainPerson.setY(mainPerson.getY() +10);
                        }
                        else if(mainPerson.getDifMode().equals("dragon")){
                            mainPerson.setY(mainPerson.getY() + 5);
                        }
                    }
                } 
            }
        });
        
        bf.addMenuAction("Back", "Back to Main Menu", new Runnable() {
            @Override
            public void run() {
                bf.hide();
                bf_mainmenu.show();
                mainPerson.setActive(false);
            }
        });
        bf.addMenuAction("Back", "Back to Choose Scenery Menu", new Runnable(){
            @Override
            public void run(){
                bf.hide();
                bf_setting.show();
                mainPerson.setActive(false);
            }
        });
        bf.addMenuAction("Quit", "Quit", new Runnable() { 
            @Override
            public void run() {
                System.exit(0);
            }
        });
        
        sc.start(0, 10);
    }
    
    public static void main(String[] args) throws IOException{
        SpriteComponent sc0;
        sc0 = new SpriteComponent(){
            @Override
            public void paintBackground (Graphics g){
                Dimension d = getSize();
                g.setColor(Color.white);
                g.fillRect(0, 0, d.width, d.height);
            }
        };
        sc0.setPreferredSize(new Dimension(300,200));
        BasicFrame mm = new BasicFrame("Main Menu");
        mm.add("Main Menu", sc0, 0, 0, 1, 1);
        
        Background startScreen = new Background();
        startScreen.init(sc0, "main");
        
        JComponent jc = new JComponent() {
          @Override
          public void paintComponent(Graphics g) {
            Dimension d = getSize();
            g.drawLine(0, 0, d.width, d.height);
          }
        };
        jc.setPreferredSize(new Dimension(500,500));
        jc.setMinimumSize(jc.getPreferredSize());
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SpriteComponent sc1 = new SpriteComponent();
                    sc1.setPreferredSize(new Dimension(800,400));
                    BasicFrame scene = new BasicFrame("Choose Scenery");
                    JComponent jc0 = new JComponent(){
                        @Override
                        public void paintComponent(Graphics g){
                            Dimension d = getSize();
                            g.drawLine(0,0,d.width,d.height);
                        }
                    };
                    jc0.setPreferredSize(new Dimension(500,500));
                    jc0.setMinimumSize(jc0.getPreferredSize());
                    scene.add("heading", new JLabel("Pick a Scenery"), 0,0,2,1);
                    JButton park = new Picture("bk_grass.png").makeButton();
                    park.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            String setting = "park";
                            runPlayerMenu(setting, scene, mm);
                            scene.hide();
                        }
                    });
                    JButton street = new Picture("bk_street.png").makeButton();
                    street.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            String setting = "street";
                            runPlayerMenu(setting, scene, mm);
                            scene.hide();
                        }
                    });
                    scene.add("choice1", new JLabel("Park"), 0,1,1,1);
                    scene.add("scene1", park, 0,2,1,1);
                    scene.add("choice2", new JLabel("Street"), 1,1,1,1);
                    scene.add("scene2", street, 1, 2, 1,1);
                    scene.show();
                    sc1.start(0,10);
                    mm.hide();
                } catch (IOException ex) {
                    Logger.getLogger(SquirrelGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JButton instructionButton = new JButton("Instructions");
        instructionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SpriteComponent sc_i = new SpriteComponent();
                    sc_i.setPreferredSize(new Dimension(800,600));
                    BasicFrame bf_i = new BasicFrame("Instructions");
                    bf_i.add("Instructions", sc_i, 0, 0, 1, 1);
                    Background instruct = new Background();
                    instruct.init(sc_i, "i" + iScreenCount);
                    iScreenCount++;
                    MouseAdapter ma = new MouseAdapter(){
                        @Override
                        public void mousePressed(MouseEvent me){
                            if(me.getX() > 650 && me.getY() > 500){
                                if(iScreenCount <= 2){
                                    try {
                                        instruct.changePic(iScreenCount);
                                        iScreenCount++;
                                    } catch (IOException ex) {
                                        Logger.getLogger(SquirrelGame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else if(iScreenCount == 3){
                                    try {
                                        instruct.changePic(iScreenCount);
                                        iScreenCount++;
                                    } catch (IOException ex) {
                                        Logger.getLogger(SquirrelGame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else if(iScreenCount == 4){
                                    try {
                                        instruct.changePic(iScreenCount);
                                        iScreenCount++;
                                    } catch (IOException ex) {
                                        Logger.getLogger(SquirrelGame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else if(iScreenCount == 5){
                                    try {
                                        instruct.changePic(iScreenCount);
                                        iScreenCount++;
                                    } catch (IOException ex) {
                                        Logger.getLogger(SquirrelGame.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else{
                                    bf_i.hide();
                                    iScreenCount=1;
                                    mm.show();
                                }
                            }
                        }
                    };
                    sc_i.addMouseListener(ma);
                    bf_i.show();
                    sc_i.start(0,10);
                    mm.hide();
                } catch (IOException ex) {
                    Logger.getLogger(SquirrelGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mm.add("top",startButton,0,1,1,1);
        mm.add("middle", instructionButton, 0,2,1,1);
        mm.add("bottom", quitButton, 0,3,1,1);
      
        mm.show();
    
        sc0.start(0,10);
    }
}
