/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
package model.Moveable;

import java.awt.geom.Rectangle2D;
import model.GameObject;

public abstract class MoveableObject implements GameObject {
    public float x, y, height = 32, width = 32;
    private boolean alive;
    public static final int MOVEMENT = 32;
    
    public MoveableObject(float x, float y){
        this.x = x;
        this.y = y;
        this.alive = true;
    }
    
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    
    public boolean isAlive(){
        return alive;
    }
    
    public abstract void update();
    
    @Override
    public Rectangle2D.Double getCollisionBox(){
        return new Rectangle2D.Double(x, y, width, height);
    }
}
