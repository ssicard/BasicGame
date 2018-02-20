package lunarlander;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.MatchResult;

public class Timer extends Sprite{
    SpriteComponent sc;
    int count = 0;
    String time;
    int minutes;
    int seconds;
    String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
    boolean num1Exists;
    Number num1 = new Number();
    Number num2 = new Number();
    Number num3 = new Number();
    Number num4 = new Number();
    
    public void init(SpriteComponent sc) throws IOException{
        setPicture(new Picture("timer_1.png"));
        setX(200);
        this.sc = sc;
        sc.addSprite(this);
    }
            
    public void setTime(String curTime) throws IOException{
        Scanner s = new Scanner(curTime);
        String pat = "(\\d)(\\d):(\\d)(\\d)";
        if(s.hasNext(pat)){
            s.next(pat);
            MatchResult m = s.match();
            String firstNum = m.group(1);
            String secondNum = m.group(2);
            String thirdNum = m.group(3);
            String fourthNum = m.group(4);
            for(int j = 0; j <10; j++){
                if(firstNum.equals(numbers[j])){
                    if(num1Exists){
                        num1.setActive(false);
                        Number num1 = new Number();
                    }
                    num1.init(sc, j, 1);
                    num1Exists = true;
                }
                /*if(secondNum.equals(numbers[j])){
                    if(num2Exists)
                    num2.init(sc, j, 2);
                }
                if(thirdNum.equals(numbers[j])){
                    num3.init(sc, j, 3);
                }
                if(fourthNum.equals(numbers[j])){
                    num4.init(sc, j, 4);
                }*/
            }
        }
    }
    
    public void format(int curCount) {
        if(curCount<60)
            seconds = curCount;
        else{
            seconds = curCount%60;
            minutes = curCount/60;
        }
        time = String.format("%02d:%02d", minutes, seconds);
    }
    
    @Override
    public void preMove(){
        count++;
        format(count);
        try {
            setTime(time);
        } catch (IOException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
