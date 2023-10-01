package seminar1.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WelcomeWindow extends JFrame {

    private static final int WINDOW_HEIGHT = 150;
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    private final String BACKUP_PATH="backup.txt";
    private String MSG_ERROR_SERVER = "ошибка соединения с сервером";
    private String MSG_ERROR_CHAT = "Отсутствует соединение с сервером";
    private String MSG_CONNECTION ="Вошел пользователь ";
   private String MSG_DISCONNECTION ="Вышел пользователь ";
    private final MainWindow mainWindow;
    private boolean isConnected;
   // private boolean isAuthorized;
    private String server=null;
    private String login="";
    private String pass=null;

    private JButton connectButton = new JButton("Соединение c сервером");
    private JButton chatButton = new JButton("Войти в чат");
    private JButton exitButton = new JButton("Выход");
    private JLabel label = new JLabel("Ожидание подключения...");
    private JTextField serverField = new JTextField("Введите сервер");
    private JTextField loginField = new JTextField("Введите логин");
    private JTextField passField = new JTextField("Введите пароль");
    private Date dateNow;
    private SimpleDateFormat formatForDateNow;

    public String getBACKUP_PATH() {
        return BACKUP_PATH;
    }

    public WelcomeWindow() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setTitle("чат");
        setResizable(false);

        mainWindow = new MainWindow(this);
        label.setBackground(Color.RED);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);// не закрывает программу, а скрывает окно
            }
        });
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isConnected) {
                    mainWindow.setVisible(true);
                    setVisible(false);
                }
                else label.setText(MSG_ERROR_CHAT);
            }
        });
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server = serverField.getText().replace("Введите сервер","");
                login = loginField.getText().replace("Введите логин","");;
                pass = passField.getText().replace("Введите пароль","");;
                if (server.isEmpty() || login.isEmpty() || pass.isEmpty()){
                    label.setText(MSG_ERROR_SERVER);
                    return;}
                dateNow = new Date();
                formatForDateNow = new SimpleDateFormat(" yyyy.MM.dd'-'HH:mm");
                label.setBackground(Color.GREEN);
                String msg =MSG_CONNECTION+login+formatForDateNow.format(dateNow);
                IOUtils.writeToFile(BACKUP_PATH,msg+'\n');
                label.setText(msg);
                isConnected = true;
            }
        });
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JPanel serverPanel = new JPanel(new GridLayout(1, 0));
        JPanel mainMenu = new JPanel(new GridLayout(2, 0));

        labelPanel.add(label);

        serverPanel.add(serverField);
        serverPanel.add(connectButton);

        mainMenu.add(loginField);
        mainMenu.add(passField);
        mainMenu.add(chatButton);
        mainMenu.add(exitButton);


        add(labelPanel, BorderLayout.NORTH);
        add(serverPanel);
        add(mainMenu, BorderLayout.SOUTH);
        setVisible(true);
    }

    public String getLogin() {
        return login;
    }


    public void unLogin(){
        isConnected=false;
        loginField.setText("");
        passField.setText("");
        String msg =MSG_DISCONNECTION+login+formatForDateNow.format(dateNow);
        IOUtils.writeToFile(BACKUP_PATH,msg+'\n');
        label.setText(msg);
    }
}
