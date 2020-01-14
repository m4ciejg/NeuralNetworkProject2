package com.maciejg.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LearningBoard extends CustomBoard {
    private Random random;

    public LearningBoard() {
        super(400,430, 20);
    }

    public void paintSection(int width, int height) {
        for(Section s : sections) {
            if (width > s.getX() && width < s.getX() + s.getWidth() && height > s.getY() && height < s.getY() + s.getHeight()) {
                s.setActive(true);
            }
        }
        repaint();
    }

    public List returnListOfPixels() {
        List<Boolean> tempList = new ArrayList<>();
        for(Section s : sections) {
            if(s.isActive()) tempList.add(true);
            else tempList.add(false);
        }
        return tempList;
    }

    public void initRandomNeuronWithTrue() {
        random = new Random();
        int randIntX;
        int randIntY;
        for (int i = 0; i < 20; i++) {
            for (Section s : sections) {
                randIntX = random.nextInt(400);
                randIntY = random.nextInt(400);
                if (randIntX > s.getX() && randIntX < s.getX() + s.getWidth() && randIntY > s.getY() && randIntY < s.getY() + s.getHeight()) {
                    s.setActive(true);
                }
            }
        }
        repaint();
    }
}
