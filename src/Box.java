import acm.graphics.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Box extends GCompound{
    private static final int paddingBox = 10;
    private static final int EMPTY = 0;
    private static final int X = 1;
    private static final int O = 2;
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
        this.setLocation(x, y);
        this.id = id;
        this.box = new GRect(0, 0, width, height);
        this.circle = new GOval(paddingBox, paddingBox,
                               width - 2*paddingBox, height - 2*paddingBox);
        this.cross = new GCompound();
        GLine line = new GLine(paddingBox, paddingBox,
                              width - paddingBox, height - paddingBox);
        cross.add(line);
        line = new GLine(paddingBox, height - paddingBox,
                        width - paddingBox, paddingBox);
        cross.add(line);
        this.add(box);
        System.out.println(box.getBounds());
    }

    public void onclick(MouseEvent mouseEvent){
        System.out.println(this.getBounds());
        if(contains(this.getLocalPoint(new GPoint(mouseEvent.getPoint())))){
            System.out.println("INSIDE" + id + " " + this.getX() + " " + this.getY());
            update();
        }
    }

    /**
     * Update the state of the box
     */
    public void update(){
        int state = this.getState();
        if(state == X && cross.getParent() == null){
            this.add(cross);
        }else if(state == O && circle.getParent() == null){
            this.add(circle);
        }
    }

    /**
     * Reset the box to default
     */
    public void reset(){
        if(cross.getParent().equals(this)){
            this.remove(cross);
        }
        if(circle.getParent().equals(this)) {
            this.remove(circle);
        }
    }

    /**
     * A method to get current state of the box
     * @return current state of the box
     */
    public int getState(){
        return 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
