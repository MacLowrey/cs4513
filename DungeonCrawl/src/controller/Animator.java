/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DungeonCrawl.DungeonCrawl;
import model.GameData;
import model.GameObject;
import model.Immoveable.Collectible.Boot;
import model.Immoveable.Collectible.Chip;
import model.Immoveable.Collectible.Collectible;
import model.Immoveable.Collectible.Key;
import model.Immoveable.Tile.Lock;
import model.Immoveable.Tile.Tile;
import model.Immoveable.Tile.Wall;
import model.LockType;
import model.Moveable.Monster;

/**
 *
 * @author russe_000
 */
public class Animator implements Runnable {

    public boolean running;
    private final int FRAMES_PER_SECOND = 15;

    @Override
    public void run() {
        running = true;

        while (running) {
            long startTime = System.currentTimeMillis();
            
            DungeonCrawl.gameData.update();
            processCollisions();
            DungeonCrawl.gamePanel.gameRender();
            DungeonCrawl.gamePanel.printScreen();
            DungeonCrawl.inventoryPanel.updateInventoryPanel();

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); // ms
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private void processCollisions() {
        for (GameObject object : GameData.gameObjects) {
            if (GameData.gamer.getCollisionBox().intersects(
                    object.getCollisionBox())) {
                if (object instanceof Monster) {

                } else if (object instanceof Collectible) {
                    if (object instanceof Key) {
                        ((Key) object).isDisplayed = false;
                        GameData.gamerInventory.add(object);
                    } else if (object instanceof Boot) {
                        ((Boot) object).isDisplayed = false;
                        GameData.gamerInventory.add(object);
                    } else if (object instanceof Chip) {
                        ((Chip) object).setAlive(false);
                        DungeonCrawl.gameData.collectChip();
                    }
                } else if (object instanceof Tile) {
                    if (object instanceof Wall) {
                        if (object instanceof Lock) {
                            if (((Lock) object).type == LockType.SOCKET) {
                                if (GameData.chipsLeft <= 0) {
                                    ((Lock) object).setAlive(false);
                                }
                                else{
                                    GameData.gamer.noMove();
                                }
                            } else {
                                boolean key = false;
                                for (GameObject o : GameData.gamerInventory) {
                                    if (o instanceof Key) {
                                        if (((Key) o).type == ((Lock) object).type) {
                                            ((Lock) object).setAlive(false);
                                            ((Key) o).setAlive(false);
                                            key = true;
                                            break;
                                        }
                                    }
                                }
                                if(!key){
                                    GameData.gamer.noMove();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
