package seminar1.tiktok;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    private static final Random random = new Random();
    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;
    private final int DOT_PADDING = 5;
    private int fieldSizeX ;
    private int fieldSizeY ;
    private int[][] field;
    private int panelWidth;
    public int panelHeight;
    private int cellHeight;
    private int cellWidth;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;
    private static final int STATE_DRAW = 0;
    private static final String MSG_WIN_HUMAN = "Победил игрок";
    private static final String MSG_WIN_AI = "Победил компьютер";
    private static final String MSG_DRAW = "Ничья";
    private int gameOverType;
    private boolean isGameOver;
    private boolean isInitialized;

    Map() {
        setBackground(Color.GRAY);
        //isInitialized=false;////????
        addMouseListener(new MouseAdapter() {
            //isInitialized=false;
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
                //isInitialized=false;///?????
            }
        });
    }

    /**
     * логика игры
     */
    private void initMap(int fSzX, int fSzY) {
        fieldSizeY = fSzX;
        fieldSizeX = fSzY;
        field = new int[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    /**
     * Проверка есть ли значение в клетке
     *
     * @param x координата x
     * @param y координата y
     * @return есть ли значение в клетке
     */
    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Проверка пустая ли клетка
     *
     * @param x координата x
     * @param y координата y
     * @return пустая ли клетка
     */
    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }

    /**
     * логика бота(случайные места на карте)
     */
    private void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[x][y] = AI_DOT;
    }

    /**
     * Проверка победы одного из игроков
     *
     * @param c игрок символ
     * @return победил кто то
     */
    private boolean checkWin(int c) {
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;

        return false;
    }

    /**
     * проверка на ничью
     *
     * @return
     */
    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    private void update(MouseEvent e) {

        if (isGameOver || isInitialized) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellWidth;
        System.out.printf("x=%d, y=%d\n", cellX, cellY);
        if (!isEmptyCell(cellX, cellY) || !isValidCell(cellX, cellY)) return;
        field[cellY][cellX] = HUMAN_DOT;
        repaint();

        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) return;
        aiTurn();
        repaint();
        if (checkEndGame(AI_DOT, STATE_WIN_AI)) return;

    }

    private boolean checkEndGame(int dot, int gameOverType) {
        if (checkWin(dot)) {
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        return false;
    }

    void startNewGame(boolean mode, int fSzX, int fSzY, int wLen) {

        initMap( fSzX, fSzY);
        System.out.printf("Mode: %b;\nSize:= x=%d, y=%d;\nWin Length:= %d", mode, fSzX, fSzY, wLen);
        isGameOver = false;
        isInitialized = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    public void render(Graphics g) {
        if (!isInitialized) return;


        panelWidth = getWidth();
        panelHeight = getHeight();

        cellHeight = panelWidth / fieldSizeX;
        cellWidth = panelHeight / fieldSizeY;
        g.setColor(Color.BLACK);
        for (int i = 0; i < fieldSizeX; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int i = 0; i < fieldSizeY; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
        System.out.println();
        System.out.println(fieldSizeX + " " + fieldSizeY);

/*        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) continue;
                if (field[i][j] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(i * cellWidth + DOT_PADDING,
                            j * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else if (field[i][j] == AI_DOT) {
                    g.setColor(Color.MAGENTA);
                    g.fillOval(i * cellWidth + DOT_PADDING,
                            j * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Unexpected value" + field[i][j]);
                }
            }
        }*/
        if (isGameOver) showMessageGameOver(g);
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DRAW -> g.drawString(MSG_DRAW, 180, getHeight() / 2);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 180, getHeight() / 2);
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 180, getHeight() / 2);
            default -> throw new RuntimeException("Unexpected gameOver state" + gameOverType);
        }
    }

    public int getFieldSizeX() {
        return fieldSizeX;
    }

    public void setFieldSizeX(int fieldSizeX) {
        this.fieldSizeX = fieldSizeX;
    }

    public int getFieldSizeY() {
        return fieldSizeY;
    }

    public void setFieldSizeY(int fieldSizeY) {
        this.fieldSizeY = fieldSizeY;
    }
}
