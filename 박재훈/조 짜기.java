import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] arr, dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        //dp[i] : i번째 학생의 조 편성 완료 후 잘 짜여진 정도의 최댓값
        dp = new int[N];
        //인덱스 1부터 N-1까지의 학생의 조 편성
        //처음 0번째 학생은 조를 구성하면 혼자이므로 잘 짜여진 정도가 0
        for (int i = 1; i < N; i++) {
            //현재 편성 중인 i번째 학생 혼자 조 짤때부터 앞의 한명과 한 조, 앞의 두명과 한 조...인원 추가해가며 잘 짜여진 정도 계산
            int min = arr[i];
            int max = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                //j번째 학생까진 완료...그 이후 학생부터 i번 까지 한 조
                int val = dp[j] + max - min;
                dp[i] = Math.max(dp[i], val);
                //다음 번에는 j번째 학생이 조에 추가되므로 미리 최대/최소 갱신
                if(min > arr[j]) {
                    min = arr[j];
                }
                if(max < arr[j]) {
                    max = arr[j];
                }
            }
            //전체 다 한 조일때까지 잘 짜여진 정도 계산
            dp[i] = Math.max(dp[i], max - min);
        }

        System.out.println(dp[N-1]);
    }

}
