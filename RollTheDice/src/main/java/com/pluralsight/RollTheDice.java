package com.pluralsight;

public class RollTheDice {
    public static void main(String[] args) {
        int two = 0, four = 0, six = 0, seven = 0;
        for (int i = 1; i <= 100; i++){
            int roll1 = Dice.roll();
            int roll2 = Dice.roll();
            int sum = roll1 + roll2;
            System.out.printf("Roll %d: %d - %d Sum: %d\n", i, roll1, roll2, sum);
            if (sum == 2) {
                two++;
            }else if (sum == 4){
                four++;
            }else if (sum == 6){
                six++;
            }else if (sum == 7){
                seven++;
            }
        }
        System.out.printf("Totals:\n\tTwos Rolled: %d\n\tFours Rolled: %d\n\tSixes Rolled: %d\n\tSevens Rolled: %d", two, four, six,seven);
    }
}
