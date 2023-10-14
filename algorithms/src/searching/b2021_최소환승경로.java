package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2021_최소환승경로 {
	// ADV 와 비슷한 문제
	/*
	10 3
	1 2 3 4 5 -1
	9 7 10 -1
	7 6 3 8 -1
	1 10*/
	
	static int staLen, lineLen, S, E;
	static boolean[] visitedLine, visitedStation;
	static ArrayList<Integer>[] lineArr, stationArr;   
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		staLen = Integer.parseInt(st.nextToken());
		lineLen = Integer.parseInt(st.nextToken());
		
		lineArr = new ArrayList[lineLen+1];    // ex. lineArr[i]   i호선에 대한 역들이 담긴다.
		stationArr = new ArrayList[staLen+1]; // ex. stationArr[i]   i역에 대한 x호선 정보들이 담긴다.
		
		visitedLine = new boolean[lineLen+1];
		visitedStation = new boolean[staLen+1];
		
		for(int i = 1; i <= lineLen; i++) lineArr[i] = new ArrayList<>();
		for(int i = 1; i <= staLen; i++) stationArr[i] = new ArrayList<>();

		
		for(int i = 1; i <= lineLen; i++) {
			st= new StringTokenizer(br.readLine());
			while(true) {
				int nextSta = Integer.parseInt(st.nextToken());
				if(nextSta == -1) break;
				lineArr[i].add(nextSta);
				stationArr[nextSta].add(i);
			}
		}
		st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		visitedStation[S] = true;
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Nodee> q = new LinkedList<>();
		for(int e : stationArr[S]) {
			q.add(new Nodee(e , 0));
			visitedLine[e] = true;
		}
		
		while(!q.isEmpty()) {
			Nodee node = q.poll();
			for(int v: lineArr[node.nowLine]) {
				if(v == E) return node.trans;
				if(visitedStation[v]) continue;
				visitedStation[v] = true;
				
				for(int e : stationArr[v]) {
					if(visitedLine[e]) continue;
					visitedLine[e] = true;
					q.add(new Nodee(e, node.trans+1));
				}
			}
		}
		
		return -1;
	}
}

class Nodee{
	int nowLine;
	int trans;
	
	Nodee(int nowLine, int trans){
		this.nowLine = nowLine;
		this.trans = trans;
	}
}
