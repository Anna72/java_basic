package cinema;
import java.util.Scanner;
import java.util.Arrays;

public class Cinema{

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scan.nextInt();
        char[][] hall = new char[rows][seats];

        for (char[] chars : hall) {
            Arrays.fill(chars, 'S');
        }

        boolean exit = true;

        while (exit) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int a = scan.nextInt();
            switch (a) {
                case 1:
                    printSeats(rows, seats, hall);
                    break;
                case 2:
                    printSeatAndPrice(rows, seats, hall);
                    break;
                case 3:
                    calculateIncome(rows, seats, hall);
                    break;
                case 0:
                    exit = false;
                    break;
            }
        }


    }


    public static void printSeatAndPrice(int rows, int seats, char[][] hall) {
        Scanner scan = new Scanner(System.in);
        int row;
        int seat;

        while(true) {
            System.out.println("Enter a row number:");
            row = scan.nextInt();
            if (row > 0 && row <= rows) {
                break;
            } else {
                System.out.println("Wrong input!");
            }
        }

        while(true) {
            System.out.println("Enter a seat number in that row:");
            seat = scan.nextInt();
            if (seat > 0 && seat <= seats) {
                break;
            } else {
                System.out.println("Wrong input!");
            }
        }
        if (hall[row  - 1][seat - 1] == 'S'){
            int cost;
            if (rows * seats < 61){
                cost = 10;
            } else {
                cost = (row <= (rows / 2)) ? 10 : 8;
            }
            System.out.printf("Ticket price: $%d%n%n", cost);
            hall[row - 1][seat - 1] = 'B';
        } else {
            System.out.println("That ticket has already been purchased!");
            printSeatAndPrice(rows, seats,  hall);
        }

    }

    public static void printSeats(int rows, int seats, char[][] hall) {

        System.out.println("Cinema:");
        for (int i = 0; i < seats + 1; i++){
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        for (int i = 1; i < rows + 1; i++){
            for (int j = 0; j < seats + 1; j++){
                if (j == 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(hall[i - 1][j - 1] + " ");
                }
            }
            System.out.println();
        }
    }


    public static void calculateIncome(int rows, int seats, char[][] hall) {
        int purchasedSeats = 0;
        int income = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                if (hall[i][j] =='B') {
                    purchasedSeats++;
                    if (rows * seats < 61){
                        income += 10;
                    } else {
                        income += (i + 1 <= (rows / 2)) ? 10 : 8;
                    }
                }
            }
        }
        float persentage = (float)purchasedSeats / (rows*seats) * 100;
        System.out.printf("Number of purchased tickets: %d%n", purchasedSeats);
        System.out.printf("Percentage: %.2f%%%n", persentage);

        System.out.printf("Current income: $%d%n", income);

        if (rows * seats < 61){
            System.out.printf("Total income: $%d%n", rows * seats * 10);
        } else {
            System.out.printf("Total income: $%d%n", (rows / 2) * seats * 10 +(rows / 2 + rows  % 2) * seats * 8);
        }
    }
}