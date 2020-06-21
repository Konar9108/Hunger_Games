package com.sda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
    private JList poleListyWybranychDrozyn;
    private JList poleListySedziow;
    private JTextField pulaDruzyn;

    private JButton nowaDruzynaButton;
    private JButton nowySedziaButton;
    private JButton usunSedziegoButton;
    private JButton modyfikujSedziegoButton;
    private JButton usunDruzyneButton;
    private JButton modyfikujDruzyneButton;
    private JButton zglosDruzyneButton;
    private JButton wycofajDruzyneButton;
    private JButton zglosLosoweDruzynyButton;
    private JComboBox konkurencjaCombo;
    private JRadioButton siatkowkaButton;
    private JRadioButton przeciaganieLinyButton;
    private JRadioButton dwaOgnieButton;

    public void showGuiWindow() {
        frame = new JFrame("System zarządzania turniejem");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setJMenuBar(addMenuBar());
        addComponents();
        frame.setSize(850, 500);
        frame.setMinimumSize(new Dimension(850,500));
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
        ////////////////////////////////////////////////////////////////////////////Pane 1
        panel1 = new JPanel();
        GridBagLayout layout1 = new GridBagLayout();
        panel1.setLayout(layout1);
        GridBagConstraints gbc1 = new GridBagConstraints();
        nowaDruzynaButton = new JButton("Nowa drużyna");
        nowaDruzynaButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "wstaw Nowa druzyna");
                    }
                }
        );
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.ipadx = 10;
        gbc1.ipady = 5;
        gbc1.insets = new Insets(5, 5, 5, 5);
        panel1.add(new JLabel(""),gbc1);
        gbc1.gridy=1;
        panel1.add(nowaDruzynaButton,gbc1);
        usunDruzyneButton= new JButton("Usuń drużynę");
        usunDruzyneButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "wybierz druzyne do usuniecia");
                    }
                }
        );
        gbc1.gridy = 2;
        panel1.add(usunDruzyneButton,gbc1);
        modyfikujDruzyneButton= new JButton("Modyfikuj drużynę");
        modyfikujDruzyneButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "wybierz druzyne do modyfikacji");
                    }
                }
        );
        gbc1.gridy = 3;
        panel1.add(modyfikujDruzyneButton,gbc1);
        zglosDruzyneButton= new JButton("Zgłoś drużynę");
        zglosDruzyneButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "Ma przesunac wybraną z listy druzynę z lewej listy na prawą");
                    }
                }
        );
        gbc1.gridy = 4;
        panel1.add(zglosDruzyneButton,gbc1);
        wycofajDruzyneButton= new JButton("Wycofaj drużynę");
        wycofajDruzyneButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "Ma przesunac wybraną z listy druzynę z prawej listy na lewa");
                    }
                }
        );
        gbc1.gridy = 5;
        panel1.add(wycofajDruzyneButton,gbc1);
        zglosLosoweDruzynyButton= new JButton("Zgłoś losowe drużyny");
        zglosLosoweDruzynyButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "wrzuca losowe druzyny z lewej listy na prawą");
                    }
                }
        );
        gbc1.gridy = 6;
        panel1.add(zglosLosoweDruzynyButton,gbc1);
        label1= new JLabel("Pula drużyn",SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 18));
        label1.setForeground(Color.BLACK);

        gbc1.gridx = 1;
        gbc1.gridy = 0;
        panel1.add(label1,gbc1);
        label2= new JLabel("Grające drużyny",SwingConstants.CENTER);
        label2.setFont(new Font("Arial", Font.BOLD, 18));
        label2.setForeground(Color.BLACK);
        gbc1.gridx = 2;
        gbc1.gridy = 0;
        panel1.add(label2,gbc1);
///myk myk myk
        // String[] listaDrozyn = {"Karol","Piotr","Damian"};
        //poleListyDrozyn = new JList(listaDrozyn);
        poleListyDrozyn = new JList();
        poleListyDrozyn.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        poleListyDrozyn.setLayoutOrientation(JList.VERTICAL_WRAP);
        poleListyDrozyn.setVisibleRowCount(-1);
        poleListyDrozyn.setFont(new Font("Calibri", Font.BOLD, 18));
        poleListyDrozyn.setForeground(Color.BLACK);
        poleListyDrozyn.setPreferredSize(new Dimension(200, 400));
        JScrollPane listScroller = new JScrollPane(poleListyDrozyn);
        listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        listScroller.setMinimumSize(new Dimension(200,230));
        gbc1.gridx=1;
        gbc1.gridy=1;
        gbc1.gridheight=8;
        gbc1.insets = new Insets(5, 5, 5, 5);
        panel1.add(listScroller, gbc1);
        // String[] listaDrozyn = {"Karol","Piotr","Damian"};
        //poleListyDrozyn = new JList(listaDrozyn);
        poleListyWybranychDrozyn = new JList();
        poleListyWybranychDrozyn.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        poleListyWybranychDrozyn.setLayoutOrientation(JList.VERTICAL_WRAP);
        poleListyWybranychDrozyn.setVisibleRowCount(-1);
        poleListyWybranychDrozyn.setFont(new Font("Calibri", Font.BOLD, 18));
        poleListyWybranychDrozyn.setForeground(Color.BLACK);
        poleListyWybranychDrozyn.setPreferredSize(new Dimension(200, 400));
        JScrollPane listScroller2 = new JScrollPane(poleListyWybranychDrozyn);
        listScroller2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        listScroller2.setMinimumSize(new Dimension(200,230));
        gbc1.gridx=2;
        panel1.add(listScroller2, gbc1);
        pane.addTab("Drużyny", panel1);
////////////////////////////////////////////////////////////////////////////////////////////Panel 2

        panel2 = new JPanel();
        GridBagLayout layout2 = new GridBagLayout();
        panel2.setLayout(layout2);
        GridBagConstraints gbc2 = new GridBagConstraints();
        nowySedziaButton = new JButton("Dodaj sędziego");
        nowySedziaButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "wstaw Nowa sedzia");
                    }
                }
        );
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.ipadx = 10;
        gbc2.ipady = 5;
        gbc2.insets = new Insets(5, 5, 5, 5);
        panel2.add(new JLabel(""),gbc2);
        gbc2.gridy=1;
        panel2.add(nowySedziaButton,gbc2);
        usunSedziegoButton= new JButton("Usuń sędziego");
        usunSedziegoButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "wybierz sedziego do usuniecia");
                    }
                }
        );
        gbc2.gridy = 2;
        panel2.add(usunSedziegoButton,gbc2);
        modyfikujSedziegoButton= new JButton("Modyfikuj sędziego");
        modyfikujSedziegoButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "wybierz sedziego do modyfikacji");
                    }
                }
        );
        gbc2.gridy = 3;
        panel2.add(modyfikujSedziegoButton,gbc2);

        label3= new JLabel("Lista dostępnych sędziów",SwingConstants.CENTER);
        label3.setFont(new Font("Arial", Font.BOLD, 18));
        label3.setForeground(Color.BLACK);
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        panel2.add(label3,gbc2);
///myk myk myk
        // String[] listaDrozyn = {"Karol","Piotr","Damian"};
        //poleListyDrozyn = new JList(listaDrozyn);
        poleListySedziow = new JList();
        poleListySedziow.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        poleListySedziow.setLayoutOrientation(JList.VERTICAL_WRAP);
        poleListySedziow.setVisibleRowCount(-1);
        poleListySedziow.setFont(new Font("Calibri", Font.BOLD, 18));
        poleListySedziow.setForeground(Color.BLACK);
        poleListySedziow.setPreferredSize(new Dimension(200, 400));
        JScrollPane listScroller3 = new JScrollPane(poleListySedziow);
        listScroller3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        listScroller3.setMinimumSize(new Dimension(200,230));
        gbc2.gridx=1;
        gbc2.gridy=1;
        gbc2.gridheight=8;
        gbc2.insets = new Insets(5, 5, 5, 5);
        panel2.add(listScroller3, gbc2);


        pane.addTab("Sędziowie", panel2);
        ////////////////////////////////////////////////////////////Pane 3
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
////////////////////////////////////////////////////////////////////////Pane 4
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