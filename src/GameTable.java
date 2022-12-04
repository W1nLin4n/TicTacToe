import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GameTable extends GCompound implements Clickable{
    public static final int EMPTY_TABLE = 0;
    public static final boolean X = false;
    public static final boolean O = true;
    private static final int squares = 3;
    private static final Color winLineColor = Color.RED;
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
     * Handle a click on the table
     */
    public void onclick(MouseEvent mouseEvent){
        for(int i = 0; i < squares*squares; i++) {
            ((Box)getElement(i)).onclick(mouseEvent);
        }
        update();
    }

    /**
     * AI makes a move
     * @param id id of the box on which the AI clicked
     */
    public void aiMove(int id){
        for(int i = 0; i < squares*squares; i++) {
            ((Box)getElement(i)).aiMove(id);
        }
        update();
    }

    /**
     * Update the state of the game table
     */
    public void update(){
        int playerResult = GameLogic.check_position(state, Main.isPlayer());
        int AIResult = GameLogic.check_position(state, !Main.isPlayer());
        if(playerResult != GameLogic.NO_WIN){
            GLine winLine = null;
            if(playerResult == GameLogic.ROW1){
                winLine = new GLine(0, getHeight()/6, getWidth(), getHeight()/6);
            }else if(playerResult == GameLogic.ROW2) {
                winLine = new GLine(0, getHeight()*3/6, getWidth(), getHeight()*3/6);
            }else if(playerResult == GameLogic.ROW3) {
                winLine = new GLine(0, getHeight()*5/6, getWidth(), getHeight()*5/6);
            }else if (playerResult == GameLogic.COL1) {
                winLine = new GLine(getWidth()/6, 0, getWidth()/6, getHeight());
            }else if (playerResult == GameLogic.COL2) {
                winLine = new GLine(getWidth()*3/6, 0, getWidth()*3/6, getHeight());
            }else if (playerResult == GameLogic.COL3) {
                winLine = new GLine(getWidth()*5/6, 0, getWidth()*5/6, getHeight());
            }else if (playerResult == GameLogic.DIAG13) {
                winLine = new GLine(0, 0, getWidth(), getHeight());
            }else if (playerResult == GameLogic.DIAG31) {
                winLine = new GLine(getWidth(), 0, 0, getHeight());
            }
            winLine.setColor(winLineColor);
            add(winLine);
            Main.setHasPlayerWon(1);
            end = true;
        }else if(AIResult != GameLogic.NO_WIN){
            GLine winLine = null;
            if(AIResult == GameLogic.ROW1){
                winLine = new GLine(0, getHeight()/6, getWidth(), getHeight()/6);
            }else if(AIResult == GameLogic.ROW2) {
                winLine = new GLine(0, getHeight()*3/6, getWidth(), getHeight()*3/6);
            }else if(AIResult == GameLogic.ROW3) {
                winLine = new GLine(0, getHeight()*5/6, getWidth(), getHeight()*5/6);
            }else if (AIResult == GameLogic.COL1) {
                winLine = new GLine(getWidth()/6, 0, getWidth()/6, getHeight());
            }else if (AIResult == GameLogic.COL2) {
                winLine = new GLine(getWidth()*3/6, 0, getWidth()*3/6, getHeight());
            }else if (AIResult == GameLogic.COL3) {
                winLine = new GLine(getWidth()*5/6, 0, getWidth()*5/6, getHeight());
            }else if (AIResult == GameLogic.DIAG13) {
                winLine = new GLine(0, 0, getWidth(), getHeight());
            }else if (AIResult == GameLogic.DIAG31) {
                winLine = new GLine(getWidth(), 0, 0, getHeight());
            }
            winLine.setColor(winLineColor);
            add(winLine);
            Main.setHasPlayerWon(-1);
            end = true;
        }else{
            boolean areEmptyCellsAvailable = false;
            for(int i = 0; i < squares*squares; i++){
                if(GameLogic.getAt(state, i) == Box.EMPTY){
                    areEmptyCellsAvailable = true;
                }
            }
            if(!areEmptyCellsAvailable){
                Main.setHasPlayerWon(0);
                end = true;
            }
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
