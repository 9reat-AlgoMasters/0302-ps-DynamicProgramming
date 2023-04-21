import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;

    static int[][] dp;
    static int num;
    static PriorityQueue<Integer> pq;
    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        for (int i = 0; i < M; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int moveSize = 1;
        while(true) {
            if (moveSize*(moveSize+1)/2 >= N) {
                moveSize--;
                break;
            }
            moveSize++;
        }

//        dp = new int[num + 1][N+1];
        dp = new int[N + 1][moveSize + 2];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], INF);
        }

        solve();
        int ans = INF;
        for (int i = 1; i <= moveSize; i++) {
            ans = Math.min(ans, dp[N][i]);
        }

        if (ans == INF) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }


    }

    static void solve() {
        int block = 0;
        if (M > 0) {
            block  = pq.poll();
        }

        dp[1][0] = 0;

        for (int i = 2; i <= N; i++) {
            if (i == block) {
                if (pq.size() > 0) {
                    block = pq.poll();
                }
                continue;
            }
            
            // 최대 움직임 범위 지정
            num = 1;
            while(true) {
                if (num*(num+1)/2 >= i) {
                    num--;
                    break;
                }
                num++;
            }

            for (int move = 1; move <= num; move++) {
                // 현재 위치에 움직임 크기move만큼으로 왔을때 최소값
                dp[i][move] = Math.min(dp[i - move][move - 1],
                        Math.min(dp[i - move][move], dp[i - move][move + 1])) + 1; // 이전 움직임중 최소값 찾기
            }
//            for (int k = 0; k <= N; k++) {
//                System.out.println(Arrays.toString(dp[k]));
//            }
        }



    }
}
