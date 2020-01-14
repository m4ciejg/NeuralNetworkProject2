package com.maciejg;
import com.maciejg.gui.CustomBoard;
import com.maciejg.gui.LearningBoard;
import com.maciejg.gui.LeftWindow;
import com.maciejg.gui.RightWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {
    private LeftWindow leftWindow;
    private RightWindow rightWindow;
    private CustomBoard mainWindow;
    private LearningBoard learningBoard;
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem jMenuItemStartLearning;
    private JMenuItem jMenuItemInitWindows;
    private List leftWindowPixels;
    private List rightWindowPixels;
    private Random random;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private void clearWindow() {
        repaint();
    }

    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setResizable(false);
        setLocationRelativeTo(null);
        add(learningBoard = new LearningBoard());
        initMenu();
        initListener();
        setVisible(true);
    }

    private void initMenu() {
        jMenu = new JMenu("Opcje");
        jMenu.add(jMenuItemStartLearning = new JMenuItem("Rozpocznij uczenie"));
        jMenu.add(jMenuItemInitWindows = new JMenuItem("Otwórz okna rysowania"));
        jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu);
        setJMenuBar(jMenuBar);
    }

    private void initListener() {
        jMenuItemInitWindows.addActionListener(e -> {
            leftWindow = new LeftWindow();
            rightWindow = new RightWindow();
        });

        jMenuItemStartLearning.addActionListener(e -> {
            System.out.println("Rozpoczynam uczenie");
            //tablice booleanow
            leftWindowPixels = new ArrayList(leftWindow.getPixels());
            rightWindowPixels = new ArrayList(rightWindow.getPixels());
                scheduledExecutorService.scheduleAtFixedRate(this::paintLearningBoard, 0, 300, TimeUnit.MILLISECONDS);
            });
    }

    private void changeWeights(List leftWindowPixels, List rightWindowPixels) {

    }

    private void paintLearningBoard() {
            random = new Random();
            int randInt = random.nextInt(400);
            int randInt2 = random.nextInt(400);
            learningBoard.paintSection(randInt, randInt2);
            learningBoard.repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Main();
        });
    }
}
