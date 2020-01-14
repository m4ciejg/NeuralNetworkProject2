package com.maciejg;
import com.maciejg.Utils.Utils;
import com.maciejg.gui.CustomBoard;
import com.maciejg.gui.LearningBoard;
import com.maciejg.gui.LeftWindow;
import com.maciejg.gui.RightWindow;
import jdk.jshell.execution.Util;

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
    private static final Integer EPOCH = 20;
    private LeftWindow leftWindow;
    private RightWindow rightWindow;
    private CustomBoard mainWindow;
    private LearningBoard learningBoard;
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem jMenuItemStartLearning;
    private JMenuItem jMenuItemInitWindows;
    private List<Boolean> leftWindowPixels;
    private List<Boolean> rightWindowPixels;
    private Boolean[][] twoDimensionArray;
    private Random random;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private void clearWindow() {
        repaint();
    }

    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,440);
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
            //convert to 2 dimension array
            twoDimensionArray = Utils.convertToTwoDimension(leftWindowPixels);
            //init Random Neuron with true and repaint board
            initRandomNeuron();

                    scheduledExecutorService.scheduleAtFixedRate(() -> adjustNeuron(leftWindowPixels), 0, 300, TimeUnit.MILLISECONDS);
            });
    }

    private void adjustNeuron(List<Boolean> leftWindowPixels) {
        List<Boolean> neuronList = learningBoard.returnListOfPixels();
        Boolean tabNeuron[][];
        Boolean tabLeftWindow[][];
        int randInt1 ;
        int randInt2;

        int randInt3;
        int randInt4;
        Random r = new Random();

        tabNeuron = Utils.convertToTwoDimension(neuronList);
        tabLeftWindow = Utils.convertToTwoDimension(leftWindowPixels);
        while(true) {
            randInt1 = r.nextInt(20);
            randInt2 = r.nextInt(20);
            //bierzemy losowy element z ciagu uczacego ktory zawiera wartosc true
            if (tabLeftWindow[randInt1][randInt2] == true) {
                break;
            }
        }
            //znajdujemy neuron z wartoscia TRUE ktory jest najblizej do tego z ciagu uczacego i ustawiamy go na tamtym miejscu


        //hmm spróbujmy wziac jakikolwiek ktory ma wartosc true i zamienic go z naszym z ciagu uczacego
        while(true) {
            randInt3 = r.nextInt(20);
            randInt4 = r.nextInt(20);
            if (tabNeuron[randInt3][randInt4] == true) {
                break;
            }
        }
        tabNeuron[randInt3][randInt4] = false;
        tabNeuron[randInt1][randInt2] = true;
        List<Boolean> listOfNeuron = Utils.convertToList(tabNeuron);
        int licznik = 0;
        for(int i = 0; i < learningBoard.sections.size(); i++) {
            if(listOfNeuron.get(licznik) == true) learningBoard.sections.get(i).setActive(true);
            licznik++;
        }
        learningBoard.repaint();
    }

    private void initRandomNeuron() {
        learningBoard.initRandomNeuronWithTrue();
        learningBoard.repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Main();
        });
    }
}
