import java.util.Scanner;

public class Main {
    private static int ROW = 8;
    private static int COL = 8;
    private static int Plac[][] = new int[ROW][COL];
    private static int player = 1;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int counter = 1;
        int choice;
        do {
            System.out.println(" ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ ");
            System.out.println(" □  The game menu:-                                          □");
            System.out.println(" ■  1. Manual entry for both ■’s moves and □’s moves.        ■\n"
                    + " □  2. Manual entry for ■’s moves & automatic moves for □.   □\n"
                    + " ■  3. Manual entry for □’s moves & automatic moves for ■.   ■\n"
                    + " □  4. Quit.                                                 □");
            System.out.println(" ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ □ ■ ");
            System.out.println("\n Enter the your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case (1):
                    initBord();
                    player=1;
                    while (true) {
                        if (counter % 2 != 0) {
                            System.out.println("  Player 1 turn");
                        } else {
                            System.out.println("  Player 2 turn");
                        }

                        System.out.println(" Enter the place you want [Row , Col]");
                        int inRow = input.nextInt();
                        int inCol = input.nextInt();

                        while (!isValid(inRow, inCol)) {
                            System.out.println("Place is not valid. Try again.");
                            inRow = input.nextInt();
                            inCol = input.nextInt();
                        }
                        enterValue(inRow, inCol, player);
                        displayBoard();
                        if (is_game_Over()) {
                            System.out.println("It's a tie!");
                            break;
                        }

                        counter++;
                        nextPlayer();
                    }
                    break;
                case (2):
                    initBord();
                    player=1;
                    while (true) {
                        if (counter % 2 != 0) {
                            System.out.println("Player 1 turn");
                            System.out.println("Enter the place you want [Row , Col]");
                            int inrow = input.nextInt();
                            int incol = input.nextInt();
                            while (!isValid(inrow, incol)) {
                                System.out.println("Place is not valid , Try again");
                                inrow = input.nextInt();
                                incol = input.nextInt();
                            }
                            enterValue(inrow, incol, player);
                            displayBoard();
                            if (is_game_Over()) {
                                break;
                            }
                        } else {
                            System.out.println("Player 2 turn");
                            int bestScore = Integer.MIN_VALUE;
                            int bestRow = -1;
                            int bestCol = -1;
                            for (int i = 0; i < ROW; i++) {
                                for (int j = 0; j < COL; j++) {
                                    if (isValid(i, j)) {
                                        Plac[i][j] = player;
                                        int score = minimax(1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                                        Plac[i][j] = 0; // Undo the move

                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestRow = i;
                                            bestCol = j;
                                        }
                                    }
                                }
                            }

                            if (bestRow != -1 && bestCol != -1) {
                                enterValue(bestRow, bestCol, player);
                                displayBoard();
                                if (is_game_Over()) {
                                    break;
                                }
                            }
                        }
                        counter++;
                        nextPlayer();
                    }

                    break;
                case (3):
                    initBord();
                    player=1;
                    while (true) {
                        if (counter % 2 != 0) {
                            System.out.println("Player 1 turn");
                            int bestScore = Integer.MIN_VALUE;
                            int bestRow = -1;
                            int bestCol = -1;
                            for (int i = 0; i < ROW; i++) {
                                for (int j = 0; j < COL; j++) {
                                    if (isValid(i, j)) {
                                        Plac[i][j] = player;
                                        int score = minimax(1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                                        Plac[i][j] = 0; // Undo the move

                                        if (score > bestScore) {
                                            bestScore = score;
                                            bestRow = i;
                                            bestCol = j;
                                        }
                                    }
                                }
                            }

                            if (bestRow != -1 && bestCol != -1) {
                                enterValue(bestRow, bestCol, player);
                                displayBoard();
                                if (is_game_Over()) {
                                    break;
                                }
                            }

                        } else {
                            System.out.println("Player 2 turn");
                            System.out.println("Enter the place you want [Row , Col]");
                            int inrow = input.nextInt();
                            int incol = input.nextInt();
                            while (!isValid(inrow, incol)) {
                                System.out.println("Place is not valid , Try again");
                                inrow = input.nextInt();
                                incol = input.nextInt();
                            }
                            enterValue(inrow, incol, player);
                            displayBoard();
                            if (is_game_Over()) {
                                break;
                            }
                        }
                        counter++;
                        nextPlayer();
                    }


                    break;
                case (4):
                    System.out.println("Done GoodBye");
                    System.exit(0);
                    break;
            }
        } while (true);
    }

    private static void nextPlayer() {
        if (player == 1)
            player = -1;
        else
            player = 1;

    }

    public static void displayBoard() {
        System.out.println("\n    0   1   2   3   4   5   6   7 ");
        System.out.print("    -   -   -   -   -   -   -   - \n");
        for (int row = 0; row < ROW; row++) {
            System.out.print((row) + " |");
            for (int col = 0; col < COL; col++) {
                if (Plac[row][col] == 0) {
                    System.out.print("   |");

                } else if (Plac[row][col] == 1) {
                    System.out.print(" ■ |");
                } else {
                    System.out.print(" □ |");
                }
            }
            System.out.print("\n    -   -   -   -   -   -   -   - \n");
        }
    }

    private static void initBord() {
        for (int col = 0; col < COL; col++) {
            for (int row = 0; row < ROW; row++) {
                Plac[col][row] = 0;
            }
        }
        displayBoard();
    }

    public static void enterValue(int inrow, int incol, int choice) {
        Plac[inrow][incol] = choice;
    }
    public static boolean isValid(int inrow, int incol) {
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                if (i == inrow && j == incol) {
                    if (Plac[i][j] == 0) {
                        if ((j == 0) || (j == 7))
                            return true;
                        else if (((Plac[i][j + 1] != 0) || (Plac[i][j - 1] != 0)))
                            return true;
                    }
                }
        return false;
    }

    public static boolean is_game_Over() {
        if (isBoardFull()) {
            System.out.println("The board is full , game over");
            return true;
        }
        int winner = checkGameWinner();
        if (winner==1||winner==-1) {
            if (winner == 1) {
                System.out.println("Game over , ■ wins the game");
            } else if (winner == -1) {
                System.out.println("Game over , □ wins the game");
            }
            return true;
        }
        return false;
    }
    public static int checkGameWinner() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (checkVerticalWin(col, row)) {
                    String winner = (Plac[row][col] == 1) ? "■" : "□";
                    System.out.println(winner + " wins vertically with " + col + (row) + " to " + col + (row + 4));
                    //  winnerExists = true;
                    return (Plac[row][col] == 1) ? 1 : -1;
                }

                if (checkHorizontalWin(col, row)) {
                    String winner = (Plac[row][col] == 1) ? "■" : "□";
                    System.out.println(winner + " wins horizontally with " + col + (row) + " to " + (col + 4) + row);
                    // winnerExists = true;
                    return (Plac[row][col] == 1) ? 1 : -1;
                }
                if (checkDiagonalWin(col, row)) {
                    String winner = (Plac[row][col] == 1) ? "■" : "□";
                    System.out.println(winner + " wins diagonally with " + col + (row) + " to " + (col + 4) + (row + 4));
                    //  winnerExists = true;
                    return (Plac[row][col] == 1) ? 1 : -1;
                }

                if (checkDiagonalOPWin(col, row)) {
                    String winner = (Plac[row][col] == 1) ? "■" : "□";
                    System.out.println(winner + " wins diagonally with " + col + (row) + " to " + (col + 4) + (row - 4));
                    // winnerExists = true;
                    return (Plac[row][col] == 1) ? 1 : -1;
                }
            }
        }
        return 0;
    }

    public static boolean checkVerticalWin(int col, int row) {
        int cellValue = Plac[row][col];

        if (row < 4 &&
                cellValue == Plac[row + 1][col] &&
                cellValue == Plac[row + 2][col] &&
                cellValue == Plac[row + 3][col] &&
                cellValue == Plac[row + 4][col] &&
                (cellValue == 1 || cellValue == -1)) {
            return true;
        }
        return false;
    }

    public static boolean checkHorizontalWin(int col, int row) {
        int cellValue = Plac[row][col];

        if (col < 4 &&
                cellValue == Plac[row][col + 1] &&
                cellValue == Plac[row][col + 2] &&
                cellValue == Plac[row][col + 3] &&
                cellValue == Plac[row][col + 4] &&
                (cellValue == 1 || cellValue == -1)) {
            return true;
        }
        return false;
    }

    public static boolean checkDiagonalWin(int col, int row) {
        int cellValue = Plac[row][col];

        if (row < 4 && col < 4 &&
                cellValue == Plac[row + 1][col + 1] &&
                cellValue == Plac[row + 2][col + 2] &&
                cellValue == Plac[row + 3][col + 3] &&
                cellValue == Plac[row + 4][col + 4] &&
                (cellValue == 1 || cellValue == -1)) {
            return true;
        }

        return false;
    }

    public static boolean checkDiagonalOPWin(int col, int row) {
        int cellValue = Plac[row][col];

        if (row > 3 && col < 4 &&
                cellValue == Plac[row - 1][col + 1] &&
                cellValue == Plac[row - 2][col + 2] &&
                cellValue == Plac[row - 3][col + 3] &&
                cellValue == Plac[row - 4][col + 4] &&
                (cellValue == 1 || cellValue == -1)) {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int row = 0; row < ROW; row++)
            for (int col = 0; col < COL; col++)
                if (Plac[row][col] == 0)
                    return false;
        return true;
    }
    public static int checkPossibleGameWinner() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (checkVerticalWin(col, row)) {
                    return (Plac[row][col] == 1) ? 1 : -1;
                }

                if (checkHorizontalWin(col, row)) {
                    return (Plac[row][col] == 1) ? 1 : -1;
                }
                if (checkDiagonalWin(col, row)) {
                    return (Plac[row][col] == 1) ? 1 : -1;
                }

                if (checkDiagonalOPWin(col, row)) {
                    return (Plac[row][col] == 1) ? 1 : -1;
                }
            }
        }
        return 0;
    }
    public static int minimax(int depth, int alpha, int beta, boolean isMaxPlayer) {
        int result = checkPossibleGameWinner();
        int opponent = (player == 1) ? -1 : 1;
        if (result != 0) {
            return result;
        }

        if (isMaxPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (isValid(i, j)) {
                        Plac[i][j] = player;

                        int score = 0;

                        if (depth == 0) {
                            score = heuristic();
                        } else {
                            score = minimax(depth - 1, alpha, beta, false);
                        }

                        Plac[i][j] = 0; // Undo the move

                        bestScore = Math.max(bestScore, score);
                        alpha = Math.max(alpha, bestScore);

                        if (beta <= alpha) {
                            break; // Beta cutoff
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (isValid(i, j)) {
                        Plac[i][j] = opponent;
                        int score = 0;

                        if (depth == 0) {
                            score = heuristic();
                        } else {
                            score = minimax(depth - 1, alpha, beta, true);
                        }
                        Plac[i][j] = 0; // Undo the move

                        bestScore = Math.min(bestScore, score);
                        beta = Math.min(beta, bestScore);

                        if (beta <= alpha) {
                            break; // Alpha cutoff
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public static int heuristic() {
        int score = 0;
        int opponent = (player == 1) ? -1 : 1;
        int winner = checkPossibleGameWinner();

        if (winner == 1) {
            score = Integer.MAX_VALUE; // Winning state for the maximizing player
        } else if (winner == -1) {
            score = Integer.MIN_VALUE; // Winning state for the minimizing player
        } else if (is_game_Over()) {
            score = 0; // Draw state
        } else {
            // Evaluate the board based on various factors
            int playerScore = evaluatePlayerScore(player);
            int opponentScore = evaluatePlayerScore(opponent);

            score = playerScore - opponentScore;
        }

        return score;
    }
    public static int evaluatePlayerScore(int player) {
        int score = 0;

        // Evaluate vertical wins
        for (int col = 0; col < COL; col++) {
            for (int row = 0; row <= ROW - 5; row++) {
                int count = 0;
                for (int i = 0; i < 5; i++) {
                    if (Plac[row + i][col] == player) {
                        count++;
                    }
                }
                score += evaluateLine(count);
            }
        }

        // Evaluate horizontal wins
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col <= COL - 5; col++) {
                int count = 0;
                for (int i = 0; i < 5; i++) {
                    if (Plac[row][col + i] == player) {
                        count++;
                    }
                }
                score += evaluateLine(count);
            }
        }

        // Evaluate diagonal wins (top-left to bottom-right)
        for (int row = 0; row <= ROW - 5; row++) {
            for (int col = 0; col <= COL - 5; col++) {
                int count = 0;
                for (int i = 0; i < 5; i++) {
                    if (Plac[row + i][col + i] == player) {
                        count++;
                    }
                }
                score += evaluateLine(count);
            }
        }

        // Evaluate diagonal wins (top-right to bottom-left)
        for (int row = 4; row < ROW; row++) {
            for (int col = 0; col <= COL - 5; col++) {
                int count = 0;
                for (int i = 0; i < 5; i++) {
                    if (Plac[row - i][col + i] == player) {
                        count++;
                    }
                }
                score += evaluateLine(count);
            }
        }

        return score;
    }

    public static int evaluateLine(int count) {
        int score = 0;

        if (count == 0) {
            score = 0; // No marks in the line
        } else if (count == 1) {
            score = 1; // One mark in the line
        } else if (count == 2) {
            score = 10; // Two marks in the line
        } else if (count == 3) {
            score = 100; // Three marks in the line
        } else if (count == 4) {
            score = 1000; // Four marks in the line
        }

        return score;
    }
}