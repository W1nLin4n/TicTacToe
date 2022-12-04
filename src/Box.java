import acm.graphics.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Box extends GCompound implements Clickable{
    private static final int paddingBox = 10;
    public static final int EMPTY = 0;
    public static final int X = 1;
    public static final int O = 2;
    private GRect box;
    private GOval circle;
    private GCompound cross;
    private int id;

    /**
     * Default box constructor
     * @param x top left corner x
     * @param y top left corner y
     * @param width box width
     * @param height box height
     * @param id box id
     */
    public Box(int x, int y, int width, int height, int id){
        setLocation(x, y);
        this.id = id;
        box = new GRect(0, 0, width, height);
        circle = new GOval(paddingBox, paddingBox,
                               width - 2*paddingBox, height - 2*paddingBox);
        cross = new GCompound();
        GLine line = new GLine(paddingBox, paddingBox,
                              width - paddingBox, height - paddingBox);
        cross.add(line);
        line = new GLine(paddingBox, height - paddingBox,
                        width - paddingBox, paddingBox);
        cross.add(line);
        add(box);
    }

    /**
     * Check if this box was clicked and if so, handle it
     */
    public void onclick(MouseEvent mouseEvent){
        GameTable table = (GameTable) this.getParent();
        if(contains(table.getLocalPoint(new GPoint(mouseEvent.getPoint())))){
            if(!table.isEnd() && getState() == EMPTY) {
                makeMove();
            }
        }
        update();
    }

    /**
     * AI makes a move
     * @param id id of the box on which the AI clicked
     */
    public void aiMove(int id){
        GameTable table = (GameTable) this.getParent();
        if(this.id == id){
            if(getState() == EMPTY) {
                makeMove();
            }
        }
        update();
    }

    /**
     * Make move in current box
     */
    private void makeMove() {
        int state = EMPTY;
        GameTable table = (GameTable) this.getParent();
        if(table.isTurn() == GameTable.X){
            state = X;
        }else if(table.isTurn() == GameTable.O){
            state = O;
        }
        table.setState(GameLogic.setAt(table.getState(), id, state));
        table.setTurn(!table.isTurn());
    }

    /**
     * Update the state of the box
     */
    public void update(){
        int state = getState();
        if(state == X && cross.getParent() == null){
            add(cross);
        }else if(state == O && circle.getParent() == null){
            add(circle);
        }
    }

    /**
     * A method to get current state of the box
     * @return current state of the box
     */
    public int getState(){
        return GameLogic.getAt(((GameTable) this.getParent()).getState(), id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
