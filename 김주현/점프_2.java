import java.io.*;
import java.util.*;

public class Q2253_2 {
    static final int[] DIR = {-1, 0, 1};
    static final int IMPOSSIBLE = -1;
    static final int INF = Integer.MAX_VALUE;
    
    static int N, M;
    static int[] possibleJumps;
    static Set<Integer> smallStones;
    static int[][] dp;
    static int maxJump;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        smallStones = new HashSet<>();
        while (M-- > 0) {
            smallStones.add(Integer.parseInt(br.readLine()));
        }
        
        
        maxJump = makePossibleJumps();
        /*System.out.println(Arrays.toString(possibleJumps));
        System.out.printf("maxJump : %d\n", maxJump);*/
        initDP();
        solveDP();
        
        int minJump = INF;
        boolean findPath = false;
        for (int i = 1; i <= maxJump; i++) {
            if (dp[N][i] == IMPOSSIBLE) continue;
            minJump = Math.min(minJump, dp[N][i]);
            findPath = true;
        }
        
        if (smallStones.contains(2) || !findPath) {
            sb.append(IMPOSSIBLE);
        } else {
            sb.append(minJump);
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void initDP() {
        dp = new int[N + 1][maxJump+1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], IMPOSSIBLE);
        }
        dp[1][1] = 0;
    }
    private static void solveDP() {
        for (int i = 2; i <= N; i++) {
            if (smallStones.contains(i)) continue;
            for (int jump = 1; jump <= possibleJumps[i]; jump++) {
                dp[i][jump] = INF;
                int preStone = i - jump;
                boolean findPath = false;
                
                for (int d : DIR) {
                    int preStoneJump = jump + d;
                    if (preStoneJump <= 0 || preStoneJump > possibleJumps[preStone]) continue;
                    
                    if (dp[i-jump][preStoneJump] == IMPOSSIBLE) continue;
                    
                    findPath = true;
                    dp[i][jump] = Math.min(dp[i][jump], dp[i-jump][preStoneJump]+1);
                }
                
                if (!findPath) {
                    dp[i][jump] = IMPOSSIBLE;
                }
            }
        }
    }
    private static int makePossibleJumps() {
        possibleJumps = new int[N+1];
        possibleJumps[1] = 1;
        int stone = 2;
        int jump = 2;
        while (stone <= N) {
            Arrays.fill(possibleJumps, stone, Math.min(stone + jump, N+1), jump - 1);
            stone += jump;
            jump++;
        }
        return jump-2;
    }
}
