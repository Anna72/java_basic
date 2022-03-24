package tictactoe;
import java.util.Scanner;

import static java.lang.Character.*;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        char[] board = new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' '};
        printBoard1(board);

        char turn = 'X';
        int newCoordinates;
        boolean game = true;

        while (game) {

            System.out.println("Enter cells:");
            String coordinates = scan.nextLine();
            newCoordinates = checkCoordinates(coordinates, board);
            if (newCoordinates != -1) {

                board[newCoordinates] = turn;
                printBoard1(board);
                int tempResult = checkState(board);
                switch (tempResult){
                    case 1:
                        turn = (turn == 'X') ? 'O': 'X';
                        break;
                    case 2:
                        game = false;
                        break;
                }
            }
        }
    }

    static int checkCoordinates(String coord, char[] s) {

        if (!isDigit(coord.charAt(0)) || !isDigit(coord.charAt(2)) ) {
            System.out.println("You should enter numbers!");
            return(-1);
        } else {
            int a = getNumericValue(coord.charAt(0));
            int b = getNumericValue(coord.charAt(2));
            if ( a > 3 || a < 1 || b > 3 || b < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                return(-1);
            } else {
                int position  = (a - 1) * 3 + b - 1;
                if (s[position] == 'X' || s[position] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                    return(-1);
                } else {
                    return(position);
                }
            }
        }
    }


    static void printBoard1(char[] s) {
        System.out.println("---------");
        System.out.println("| " + s[0] + " " + s[1] + " " + s[2] + " |");
        System.out.println("| " + s[3] + " " + s[4] + " " + s[5] + " |");
        System.out.println("| " + s[6] + " " + s[7] + " " + s[8] + " |");
        System.out.println("---------");
    }


    static int checkState(char[] s) {

        char[][] a = new char[3][3];
        boolean xWins = false;
        boolean oWins = false;
        int dashCounter = 0;
        int xCounter = 0;
        int oCounter = 0;
        int state;
        // state :
        // 0 not defined
        // 1 impossible
        // 2 draw
        // 3 game not finished
        // 4 x wins
        // 5 o wins


        for (int i = 0; i < 9; i++) {
            a[i / 3][i % 3] = s[i];
            if (s[i] == '_'|| s[i] == ' ') {
                dashCounter++;
            } else {
                if (s[i] == 'X') {
                    xCounter++;
                } else {
                    if (s[i] == 'O') {
                        oCounter++;
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (a[i][0] == 'X' && a[i][1] == 'X' && a[i][2] == 'X') {
                xWins = true;
            }
            if (a[0][i] == 'X' && a[1][i] == 'X' && a[2][i] == 'X') {
                xWins = true;
            }
            if (a[i][0] == 'O' && a[i][1] == 'O' && a[i][2] == 'O') {
                oWins = true;
            }
            if (a[0][i] == 'O' && a[1][i] == 'O' && a[2][i] == 'O') {
                oWins = true;
            }
        }

        if (a[0][0] == 'X' && a[1][1] == 'X' && a[2][2] == 'X' || a[0][2] == 'X' && a[1][1] == 'X' && a[2][0] == 'X') {
            xWins = true;
        } else {
            if (a[0][0] == 'O' && a[1][1] == 'O' && a[2][2] == 'O' || a[0][2] == 'O' && a[1][1] == 'O' && a[2][0] == 'O') {
                oWins = true;
            }
        }

        if (Math.abs(xCounter - oCounter) > 1 || (xWins && oWins)) {
            //System.out.println("Impossible");
            state = 0;
        } else {
            if (!xWins && !oWins) {
                if (dashCounter > 0) {
                    //System.out.println("Game not finished");
                    state = 1;
                } else {
                    System.out.println("Draw");
                    state = 2;
                }
            } else {
                if (xWins) {
                    System.out.println("X wins");
                    state = 2;
                } else {
                    System.out.println("O wins");
                    state = 2;
                }
            }
        }
        return state;
    }



}


