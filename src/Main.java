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
    private static final int togglePlayerButtonX = 50;
    private static final int togglePlayerButtonY = 100;
    private static final int togglePlayerButtonWidth = 200;
    private static final int togglePlayerButtonHeight = 200;
    private static final int toggleDifficultyButtonX = 325;
    private static final int toggleDifficultyButtonY = 150;
    private static final int toggleDifficultyButtonWidth = 300;
    private static final int toggleDifficultyButtonHeight = 100;
    private static final int toggleDifficultyButtonFont = 32;
    private static final int startButtonX = 200;
    private static final int startButtonY = 450;
    private static final int startButtonWidth = 300;
    private static final int startButtonHeight = 100;
    private static final int startButtonFont = 32;
    private static int page;
    private static boolean player;
    private static int difficulty;
    private static boolean start;
    private static boolean restart;

    public void init(){
        addMouseListeners();
        start = false;
        restart = false;
    }

    public void run(){
        this.setSize(screenWidth, screenHeight);
        menu();
        game();
//        results();
    }

    /**
     * Display a menu with player selection and ai mode
     */
    public void menu(){
        page = MENU_PAGE;
        removeAll();
        TogglePlayerButton togglePlayerButton = new TogglePlayerButton(togglePlayerButtonX, togglePlayerButtonY,
                                                                        togglePlayerButtonWidth, togglePlayerButtonHeight);
        add(togglePlayerButton);
        ToggleDifficultyButton toggleDifficultyButton = new ToggleDifficultyButton(toggleDifficultyButtonX, toggleDifficultyButtonY,
                                                                                    toggleDifficultyButtonWidth, toggleDifficultyButtonHeight,
                                                                                    toggleDifficultyButtonFont);
        add(toggleDifficultyButton);
        StartButton startButton = new StartButton(startButtonX, startButtonY, startButtonWidth, startButtonHeight, startButtonFont);
        add(startButton);
        while (!start){
            waitForClick();
            pause(1);
        }
        start = false;
        player = togglePlayerButton.isPlayer();
        difficulty = toggleDifficultyButton.getDifficulty();
    }

    /**
     * Start a game with selected parameters
     */
    public void game(){
        page = GAME_PAGE;
        removeAll();
        GameTable table = new GameTable(tableX, tableY, tableWidth, tableHeight);
        add(table);
        while(!table.isEnd()){
            waitForClick();
            pause(1);
        }
    }

    /**
     * Show the result of a game
     */
    public void results(){
        page = RESULTS_PAGE;
        removeAll();
    }

    /**
     * Handler for mouse clicks
     * @param mouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        GObject elementClicked = getElementAt(new GPoint(mouseEvent.getPoint()));
        if(elementClicked instanceof Clickable){
            ((Clickable)elementClicked).onclick(mouseEvent);
        }
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        Main.difficulty = difficulty;
    }

    public static boolean isPlayer() {
        return player;
    }

    public static void setPlayer(boolean player) {
        Main.player = player;
    }


    public static boolean isStart() {
        return start;
    }

    public static void setStart(boolean start) {
        Main.start = start;
    }

    public static boolean isRestart() {
        return restart;
    }

    public static void setRestart(boolean restart) {
        Main.restart = restart;
    }
}