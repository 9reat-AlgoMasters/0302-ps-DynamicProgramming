import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int N, M;
    static Map<Integer,Integer>[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
      
        //dp[i] : i번 돌에 도착 시 <이전 돌에서 점프한 거리, 최소 점프 횟수 > 쌍을 저장
        //ex) dp[i] hashmap 안의 원소 <x,y> : 이전 돌에서 x만큼 점프해서 i번 돌에 도착하는 경우의 수 중 최소 점프 횟수가 y임
        dp = new HashMap[N+1];
        for (int i = 1; i <= N; i++) {
            dp[i] = new HashMap<>();
        }

        for (int i = 0; i < M; i++) {
            int val = Integer.parseInt(br.readLine());
            //못 올라가는 돌은 hashmap 할당 x
            dp[val] = null;
        }

        System.out.println(solve());

    }

    static int solve(){
        //첫번째 돌에는 점프 안해도 도착해있으므로 0,0 저장
        dp[1].put(0, 0);
        int min = Integer.MAX_VALUE;

        //1번째 돌부터 점프 시작
        for (int i = 1; i < N; i++) {
            //못 올라가는 돌은 넘기기
            if(dp[i] == null) continue;
            
            //i번째 돌에 도착할 때 점프 거리의 경우의 수 탐색
            for (int j : dp[i].keySet()) {
                //이전 돌에서 j만큼 점프해서 i번째 돌 도착, 그때까지의 점프 수 count 
                int count = dp[i].get(j);
                //다음 점프 가능 거리 j-1, j, j+1
                for (int k = j-1; k < j+2 ; k++) {
                    if(k <= 0) continue;
                    if(i+k > N) break;
                    if(dp[i+k] == null) continue;
                    //안 되는 돌들은 거르고 i+k번째 돌 도착
                  
                    Integer curMin = dp[i+k].get(k);
                    //첫 도착이거나 기존보다 더 적은 값으로 도착한 것이라면 갱신
                    if(curMin == null || curMin > count + 1) {
                        dp[i+k].put(k, count + 1);
                        if(i+k == N){
                            min = Math.min(min, count+1);
                        }
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1:min;
    }
}
