package lecture1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    /**
     * Константы с основными размерами
     */
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    JLabel labelMode =new JLabel("Выберите режим игры");
    JRadioButton humanVsHumanButton=new JRadioButton("Человек против человека",true);
    JRadioButton humanVsAiButton=new JRadioButton("Человек против компа");

    JLabel labelSize =new JLabel("Установите размер поля");
    JSlider sliderSize =new JSlider(3,10,3);

    JLabel labelWin =new JLabel("Установите длину для победы");
    JSlider sliderWin =new JSlider(3,10,3);

    JButton startButton=new JButton("Start new Game");
    public SettingWindow(GameWindow gameWindow) throws HeadlessException {
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame(0,3,3,3);
            }
        });
        ButtonGroup buttonGroup =new ButtonGroup();
        buttonGroup.add(humanVsHumanButton);
        buttonGroup.add(humanVsAiButton);
        JPanel panel1 = new JPanel(new GridLayout(1, 1));
        panel1.add(humanVsHumanButton,humanVsAiButton);


/*        JPanel panel1 = new JPanel(new GridLayout(1, 1));
        panel1.add(label1);
        panel1.add(humanVsHumanButton);
        panel1.add(humanVsAiButton);
        //add(panel1);
        JPanel panel2 =new JPanel(new GridLayout(2, 1));
        panel2.add(label2);
        panel2.add(slider);
        add(panel1,panel2);*/
        add(startButton);
    }
}
