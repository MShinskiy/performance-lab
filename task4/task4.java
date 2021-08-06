//Максим Шинский

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class task4 {

    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            System.out.println("Usage: java task4 <file.txt>");
            System.exit(1);
        }

        //initialization
        int dev;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        ArrayList<Integer> nums = new ArrayList<>();

        //iterate through the numbers
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                int num = Integer.parseInt(line);
                nums.add(num);
                if(num < min) min = num;
                if(num > max) max = num;
            }
        }
        //initialize an array of
        int[] roundNums = new int[max-min + 1];

        //round every number in the list to every number in the range (max-min)
        for(int i = 0; i < roundNums.length; i++) {
            dev = 0;
            //do for every num in list
            for (int n : nums) dev += Math.abs(i+min - n);
            roundNums[i] = dev;
        }

        min = Integer.MAX_VALUE;
        //find a minimum in the array
        for (int arrOfNum : roundNums) if (arrOfNum < min) min = arrOfNum;

        System.out.println(min);
    }
}
