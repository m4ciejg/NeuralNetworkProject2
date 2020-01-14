package com.maciejg.gui;

import com.maciejg.Conversion;

import javax.swing.*;
import java.util.List;

public class LeftWindow extends JFrame implements Conversion {
    private DrawingBoard drawingBoard;

    public LeftWindow() {
        super("Rysunek 1");
        int h = getHeight();
        int w = getWidth();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,430);
        setResizable(false);
        setLocation(200, 400);
        initBoard();
        setVisible(true);
    }

    private void initBoard() {
        add(drawingBoard = new DrawingBoard(400, 430, 20));
    }

    public List getPixels() {
        return drawingBoard.returnListOfPixels();
    }
}
