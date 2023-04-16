import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2229 {
    static final int EMPTY = -1;
    
    static int N;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N][N];
        initDP();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        sb.append(solveDP(0, N - 1));
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void initDP() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], EMPTY);
        }
    }
    
    private static int solveDP(int from, int to) {
        
        if (from >= to) {
            return 0;
        }
        
        if (dp[from][to] != EMPTY) {
            return dp[from][to];
        }
        
        int min = Integer.MAX_VALUE;
        int max = -1;
        
        for (int i = from; i <= to; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            int diff = max - min;
            dp[from][to] = Math.max(dp[from][to], diff + solveDP(i + 1, to));
        }
        
        return dp[from][to];
    }
}


















