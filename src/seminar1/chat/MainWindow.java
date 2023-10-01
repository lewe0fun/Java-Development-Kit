package seminar1.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private String text;
    private JTextField entryField = new JTextField();
    private JTextArea fieldView = new JTextArea();
    private JButton sendButton = new JButton("Отправить");
    private JButton backButton = new JButton("Назад");
    private Date dateNow;
    private SimpleDateFormat formatForDateNow;


    public MainWindow(WelcomeWindow welcomeWindow) throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(welcomeWindow);
        try {
            fieldView.append(IOUtils.readFromFile(welcomeWindow.getBACKUP_PATH()).toString());
        } catch (NullPointerException e) {
            System.out.println("Бэкапа не найдено");
        }
        ;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeWindow.unLogin();
                welcomeWindow.setVisible(true);
                setVisible(false);
            }
        });
        entryField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    logger(welcomeWindow);
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger(welcomeWindow);
            }
        });
        JPanel chatPanel = new JPanel(new GridLayout(0, 1));
        JPanel buttonsPanel = new JPanel(new GridLayout(0, 2));
        buttonsPanel.add(sendButton);
        buttonsPanel.add(backButton);
        chatPanel.add(fieldView);
        chatPanel.add(entryField);

        add(chatPanel);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    private void logger(WelcomeWindow welcomeWindow){
        dateNow = new Date();
        formatForDateNow = new SimpleDateFormat("yyyy.MM.dd'-'HH:mm");
        String msg = formatForDateNow.format(dateNow) + " " + welcomeWindow.getLogin() + ": " + entryField.getText() + '\n';
        IOUtils.writeToFile(welcomeWindow.getBACKUP_PATH(), msg);
        fieldView.append(msg);
        entryField.setText("");
    }
}
