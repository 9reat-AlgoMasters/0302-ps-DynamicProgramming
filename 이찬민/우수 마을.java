import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] people;
    static List<Integer>[] g;
    static int[][] dp;

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        people = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        g = new List[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++) {
            g[i] = new ArrayList<>();
        }

        int sNum = 1;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            g[A].add(B);
            g[B].add(A);
        }

        dp = new int[N + 1][2];
        for (int i = 1; i < N + 1; i++) {
            dp[i][1] = people[i];
        }

        dfs(sNum);

        System.out.println(Math.max(dp[sNum][0], dp[sNum][1]));
    }

    static void dfs(int n) {
        if (visited[n]) {
            return;
        }

        visited[n] = true;

        for (Integer num : g[n]) {
            if (!visited[num]) {
                dfs(num);
                // 일반 마을의 경우 자식이 우수 이거나 일반이거나 최대로
                dp[n][0] = dp[n][0] + Math.max(dp[num][0], dp[num][1]);
                // 우수면 자식 다 일반
                dp[n][1] = dp[n][1] + dp[num][0];
            }
        }
    }
}
