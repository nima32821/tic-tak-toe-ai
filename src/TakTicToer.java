public class TakTicToer
{
    public void gameStatus(String s)
    {
        char winner = s.charAt(0);

        switch (winner)
        {
            case 'X': System.out.println("The X is better currently"); break;
            case 'O': System.out.println("The O is better currently"); break;
            case 'D': System.out.println("The game is Draw currently"); break;
        }
    }
    public char checkBoard(char[] board)
    {
        /*
        returns 'X' if the X player wins
        returns 'O' if the O player wins
        returns 'D' if the game is Draw
        returns ' ' if the game just should continue.
         */

        if (((board[0] == 'X') && (board[1] == 'X') && (board[2] == 'X'))
                || ((board[3] == 'X') && (board[4] == 'X') && (board[5] == 'X'))
                || ((board[6] == 'X') && (board[7] == 'X') && (board[8] == 'X'))
                || ((board[0] == 'X') && (board[3] == 'X') && (board[6] == 'X'))
                || ((board[1] == 'X') && (board[4] == 'X') && (board[7] == 'X'))
                || ((board[2] == 'X') && (board[5] == 'X') && (board[8] == 'X'))
                || ((board[0] == 'X') && (board[4] == 'X') && (board[8] == 'X'))
                || ((board[2] == 'X') && (board[4] == 'X') && (board[6] == 'X')))
        {
            return 'X';
        }
        else if (((board[0] == 'O') && (board[1] == 'O') && (board[2] == 'O'))
                || ((board[3] == 'O') && (board[4] == 'O') && (board[5] == 'O'))
                || ((board[6] == 'O') && (board[7] == 'O') && (board[8] == 'O'))
                || ((board[0] == 'O') && (board[3] == 'O') && (board[6] == 'O'))
                || ((board[1] == 'O') && (board[4] == 'O') && (board[7] == 'O'))
                || ((board[2] == 'O') && (board[5] == 'O') && (board[8] == 'O'))
                || ((board[0] == 'O') && (board[4] == 'O') && (board[8] == 'O'))
                || ((board[2] == 'O') && (board[4] == 'O') && (board[6] == 'O')))
        {
            return 'O';
        }
        else
        {
            for (char c : board) // for any square at the board:
            {
                if (c == ' ') // if we find an empty square:
                {
                    return ' ';
                }
            }

            return 'D'; // if we didn't find an empty square.
        }
    }

    public boolean isEmpty(char c)
    {
        return (c == ' ');
    } // by the given character of the board, determines that it
    //is empty or not.

    public char changeTurn(char mover) /* used to change the turn of the game, or determining the opponent of the
    current player*/
    {
        if (mover == 'X')
        {
            return 'O';
        }

        return 'X';
    }

    public void printBoard(char[] board) // simply prints the board on the console.
    {
        System.out.println(board[0] + "|" + board[1] + "|" + board[2]);
        System.out.println("-----");
        System.out.println(board[3] + "|" + board[4] + "|" + board[5]);
        System.out.println("-----");
        System.out.println(board[6] + "|" + board[7] + "|" + board[8]);
    }

    public String bestMove(char[] board , char mover)   /* finds out the best move possible for the mover in the current
    board. also tells who will win the game if both players play their best moves.

    the format: the function returns a String in format "%c%d";
    the character determines who will win the game:
    X -> player X will win
    D -> the game will be Draw
    O -> player O will win
    and the number determines the best move possible for mover.
    an example of the format is : X1
    (the parameter mover is having value 'X' currently).
    it determines that in the given board and the given mover, the player X (the mover) could win the game, by doing the
    move: board[1] = 'X'.

    how it works: it just uses a type of the backtracking algorithm to analyse each move possible on the board, and
    determines the result of that move (X wins, Draw, O wins).
    the result of each move depends on the result of the next moves and the player who is doing the move.
    */
    {
        char[] theWinner = new char[9];
        // there are at most 9 moves possible in the current position of board.
        // we use a format nearly same as the format
        // we told above, an example of this array : theWinner[] = { 'D' , 'X' , 'O' , ... }
        // in the given example:
        // theWinner[0] = 'D', means that if mover do board[0] = mover, the game will be Draw.
        // theWinner[1] = 'X', means that if mover do board[1] = mover, the player X will win.
        // theWinner[2] = 'O', means that if mover do board[2] = mover, the player O will win.
        // etc.
        char opponentOfMover = changeTurn(mover);
        for (int n = 0 ; n <= 8 ; n++) // a loop for analysing each move possible in the board for the current mover.
        {
            if (isEmpty(board[n]))
                // if the current square of board is empty, then we could do a move as the current mover, and analyse
                // that move.
            {
                board[n] = mover;
                // doing the move on the board.
                // NOTE: the function just will do this moves to analyse the board, it will take back that move after
                // analyse is complete. the function will not change the board at the end, but changes board during
                // the function.

                theWinner[n] = checkBoard(board); // just checking the board.

                if (theWinner[n] == ' ')
                    // if the game should continue and the result is not known yet, we will continue also! xd.
                {
                    theWinner[n] = bestMove(board, opponentOfMover).charAt(0); // continuing the game as the opponent.
                }

                if (theWinner[n] == mover)
                {
                    board[n] = ' ';
                    // taking back the move, as we could not take it back later because of returning the result.
                    return String.format("%c%d" , theWinner[n] , n); // the result.
                }
                // the reason of having this if statement: just removing the need to check the other possible moves when
                // we already find the winning situation, I mean if we find the winning move, we will just do it,
                // there is no need to check other possible moves.

                board[n] = ' '; // here we just take back our move after the end of analysing that move.
            }
        }

        // and here we are! we just analysed each of the moves currently possible by the mover
        // until we find a winning move for mover or there is no more moves available,
        // and we will choose the best move for mover, very easy, first we check for possible Draw move, then we will do
        // a possible losing move


        // NOTE: we did not find any moves for winning the game:
        for (int i = 0 ; i <= 8 ; i++) // if we could not win, we have to try to draw the game.
        {
            if (theWinner[i] == 'D')
            {
                return String.format("%c%d" , 'D' , i);
            }
        }

        for (int i = 0 ; i <= 8 ; i++)
            // if we could not even draw the game (are we stupid?! no! the mover just did not do best moves possible,
            // and we should respect him/her.)
            // we have to sadly do a losing move.
        {
            if (theWinner[i] == opponentOfMover)
            {
                return String.format("%c%d" , opponentOfMover , i);
            }
        }

        return "ERROR!";
    }
}
