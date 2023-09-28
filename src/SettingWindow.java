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
        add(startButton);
    }
}
