package com.maciejg.gui;

public class LearningBoard extends CustomBoard {

    public LearningBoard() {
        super(400,400, 20);
    }

    public void paintSection(int width, int height) {
        for(Section s : sections) {
            if (width > s.getX() && width < s.getX() + s.getWidth() && height > s.getY() && height < s.getY() + s.getHeight()) {
                s.setActive(true);
            }
        }
        repaint();
    }
}
