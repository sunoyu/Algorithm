package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b1167_트리의지름 {
// 힌트: 임의의 노드에서 가장 멀리 떨어진 노드는 지름의 꼭지점에 해당하는 두 노드 중 하나이다.
	static int n;
	static ArrayList<Nod>[] AL;
	static boolean[] visited;
	static int[] distance;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		AL = new ArrayList[n+1];
		StringTokenizer st;
		
		//ArrayList 만들기   - 아래 for문에서 AL객체들을 생성해주면 NullPointer 에러가 발생하더라.
		for(int i = 1; i <= n; i++) {
			AL[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= n; i++) {

			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens()) {
				int a = Integer.parseInt(st.nextToken());
				if(a == -1) break;
				int b = Integer.parseInt(st.nextToken());
				AL[node].add(new Nod(a,b));
			}
		}
		
		visited = new boolean[n+1];
		distance = new int[n+1];
		bfs(1);
		int max = 1;
		
		for(int i = 1; i <=n; i++) {
			if(distance[max] < distance[i]) {
				max = i;
			}
		}
		
		visited = new boolean[n+1];
		distance = new int[n+1];
		bfs(max);
		for(int i = 1; i <=n; i++) {
			if(distance[max] < distance[i]) {
				max = i;
			}
		}
		System.out.println(distance[max]);
		
	}
	static void bfs(int node) {
		Queue<Integer> q = new LinkedList<>();
		q.add(node);
		visited[node] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for(Nod i : AL[now]) {
				if(visited[i.dest]) continue;
				visited[i.dest] = true;
				q.add(i.dest);
				distance[i.dest] = distance[now] + i.dt;
			}
		}
	}

}


class Nod{
	int dest;
	int dt;
	
	public Nod(int dest, int dt) {
		this.dest = dest;
		this.dt = dt;
	}
	
}