package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨거리 {
	static int N,M;
	static boolean[] visited; 
	static int[] check;
	static int min;
	static int dosimin = Integer.MAX_VALUE;
	static ArrayList<Node> H;
	static ArrayList<Node> C;
//	static ArrayList<Integer> CH;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		H = new ArrayList<>();
		C = new ArrayList<>();
		
		check = new int[M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int now = Integer.parseInt(st.nextToken());
				if(now == 1) H.add(new Node(i,j));         // 집 주소를 저장
				else if(now == 2) C.add(new Node(i,j));
			}
		}
		
		visited = new boolean[C.size()];

		dfs(0);
		
		System.out.println(dosimin);
		
	}
	static void dfs(int lev) {

		if(lev == M) {
			// 전체 집들과 비교?
			int dosi = 0;
			for(int i = 0; i < H.size(); i++) {
				min = Integer.MAX_VALUE;
				int hr = H.get(i).r;
				int hc = H.get(i).c;
				
				for(int j = 0; j < M; j++) {
					int cr = C.get(check[j]).r;
					int cc = C.get(check[j]).c;
					
					int sum = Math.abs(hr-cr) + Math.abs(hc-cc);
					min = Math.min(sum, min);
				}
				dosi += min;
			}
			
			dosimin = Math.min(dosi, dosimin);
			return;

		}
		
		for(int i = 0; i < C.size(); i++) {
			if(visited[i]) continue;
			visited[i] = true;
			check[lev] = i;
			dfs(lev +1);
			visited[i] =false;
		}
		
	}

}

class Node{
	int r;
	int c;
	Node(int r, int c){
		this.r = r;
		this.c = c;
	}
}
