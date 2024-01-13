import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/13549
public class 숨바꼭질3 {
	static int S, E;
	static int min = Integer.MAX_VALUE;
	static boolean[] visited;
	static PriorityQueue<Edge3> q = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		visited = new boolean[(S>E?S:E)+5];
		
		System.out.println(bfs());
		
	}
	private static int bfs() {
		q.add(new Edge3(S, 0));
		
		while(!q.isEmpty()) {
			Edge3 edge = q.poll();
			visited[edge.vertex] = true;    // 중요1 !!! 뽑아줄 때 방문 체크

			if(edge.vertex == E) {  		// 중요2 !!! PQ에서 한번 필터링 된 엣지에 조건걸기, 밑으로 뺴주면 틀림
				return edge.time;
			}
			
			for(int i = 0 ; i < 3; i++) {
				int nPoint = 0, nTime =0;
				switch (i) {
				case 0:
					nPoint = edge.vertex * 2;
					nTime = edge.time;
					break;
				case 1:
					nPoint = edge.vertex -1;
					nTime = edge.time + 1;
					break;

				case 2:
					nPoint = edge.vertex + 1;
					nTime = edge.time + 1;
					break;
				}
				
				if(nPoint < 0 || nPoint >= ((S>E?S:E)+5) ) continue;
				if(visited[nPoint]) continue;
				q.add(new Edge3(nPoint, nTime));
			}
		}
		return -1;
	}

}

class Edge3 implements Comparable<Edge3>{
	int vertex;
	int time;
	public Edge3(int vertex, int time) {
		this.vertex = vertex;
		this.time= time;
	}
	@Override
	public int compareTo(Edge3 o) {
		return this.time - o.time;        // 동일    오름차순
//		if(this.value > o.value) return 1;
//		else 			         return -1;
	}	
}



