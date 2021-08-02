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
        int sum = 0, dev = 0, avg;
        ArrayList<Integer> nums = new ArrayList<>();

        //iterate through the numbers
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                int num = Integer.parseInt(line);
                nums.add(num);
                sum += num;
            }
        }
        avg = sum/nums.size();
        //find sum of deviation
        for (int n : nums) dev += Math.abs(avg - n);
        System.out.println(dev);
    }
}
