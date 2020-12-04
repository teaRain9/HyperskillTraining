package machine;

public class CoffeeType {
    public int getMinWater() {
        return minWater;
    }

    public int getMinMilk() {
        return minMilk;
    }

    public int getMinCoffee() {
        return minCoffee;
    }

    public int getMoney() {
        return money;
    }

    private final int minWater;
    private final int minMilk;
    private final int minCoffee;
    private final int money;




    public CoffeeType(int minWater, int minMilk, int minCoffee, int money) {
        this.minWater = minWater;
        this.minMilk = minMilk;
        this.minCoffee = minCoffee;
        this.money = money;
    }

    public boolean checkRes(int water, int milk, int coffee, int cup){
        if (water < minWater){
              System.out.println("Sorry, not enough water!");
              return false;
        }
        if (milk < minMilk) {
              System.out.println("Sorry, not enough milk!");
              return false;
        }

        if (coffee < minCoffee) {
              System.out.println("Sorry, not enough coffee beans!");
              return false;
        }

        if (cup < 1) {
              System.out.println("Sorry, not enough cup!");
              return false;
        }

        System.out.println("I have enough resources, making you a coffee!");
        return true;

    }
}
