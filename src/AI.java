public class AI {
    public static int LOSE = -1;
    public static int DRAW = 0;
    public static int WIN = 1;
    private static int SQUARES = 9;
    private static int NORMAL_DEPTH_CAP = 0;
    private static int HARD_DEPTH_CAP = 2;
    private static int IMPOSSIBLE_DEPTH_CAP = 5;

    public static void move(GameTable gameTable){
        int optimalMove = -1;
        int resultOfOptimalMove = -100;
        for(int i = 0; i < SQUARES; i++){
            if(GameLogic.getAt(gameTable.getState(), i) == 0) {
                int tryMoveResult = analysis(gameTable.getState(), i, true, 0);
                if (tryMoveResult > resultOfOptimalMove) {
                    optimalMove = i;
                    resultOfOptimalMove = tryMoveResult;
                }
            }
        }
        gameTable.aiMove(optimalMove);
    }

    private static int analysis(int position, int square, boolean isAi, int depth){
        int move;
        if(isAi){
            if(Main.isPlayer() == GameTable.X){
                move = Box.O;
            }else{
                move = Box.X;
            }
        }else{
            if(Main.isPlayer() == GameTable.X){
                move = Box.X;
            }else{
                move = Box.O;
            }
        }
        int newPosition = GameLogic.setAt(position, square, move);
        int resultCurrentPlayer = GameLogic.check_position(newPosition, isAi ^ Main.isPlayer());
        int resultOtherPlayer = GameLogic.check_position(newPosition, !(isAi ^ Main.isPlayer()));
        int result = AI.DRAW;
        if(resultCurrentPlayer != GameLogic.NO_WIN){
            result = AI.WIN;
        }else if(resultOtherPlayer != GameLogic.NO_WIN){
            result = AI.LOSE;
        }
        if(result != AI.DRAW ||
            (Main.getDifficulty() == ToggleDifficultyButton.NORMAL_VALUE && depth == NORMAL_DEPTH_CAP) ||
            (Main.getDifficulty() == ToggleDifficultyButton.HARD_VALUE && depth == HARD_DEPTH_CAP) ||
            (Main.getDifficulty() == ToggleDifficultyButton.IMPOSSIBLE_VALUE && depth == IMPOSSIBLE_DEPTH_CAP)){
            return result;
        }
        int optimalMove = -1;
        int resultOfOptimalMove = -100;
        for(int i = 0; i < SQUARES; i++){
            if(GameLogic.getAt(newPosition, i) == 0) {
                int tryMoveResult = analysis(newPosition, i, !isAi, depth+1);
                if (tryMoveResult > resultOfOptimalMove) {
                    optimalMove = i;
                    resultOfOptimalMove = tryMoveResult;
                }
            }
        }
        if(optimalMove == -1){
            return AI.DRAW;
        }else if(resultOfOptimalMove == AI.WIN){
            return AI.LOSE;
        }else if(resultOfOptimalMove == AI.LOSE){
            return AI.WIN;
        }else{
            return AI.DRAW;
        }
    }
}
