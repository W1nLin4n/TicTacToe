public class GameLogic {
    public static final int NO_WIN = 0;
    public static final int ROW1 = 1;
    public static final int ROW2 = 2;
    public static final int ROW3 = 3;
    public static final int COL1 = 4;
    public static final int COL2 = 5;
    public static final int COL3 = 6;
    public static final int DIAG13 = 7;
    public static final int DIAG31 = 8;

    /**
     * Check if position is a win for a player
     * @param position current position
     * @param player player
     * @return the line where 3 marks of the player are located or
     * a conclusion, that the player has not yet won
     */
    public static int check_position(int position, boolean player){
        int playerCode = Box.EMPTY;
        if(player == GameTable.X){
            playerCode = Box.X;
        } else if (player == GameTable.O) {
            playerCode = Box.O;
        }
        if(getAt(position, 0) == playerCode && getAt(position, 1) == playerCode && getAt(position, 2)  == playerCode){
            return ROW1;
        }else if(getAt(position, 3) == playerCode && getAt(position, 4) == playerCode && getAt(position, 5)  == playerCode){
            return ROW2;
        }else if(getAt(position, 6) == playerCode && getAt(position, 7) == playerCode && getAt(position, 8)  == playerCode){
            return ROW3;
        }else if(getAt(position, 0) == playerCode && getAt(position, 3) == playerCode && getAt(position, 6)  == playerCode){
            return COL1;
        }else if(getAt(position, 1) == playerCode && getAt(position, 4) == playerCode && getAt(position, 7)  == playerCode){
            return COL2;
        }else if(getAt(position, 2) == playerCode && getAt(position, 5) == playerCode && getAt(position, 8)  == playerCode){
            return COL3;
        }else if(getAt(position, 0) == playerCode && getAt(position, 4) == playerCode && getAt(position, 8)  == playerCode){
            return DIAG13;
        }else if(getAt(position, 2) == playerCode && getAt(position, 4) == playerCode && getAt(position, 6)  == playerCode){
            return DIAG31;
        }else{
            return NO_WIN;
        }
    }

    /**
     * Get value inside a box using position and box's id
     * @param position position
     * @param square box's id
     * @return value inside box
     */
    public static int getAt(int position, int square){
        return (position & (3<<(square*2)))>>(square*2);
    }

    /**
     * Set value inside a box using position and box's id
     * @param position position
     * @param square box's id
     * @param value the value to set
     * @return the resulting position
     */
    public static int setAt(int position, int square, int value){
        return position | (value<<(square*2));
    }

}
