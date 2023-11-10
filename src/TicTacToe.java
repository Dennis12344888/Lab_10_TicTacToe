import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearBoard();
            display();
            playGame(scanner);

            System.out.println("Do you want to play again? (Y/N)");
            if (!getYNConfirm(scanner, "")) {
                break;
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private static void playGame(Scanner scanner) {
        while (true) {
            int rowMove = getRangedInt(scanner, "Enter the row (1-3): ", 1, 3);
            int colMove = getRangedInt(scanner, "Enter the column (1-3): ", 1, 3);

            if (isValidMove(rowMove, colMove)) {
                board[rowMove - 1][colMove - 1] = currentPlayer;
                display();

                if (isWin(currentPlayer)) {
                    System.out.println(currentPlayer + " wins!");
                    break;
                } else if (isTie()) {
                    System.out.println("It's a tie!");
                    break;
                }

                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
    }

    private static void display() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j]);
                if (j < COL - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < ROW - 1) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row - 1][col - 1].equals(" ");
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int getRangedInt(Scanner scanner, String prompt, int low, int high) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print(prompt);
                scanner.next(); // Consume the invalid input
            }
            input = scanner.nextInt();
        } while (input < low || input > high);
        return input;
    }

    private static boolean getYNConfirm(Scanner scanner, String prompt) {
        System.out.print(prompt);
        char response;
        do {
            response = scanner.next().toUpperCase().charAt(0);
            if (response != 'Y' && response != 'N') {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                System.out.print(prompt);
            }
        } while (response != 'Y' && response != 'N');
        return response == 'Y';
    }
}
