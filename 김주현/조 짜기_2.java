import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2229_2 {
    static final int EMPTY = -1;
    
    static int N;
    static int[] arr;
    static int[] dp;
    
    public static void setInputFile(String path, String fileName) throws FileNotFoundException {
        String curPath = System.getProperty("user.dir");
        System.setIn(new FileInputStream(curPath + path + fileName));
    }
    public static void main(String[] args) throws IOException {
        String path = "\\solve\\tc\\";
        String fileName = "Q2229.txt";
        setInputFile(path, fileName);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        initDP();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        sb.append(solveDP(0));
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void initDP() {
        Arrays.fill(dp, EMPTY);
    }
    
    private static int solveDP(int from) {
        if (from >= N-1) {
//            System.out.printf("[OUT - 0이 되는 범위] from : %d\n", from);
            return 0;
        }
        
        if (dp[from] != EMPTY) {
//            System.out.printf("[OUT - 이미 조사됨] from : %d\n", from);
            return dp[from];
        }
        
        int min = Integer.MAX_VALUE;
        int max = -1;
        
        for (int i = from; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            int diff = max - min;
            dp[from] = Math.max(dp[from], diff + solveDP(i + 1));
        }
        
//        System.out.printf("[OUT - for end] from : %d\n", from);
        return dp[from];
    }
}


















