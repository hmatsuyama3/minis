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
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        //customize the text field.
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);
    }

    @Override //required for ActionListener interface
    public void actionPerformed(ActionEvent e) {

    }
    public void firstTurn(){
     //method to decide who goes first

    }

    public void check(){
     //check if any winning conditions are met

    }

    public void xWins(int a, int b, int c){
     //conditions for x to win
    }
    public void oWins(int a, int b, int c){
        //conditions for o to win
    }
}
