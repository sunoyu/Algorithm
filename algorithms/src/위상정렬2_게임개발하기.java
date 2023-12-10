import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1516
public class 위상정렬2_게임개발하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> AL = new ArrayList<>();
		int[] cost = new int[N+1];
		int[] indegree = new int[N+1];     // 특정 노드에 대해서 다른 노드로부터 들어오는 간선의 개수
		int[] result = new int[N+1];
		
		for(int i = 0; i <=N; i++) {
			AL.add(new ArrayList<>());
		}
		for(int i = 1; i <=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreElements()) {
				int next = Integer.parseInt(st.nextToken());
				if(next == -1) break;
				AL.get(next).add(i);        // ex. 4: 4 3 1 -1       3 -> 4   , 1 -> 4 추가
				indegree[i]++;
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		for(int i = 1; i <=N; i++) {
			if(indegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int next : AL.get(now)) {
				indegree[next] --;
				result[next] = Math.max(result[next], result[now] + cost[now]);
				if(indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		for(int i = 1; i<=N; i++) {
			System.out.println(result[i]+ cost[i]);
		}
		
	}

}
