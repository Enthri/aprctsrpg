package ui;

import javax.swing.*;
import javax.imageio.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import java.io.*;

public class Window extends JFrame implements ActionListener { 
    private static Painter painterVar;
    private static Window windowVar;
    
    private static JPanel actionsMenu;
    private static JPanel statsMenu;
    private static JPanel ostatsMenu;
    private static JPanel displayMenu;
    private static JPanel imageMenu;
    
    public static void init() {
        windowVar = new Window("RPG");
        Stage.begin();
    }
    
    public Window(String str) {
        super(str);
        this.setSize(800,600);
        painterVar = new Painter();
        displayMenu = new JPanel();
        statsMenu = new JPanel();
        ostatsMenu = new JPanel();
        actionsMenu = new JPanel();
        imageMenu = new JPanel();
        this.setLayout(new BorderLayout());
        displayMenu.setLayout(new GridLayout(1,2));
        displayMenu.add(painterVar);
        displayMenu.add(imageMenu);
        this.add(displayMenu, BorderLayout.CENTER);
        this.add(statsMenu, BorderLayout.NORTH);
        this.add(ostatsMenu, BorderLayout.SOUTH);
        this.add(actionsMenu, BorderLayout.WEST);
        this.setVisible(true);
    }
    
    public static void renderDisplay() {
        displayMenu.revalidate();
        displayMenu.repaint();
        painterVar.repaint();
    }
    
    private static void syncImages() { //MY way of adding images next to eachother without adding another painter
        if(Stage.getStage().getImages() == null) return;
        else imageMenu.removeAll();
        imageMenu.setLayout(new GridLayout(1, Stage.getStage().getChoices().length));
        for(int i = 0; i < Stage.getStage().getImages().length; i++) {
            if(Stage.getStage().getImages()[i].length() < 1) continue;
            JLabel jl = new JLabel();
            BufferedImage bufferedImage;
            try { bufferedImage = ImageIO.read(new File("resources/" + Stage.getStage().getImages()[i] + ".png"));
            } catch(Exception e) { bufferedImage = null; }
            Image image = bufferedImage.getScaledInstance(bufferedImage.getWidth() / bufferedImage.getHeight() * imageMenu.getHeight(), imageMenu.getHeight(), Image.SCALE_FAST);
            jl.setIcon(new ImageIcon(image));
            imageMenu.add(jl);
        }
        imageMenu.revalidate();
        imageMenu.repaint();
    }
    
    public static void syncChoices() {
        if(Stage.getStage().getChoices() == null) return;
        else actionsMenu.removeAll();
        actionsMenu.setLayout(new GridLayout(Stage.getStage().getChoices().length, 1));
        for(int i = 0; i < Stage.getStage().getChoices().length; i++) {
            JButton jb = new JButton(Stage.getStage().getChoices()[i]);
            jb.addActionListener(windowVar);
            actionsMenu.add(jb);
        }
        actionsMenu.revalidate();
        actionsMenu.repaint();
        renderDisplay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < actionsMenu.getComponents().length; i++) {
            actionsMenu.getComponents()[i].setEnabled(false);
        }
        Stage.getStage().choiceDone(((JButton)e.getSource()).getText());
        renderDisplay();
    }
    
    static class Painter extends JComponent {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D render = (Graphics2D)g;
            syncImages();
            render.setPaint(Color.BLACK);
            render.drawString(Stage.getStage().getDialog(), 0, 20);
        }
    };
}
