import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int A, B, C;
    static char[] X, Y;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        A = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);
        C = Integer.parseInt(input[2]);
        X = br.readLine().toCharArray();
        Y = br.readLine().toCharArray();

        int size1 = X.length;
        int size2 = Y.length;
        //공백+문자열길이 의 2차원 배열 선언
        dp = new int[size1+1][size2+1];
        //0열은 Y문자열의 공백과 X문자열의 문자를 비교할 때의 점수를 담을 것임 => 적어도 하나 공백이므로 B
        for (int i = 1; i <= size1; i++) {
            dp[i][0] = dp[i-1][0] + B;
        }
        //0행은 X문자열의 공백과 Y문자열 문자 비교
        /*
         ex) X:abc Y:de
         ' 'abc => dp[0][1]      ' 'abc => dp[0][2]
          d e                   d e
         */
        for (int i = 1; i <= size2; i++) {
            dp[0][i] = dp[0][i-1] + B;
        }

        for (int i = 1; i <= size1; i++) {
            for (int j = 1; j <= size2; j++) {
                //X의 i번째 문자와 Y의 j번째 문자 비교
                //같으면 이전에 검사한 [i-1][j-1]에 A 더하기  / 다르면 C 더하기
                if(X[i-1] == Y[j-1]){
                    dp[i][j] = dp[i-1][j-1] + A;
                }else{
                    dp[i][j] = dp[i-1][j-1] + C;
                }
                //[i][j] 검사 바로 이전에 공백을 넣어서 온 경우 고려. 그 중 최대점수를 저장
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i][j-1] + B, dp[i-1][j] + B));
            }
        }
        System.out.println(dp[size1][size2]);
    }
}
