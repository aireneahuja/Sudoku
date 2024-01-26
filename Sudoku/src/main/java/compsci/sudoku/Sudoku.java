package compsci.sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *<h1>Sudoku</h1>
 * This Sudoku program implements an application through which a user can play
 * the game Sudoku. 
 * @author aireneahuja
 * @version 1.0
 * @since 2023-01-31
 */
public class Sudoku extends JFrame{
    Button[][] buttonGrid;
    NumberPadButtons [][] numberGrid;
    public static int ROWS = 3;
    public static int COLS = 3;
    public static int WID = 9;
    public static int LEN = 9;
    boolean wonGame;
    int strike = 0;
    boolean lostGame;
    JLabel message;
  
    int[][] nums = {
        {2,3,5,1,4,7,9,8,6},
        {4,1,8,9,6,5,7,2,3},
        {6,9,7,2,8,3,1,4,5},
        {9,8,6,5,7,4,2,3,1},
        {5,7,3,8,1,2,4,6,9},
        {1,4,2,6,3,9,8,5,7},
        {7,5,9,3,2,8,6,1,4},
        {8,6,4,7,5,1,3,9,2},
        {3,2,1,4,9,6,5,7,8},
    };
    
    boolean[][] visible = {
        {true,false,true,false,false,true,false,false,true},
        {true,false,false,true,true,false,false,true,false},
        {false,false,false,false,true,false,false,true,true},
        {true,true,false,false,true,true,false,false,false},
        {true,true,false,true,false,true,false,true,true},
        {false,false,false,true,true,false,false,true,true},
        {true,true,false,false,true,false,false,false,false},
        {false,true,false,false,true,true,false,false,true},
        {true,false,false,true,false,false,true,false,true},
    };
    /*
    //using this array of 'visible' shows all numbers except one
    
    boolean[][] visible = {
        {false,true,true,true,true,true,true,true,true},
        {true,true,true,true,true,true,true,true,true},
        {true,true,true,true,true,true,true,true,true},
        {true,true,true,true,true,true,true,true,true},
        {true,false,true,true,true,true,true,true,true},
        {true,true,true,true,true,true,true,true,true},
        {true,true,true,true,true,true,true,true,true},
        {true,true,true,true,true,true,true,true,true},
        {true,true,true,true,true,true,true,true,true},
    };
    */
    int[][] numpad = {
        {1,2,3},{4,5,6},{7,8,9}
    };
    
    int numEnt = 1;
    public int getNumEnt() {
        return numEnt;
    }
    /**
     * This is the constructor method that sets the layout and mechanics of the
     * game when it is first run. 
     */    
    public Sudoku(){
        super("Sudoku");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        //add message
        JPanel messagePanel = new JPanel();
        message = new JLabel("Fill in the numbers using the keypad below!");
        message.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        messagePanel.add(message, BorderLayout.NORTH);
        this.add(messagePanel);
        
        //format layout
        GridLayout lm = new GridLayout(ROWS,COLS);
        JPanel mainPanel = new JPanel(lm);
        JPanel numPanel = new JPanel(lm);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        mainPanel.setBorder(blackline);
        this.add(mainPanel, BorderLayout.NORTH);
        this.add(numPanel, BorderLayout.SOUTH);
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++){
                JPanel panel = new JPanel(lm);
                panel.setBorder(blackline);
                buttonGrid = new Button[ROWS][COLS];
                
                for (int k = 0; k < ROWS; k++) {
                    for (int l = 0; l < COLS; l++){
                        int row = i*3+k;
                        int col = j*3+l;
                        int correctNum = nums[row][col];
                        boolean disp = visible[row][col];
                        int[] position = {row,col};
                        Button b = new Button(correctNum, disp, 
                                position, this);
                        TileClickHandler tch = new TileClickHandler(this);
                        b.addActionListener(tch);
                        buttonGrid[k][l] = b;
                        panel.add(b);
                    }
                }
                mainPanel.add(panel); 
            }
        }  
        
        numberGrid = new NumberPadButtons[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++){       
                numPanel.setBorder(blackline);
                int num = numpad[i][j];
                NumberPadButtons b = new NumberPadButtons(num, this);
                TileClickHandler tch = new TileClickHandler(this);
                b.addActionListener(tch);
                numberGrid[i][j] = b;
                numPanel.add(b);
            }
        }
        this.pack();    
    }
    
    /**
     * This helper method sets the value of the tile with the value that was 
     * entered by the user via the keypad.
     * @param num 
     */
    public void setValue(int num){
        numEnt = num;
    }
    
    /**
     * This helper method checks if the user has completed the board and won
     * the game, as well as indicating if they entered the correct number.
     * @param correctNum
     * @param numShown 
     * @param buttonPos 
     */
    public void checkwin( int correctNum, int numShown, int[] buttonPos){
        if (correctNum == numShown){
            if (visible[buttonPos[0]][buttonPos[1]] != true){
                message.setText("Correct.");
            }
            
            visible[buttonPos[0]][buttonPos[1]] = true;
            wonGame = true;
            for (int i = 0; i < LEN; i++) {
                for (int j = 0; j < WID; j++){
                    if (visible[i][j] != true){
                        wonGame = false;
                    }
               }
            }
        }
        else{
            visible[buttonPos[0]][buttonPos[1]] = false;
            wonGame = false;
            strike += 1;
            message.setText("Wrong number! Strike "+strike);
            if (strike > 2){
                lostGame = true;
                message.setText("You lost. Exit and try again.");
            }
            //add in a gameover after 3 strikes
        }
        
        if (wonGame){
            System.out.println("game won");
            message.setText("You won!");
        } 
    }
    
    /**
     * This is the main method that creates a new Sudoku game.
     * @param args Unused.
     */
    public static void main(String[] args) {
        Sudoku game = new Sudoku();
    }
}
