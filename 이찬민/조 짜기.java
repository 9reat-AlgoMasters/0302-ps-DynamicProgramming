import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[][] diff;
    static int[] dp;

    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        dp = new int[N];
        diff = new int[N + 1][N + 1];


        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            max = -1;
            min = 123456781;
            for (int j = i; j < N; j++) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                diff[i][j] = max - min;
            }
        }

        solve();
        System.out.println(dp[N-1]);
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
//                System.out.println(diff[j+1][i]);
                if(dp[i] < dp[j] + diff[j+1][i]){
                    dp[i] = dp[j] + diff[j+1][i];
//                    System.out.println(dp[i]);
                }
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i], Math.max(dp[i-1], diff[0][i]));
            }
        }
    }
}
