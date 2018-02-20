package lunarlander;

import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.io.IOException;

public class Number extends Sprite{
    public Picture basePic;
    SpriteComponent sc;
    int baseXPos = 200;
    int xPos;
    
    public void init(SpriteComponent sc, int number, int position) throws IOException{
        basePic = setBasePic(number);
        setPicture(basePic);
        xPos = setXPos(position);
        setX(xPos);
        setY(2);
        this.sc = sc;
        sc.addSprite(this);
    }
    
    public int setXPos(int pos){
        if(pos ==1)
            return baseXPos;
        else if(pos ==2)
            return baseXPos+50;
        else if(pos==3)
            return baseXPos+110;
        else 
            return baseXPos+160;
    }
    
    public Picture setBasePic(int num) throws IOException{
        for(int i = 0; i < 10; i++){
            if(num == i)
                return new Picture(i+".png");
        }
        return null;
    }
}
