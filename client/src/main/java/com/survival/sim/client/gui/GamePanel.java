package com.survival.sim.client.gui;

import com.survival.sim.client.game.Camera;
import com.survival.sim.client.game.LocalData;
import com.survival.sim.client.game.LocalPlayer;
import com.survival.sim.common.entities.Tile;
import com.survival.sim.common.entities.WorldObject;
import com.survival.sim.common.entities.interfaces.Locatable;
import com.survival.sim.common.entities.interfaces.Renderable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(GamePanel.class);
    int verticalHeight = 16;
    int horizontalHeight = 16;

    public GamePanel() {

    }

    /**
     * Pains the world
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        try{
            Camera.centerOn(LocalPlayer.getLocalPlayer());

            Tile location = Camera.getCameraLocation();
            if (location == null) return;

            for (int y = location.getY() - verticalHeight; y < location.getY() + verticalHeight; y++){
                for (int x = location.getX() - horizontalHeight; x < location.getX() + horizontalHeight; x++){
                    WorldObject t = LocalData.getWorld().getTile(x, y, LocalPlayer.getLocalPlayer().getLocation().getPlane());
                    if (t != null){
                        t.render((Graphics2D) g);
                    }
                }
            }

            for (Locatable locatable : LocalData.getWorld().getEntities()) {
                if (locatable instanceof Renderable){
                    ((Renderable) locatable).render((Graphics2D) g);
                }
            }
        } catch (IOException e) {
            logger.error("Error during rendering scene.", e);
        }
    }
}
