import java.util.Arrays;

public class TestStatistics {
    public static void main(String[] args) {
        int[] scores = {1232,12321312,12321321,213213213,213553,5345,345324,522,45,432472341};
        Arrays.sort(scores);
        int sum = 0;
        for (int score : scores){
            sum += score;
        }
        int median = 0;
        if (scores.length % 2 != 0){
            median = scores[(scores.length - 1)/2];
        }else{
            median = (scores[scores.length / 2 - 1] + scores[scores.length / 2]) /2;
        }
        System.out.printf("Average: %d\nHigh Score: %d\nLow Score: %d\nMedian: %d", sum/scores.length, scores[scores.length-1], scores[0], median);//slight rounding errors due to int rounding
    }
}
