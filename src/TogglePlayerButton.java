import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;

import java.awt.event.MouseEvent;

public class TogglePlayerButton extends GCompound implements Clickable{
    private static final int paddingBox = 10;
    private static final boolean X = GameTable.X;
    private static final boolean O = GameTable.O;
    private GRect box;
    private GOval circle;
    private GCompound cross;
    private boolean player;

    public TogglePlayerButton(int x, int y, int width, int height){
        setLocation(x, y);
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
        add(cross);
        player = X;
    }

    private void toggle(){
        if(player == X && cross.getParent() != null){
            remove(cross);
            add(circle);
        }else if(player == O && circle.getParent() != null){
            remove(circle);
            add(cross);
        }
        player = !player;
    }

    @Override
    public void onclick(MouseEvent mouseEvent) {
        toggle();
    }

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }
}
