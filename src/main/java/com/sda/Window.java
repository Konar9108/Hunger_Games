package com.sda;

import com.sda.entities.*;
import com.sda.jdbc.HungerGamesService;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Window extends JFrame implements ActionListener {

    HungerGamesService service;
    private JFrame frame;
    private JTabbedPane pane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;


    private JList poleListyDrozyn;
    private JList poleListyWybranychDrozyn;
    private JList poleListySedziow;

    private JButton nowaDruzynaButton;
    private JButton nowySedziaButton;
    private JButton usunSedziegoButton;
    private JButton modyfikujSedziegoButton;
    private JButton usunDruzyneButton;
    private JButton modyfikujDruzyneButton;
    private JButton zglosDruzyneButton;
    private JButton wycofajDruzyneButton;
    private JButton zglosLosoweDruzynyButton;
    private JButton modyfikujMeczButton;
    private JButton generujMeczeButton;
    private ButtonGroup konkurencjeButtons;
    private JRadioButton siatkowkaButton;
    private JRadioButton przeciaganieLinyButton;
    private JRadioButton dwaOgnieButton;
    private JTable table;
    private JTable table2;

    private ArrayList<Team> zgloszoneDruzyny = new ArrayList<>();
    private GameType gameType = GameType.VOLLEYBALL;
    private Tournament tournament;
    private DefaultTableModel model;
    private DefaultTableModel model2;
    private  List<Game> gameList;


    public void setUpDB() {

        service = new HungerGamesService();
        service.openConnection();
        service.addJudge("Bogdan", "Bogdanowicz", 25);
        service.addJudge("Janusz", "Janowicz", 76);
        service.addJudge("Roman", "Romanowski", 65);
        service.addJudge("Adam", "Adamowicz", 35);
        service.addJudge("Piotr", "Piotrowicz", 43);

        service.addTeam("BULDOŻERY");
        service.addTeam("II LO");
        service.addTeam("KUCHARZE");
        service.addTeam("VII LO");
        service.addTeam("ROLNICY");
        service.addTeam("SDA");
        service.addTeam("ZSE");
        service.addTeam("BACKENDOWCY");

    }

    public void exitListener() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                service.closeConnection();
                System.out.println("zamknięto polaczenie do db i aplikację");
                System.exit(0);
            }
        });
    }




    public void showGuiWindow() {

        setUpDB();
        frame = new JFrame("System zarządzania turniejem");
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setJMenuBar(addMenuBar());
        addComponents();
        frame.setSize(850, 500);
        frame.setMinimumSize(new Dimension(850, 500));
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
        dwaOgnieButton.addActionListener(this);
    }

    private void addComponents() {
        exitListener();
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
                        String nazwaNowejDruzyny = JOptionPane.showInputDialog(frame, "Wpisz nazwę nowej drużyny", "Tworzenie nowej drużyny", JOptionPane.CANCEL_OPTION);
                        if (nazwaNowejDruzyny != null) {
                            service.addTeam(nazwaNowejDruzyny);
                            poleListyDrozyn.setListData(service.getAllTeamNames(service.findAllTeams()));
                        }
                    }
                }
        );
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.ipadx = 10;
        gbc1.ipady = 5;
        gbc1.insets = new Insets(5, 5, 5, 5);
        panel1.add(new JLabel(""), gbc1);
        gbc1.gridy = 1;
        panel1.add(nowaDruzynaButton, gbc1);
        usunDruzyneButton = new JButton("Usuń drużynę");
        usunDruzyneButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (poleListyDrozyn.getSelectedValue() == null) {
                            JOptionPane.showMessageDialog(null, "wybierz druzyne do usuniecia!");
                        } else {
                            String selectedTeam = poleListyDrozyn.getSelectedValue().toString();
                            service.deleteTeamFromGivenName(selectedTeam);
                            poleListyDrozyn.setListData(service.getAllTeamNames(service.findAllTeams()));

                        }
                    }
                }
        );
        gbc1.gridy = 2;
        panel1.add(usunDruzyneButton, gbc1);
        modyfikujDruzyneButton = new JButton("Modyfikuj drużynę");
        modyfikujDruzyneButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {

                        String teamName = poleListyDrozyn.getSelectedValue().toString();
                        String nowaNazwa = (String) JOptionPane.showInputDialog(null,null,"Modyfikacja drużyny",JOptionPane.PLAIN_MESSAGE,null,null,teamName);
                        if(nowaNazwa == null){
                            return;
                        }
                        Team team = service.findTeamByName(teamName);
                        try {
                            service.modifyTeam(team,nowaNazwa);
                            poleListyDrozyn.setListData(service.getAllTeamNames(service.findAllTeams()));

                        } catch (Exception e){
                            JOptionPane.showMessageDialog(null, "INVALID");
                        }
                    }
                }
        );
        gbc1.gridy = 3;
        panel1.add(modyfikujDruzyneButton, gbc1);
        zglosDruzyneButton = new JButton("Zgłoś drużynę");
        zglosDruzyneButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if(poleListyDrozyn.getSelectedValue() == null) {
                            JOptionPane.showMessageDialog(null, "Zaznacz drużynę z puli drużyn");
                        }
                        else {
                            String selectedTeam = poleListyDrozyn.getSelectedValue().toString();
                            Team team = service.findTeamByName(selectedTeam);
                            if(!zgloszoneDruzyny.contains(team)){
                                zgloszoneDruzyny.add(team);
                            }
                            poleListyWybranychDrozyn.setListData(service.getAllTeamNames(zgloszoneDruzyny));
                        }
                    }
                }
        );
        gbc1.gridy = 4;
        panel1.add(zglosDruzyneButton, gbc1);
        wycofajDruzyneButton = new JButton("Wycofaj drużynę");
        wycofajDruzyneButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if(poleListyWybranychDrozyn.getSelectedValue() == null) {
                        JOptionPane.showMessageDialog(null, "Zaznacz drużynę z puli drużyn");
                    }
                    else {
                        String selectedTeam = poleListyWybranychDrozyn.getSelectedValue().toString();
                        Team team = service.findTeamByName(selectedTeam);
                        zgloszoneDruzyny.remove(team);
                        poleListyWybranychDrozyn.setListData(service.getAllTeamNames(zgloszoneDruzyny));
                    }
                }
            }
        );
        gbc1.gridy = 5;
        panel1.add(wycofajDruzyneButton,gbc1);
        zglosLosoweDruzynyButton= new JButton("Zgłoś losowe drużyny");
        zglosLosoweDruzynyButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        zgloszoneDruzyny = service.getRandomTeams();
                        poleListyWybranychDrozyn.setListData( service.getAllTeamNames(zgloszoneDruzyny));
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

        String[] listaDrozyn = service.getAllTeamNames(service.findAllTeams());
        poleListyDrozyn = new JList(listaDrozyn);
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
                        JTextField field1 = new JTextField();
                        JTextField field2 = new JTextField();
                        JTextField field3 = new JTextField();

                        Object [] fields = {
                                "Imię", field1,
                                "Nazwisko", field2,
                                "Wiek", field3,
                        };

                        JOptionPane.showConfirmDialog(null,fields,"Dodaj nowego Sędziego",JOptionPane.OK_CANCEL_OPTION);

                        String imieSedziego = field1.getText();
                        String nazwiskoSedziego = field2.getText();
                        String wiekSedziego = field3.getText();

                        try {
                                int wiek = Integer.parseInt(wiekSedziego);
                                service.addJudge(imieSedziego, nazwiskoSedziego, wiek);
                                poleListySedziow.setListData(service.getAllJudgesNames(service.findAllJudges()));

                        } catch (Exception e){
                            JOptionPane.showMessageDialog(null, "INVALID");
                        }
                    }
                }
        );
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.ipadx = 10;
        gbc2.ipady = 5;
        gbc2.insets = new Insets(5, 5, 5, 5);
        panel2.add(new JLabel(""), gbc2);
        gbc2.gridy = 1;
        panel2.add(nowySedziaButton, gbc2);
        usunSedziegoButton = new JButton("Usuń sędziego");
        usunSedziegoButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JTextField field1 = new JTextField();
                        JTextField field2 = new JTextField();
                        JTextField field3 = new JTextField();

                        String sedzia = poleListySedziow.getSelectedValue().toString();
                        String[] sedziaSplit = sedzia.split("\\s+");
                        field1.setText(sedziaSplit[0]);
                        field2.setText(sedziaSplit[1]);
                        field3.setText(sedziaSplit[2]);


                        Judge judge = service.findJudgeFromNameAndLastNameAndAge(sedziaSplit[0],sedziaSplit[1],Integer.parseInt(sedziaSplit[2]));
                        try {
                            service.deleteJudge(judge);
                            poleListySedziow.setListData(service.getAllJudgesNames(service.findAllJudges()));

                        } catch (Exception e){
                            JOptionPane.showMessageDialog(null, "BŁĄD");
                        }
                    }
                }
        );
        gbc2.gridy = 2;
        panel2.add(usunSedziegoButton, gbc2);
        modyfikujSedziegoButton = new JButton("Modyfikuj sędziego");
        modyfikujSedziegoButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JTextField field1 = new JTextField();
                        JTextField field2 = new JTextField();
                        JTextField field3 = new JTextField();

                        String sedzia = poleListySedziow.getSelectedValue().toString();
                        String[] sedziaSplit = sedzia.split("\\s+");
                        field1.setText(sedziaSplit[0]);
                        field2.setText(sedziaSplit[1]);
                        field3.setText(sedziaSplit[2]);

                        Object [] fields = {
                                "Imię", field1,
                                "Nazwisko", field2,
                                "Wiek", field3,
                        };

                        JOptionPane.showConfirmDialog(null,fields,"Modyfikuj sędziego",JOptionPane.OK_CANCEL_OPTION);

                        Judge judge = service.findJudgeFromNameAndLastNameAndAge(sedziaSplit[0],sedziaSplit[1],Integer.parseInt(sedziaSplit[2]));

                        String imieSedziego = field1.getText();
                        String nazwiskoSedziego = field2.getText();
                        String wiekSedziego = field3.getText();

                        try {
                            int wiek = Integer.parseInt(wiekSedziego);
                            service.modifyJudge(judge,imieSedziego,nazwiskoSedziego,wiek);
                            poleListySedziow.setListData(service.getAllJudgesNames(service.findAllJudges()));

                        } catch (Exception e){
                            JOptionPane.showMessageDialog(null, "INVALID");
                        }
                    }
                }
        );
        gbc2.gridy = 3;
        panel2.add(modyfikujSedziegoButton, gbc2);

        label3 = new JLabel("Lista dostępnych sędziów", SwingConstants.CENTER);
        label3.setFont(new Font("Arial", Font.BOLD, 18));
        label3.setForeground(Color.BLACK);
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        panel2.add(label3, gbc2);
///myk myk myk
        String[] listaSedziow = service.getAllJudgesNames(service.findAllJudges());
        poleListySedziow = new JList(listaSedziow);
        poleListySedziow.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        poleListySedziow.setLayoutOrientation(JList.VERTICAL_WRAP);
        poleListySedziow.setVisibleRowCount(-1);
        poleListySedziow.setFont(new Font("Calibri", Font.BOLD, 18));
        poleListySedziow.setForeground(Color.BLACK);
        poleListySedziow.setPreferredSize(new Dimension(200, 400));
        JScrollPane listScroller3 = new JScrollPane(poleListySedziow);
        listScroller3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        listScroller3.setMinimumSize(new Dimension(200, 230));
        gbc2.gridx = 1;
        gbc2.gridy = 1;
        gbc2.gridheight = 8;
        gbc2.insets = new Insets(5, 5, 5, 5);
        panel2.add(listScroller3, gbc2);


        pane.addTab("Sędziowie", panel2);
        //////////////////////////////////////////////////////////////////////////////////////////////////////Pane 3
        panel3 = new JPanel();
        GridBagLayout layout3 = new GridBagLayout();
        panel3.setLayout(layout3);
        GridBagConstraints gbc3 = new GridBagConstraints();
        siatkowkaButton = new JRadioButton("Turniej Siatkówki");
        siatkowkaButton.setSelected(true);
        siatkowkaButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        gameType = GameType.VOLLEYBALL;
                    }
                }
        );
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.ipadx = 10;
        gbc3.ipady = 5;
        gbc3.insets = new Insets(5, 5, 5, 5);
        panel3.add(new JLabel(""), gbc3);
        gbc2.gridy = 1;
        panel3.add(siatkowkaButton, gbc3);
        dwaOgnieButton = new JRadioButton("Turniej Dwóch Ogni");
        dwaOgnieButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        gameType = GameType.DODGEBALL;
                    }
                }
        );
        gbc3.gridy = 2;
        panel3.add(dwaOgnieButton, gbc3);
        przeciaganieLinyButton = new JRadioButton("Turniej Przeciągania Liny");
        przeciaganieLinyButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        gameType = GameType.TUG_OF_WAR;
                    }
                }
        );
        gbc3.gridy = 3;
        panel3.add(przeciaganieLinyButton, gbc3);
        konkurencjeButtons = new ButtonGroup();
        konkurencjeButtons.add(siatkowkaButton);
        konkurencjeButtons.add(dwaOgnieButton);
        konkurencjeButtons.add(przeciaganieLinyButton);
        modyfikujMeczButton = new JButton("Modyfikuj wynik meczu");
        modyfikujMeczButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        int row = table.getSelectedRow();
                        if(row ==-1) JOptionPane.showMessageDialog(null, "Zaznacz mecz do wstawienia wyniku");
                        else {
                            JTextField field11 = new JTextField();
                            JTextField field21 = new JTextField();
                            String wynik = (String)table.getValueAt(row,4).toString();
                            field11.setText("");
                            field21.setText("");
                            Object [] fields1 = {
                                    "Wynik drużyny: " + table.getValueAt(row,1).toString(), field11,
                                    "Wynik drużyny: " + table.getValueAt(row,2).toString(), field21,
                            };
                            JOptionPane.showConfirmDialog(null,fields1,"Modyfikuj wynik meczu",JOptionPane.OK_CANCEL_OPTION);
                            String score = field11.getText()+ "-"+field21.getText();
                            service.modifyGame((Integer) table.getValueAt(row, 0),score);
                            refreshGameJTable();
                        }
                    }
                }
        );
        gbc3.gridy = 4;
        panel3.add(modyfikujMeczButton, gbc3);
        generujMeczeButton = new JButton("Generuj Mecze");
        generujMeczeButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                            int input = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz utworzyć nowy turniej?", "Uwaga!",JOptionPane.YES_NO_OPTION);
                            if(input==JOptionPane.YES_OPTION) {
                                tournament = new Tournament();
                                tournament.setTeamList(zgloszoneDruzyny);
                                if (zgloszoneDruzyny.size() < 3) {
                                    JOptionPane.showMessageDialog(null, "Za mało zgłoszonych drużyn! Muszą być przynajmniej 3!");
                                    return;
                                }
                                tournament.setJudgeList(service.getRandomJudges());
                                tournament.setGameType(gameType);
                                service.getEntityManager().getTransaction().begin();
                                service.getEntityManager().persist(tournament);
                                service.getEntityManager().getTransaction().commit();
                                service.generateTournamentMatches(tournament);
                                refreshGameJTable();
                            }
                    }
                }
        );
        gbc3.gridy = 5;
        panel3.add(generujMeczeButton, gbc3);

        label4 = new JLabel("Lista meczów", SwingConstants.CENTER);
        label4.setFont(new Font("Arial", Font.BOLD, 18));
        label4.setForeground(Color.BLACK);
        gbc3.gridx = 1;
        gbc3.gridy = 0;
        panel3.add(label4, gbc3);
        String[] columnNames = {"ID meczu","Drużyna 1", "Drużyna 2", "Wynik", "Sędzia"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable() {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.setModel(model);
        table.setFont(new Font("Arial",0,14));

        JScrollPane listScroller4 = new JScrollPane(table);
        listScroller4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        listScroller4.setMinimumSize(new Dimension(200, 230));
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        gbc3.gridy = 1;
        gbc3.gridheight = 8;
        panel3.add(listScroller4, gbc3);
        pane.addTab("Turniej", panel3);
//////////////////////////////////////////////////////////////////////////////////////////////////////Pane 4
        panel4 = new JPanel();
        GridBagLayout layout4 = new GridBagLayout();
        panel4.setLayout(layout4);
        GridBagConstraints gbc4 = new GridBagConstraints();

        label5 = new JLabel("Tabilca Wyników", SwingConstants.CENTER);
        label5.setFont(new Font("Arial", Font.BOLD, 18));
        label5.setForeground(Color.BLACK);
        gbc4.fill = GridBagConstraints.HORIZONTAL;
        gbc4.gridx = 0;
        gbc4.gridy = 0;
        gbc4.ipadx = 10;
        gbc4.ipady = 5;
        gbc4.insets = new Insets(5, 5, 5, 5);
        panel4.add(label5, gbc4);
        String[] columnNames2 = {"Drużyna","Punktacja"};
        model2 = new DefaultTableModel();
        model2.setColumnIdentifiers(columnNames2);
        table2 = new JTable() {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table2.setModel(model2);
        table2.setFont(new Font("Arial",0,14));

        JScrollPane listScroller5 = new JScrollPane(table2);
        listScroller5.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScroller5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        listScroller5.setMinimumSize(new Dimension(200, 230));
        table2.setAutoCreateRowSorter(true);
        table2.setFillsViewportHeight(true);
        gbc4.gridy = 1;
        gbc4.gridheight = 8;
        panel4.add(listScroller5, gbc4);

        pane.addTab("Tablica Wyników", panel4);
    }
    //{"Drużyna 1", "Drużyna 2", "Wynik", "Sędzia", "Sędzia Asystujący 1, Sędzia asystujący 2"};
    void refreshGameJTable() {
        gameList = tournament.getGameList();
        model.setRowCount(0);
        for (int i = 0; i<gameList.size(); i++) {
            int col0 = gameList.get(i).getMatch_id();
            String col1 = gameList.get(i).getTeamOne().getName();
            String col2 = gameList.get(i).getTeamTwo().getName();
            String col3 = gameList.get(i).getResult();
            String col4 = gameList.get(i).getMainJudge().getFirst_name() + " " + gameList.get(i).getMainJudge().getLast_name();
            Object[] objs = {col0, col1,col2,col3,col4};
            model.addRow(objs);


        }
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