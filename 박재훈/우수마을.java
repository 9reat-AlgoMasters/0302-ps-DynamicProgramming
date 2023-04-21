import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N;
    static int[] population;
    static int[][] dp;
    static boolean[] visited;
    static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
      //인구 저장 배열
        population = new int[N+1];
      //1번마을부터 N번마을까지 / 
      //dp[i][0] : i번 마을을 우수마을로 선정x일 때 인구수 합의 최대
      //dp[i][1] : i번 마을을 우수마을로 선정할 때 최대
        dp = new int[N+1][2];
        visited = new boolean[N+1];
        adj = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        String[] input = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(input[i-1]);
        }
        for (int i = 1; i < N; i++) {
            input = br.readLine().split(" ");
            int v1 = Integer.parseInt(input[0]);
            int v2 = Integer.parseInt(input[1]);
          //인접리스트
            adj[v1].add(v2);
            adj[v2].add(v1);
        }
        //1번마을부터 모든마을 탐색, dp테이블 갱신
        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
    static void dfs(int cur) {
    	visited[cur] = true;
      //cur번 마을을 우수마을로 선정하므로 일단 그마을의 인구수로 설정
    	dp[cur][1] = population[cur];
    	
      for (int next : adj[cur]) {
          if(visited[next]) continue;
          //인접한 마을 탐색
          dfs(next);
          //인접 마을 탐색 후의 갱신된 dp테이블 결과로 cur번의 결과를 갱신
          dp[cur][0] += Math.max(dp[next][0], dp[next][1]);
          dp[cur][1] += dp[next][0];

      }
    }
}
