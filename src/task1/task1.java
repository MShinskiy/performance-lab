package task1;

public class task1 {

    private static int path(int n, int m){
        //initialize
        String res = "";    //result path string
        int p = 1;          //pointer

        //while the path is incomplete
        do {
            res += p;   //add pointer to the path
            //create next tuple of size m and move pointer
            for (int i = 0; i < m; ++i) {
                if(p > n) p -= n;
                p++;
            }
            p--;    //tuples begin and finish on the same number
        }while(p != 1);

        return Integer.parseInt(res);
    }

    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Usage: java task1 <n> <m>\nwhere n and m are integers.");
            System.exit(1);
        }
        System.out.println(path(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
    }
}
