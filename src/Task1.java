public class Task1 {

    private static int path(int n, int m){
        String res = "";
        int p = 1;
        do {
            res += p;
            for (int i = 0; i < m; ++i) {
                if(p > n) p -= n;
                p++;
            }
            p--;
        }while(p != 1);

        return Integer.parseInt(res);
    }

    public static void main(String[] args) {
        System.out.println(path(5, 4));
    }
}
