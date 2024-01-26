
package compsci.sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *This class handles the tile clicks and what needs to happen in the game
 * accordingly.
 * @author aireneahuja
 */
public class TileClickHandler implements ActionListener{
    Sudoku game;
    
    /**
     * This method links the Sudoku game to this handler.
     * @param game 
     */
    public TileClickHandler(Sudoku game) {
        this.game = game;
    }
    
    /**
     * This is the main method to handle changes to the buttons when they are
     * clicked.
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (game.wonGame != true && game.lostGame != true){
            if (ae.getSource() instanceof NumberPadButtons){
                NumberPadButtons numbutton = (NumberPadButtons)ae.getSource();
                game.setValue(numbutton.num);
            
            }
            else{ 
                Button button = (Button)ae.getSource();
                button.setDisplayValue();
                game.checkwin(button.getCorrectNum(), 
                        button.getNumShown(), button.getButtonPos()); 
            }
        }
    }
}
