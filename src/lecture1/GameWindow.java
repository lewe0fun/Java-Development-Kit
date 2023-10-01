package lecture1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    /**
     * Константы с основными размерами
     */
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    /**
     * Создание кнопок
     */
    JButton btnStart = new JButton("New Game");
    JButton btnExit = new JButton("Exit");
    Map map;
    SettingWindow settingWindow;

    /**
     * Конструктор окна
     */
    public GameWindow() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("KPECTUKU HO/\\UKU");
        setResizable(false);


        map = new Map();
        settingWindow = new SettingWindow(this);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);// не закрывает программу, а скрывает окно
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingWindow.setVisible(true);
            }
        });


        JPanel jPanel = new JPanel(new GridLayout(1, 2));
        jPanel.add(btnStart);
        jPanel.add(btnExit);

        add(jPanel, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }

    public void startNewGame(int mode, int fSzX, int fSzY, int wLlen) {
        map.startNewGame(mode,fSzX,fSzY,wLlen);
    }
}

