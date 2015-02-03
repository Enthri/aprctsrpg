package ui;

import ui.elem.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class Display extends JFrame implements ActionListener { 
    private static Display displayVar;
    
    private static JPanel actionsMenu;
    private static JPanel statsMenu;
    private static JPanel statsMenu2;
    private static JPanel imageMenu;
    private static JPanel inMenu;
    
    private static JLabel dialog;
    private static JLabel stats;
    private static JLabel stats2;
    
    public static void init() {
        displayVar = new Display("RPG");
        Stage.begin();
    }
    
    public Display(String str) {
        super(str);
        this.setSize(800,600);
        JPanel subContainerA = new JPanel(new GridLayout(1,2));
        JPanel subContainerB = new JPanel(new GridLayout(2,1));
        subContainerA.add(subContainerB);
        this.add(subContainerA, BorderLayout.CENTER);
        JPanel temp;
        JScrollPane temp2;
        actionsMenu = new JPanel(new BoxLayout(actionsMenu, BoxLayout.Y_AXIS));
        imageMenu = new JPanel(); //imageMenu
        imageMenu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        subContainerA.add(imageMenu);
        inMenu = new JPanel();
        inMenu.setLayout(new BoxLayout(inMenu, BoxLayout.Y_AXIS));
        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dialog = new JLabel("");
        temp.add(dialog);
        temp2 = new JScrollPane(temp); //dialog
        temp2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        subContainerB.add(temp2);
        temp2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        temp2 = new JScrollPane(inMenu); //inputs
        subContainerB.add(temp2);
        actionsMenu.setLayout(new BoxLayout(actionsMenu, BoxLayout.Y_AXIS));
        statsMenu = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        statsMenu2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        stats = new JLabel("");
        stats2 = new JLabel("");
        statsMenu.add(stats); //statistics
        this.add(statsMenu, BorderLayout.NORTH);
        statsMenu2.add(stats2); //stats 2
        this.add(statsMenu2, BorderLayout.SOUTH);
        temp2 = new JScrollPane(actionsMenu); //actions
        temp2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.add(temp2, BorderLayout.WEST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        Timer timerVar = new Timer(50, this); //ticking timer
        timerVar.start();
    }
    
    @Override
    public void paint(Graphics g) {
        Stage.getStage().syncElements();
        super.paint(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof Timer) {
            Stage.getStage().countDown();
            Stage.getStage().syncElements();
            this.revalidate();
            this.repaint();
            return;
        }
        for(int i = 0; i < actionsMenu.getComponents().length; i++) {
            actionsMenu.getComponents()[i].setEnabled(false);
        }
        Stage.getStage().choiceClicked(((JButton)e.getSource()).getText());
    }
    
    public static Display getInstance() {
        return displayVar;
    }

    public static Component getComponent(String type) {
        switch(type) {
            case "choice": return actionsMenu;
            case "image": return imageMenu;
            case "input": return inMenu;
            case "text": return dialog;
            case "stats": return stats;
            case "stats2": return stats2;
            default: return null;
        }
    }
    
    public static JPanel getComponentPanel(String type) {
       if(type == "text" || type == "stats" || type == "stats2") return null;
       return (JPanel)getComponent(type);
    }
    
    public static Component[] getComponentArray(String type) {
        if(type == "text" || type == "stats" || type == "stats2") return null;
        return getComponentPanel(type).getComponents();
    }
}
