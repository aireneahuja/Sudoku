
package compsci.sudoku;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 *This class controls the buttons on the number pad.
 * @author aireneahuja
 */
public class NumberPadButtons extends JButton{
    Sudoku game;
    int num;
    
    /**
     * This constructor method sets the size and text of each key on the number 
     * pad.
     * @param num 
     * @param game 
     */
    public NumberPadButtons(int num, Sudoku game) {
        this.game = game;
        this.num = num;
        Dimension dmnsn = new Dimension(50,50);
        this.setPreferredSize(dmnsn);
        String numtext = String.valueOf(num);
        this.setText(numtext);
        this.setBackground(Color.red);
        }
    
    /**
     * This helper method changes the value of the Sudoku button based on the
     * button pressed on the keypad.
     */
    public void numChange(){
        game.setValue(this.num);
    }
}
