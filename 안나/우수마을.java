import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int population[];
	static ArrayList<Integer> [] tree;
	static boolean visited[];
	static int select[];
	static int notSelect[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		population = new int[N+1];
		visited = new boolean[N+1];
		tree = new ArrayList[N+1];
		select = new int[N+1]; //i번째 마을이 우수마을로 선정되었을때 단말노드에서 i번째까지의 최대 총합의 수
		notSelect = new int[N+1];//i번째 마을이 우수마을로 선정되지 않았을때 단말노드에서 i번째까지의 최대 총합의 수
		for (int i = 0; i < N+1; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int city1 = Integer.parseInt(st.nextToken());
			int city2 = Integer.parseInt(st.nextToken());
			
			tree[city1].add(city2);
			tree[city2].add(city1);
		}
		dfs(1); //출발 노드는 상관없음
		
		System.out.println(Math.max(select[1], notSelect[1]));
	}

	static void dfs(int root) {
		visited[root] = true;
		select[root] = population[root];
		
		for (int node : tree[root]) {
			if(visited[node]) continue;
			
			dfs(node);
			select[root] += notSelect[node];
			notSelect[root] += Math.max(select[node], notSelect[node]);
		}
	}

}