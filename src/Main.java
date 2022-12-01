import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class Main extends GraphicsProgram{
    private static final int MENU_PAGE = 0;
    private static final int GAME_PAGE = 1;
    private static final int RESULTS_PAGE = 2;
    private static final int screenWidth = 700;
    private static final int screenHeight = 700;
    private static final int tableWidth = 500;
    private static final int tableHeight = 500;
    private static final int tableX = (screenWidth - tableWidth)/2;
    private static final int tableY = 50;
    private int page;

    public void init(){
        addMouseListeners();
    }

    public void run(){
        this.setSize(screenWidth, screenHeight);
//        menu();
        game();
//        results();
    }

    public void menu(){
        page = MENU_PAGE;

    }

    public void game(){
        GameTable table = new GameTable(tableX, tableY, tableWidth, tableHeight);
        add(table);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        GObject obj = getElementAt(new GPoint(mouseEvent.getPoint()));
        if(obj instanceof Clickable){
            ((Clickable)obj).onclick(mouseEvent);
        }
    }
}