import java.io.*;
import java.util.*;

/*
너무 깊은 재귀로 메모리 초과
 */

public class Q2253 {
    static final int[] DIR = {-1, 0, 1};
    static final int IMPOSSIBLE = -1;
    static final int ALL_BITS_ON = (1<<14)-1;
    static final int STONE = 0;
    static final int JUMP = 1;
    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static Set<Integer> smallStones;
    static Map<Integer, Integer> dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        smallStones = new HashSet<>();
        dp = new HashMap<>();
        while (M-- > 0) {
            smallStones.add(Integer.parseInt(br.readLine()));
        }
        
        if (smallStones.contains(2)) {
            sb.append(IMPOSSIBLE);
        } else {
            sb.append(solveDP(2, 1) + 1);
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static int solveDP(int stone, int jump) {
        System.out.printf("[IN] stone : %d, jump : %d\n", stone, jump);
        if (stone == N) {
            System.out.printf("[OUT - N번 도착!] stone : %d, jump : %d\n", stone, jump);
            return 0;
        }
        
        int mask = makeMask(stone, jump);
        if (dp.containsKey(mask)) {
            System.out.printf("[OUT - 이미 조사됨!] stone : %d, jump : %d\n", stone, jump);
            return dp.get(mask);
        }
        
        int minJump = INF;
        
        for (int d : DIR) {
            if (jump + d <= 0) continue;
            int nextStone = stone + jump + d;
            if (nextStone > N || smallStones.contains(nextStone)) continue;
            int tempJump = solveDP(nextStone, jump + d);
            if (tempJump == IMPOSSIBLE) continue;
            minJump = Math.min(minJump, tempJump);
        }
        
        if (minJump == INF) {
            System.out.printf("[OUT - 모든 경우 조사했지만 가능한 길이 없음] stone : %d, jump : %d\n", stone, jump);
            return IMPOSSIBLE;
        }
        
        System.out.printf("[OUT - 가능한 길 찾고 빠져나옴] stone : %d, jump : %d\n", stone, jump);
        dp.put(mask, minJump + 1);
        return dp.get(mask);
    }
    
    public static int makeMask(int stone, int jump) {
        return (stone << 14) + jump;
    }
}
