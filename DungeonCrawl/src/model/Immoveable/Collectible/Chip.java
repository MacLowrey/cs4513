package model.Immoveable.Collectible;

import controller.ImageFinder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Chip extends Collectible {
    private final int width = 32;
    private final int height = 32;
    
    public BufferedImage chip;
    
    public Chip(float x, float y) {
        super(x, y);
        
        try{
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip.png");
            chip = image;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    };

    @Override
    public void render(Graphics2D g) {
        if(this.isAlive() && this.isDisplayed){
            g.drawImage(chip, (int)super.x, (int)super.y, (int)super.width, (int)super.height, null);
        }
        
        //Draw Collision Box
        g.setColor(Color.blue);
        g.draw(this.getCollisionBox());
    }
}
