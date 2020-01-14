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
    private List<Integer> neuronsXUsed;
    private List<Integer> neuronsYUsed;
    private Random random;
    private int epoka = 0;
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
           // rightWindow = new RightWindow();
        });

        jMenuItemStartLearning.addActionListener(e -> {
            System.out.println("Rozpoczynam uczenie");
            //tablice booleanow
            leftWindowPixels = new ArrayList(leftWindow.getPixels());
            //rightWindowPixels = new ArrayList(rightWindow.getPixels());
            //convert to 2 dimension array
            twoDimensionArray = Utils.convertToTwoDimension(leftWindowPixels);
            //init Random Neuron with true and repaint board
            initRandomNeuron();

            //todo Wykonuj tak długo aż jest 90% zgodności, nie dziala bo zawsze jest tyle zgodnosci bo patrzy tez na biale piksele

            //while(!Utils.checkIfNetworkIsGoodEnough(leftWindowPixels, learningBoard.returnListOfPixels())) {
                scheduledExecutorService.scheduleAtFixedRate(() -> adjustNeuron(leftWindowPixels), 0, 300, TimeUnit.MILLISECONDS);
            //}
            });
    }

    private void adjustNeuron(List<Boolean> leftWindowPixels) {
        List<Boolean> neuronList = learningBoard.returnListOfPixels();
        Boolean tabNeuron[][];
        Boolean tabLeftWindow[][];
        neuronsXUsed = new ArrayList<>();
        neuronsYUsed = new ArrayList<>();
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
            if (tabLeftWindow[randInt1][randInt2]) {
                break;
            }

             //to warunek do Ifa tego wyżej aby nie brał tych samych elementów kilka razy ale jakos nie działa && (!neuronsXUsed.contains(randInt1) || !neuronsYUsed.contains(randInt2))
//            neuronsXUsed.add(randInt1);
//            neuronsYUsed.add(randInt2);
//            System.out.println(neuronsXUsed.size());
//            System.out.println(neuronsYUsed.size());

        }
            //TODO znajdujemy neuron z wartoscia TRUE ktory jest najblizej do tego z ciagu uczacego i przesówamy go troche w tamtym kierunku
            //TODO jak to będzie działać to właściwie już chyba program gotowy
            //TODO zeby to zrobic to trzeba uzyc tego wzoru matematycznego mam na wektor



        //hmm spróbujmy wziac jakikolwiek ktory ma wartosc true i zamienic go z naszym z ciagu uczacego
        while(true) {
            randInt3 = r.nextInt(20);
            randInt4 = r.nextInt(20);
            if (tabNeuron[randInt3][randInt4]) {
                break;
            }
        }
        //podmieniamy wartosc naszego neuronu ktory chcemy ustawic, z false na true
        tabNeuron[randInt3][randInt4] = false;
        tabNeuron[randInt1][randInt2] = true;

        //konwertujemy z tablicy do listy, ponieważ nasz zjebany program tego wymaga
        List<Boolean> listOfNeuron = Utils.convertToList(tabNeuron);
        int licznik = 0;

        // Wedłóg naszej odswieżonej listy neuronow ustawiamy, lecimy po wszystkich elementach sekcji i ustawiamy je tam na true gdzie true jest w naszej odswiezonej liscie
        for(int i = 0; i < learningBoard.sections.size(); i++) {
            if(listOfNeuron.get(licznik)) learningBoard.sections.get(i).setActive(true);
            licznik++;
        }
        learningBoard.repaint();
        epoka++;
       // System.out.println("EPOKA" + epoka);
        //System.out.println("najblizszy neuron" + getClosestNeuron(tabLeftWindow, tabNeuron));
    }

    //todo nie dziala
    private List<Integer> getClosestNeuron(Boolean[][] tabLearning, Boolean[][] tabNeurons) {
        double minDistance = Integer.MAX_VALUE;
        List<Integer> points = new ArrayList<>();
        for(int i = 0; i < tabLearning.length; i++) {
            for(int j = 0; j < tabLearning.length; j++) {
                double distance = Utils.calculateDistanceBetweenPoints(i, j, i, j);
                if(distance < minDistance) {
                    points.clear();
                    minDistance = distance;
                    points.add(i);
                    points.add(j);
                }

            }
        }
        return points;
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
