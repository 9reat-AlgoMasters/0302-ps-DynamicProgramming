import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static int A;
    static int B;
    static int C;
    static String X;
    static String Y;

    static int xLen;
    static int yLen;

    static int[][] dp;

    static final int INF = Integer.MIN_VALUE /2;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        X = br.readLine();
        Y = br.readLine();
        xLen = X.length();
        yLen = Y.length();
        dp = new int[xLen][yLen];

        for (int i = 0; i < X.length(); i++) {
            for(int j = 0; j < Y.length(); j++) {
                dp[i][j] = INF;
            }
        }

        int ans = solve(0, 0);

        System.out.println(ans);
    }

    static int solve(int p1, int p2) {
//        System.out.println(p1 + " " + p2);
        if(p1 == xLen  && p2 == yLen) {
//            System.out.println("in");
            return 0;
        } else if (p1 == xLen && p2 < yLen) {
//            System.out.println("in2");
            return B * (yLen - p2);
        } else if(p1 < xLen && p2 == yLen) {
//            System.out.println("in3");
            return B * (xLen - p1);
        }

        if (dp[p1][p2] != INF) {
//            System.out.println("value in dp table");
            return dp[p1][p2];
        }
        int val = INF;
        int checkAC = 0;

        if(X.charAt(p1) == Y.charAt(p2)) {
//            System.out.println("same");
            checkAC = A;
        } else {
//            System.out.println("not same");
            checkAC = C;
        }

        val = Math.max(val, Math.max(checkAC + solve(p1+1, p2+1), Math.max( B  + solve(p1+1, p2), B + solve(p1, p2+1))));

        dp[p1][p2] = val;

//        for (int i = 0; i < X.length(); i++) {
//            for(int j = 0; j < Y.length(); j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("///////////////////////////////////////////");

        return dp[p1][p2];
    }
}
