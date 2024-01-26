package compsci.sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 *This class controls the buttons on the main Sudoku grid.
 * @author aireneahuja
 */
public class Button extends JButton{
    int correctNum;
    boolean disp;
    int userEnt;
    int numShown;
    int[] buttonPos;
    Sudoku game;
    
    /**
     * This constructor method sets the size and text of the button depending
     * on whether or not the value should be shown at the beginning of the game.
     * @param correctNum
     * @param disp 
     * @param buttonPos
     * @param game 
     */
    public Button(int correctNum, boolean disp, int[] buttonPos, Sudoku game){
        this.game = game;
        this.correctNum = correctNum;
        this.disp = disp;
        this.buttonPos = buttonPos;
        Dimension dmnsn = new Dimension(50,50);
        this.setPreferredSize(dmnsn);
        if (disp){
            String numtext = String.valueOf(correctNum);
            this.setText(numtext);
            setBackground(Color.green);
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
            this.setFont(font);
        } else {
            this.setBackground(Color.white);
            Font font = new Font(Font.SERIF, Font.PLAIN, 20);
            this.setFont(font);
        }
    }
    
    /**
     * This helper method displays the number entered by the user on the tile.
     */
    public void setDisplayValue(){
        userEnt = game.getNumEnt();
        if (!disp){
            String numtext = String.valueOf(userEnt);
            this.setText(numtext);
        } 
    }
    
    /**
     * This getter method returns the variable numShown so it can be accessed
     * outside this class.
     * @return 
     */
    public int getNumShown() {
        userEnt = game.getNumEnt();
        if (disp){
            numShown = correctNum;
        }
        if (!disp){
            numShown = userEnt;
        }
        return numShown;
    }

    /**
     * This getter method returns the variable correctNum so it can be accessed
     * outside this class.
     * @return 
     */
    public int getCorrectNum(){
        return correctNum;
    }
    
    /**
     * This getter method returns the position of the button in the gird so it 
     * can be accessed outside this class.
     * @return 
     */
    public int[] getButtonPos(){
        return buttonPos;
    }
}

