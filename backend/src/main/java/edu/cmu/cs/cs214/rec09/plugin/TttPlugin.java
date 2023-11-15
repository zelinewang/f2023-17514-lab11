package edu.cmu.cs.cs214.rec09.plugin;

import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec09.games.TicTacToe;

/**
 * A TicTacToe game plug-in.
 */
public class TttPlugin implements GamePlugin<TicTacToe.Player> {
    private static final int WIDTH = TicTacToe.SIZE;
    private static final int HEIGHT = TicTacToe.SIZE;

    private static final String GAME_NAME = "Tic-Tac-Toe";
    private static final String X_SQUARE_STRING = "X";
    private static final String O_SQUARE_STRING = "O";
    private static final String EMPTY_SQUARE_STRING = "";
    private static final String GAME_OVER_MSG = "Game Over! Winner: %s";
    private static final String TIE_MSG = "Game Over! It's a Tie.";

    private GameFramework framework;
    private TicTacToe game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return WIDTH;
    }

    @Override
    public int getGridHeight() {
        return HEIGHT;
    }

    @Override
    public void onRegister(GameFramework framework) {
        this.framework = framework;
    }

    @Override
    public void onNewGame() {
        game = new TicTacToe();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                framework.setSquare(i, j, EMPTY_SQUARE_STRING);
            }
        }
        framework.setFooterText("Player " + game.currentPlayer() + "'s turn");
    }

    @Override
    public void onNewMove() {
        // Implementation for a new move, if necessary
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return game.isOver();
    }

    @Override
    public void onMovePlayed(int x, int y) {
        game.play(x, y);
        framework.setSquare(x, y, game.currentPlayer() == TicTacToe.Player.O ? X_SQUARE_STRING : O_SQUARE_STRING);
        if (!game.isOver()) {
            framework.setFooterText("Player " + game.currentPlayer() + "'s turn");
        }
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        if (game.winner() == null) {
            return TIE_MSG;
        } else {
            return String.format(GAME_OVER_MSG, game.winner());
        }
    }

    @Override
    public void onGameClosed() {
        // Cleanup if needed
    }

    @Override
    public TicTacToe.Player currentPlayer() {
        return game.currentPlayer();
    }
}
