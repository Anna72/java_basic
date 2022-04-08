package machine;
import java.util.Scanner;
import static machine.MachineStatus.EXIT;

enum MachineStatus {
    CHOOSING_AN_ACTION, CHOOSING_COFFEE, EXIT, F_WATER, F_MILK, F_COFFEE, F_CUPS
}

public class CoffeeMachine {
    static MachineStatus machine = MachineStatus.CHOOSING_AN_ACTION;
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;
    final static int[] waterNeeded = {250, 350, 200}; // for espresso, latte, cappuccino
    final static int[] milkNeeded = {0, 75, 100};
    final static int[] beansNeeded = {16, 20, 12};
    final static int[] cupCost = {4, 7, 6};

    static void action(String s) {
        switch (machine) {
            case CHOOSING_AN_ACTION:
                switch (s) {
                    case "buy":
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                        machine = MachineStatus.CHOOSING_COFFEE;
                        break;
                    case "fill":
                        System.out.println("Write how many ml of water you want to add: ");
                        machine = MachineStatus.F_WATER;
                        break;
                    case "take":
                        take();
                        break;
                    case "remaining":
                        showStatus();
                        break;
                    case "exit":
                        machine = EXIT;
                        break;
                }
                break;
            case CHOOSING_COFFEE:
                switch (s) {
                    case "1":
                    case "2":
                    case "3":
                        makeCoffee(Integer.parseInt(s) - 1);
                        break;
                    case "back":
                        toChoose();
                        break;
                }
                break;
            case F_WATER:
                water += Integer.parseInt(s);
                System.out.println("Write how many ml of milk you want to add:");
                machine = MachineStatus.F_MILK;
                break;
            case F_MILK:
                milk += Integer.parseInt(s);
                System.out.println("Write how many grams of coffee beans you want to add: ");
                machine = MachineStatus.F_COFFEE;
                break;
            case F_COFFEE:
                beans += Integer.parseInt(s);
                System.out.println("Write how many disposable cups of coffee you want to add: ");
                machine = MachineStatus.F_CUPS;
                break;
            case F_CUPS:
                cups += Integer.parseInt(s);
                toChoose();
                break;
        }
    }

    static void makeCoffee(int coffeeType) {
        if (water < waterNeeded[coffeeType]) {
            System.out.println("Sorry, not enough water!");
        } else
        if (milk  < milkNeeded[coffeeType]) {
            System.out.println("Sorry, not enough milk!");
        } else
        if (beans < beansNeeded[coffeeType]) {
            System.out.println("Sorry, not enough coffee beans!");
        } else
        if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            water -= waterNeeded[coffeeType];
            milk -= milkNeeded[coffeeType];
            beans -= beansNeeded[coffeeType];
            money += cupCost[coffeeType];
            cups -= 1;
            System.out.println("I have enough resources, making you a coffee!");
        }
        toChoose();
    }

    static void take() {
        System.out.printf("I gave you $%d%n", money);
        money = 0;
        toChoose();
    }

    static void showStatus() {
        System.out.printf("The coffee machine has:%n" +
                "%d ml of water%n" +
                "%d ml of milk%n" +
                "%d g of coffee beans%n" +
                "%d disposable cups%n" +
                "$%d of money%n",water ,milk , beans, cups, money);
        toChoose();
    }

    static void toChoose() {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
        machine = MachineStatus.CHOOSING_AN_ACTION;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        while(CoffeeMachine.machine != EXIT) {
            String s = scan.nextLine();
            CoffeeMachine.action(s);
        }
    }
}
