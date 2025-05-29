import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import static java.awt.Color.BLACK;
import static java.awt.Color.lightGray;

class GUI extends JFrame {
    public Board board;
    private JLabel labelTargetValue;
    private JLabel labelCurrentSum;
    private JLabel labelMovesLeft;
    private JPanel gridPanel;

    private Integer Previous;
    private Integer Current;
    public int v1;
    public int v2;
    public int v3;
    public int size;
    public int moves;
    public int targetvalue;

    public void Game(int size, int targetValue, int moves) {
        Previous = null;
        Current = null;
        labelTargetValue = new JLabel();
        labelCurrentSum = new JLabel();
        labelMovesLeft = new JLabel();
        this.board = new Board(size, targetValue, moves);
        initComponents(size);

    }

    private void initComponents(int size) {
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,4));
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        updateTargetValue();
        updateCurrentSum();
        updateMovesLeft();
        gridPanel = new JPanel(new GridLayout(size, size, 1, 5));
        createButtonGrid();

        JButton restart = new JButton("Restart");
        restart.setBackground(Color.WHITE);
        restart.setPreferredSize(new Dimension(30, 50));
        restart.addActionListener(e -> Restart());

        JButton save = new JButton("Save");
        save.setBackground(Color.WHITE);
        save.setPreferredSize(new Dimension(30, 50));
        save.addActionListener(e -> SaveData());

        add(topPanel, BorderLayout.NORTH);
        topPanel.add(labelTargetValue);
        topPanel.add(restart);
        topPanel.add(save);
        topPanel.add(labelMovesLeft, BorderLayout.EAST);
        add(gridPanel, BorderLayout.CENTER);
        add(labelCurrentSum, BorderLayout.SOUTH);


        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateTargetValue() {
        labelTargetValue.setText("Target Value: " + board.getTargetValue());
        Font font = labelTargetValue.getFont().deriveFont(Font.PLAIN, 20);
        labelTargetValue.setFont(font);
    }

    private void updateCurrentSum() {
        labelCurrentSum.setText("Current Sum: " + board.getCurrentSum());
        Font font = labelCurrentSum.getFont().deriveFont(Font.PLAIN, 20);
        labelCurrentSum.setFont(font);
    }

    private void updateMovesLeft() {
        labelMovesLeft.setText("Moves Left: " + board.getMovesLeft());
        Font font = labelMovesLeft.getFont().deriveFont(Font.PLAIN, 20);
        labelMovesLeft.setFont(font);
    }

    private void updateButtonGrid() {
        gridPanel.removeAll();
        createButtonGrid();
        repaint();
    }

    private void createButtonGrid() {
        Button[][] buttons = board.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                JButton button = new JButton(String.valueOf(buttons[i][j].getDigit()));
                button.putClientProperty("row", i);
                button.putClientProperty("column", j);

                if (Previous != null) {
                    if ((i + 1) % Current == 0 && (j + 1) % Previous == 0) {
                        int digit = buttons[i][j].getDigit();
                        int firstValue = Current > Previous ? Previous : Current;
                        int secondValue = Current > Previous ? Current : Previous;
                        if (!(digit > firstValue && digit < secondValue)) {
                            button.setEnabled(true);
                        }
                    } else {
                        button.setEnabled(false);
                    }
                } else {
                    if (Current != null) {
                        if ((i + 1) % Current != 0 && (j + 1) % Current != 0) {
                            button.setEnabled(false);
                        }

                    }
                }

                if (!buttons[i][j].isActive()) {
                    button.setEnabled(false);
                    button.setBackground(lightGray);
                }
                if (board.getCurrentSum() >= board.getTargetValue()) {
                    JOptionPane.showMessageDialog(null, "You won, deviation:" + (board.getCurrentSum() - board.getTargetValue()), "Winner", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                button.addActionListener(e -> {
                    JButton clickedButton = (JButton) e.getSource();
                    clickedButton.setBackground(BLACK);
                    int buttonValue = Integer.parseInt(clickedButton.getText());
                    board.setCurrentSum(board.getCurrentSum() + buttonValue);
                    board.setMovesLeft(board.getMovesLeft() - 1);
                    Previous = Current;
                    Current = buttonValue;
                    board.getButtons()[(int) clickedButton.getClientProperty("row")][(int) clickedButton.getClientProperty("column")].deactivate();
                    updateCurrentSum();
                    updateMovesLeft();
                    updateButtonGrid();
                    if (areAllButtonsDisabled()) {
                        JOptionPane.showMessageDialog(null, "All buttons are disabled. Closing the program. Deviation(you need this much points):" + (board.getCurrentSum() - board.getTargetValue()) * (-1), "Game Over", JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                    }
                });
                gridPanel.add(button);
            }
        }
    }

    private boolean areAllButtonsDisabled() {
        Component[] components = gridPanel.getComponents();

        for (Component component : components) {
            if (component instanceof JButton button) {
                if (button.getBackground() != Color.LIGHT_GRAY && button.isEnabled()) {
                    return false;

                }
            }
        }

        return true;
    }

    private void Restart() {
        Component[] components = gridPanel.getComponents();
        Button[][] buttons = board.getButtons();
        for (Component component : components) {
            if (component instanceof JButton button) {
                button.setEnabled(true);
                button.setBackground(null);
                for (Button[] value : buttons) {
                    for (Button item : value) {
                        item.isActive2();
                    }
                }
            }
        }
        Previous = null;
        Current = null;
        board.setCurrentSum(0);
        updateCurrentSum();
        board.setMovesLeft(board.var);
        updateMovesLeft();
    }

    public void LoadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("saveFile.txt"));
            size=Integer.parseInt(br.readLine());
            targetvalue=Integer.parseInt(br.readLine());
            moves=Integer.parseInt(br.readLine());
            Previous = Integer.parseInt(br.readLine());
            Current = Integer.parseInt(br.readLine());
            v1=Integer.parseInt(br.readLine());
            v2=Integer.parseInt(br.readLine());
            v3=Integer.parseInt(br.readLine());
            br.close();
        } catch (Exception ignored) {
        }
        this.board = new Board(size, targetvalue, moves);
        labelTargetValue = new JLabel();
        labelCurrentSum = new JLabel();
        labelMovesLeft = new JLabel();
        board.setCurrentSum(v1);
        board.setMovesLeft(v2);
        board.setTargetValue(v3);
        revalidate();
        repaint();
        initComponents(size);
    }


    public void SaveData() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("saveFile.txt"));
            bw.write("" + board.var3);
            bw.newLine();
            bw.write("" + board.var2);
            bw.newLine();
            bw.write("" + board.var);
            bw.newLine();
            bw.write("" + Previous);
            bw.newLine();
            bw.write("" + Current);
            bw.newLine();
            bw.write("" + board.getCurrentSum());
            bw.newLine();
            bw.write("" + board.getMovesLeft());
            bw.newLine();
            bw.write("" + board.getTargetValue());
            bw.newLine();
            bw.close();
        } catch (Exception ignored) {

        }
        JOptionPane.showMessageDialog(null, "Game has been saved now exiting", "save", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}






