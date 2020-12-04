package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;
    private MachineState machineState;



    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            coffeeMachine.print();
            String command = scanner.next();
            boolean shouldFinish = coffeeMachine.handleCommand(command);
            if (shouldFinish){
                break;
            }
        }
    }
    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.coffeeBeans = 120;
        this.cups = 9;
        this.money = 550;
        this.machineState = MachineState.MAIN;
    }

    enum MachineState {
        MAIN,
        BUY,
        FILL,
    }

    int material = 1;

        public void print() {
        switch (machineState) {
            case MAIN:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
            case FILL:
                switch (material) {
                    case 1:
                        System.out.println("Write how many ml of water do you want to add:");
                        break;
                    case 2:
                        System.out.println("Write how many ml of milk do you want to add:");
                        break;
                    case 3:
                        System.out.println("Write how many grams of coffee beans do you want to add:");
                        break;
                    case 4:
                        System.out.println("Write how many disposable cups do you want to add:");
                        break;
                }
            default: break;

        }
    }

    public boolean handleCommand(String command) {
        switch (machineState) {
            case MAIN:
                switch (command) {
                    case "buy":
                        machineState = MachineState.BUY;
                        return false;

                    case "fill":
                        machineState = MachineState.FILL;
                        return false;

                    case "take":
                        System.out.printf("I gave you $%d\n", money);
                        money = 0;
                        machineState = MachineState.MAIN;
                        return false;

                    case "remaining":
                        System.out.println("The coffee machine has:");
                        System.out.printf("%d of water\n", water);
                        System.out.printf("%d of milk\n", milk);
                        System.out.printf("%d of coffee beans\n", coffeeBeans);
                        System.out.printf("%d of disposable cups\n", cups);
                        System.out.printf("$%d of money\n", money);
                        machineState = MachineState.MAIN;
                        return false;

                    case "exit":
                        return true;

                    default:
                        System.out.println("no such a command");
                        return false;
                }

            case BUY:
                switch (command) {
                    case "1":
                        CoffeeType espresso = new CoffeeType(250, 0, 16, 4);
                        if (espresso.checkRes(water, milk, coffeeBeans, cups)) {
                            makeCoffee(espresso);
                        }
                        break;
                    case "2":
                        CoffeeType latte = new CoffeeType(350, 75, 20, 7);
                        if (latte.checkRes(water, milk, coffeeBeans, cups)) {
                            makeCoffee(latte);
                        }
                        break;
                    case "3":
                        CoffeeType cappuccino = new CoffeeType(200, 100, 12, 6);
                        if (cappuccino.checkRes(water, milk, coffeeBeans, cups)) {
                            makeCoffee(cappuccino);
                        }
                        break;
                    case "back":
                        break;
                    default:
                        System.out.println("no such a command");
                        machineState = MachineState.BUY;
                }

                machineState = MachineState.MAIN;
                return false;

            case FILL:
                switch (material) {
                    case 1:
                        water += Integer.parseInt(command);
                        material++;
                        break;
                    case 2:
                        milk += Integer.parseInt(command);
                        material++;
                        break;
                    case 3:
                        coffeeBeans += Integer.parseInt(command);
                        material++;
                        break;
                    case 4:
                        cups += Integer.parseInt(command);
                        material = 1;
                        machineState = MachineState.MAIN;
                        break;
                }
                return false;

            default: return false;
        }
    }

    public void makeCoffee(CoffeeType coffeeType){
        water -= coffeeType.getMinWater();
        milk -= coffeeType.getMinMilk();
        coffeeBeans -= coffeeType.getMinCoffee();
        cups--;
        money += coffeeType.getMoney();
    }
}
