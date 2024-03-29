import acm.graphics.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ToggleDifficultyButton extends GCompound implements Clickable{
    private static final String NORMAL_TEXT = "Normal difficulty";
    private static final String HARD_TEXT = "Hard difficulty";
    private static final String IMPOSSIBLE_TEXT = "Impossible difficulty";
    public static final int NORMAL_VALUE = 0;
    public static final int HARD_VALUE = 1;
    public static final int IMPOSSIBLE_VALUE = 2;
    private static final Color backgroundColor = Color.WHITE;
    private static final String fontFamily = "Calibri";
    private static final int fontStyle = Font.PLAIN;
    private GRect box;
    private GLabel label;
    private int difficulty;

    /**
     * Default button to toggle difficulty
     * @param x top left corner x
     * @param y top left corner y
     * @param width button width
     * @param height button height
     * @param fontSize font size of a text
     */
    public ToggleDifficultyButton(int x, int y, int width, int height, int fontSize){
        setLocation(x, y);
        box = new GRect(0, 0, width, height);
        box.setFillColor(backgroundColor);
        box.setFilled(true);
        add(box);
        label = new GLabel(NORMAL_TEXT);
        label.setFont(new Font(fontFamily, fontStyle, fontSize));
        label.setLocation((width - label.getWidth())/2, (height + label.getHeight())/2 - label.getDescent());
        add(this.label);
        difficulty = NORMAL_VALUE;
    }

    /**
     * Toggle to another difficulty
     */
    private void toggle(){
        switch (difficulty){
            case NORMAL_VALUE:
                label.setLabel(HARD_TEXT);
                label.setFont(new Font(fontFamily, fontStyle, label.getFont().getSize()));
                label.setLocation((getWidth() - label.getWidth())/2, (getHeight() + label.getHeight())/2 - label.getDescent());
                difficulty = HARD_VALUE;
                break;
            case HARD_VALUE:
                label.setLabel(IMPOSSIBLE_TEXT);
                label.setFont(new Font(fontFamily, fontStyle, label.getFont().getSize()));
                label.setLocation((getWidth() - label.getWidth())/2, (getHeight() + label.getHeight())/2 - label.getDescent());
                difficulty = IMPOSSIBLE_VALUE;
                break;
            case IMPOSSIBLE_VALUE:
                label .setLabel(NORMAL_TEXT);
                label.setFont(new Font(fontFamily, fontStyle, label.getFont().getSize()));
                label.setLocation((getWidth() - label.getWidth())/2, (getHeight() + label.getHeight())/2 - label.getDescent());
                difficulty = NORMAL_VALUE;
                break;
        }
    }

    /**
     * The difficulty should be toggled after a click
     * @param mouseEvent
     */
    @Override
    public void onclick(MouseEvent mouseEvent) {
        toggle();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
