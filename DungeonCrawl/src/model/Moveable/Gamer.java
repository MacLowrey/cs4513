/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author russe_000
 */
package model.Moveable;

import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Gamer extends MoveableObject {
    private final int width = 32;
    private final int height = 32;
       
    public BufferedImage[] leftIdle;
    public BufferedImage[] rightIdle;
    public BufferedImage[] downIdle;
    public BufferedImage[] upIdle;
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    private float dx;
    private float dy;
    
    public int facing = 2; //0 = North, 1 = East, 2 = South, 3 = West,
    
    private final ObjectAnimator gamerMoves;
    
    public Gamer(float x, float y) {
        super(x, y);
        
        gamerMoves = new ObjectAnimator();              
        upIdle = new BufferedImage[1];
        rightIdle = new BufferedImage[1];
        downIdle = new BufferedImage[1];
        leftIdle = new BufferedImage[1];     
        setImages();
    }
    
    public void setImages(){
        try{
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip_S.png");
            
            downIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip_N.png");
            
            upIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip_W.png");
            
            leftIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip_E.png");
            
            rightIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip_S.png");      
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setLeft(boolean b)
    {
        left = b;
    }
    
    public void setRight(boolean b)
    {
        right = b;
    }
    
    public void setUp(boolean b)
    {
        up = b;
    }
    
    public void setDown(boolean b)
    {
        down = b;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(gamerMoves.getImage(), (int)super.x, (int)super.y, (int)super.width, (int)super.height, 
            null);
        g.setColor(Color.blue);
        g.draw(this.getCollisionBox());
    }

    @Override
    public void update() {
        dx = super.x;
        dy = super.y;
        if(left)
        {
            super.x -= super.MOVEMENT;
            
            facing = 3;
            gamerMoves.setFrames(leftIdle);
            left = false;
        } 
        else if(right)
        {
            super.x += super.MOVEMENT;
            
            facing = 1;
            gamerMoves.setFrames(rightIdle);
            right = false;
        }
        
        if(down)
        {
            super.y += super.MOVEMENT;
            
            facing = 2;
            gamerMoves.setFrames(downIdle);
            down = false;
        } 
        else if(up)
        {
            super.y -= super.MOVEMENT;
            
            facing = 0;
            gamerMoves.setFrames(upIdle);
            up = false;
        }
        
        if (!(right && down && left && up))
        {
            switch(facing){
                case 0:
                    gamerMoves.setFrames(upIdle);
                    break;
                case 1:
                    gamerMoves.setFrames(rightIdle);
                    break;
                case 2:
                    gamerMoves.setFrames(downIdle);
                    break;
                case 3:
                    gamerMoves.setFrames(leftIdle);
                    break;
            }
            gamerMoves.setDelay(-1);
        }
        else
        {
            gamerMoves.setDelay(100);
        }
        
        gamerMoves.update();                
    }

    public void noMove() {
        super.x = dx;
        super.y = dy;
    }
}