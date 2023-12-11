import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

//https://www.acmicpc.net/problem/1916
public class 데이크스트라2_최소비용 {
	private static int N;
	private static int M;
	private static int[] distance;
	private static boolean[] visited;
	private static PriorityQueue<Edge2> pq;
	static ArrayList<Edge2>[] list;
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());  // 도시 갯수
		M = Integer.parseInt(br.readLine());  // 버스 대수
		
		visited = new boolean[N+1];
		
		list = new ArrayList[N+1];
		for(int i = 0 ; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[s].add(new Edge2(e, w));
		}
		st = new StringTokenizer(br.readLine());
		int src = Integer.parseInt(st.nextToken());
		int dest = Integer.parseInt(st.nextToken());
		
		
		distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[src] = 0;
		System.out.println(dijkstra(src, dest));

		
		
		
	}
	static int dijkstra(int start , int end) {
		pq = new PriorityQueue<Edge2>();
		pq.offer(new Edge2(start, 0));
		
		while(!pq.isEmpty()) {
			Edge2 current = pq.poll();
			int now = current.targetVertex;
			
			if(visited[now]) continue;
			visited[now] = true;
			
			for(Edge2 i : list[now]) {
//				if(visited[i.targetVertex]) continue;       없어야해!!!
//				visited[i.targetVertex] = true;
				if(distance[i.targetVertex] > distance[now] + i.value) {
					distance[i.targetVertex] = distance[now]+ i.value;
				}
				System.out.println(distance[end]);
				pq.offer(new Edge2(i.targetVertex, distance[i.targetVertex]));
			}
			
		}
		return distance[end];
	}
}

class Edge2 implements Comparable<Edge2>{
	int targetVertex;
	int value;
	Edge2(int targetVertex, int value){
		this.targetVertex = targetVertex;
		this.value = value;
	}
	@Override
	public int compareTo(Edge2 o) {
		return	this.value - o.value;      // 양수가 나오면 this가 더 큰 것. 

	}
	
	
}