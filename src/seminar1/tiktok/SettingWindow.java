package seminar1.tiktok;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    /**
     * Константы с основными размерами
     */
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    private int maxSliderValue=3;
    private int maxWinValue=3;
    private  boolean modeValue;
    JLabel labelMode = new JLabel(" Выберите режим игры:");
    JRadioButton humanVsHumanButton = new JRadioButton("Человек против человека", true);
    JRadioButton humanVsAiButton = new JRadioButton("Человек против компа");

    JLabel labelSize = new JLabel(" Установите размер поля:" +maxSliderValue);
    JSlider sliderSize = new JSlider(3, 10, maxSliderValue);

    JLabel labelWin = new JLabel(" Установите длину для победы:"+ maxWinValue);
    JSlider sliderWin = new JSlider(3, 10, maxWinValue);

    JButton startButton = new JButton("Начать новую игру");

    public SettingWindow(GameWindow gameWindow) throws HeadlessException {
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        startButton.setBackground(Color.GREEN);
        sliderSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                maxSliderValue= sliderSize.getValue();
                labelSize.setText("Установите размер поля: " +maxSliderValue);
            }
        });

        sliderWin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                maxWinValue=sliderWin.getValue();
                labelWin.setText("Установите длину для победы: " + maxWinValue);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gameWindow.startNewGame(humanVsHumanButton.isSelected(), maxSliderValue, maxSliderValue, maxWinValue);
            }
        });



        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(humanVsHumanButton);
        buttonGroup.add(humanVsAiButton);

        JPanel modePanel = new JPanel(new GridLayout(0, 1));
        modePanel.add(labelMode);
        modePanel.add(humanVsHumanButton);
        modePanel.add(humanVsAiButton);

        JPanel slidersPanel = new JPanel(new GridLayout(0, 1));
        slidersPanel.add(labelSize);
        slidersPanel.add(sliderSize);
        slidersPanel.add(labelWin);
        slidersPanel.add(sliderWin);

        JPanel startPanel = new JPanel(new GridLayout(0, 1));
        startPanel.add(startButton);

        add(modePanel, BorderLayout.NORTH);
        add(slidersPanel);
        add(startPanel,BorderLayout.SOUTH);

    }
}
