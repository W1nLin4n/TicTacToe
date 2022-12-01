import acm.graphics.GCompound;
import acm.graphics.GLabel;

import java.awt.event.MouseEvent;

public class GameTable extends GCompound{
    private static final int EMPTY_TABLE = 0;
    private static final boolean X = false;
    private static final boolean O = true;
    private static final int squares = 3;
    private int state;
    private boolean turn;
    private boolean end;

    /**
     * Default game table constructor
     * @param x top left corner x
     * @param y top left corner y
     * @param width game table width
     * @param height game table height
     */
    public GameTable(int x, int y, int width, int height){
        this.setLocation(x, y);
        state = EMPTY_TABLE;
        turn = X;
        end = false;
        int stepX = width/squares;
        int stepY = height/squares;
        for(int i = 0; i < squares; i++) {
            for (int j = 0; j < squares; j++) {
                Box box = new Box(stepX * i, stepY * j, stepX, stepY, j * squares + i);
                this.add(box);
            }
        }
    }

    /**
     *
     */
    public void onclick(MouseEvent mouseEvent){
        for(int i = 0; i < squares*squares; i++) {
            ((Box)getElement(i)).onclick(mouseEvent);
        }
    }

    /**
     * Update the state of the game table
     */
    public void update(){

    }

    /**
     * Reset the game table to default
     */
    public void reset(){
        state = EMPTY_TABLE;
        turn = X;
        end = false;
        for(int i = 0; i < squares*squares; i++){
            ((Box)this.getElement(i)).reset();
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

}
