import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        JLabel label = new JLabel("Select:");
        JButton button1 = new JButton("New Game");

        button1.setBackground(Color.WHITE);
        button1.addActionListener(e -> {
            frame.dispose();
            NewGame();
        });

        JButton button2 = new JButton("Load Game");
        button2.setBackground(Color.WHITE);
        button2.addActionListener(e -> {
            frame.dispose();
            new GUI().LoadData();
        });

        panel1.add(label);
        panel2.add(button1);
        panel2.add(button2);

        frame.setSize(300,200);
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("More or less, less is more");
    }
    public static void NewGame(){
        JFrame frame = new JFrame();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        JLabel label = new JLabel("Select Difficulty:");
        JButton button1 = new JButton("Easy");
        button1.setBackground(Color.WHITE);
        button1.addActionListener(e -> {
            frame.dispose();
            new GUI().Game(10,50,20);
        });
        JButton button2 = new JButton("Medium");
        button2.setBackground(Color.WHITE);
        button2.addActionListener(e -> {
            frame.dispose();
            new GUI().Game(15,75,25);
        });
        JButton button3 = new JButton("Hard");
        button3.setBackground(Color.WHITE);
        button3.addActionListener(e -> {
            frame.dispose();
            new GUI().Game(20,100,30);
        });
        JButton button4 = new JButton("Custom");
        button4.setBackground(Color.WHITE);
        button4.addActionListener(e -> {
            frame.dispose();
            new Custom().CustomGame();
        });
        panel1.add(label);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);

        frame.setSize(450,200);
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("MainMenu");
    }
}