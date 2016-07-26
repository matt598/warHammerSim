import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

class UserInterface extends JFrame{
    private static String [] unitData;
    private Squadron squad;
    private Army army1 = new Army();
    private Army army2 = new Army();
    private Army army;
    private Container contentPane;
    private UserInterface(){
        super("Welcome to the WarHammerSim");
        army1 = new Army();
        army2 = new Army();
        squad = army1.GetSquad(0);
        army = army1;
        contentPane = getContentPane();
        setSize(500, 300);
        SetUpContentPane();
        MakeUnitContentPane();
    }

    public static void main (String [] args){
        UserInterface app;
        app = new UserInterface();
        app.setVisible(true);
    }

    private void ChangeSquad(int sq){
        if(army.getNumOfSquads()<sq+1){
            army.addSquad();
            ChangeSquad(sq);
        }
        squad = army.GetSquad(sq);
    }

    private void ChangeArmy(){
        if(army == army1){ army = army2; }
        else{ army = army1; }
    }

    private boolean clearData(){
        if(0 == JOptionPane.showConfirmDialog(null,"Are you sure you want to clear the data?", "Confirm Data Clear",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE)){
            army1 = new Army();
            army2 = new Army();
            squad = army1.GetSquad(0);
            army = army1;
            return true;
        }
        return false;
    }

    private void SetUpContentPane(){
        GenerateMenuBar();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void ClearContentPane(){
        contentPane.removeAll();
        contentPane.revalidate();
        contentPane.repaint();
        contentPane = getContentPane();
    }

    private void MakeUnitContentPane(){
        ClearContentPane();

        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.VERTICAL;
        String [] lableStatTitles = {"M","WS","BS","S","T","W","I","A","Ld","#"};

        JTextArea textArea = new JTextArea("This Squadron consists of: \nUnit:\tM,  WS, BS, S,  T,  W,  I,  A,  Ld, #"+squad.displayUnits());
        textArea.setOpaque(false);
        textArea.setEditable(false);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = lableStatTitles.length;
        c.weighty = 1;
        contentPane.add(textArea, c);
        JLabel label;
        JTextField [] textField = GenerateTextFields(lableStatTitles.length);
        c.weightx = 0.5;
        c.weighty = 0.5;
        label = new JLabel ("Unit stats",SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = lableStatTitles.length;
        contentPane.add(label, c);
        c.gridwidth = 1;
        for(int x = 0; x < lableStatTitles.length;x++){
            label= new JLabel(lableStatTitles[x],SwingConstants.CENTER);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = x;
            c.gridy = 2;
            contentPane.add(label, c);
            c.gridy = 3;
            contentPane.add(textField[x], c);
        }
        c.gridx = lableStatTitles.length;
        c.gridy = 2;
        Button add = new Button("Add Units");
        add.addActionListener(e->{
            for(int x = 0; x < lableStatTitles.length; x++){
                    unitData[x] = textField[x].getText();
                    textField[x].setText("");
                }
                textArea.setText(textArea.getText() + "\n" + squad.addUnit(unitData));
        });
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth=lableStatTitles.length-4;
        contentPane.add(add, c);
        Button clear = new Button("Clear");
        clear.addActionListener(e->ClearContentPane());

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        contentPane.add(clear, c);
        Button editArmy = new Button("Edit Army");
        editArmy.addActionListener(e->EditSquadContentPane());

        c.gridx = lableStatTitles.length-2;
        c.gridy = 4;
        c.gridwidth = 2;
        contentPane.add(editArmy, c);
    }

    private void EditSquadContentPane(){
        ClearContentPane();

        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.VERTICAL;
        JTextArea textArea = new JTextArea("This Army consists of these Squads: " + army.displayArmy());

        textArea.setOpaque(false);
        textArea.setEditable(false);
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        c.gridwidth = 3;
        contentPane.add(textArea, c);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridy = 4;
        c.gridx = 0;
        Button run = new Button("Run");
        run.addActionListener(e->MakeBattleContentPane());
        c.gridy = 4;
        c.gridwidth = 1;
        contentPane.add(run, c);
        Button switchArmy = new Button("Switch Army");
        switchArmy.addActionListener(e->{
            ChangeArmy();
            textArea.setText("This Army consists of these Squads: " + army.displayArmy());
        });
        c.gridx = 1;
        contentPane.add(switchArmy, c);
        c.gridx = 2;
        Button pickSquad = new Button("Pick Squad");
        pickSquad.addActionListener(e->{
            Object[] options = {"1",  "2",  "3"};
            ChangeSquad(JOptionPane.showOptionDialog(null,"Which Squad would you like",
                "Pick Squad:",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]));
            MakeUnitContentPane();
        });
        contentPane.add(pickSquad, c);
    }

    private void MakeBattleContentPane(){
        ClearContentPane();

        //needs to display 2 lists of squadron buttons
        //when one from each list is selected, they will both look different
        //should be a simulate button in the bottom middle that will make a popup summary of the battle.
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.VERTICAL;
        JTextArea textArea = new JTextArea("Pick two Squads to run the sim with");
        textArea.setOpaque(false);
        textArea.setEditable(false);
        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 1;
        c.gridwidth = 3;
        contentPane.add(textArea, c);
    }

    private JTextField[] GenerateTextFields(int xWidth){
        JTextField [] textFields = new JTextField [xWidth];
        unitData = new String [xWidth];
        for(int x = 0; x < xWidth; x++){
            final int finX = x;
            textFields[x] = new JTextField();
            textFields[x].addActionListener(e->unitData[finX] = textFields[finX].getText());
        }
        return textFields;
    }

    private void GenerateMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        JMenu aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);
        JMenu optionMenu = new JMenu("Options");
        menuBar.add(optionMenu);

        JMenuItem helpAction = new JMenuItem("Instructions");
        helpMenu.add(helpAction);
        helpAction.addActionListener(e->displayInstructionsBox());

        JMenuItem aboutAction = new JMenuItem("About the WarHammerSim");
        aboutMenu.add(aboutAction);
        aboutAction.addActionListener(e->displayAboutBox());

        JMenuItem clearAction = new JMenuItem("Clear Data");
        optionMenu.add(clearAction);
        clearAction.addActionListener(e->clearData());

        JMenuItem setPointCap = new JMenuItem("Establish Point Cap");
        optionMenu.add(setPointCap);
    }

    private void displayInstructionsBox(){
        JOptionPane.showMessageDialog(this, "Put in the stats for the two sides fighting\n# represents the number of them,\nNOTE: YOU NEED TO PRESS ENTER AFTER EACH NUMBER",
                "Instructions for the WarHammerSim", JOptionPane.YES_NO_OPTION);
    }

    private void displayAboutBox(){
        JOptionPane.showMessageDialog(this, "WarHammerSim was written by Matt Sauer",
                "About the WarHammerSim", JOptionPane.YES_NO_OPTION);
    }
}
