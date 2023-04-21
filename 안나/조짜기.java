import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int score[];
	static int memo[][];
	static boolean[] selected;
	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		score= new int[N];
		memo = new int[N][N]; //i에서 j까지의 조가 잘짜여진 정도의 합의 최대값
		selected = new boolean[N];
		StringTokenizer st =new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		//길이가 2부터 N까지의 조를 봐야하기 때문에
		for (int len = 2; len <=N ; len++) {
			int start = 0; 
			while (start+len <= N) { //길이가 len인 조의 경우들
				int end = start+len-1;
				for (int p = start+1; p < start+len; p++) { //길이가 len인 조들 중 start에서 시작하는 조를 나누기
					int value = memo[start][p-1]+memo[p][end];
					memo[start][end]= Math.max(memo[start][end], value);
				}
				//전체랑 비교하기 즉 나누지 않은 경우
				memo[start][end]= Math.max(memo[start][end], getDegree(start,end));
				
				start++;
			}
			
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(memo[i]));
//			
//		}
		
		System.out.println(memo[0][N-1]);
		
	}
	static int getDegree(int start, int end) {
		int sum=0;

		int maxvalue =0;
		int minvalue =Integer.MAX_VALUE;
		for (int j = start; j <=end ; j++) {
			maxvalue = Math.max(maxvalue, score[j]);
			minvalue = Math.min(minvalue, score[j]);
		}
		return maxvalue - minvalue;
	}

}