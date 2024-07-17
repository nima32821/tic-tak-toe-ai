import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain)
        {
            char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

            TakTicToer theAI = new TakTicToer();
            char mover = 'X';
            int move;
            theAI.printBoard(board);
            System.out.println("which player do you want to be? (X = 1 / O = 0) the X plays first.");
            int player = input.nextInt();
            if (player == 1) // the player did choose the X.
            {
                while (theAI.checkBoard(board) == ' ') // while the game did not end yet.
                {
                    if (mover == 'X') {
                        System.out.println("do your move: ");

                        move = input.nextInt();

                        if (theAI.isEmpty(board[move])) {
                            board[move] = 'X';
                        } else {
                            System.out.println("Illegal move");
                            continue;
                        }
                    } else {
                        String bestMove = theAI.bestMove(board, mover);
                        theAI.gameStatus(bestMove);
                        board[Integer.parseInt(String.format("%c", bestMove.charAt(1)))] = 'O';
                    }

                    theAI.printBoard(board);
                    mover = theAI.changeTurn(mover);
                }
            } else if (player == 0) // the player did choose the O.
            {
                while (theAI.checkBoard(board) == ' ') {
                    if (mover == 'O') {
                        System.out.println("do your move: ");

                        move = input.nextInt();

                        if (theAI.isEmpty(board[move])) {
                            board[move] = 'O';
                        } else {
                            System.out.println("Illegal move");
                            continue;
                        }
                    } else {
                        String bestMove = theAI.bestMove(board, mover);
                        theAI.gameStatus(bestMove);
                        board[Integer.parseInt(String.format("%c", bestMove.charAt(1)))] = 'X';
                    }

                    theAI.printBoard(board);
                    mover = theAI.changeTurn(mover);
                }
            }

            // defining the result after the end of game.
            switch (theAI.checkBoard(board)) {
                case 'X':
                    System.out.println("The Winner is: X");
                    break;
                case 'O':
                    System.out.println("The Winner is O");
                    break;
                case 'D':
                    System.out.println("The game is Draw");
                    break;
            }

            System.out.println("Want to play again?");
            System.out.println("1.yes");
            System.out.println("2.no, exit");
            if (input.nextInt() != 1)
            {
                playAgain = false;
            }
        }
    }
}