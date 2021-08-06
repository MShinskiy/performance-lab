//Максим Шинсикй

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class task2 {
    //calc(circle, radius, point)
    public static int calc(float[] c, int r, float[] p) {
        //distance = sqrt{(Xc - Xp)^2 + (Yc - Yp)^2}
        float dist = (float) Math.sqrt(Math.pow((c[0] - p[0]), 2) +
                Math.pow((c[1] - p[1]), 2));
        if(dist == r) return 0; //on
        if(dist < r) return 1;  //in
        return 2;               //out
    }


    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.out.println("Usage: java task2 <file1.txt> <file2.txt>");
            System.exit(1);
        }

        float[] cCoords = new float[2]; //circle(x, y)
        int r = 0;

        //read file with circle info
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String line;
            while((line = br.readLine()) != null) {
                String[] lineSplit = line.split(" ");
                if(lineSplit.length > 1)
                    for (int i = 0; i < lineSplit.length; i++)
                        cCoords[i] = Float.parseFloat(lineSplit[i]);
                else r = Integer.parseInt(line);
            }
        }

        //read file with points
        try(BufferedReader br = new BufferedReader(new FileReader(args[1]))){
            String line;
            while((line = br.readLine()) != null){
                String[] points = line.split(" ");
                System.out.println(calc(cCoords, r, new float[]{Float.parseFloat(points[0]), Float.parseFloat(points[1])}));
            }
        }
    }
}
