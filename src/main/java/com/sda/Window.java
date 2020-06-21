package com.sda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {
    private JFrame frame;
    private JTabbedPane pane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JButton panel1button;
    private JButton panel2button;
    private JButton panel3button;
    private JButton buton1;
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;


    private JList poleListyDrozyn;
    private JTextField pulaDruzyn;
    private JButton nowaDruzynaButton;
    private JButton usunDruzyneButton;
    private JButton modyfikujDruzyneButton;
    private JComboBox konkurencjaCombo;
    private JRadioButton siatkowkaButton;
    private JRadioButton przeciaganieLinyButton;
    private JRadioButton dwaOgnieButton;

    public void showGuiWindow() {
        frame = new JFrame("System zarządzania turniejem");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setJMenuBar(addMenuBar());
        frame.setPreferredSize(new Dimension(400, 400));
        addComponents();
        //addActionToConverterBtn();
        frame.setSize(600, 600);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(addMenuBar());


    }

    private JMenuBar addMenuBar() {
        JMenu fileMenu = new JMenu("File");
        JMenu optMenu = new JMenu("Options");
        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        exit.addActionListener(this);
        fileMenu.add(exit);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(optMenu);
        return menuBar;
    }

    private void addActionToConverterBtn() {
        buton1.addActionListener(this);
    }

    private void addComponents() {
        pane = new JTabbedPane();
        frame.add(pane);
        panel1 = new JPanel();

      // String[] listaDrozyn = {"Karol","Piotr","Damian"};
        //poleListyDrozyn = new JList(listaDrozyn);
        poleListyDrozyn = new JList();
        poleListyDrozyn.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        poleListyDrozyn.setLayoutOrientation(JList.VERTICAL_WRAP);
        poleListyDrozyn.setVisibleRowCount(-1);
        poleListyDrozyn.setFont(new Font("Calibri", Font.BOLD, 18));
        poleListyDrozyn.setForeground(Color.BLACK);
        poleListyDrozyn.setPreferredSize(new Dimension(100, 200));
        JScrollPane listScroller = new JScrollPane(poleListyDrozyn);
        listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //poleListyDrozyn.setPreferredSize(new Dimension(100, 400));


        panel1button = new JButton("Enter");
        panel1button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "First Name is " + field1.getText());
                    }
                }
        );
        panel1.add(label1, BorderLayout.NORTH);
        //panel1.add(field1, BorderLayout.CENTER);
        panel1.add(panel1button, BorderLayout.SOUTH);
        panel1.add(listScroller, BorderLayout.CENTER);
        pane.addTab("Drużyny", panel1);
        panel2 = new JPanel();
        label2 = new JLabel("Pula Drużyn");
        field2 = new JTextField(20);
        field2.setFont(new Font("Calibri", Font.BOLD, 18));
        field2.setForeground(Color.RED);
        panel2button = new JButton("Enter");
        panel2button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "Middle Name is " + field2.getText());
                    }
                }
        );
        panel2.add(label2, BorderLayout.NORTH);
        panel2.add(field2, BorderLayout.CENTER);
        panel2.add(panel2button, BorderLayout.SOUTH);
        pane.addTab("Sędziowie", panel2);
        panel3 = new JPanel();
        label3 = new JLabel("Enter Last Name Here");
        field3 = new JTextField(20);
        field3.setFont(new Font("Calibri", Font.BOLD, 18));
        field3.setForeground(Color.BLUE);
        panel3button = new JButton("Enter");
        panel3button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "Last Name is " + field3.getText());
                    }
                }
        );
        panel3.add(label3, BorderLayout.NORTH);
        panel3.add(field3, BorderLayout.CENTER);
        panel3.add(panel3button, BorderLayout.SOUTH);
        pane.addTab("Turniej", panel3);

        panel4 = new JPanel();
        pane.addTab("Tablica Wyników", panel4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //c*1,8+32->F
//            double cels = Double.parseDouble(inputFiled.getText());
//            double far = cels*1.8+32;
//            String temp = String.format("%.2f Farenheit",far);
//            System.out.println(temp);
//            fahrenheitLabel.setText(temp);
//            frame.pack();
    }
}