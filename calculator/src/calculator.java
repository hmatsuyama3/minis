import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Calculator implements ActionListener{
    //declare everything we need.
    JFrame frame;
    JTextField textfield; //holds all the numbers that are being inputted
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton, negButton;
    JButton decButton, equButton, delButton, clrButton;
    JPanel panel; //panel to hold all the buttons

    Font myFont = new Font("Verdana", Font.BOLD, 30);

    double num1 = 0, num2=0, result=0;
    char operator =' '; //will hold operator character

    Calculator(){
        //constructor
        frame = new JFrame("Calculator"); //finish initializing the frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textfield=new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont); //you can reuse this font declared earlier
        textfield.setEditable(false); //without this - anyone can just type in text

        //instantiate buttons for each function
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");

        //add the fucntion buttons to the array
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        //add action listener to each function button
        for(int i=0; i<functionButtons.length; i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false); //gets rid of the "focusability" its the weird outline on button when you click it
        }

        //create buttons for numbers
        for(int i=0; i<numberButtons.length; i++){
            //step 1: finish instantiating number buttons
            numberButtons[i] = new JButton(String.valueOf(i)); //add number to button as a String
            //finish creating the buttons
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        //delete and clear buttons are not on the JPanel with grid layout. set bounds separately
        delButton.setBounds(150, 430, 100,50);
        clrButton.setBounds(250,430,100,50);
        negButton.setBounds(50, 430, 100, 50);

        //panel was declared before constructor but needs instantiated
        panel = new JPanel();
        panel.setBounds(50,100,300,300);
        panel.setLayout(new GridLayout(4,4,10,10)); //4 by 4 grid with 10px in between

        //add buttons to panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton); //the top row is number 1-3 and the add operand
        //row 2
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        //row 3
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        //row 4
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(negButton);
        frame.add(delButton); //add these buttons separately since they're not on the jPanel withe numbers & operators.
        frame.add(clrButton);
        frame.add(panel); //add Jpanel that holds the buttons onto the jframe
        frame.add(textfield);//add the text field
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override //add functionality to the calculator!
    public void actionPerformed(ActionEvent e) {
        //update text field with number that is clicked - number buttons only!
        for(int i=0; i<10; i++){
            if(e.getSource() == numberButtons[i]){
                textfield.setText(textfield.getText().concat(String.valueOf(i))); //concat will update value with each number.
            }
        }
        //functionality for decimal button
        if(e.getSource()==decButton){
            if(!textfield.getText().contains(".")){ //check if a decimal is already in the text field.
                textfield.setText(textfield.getText().concat("."));
            }
        }
        //functionality for operands!
        if(e.getSource()==addButton){
            if (num2 != 0) { //if trying to add more than one number
                equals(num1, num2, operator); //will set num1 to the result
                num2 = num1;
            }
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if(e.getSource()==subButton){
            if (num2 != 0) {
                equals(num1, num2, operator);
                num2 = num1;
            }
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if(e.getSource()==mulButton){
            if (num2 != 0) {
                equals(num1, num2, operator);
                num2 = num1;
            }
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        }
        if(e.getSource()==divButton){
            if (num2 != 0) {
                equals(num1, num2, operator);
                num2 = num1;
            }
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }

        //neg button
        if(e.getSource()==negButton){
            double temp = Double.parseDouble(textfield.getText());
            temp*=-1;
            textfield.setText(String.valueOf(temp));
        }

        //equals button
        if(e.getSource()==equButton){

            equals(num1, num2,operator);
            //num1=0;
            num2=0;
            operator=' ';
            result = 0;
//            num2 = Double.parseDouble(textfield.getText());
//
//            switch (operator){
//                case'+':
//                    result = num1+num2;
//                    break;
//                case'-':
//                    result = num1-num2;
//                    break;
//                case'*':
//                    result = num1*num2;
//                    break;
//                case'/':
//                    result = num1/num2;
//                    break;
//            }
//            textfield.setText(String.valueOf(result));
//            num1=result;
        }

        //clear button
        if(e.getSource()==clrButton){
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = ' ';
            textfield.setText("");
        }

        //delete button - remove one number at a time :)
        if(e.getSource()==delButton){
            String string = textfield.getText();
            textfield.setText("");
            for(int i=0; i<string.length()-1; i++){
                textfield.setText(textfield.getText()+string.charAt(i));
            }
        }
    }

    public void equals(double num1, double num2, char operator){
        if(Integer.parseInt(textfield.getText())==0){
            textfield.setText("Error");
            num1=0;
            num2=0;
            result=0;
            operator=' ';
        }else{
            num2 = Double.parseDouble(textfield.getText());

            switch (operator){
                case'+':
                    result = num1+num2;
                    break;
                case'-':
                    result = num1-num2;
                    break;
                case'*':
                    result = num1*num2;
                    break;
                case'/':
                    result = num1/num2;
                    break;
                case' ': //bug fix for inputting no operator
                    result = num2;
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1=result;
        }

    }
}