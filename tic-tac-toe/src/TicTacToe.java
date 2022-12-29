import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    Random random = new Random(); //use to determine who goes first randomly
    JFrame frame = new JFrame(); //frame for the game
    JPanel title_panel = new JPanel(); //panel to hold the title
    JPanel button_panel = new JPanel(); //panel to hold the buttons
    JLabel textfield = new JLabel(); //label to display messages
    JButton[] buttons = new JButton[9]; //9 buttons for the game - it lives in an array.
    boolean player1_turn; //true if it is player 1's turn. false if it's not.
    TicTacToe(){
        //make the frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout()); //allows for customization later (see frame.add....at the end)
        frame.setVisible(true);

        //customize the text field.
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe"); //initial text - this will change with the game
        textfield.setOpaque(true);

        //customize title panel. text field will be added to the title panel. title panel will then be added to the frame!
        //text field will also change depending on what is going on in the game.
        title_panel.setLayout(new BorderLayout()); //user border layout..
        title_panel.setBounds(0,0,800,100); //coordinates of where you want the title_panel to start, then the size.

        //customize button panel
        button_panel.setLayout(new GridLayout(3,3)); //different layout!
        button_panel.setBackground(new Color(150,150, 150));

        //add buttons to button panel.
        //can use a for loop to add them to the grid since we used an array
        for(int i=0; i<9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this); //add an action listener to each button!
        }

        //add text field to title panel
        title_panel.add(textfield);
        //add title panel to frame
        frame.add(title_panel, BorderLayout.NORTH); //adding BorderLayout.NORTH puts the title panel to the top - without it, it takes up the whole Jframe
        //add button panel to frame - it will live under the title panel.
        frame.add(button_panel);

        firstTurn(); //call method to determine who goes first
    }

    @Override //required for ActionListener interface
    public void actionPerformed(ActionEvent e) { //each button has an action listener.
        for(int i=0; i<9; i++){ //every time something is clicked, app will loop through all the available buttons.
            if(e.getSource()==buttons[i]){ //if the button clicked matches the button in the array..
                if(player1_turn){               //first, check whose turn it is.
                    if(buttons[i].getText()==""){ //then, check to see if there's already text assigned to it.
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X"); //if no text, change it
                        player1_turn=false; //then flip the turn to the other player.
                        textfield.setText("O turn");
                        check(); //check to see if any winning conditions were met
                    }
                }else{                          //same thing, except it's player 2's turn.
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        player1_turn=true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }

    }
    public void firstTurn(){
     //method to decide who goes first
        //Use thread.sleep to have game title "tic tac toe" display for some time before it changes to reflect who's turn it is.
        try{
            Thread.sleep(3000); //this needs a try-catch block.
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        if(random.nextInt(2)==0){ //get a random number, 0 or 1.
            player1_turn = true; //if number is 0, player 1's turn.
            textfield.setText("X turn"); //indicate that it is player 1's turn on the title panel by changing the text field.
        }else{
            player1_turn = false;
            textfield.setText("O turn");
        }
    }

    public void check(){
     //check if any winning conditions are met
        if(     (buttons[0].getText()=="X")&& 
                (buttons[1].getText()=="X")&&
                (buttons[2].getText()=="X")){
            xWins(0,1,2);
        }
        if(     (buttons[3].getText()=="X")&&
                (buttons[4].getText()=="X")&&
                (buttons[5].getText()=="X")){
            xWins(3,4,5);
        }
        if(     (buttons[6].getText()=="X")&&
                (buttons[7].getText()=="X")&&
                (buttons[8].getText()=="X")){
            xWins(6,7,8);
        }
        if(     (buttons[0].getText()=="X")&&
                (buttons[3].getText()=="X")&&
                (buttons[6].getText()=="X")){
            xWins(0,3,6);
        }
        if(     (buttons[1].getText()=="X")&&
                (buttons[4].getText()=="X")&&
                (buttons[7].getText()=="X")){
            xWins(1,4,7);
        }
        if(     (buttons[2].getText()=="X")&&
                (buttons[5].getText()=="X")&&
                (buttons[8].getText()=="X")){
            xWins(2,1,8);
        }
        if(     (buttons[0].getText()=="X")&&
                (buttons[4].getText()=="X")&&
                (buttons[8].getText()=="X")){
            xWins(0,4,8);
        }
        if(     (buttons[2].getText()=="X")&&
                (buttons[4].getText()=="X")&&
                (buttons[6].getText()=="X")){
            xWins(2,4,6);
        }
        //O win conditions
        if(     (buttons[0].getText()=="O")&&
                (buttons[1].getText()=="O")&&
                (buttons[2].getText()=="O")){
            oWins(0,1,2);
        }
        if(     (buttons[3].getText()=="O")&&
                (buttons[4].getText()=="O")&&
                (buttons[5].getText()=="O")){
            oWins(3,4,5);
        }
        if(     (buttons[6].getText()=="O")&&
                (buttons[7].getText()=="O")&&
                (buttons[8].getText()=="O")){
            oWins(6,7,8);
        }
        if(     (buttons[0].getText()=="O")&&
                (buttons[3].getText()=="O")&&
                (buttons[6].getText()=="O")){
            oWins(0,3,6);
        }
        if(     (buttons[1].getText()=="O")&&
                (buttons[4].getText()=="O")&&
                (buttons[7].getText()=="O")){
            oWins(1,4,7);
        }
        if(     (buttons[2].getText()=="O")&&
                (buttons[5].getText()=="O")&&
                (buttons[8].getText()=="O")){
            oWins(2,1,8);
        }
        if(     (buttons[0].getText()=="O")&&
                (buttons[4].getText()=="O")&&
                (buttons[8].getText()=="O")){
            oWins(0,4,8);
        }
        if(     (buttons[2].getText()=="O")&&
                (buttons[4].getText()=="O")&&
                (buttons[6].getText()=="O")){
            oWins(2,4,6);
        }
    }

    public void xWins(int a, int b, int c){
    //called when conditions for x to win are met.
        //change winning button colors!
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i=0; i<9; i++){ //go through all the buttons and disable the action listener
            buttons[i].setEnabled(false);
        }
        textfield.setText("X wins"); //change title field to indicate winner
    }
    public void oWins(int a, int b, int c){
    //called when conditions for o to win are met.
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i=0; i<9; i++){ //go through all the buttons and disable the action listener
            buttons[i].setEnabled(false);
        }
        textfield.setText("O wins");
    }
}
