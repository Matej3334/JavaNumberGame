import javax.swing.*;
import java.awt.*;

public class Custom {

    public void CustomGame() {
        JFrame frame = new JFrame();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JButton button = new JButton("Ok");
        button.setPreferredSize(new Dimension(70,50));

        JLabel lb1 = new JLabel("Grid Size");
        JLabel lb2 = new JLabel("Target Value");
        JLabel lb3 = new JLabel("Moves");

        JSlider slider1 = new JSlider(10,30);
        slider1.setPaintTrack(true);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider1.setMajorTickSpacing(5);
        slider1.setMinorTickSpacing(1);

        JSlider slider2 = new JSlider(30,150);
        slider2.setPaintTrack(true);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);
        slider2.setMajorTickSpacing(40);
        slider2.setMinorTickSpacing(10);

        JSlider slider3 = new JSlider(20,40);
        slider3.setPaintTrack(true);
        slider3.setPaintTicks(true);
        slider3.setPaintLabels(true);
        slider3.setMajorTickSpacing(5);
        slider3.setMinorTickSpacing(1);


        panel1.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel1.setLayout(new GridLayout(6,1));
        panel1.add(lb1);
        panel1.add(slider1);
        panel1.add(lb2);
        panel1.add(slider2);
        panel1.add(lb3);
        panel1.add(slider3);
        panel2.add(button);

        button.setBackground(Color.WHITE);
        button.addActionListener(e -> {
            frame.dispose();
            new GUI().Game(slider1.getValue(),slider2.getValue(),slider3.getValue());
        });

        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("CustomGame");
    }
}
