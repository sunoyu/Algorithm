import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2252
public class 위상정렬1_줄세우기 {

	private static int N;
	private static int M;

	public static void main(String[] args) throws IOException {
		// 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
																// 어레이리스트 배열을 아래와 같이 써줘도 된다.
		ArrayList<Integer>[] AL = new ArrayList[N+1];          // ArrayList<ArrayList<Integer> AL = new ArrayList<>();
		int[] count = new int[N+1]; // 진입차수 배열

		for(int i = 1; i<= N; i++) { 
			AL[i] = new ArrayList<>();
		}
		// ArrayList에 담기
		for(int i = 0; i<M ;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			AL[s].add(e);                                      // AL.get(s).add(e);
			count[e] ++;    // 지목당한 노드 진입차수 ++
		}

		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <=N; i++) {
			if(count[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			System.out.print(now + " ");
			
			for(int i : AL[now]) {
				int next = i;
				count[next] --;
				
				if(count[next] == 0) {
					q.offer(next);
				}
			}
			
			
		}
		
	}
}
