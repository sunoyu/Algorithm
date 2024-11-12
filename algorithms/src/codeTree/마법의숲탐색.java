package algorithms.src.codeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
6 5 6
2 3
2 0
4 2
2 0
2 0
2 2
*/

public class 마법의숲탐색 {
	private static int R, C, K, num, sum;
	// 각 골렘마다의 출구 위치를 알아야한다.

	// 요정을 기준으로 출구 위치
	static int[] dy = { -1, 0, 1, 0 }; // 북 동 남 서
	static int[] dx = { 0, 1, 0, -1 };
	static boolean[][] visited; // 요정이 맵 돌때
	static int[][] map;
	static Gol[] exitList = new Gol[1001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		num = 1;

		initMap();

		for (int idx = 0; idx < K; idx++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int exit = Integer.parseInt(st.nextToken());

			int fy = 1; 
			int fx = x;

			int flag = 0;
			while (true) {
				// to South
				if (fy > R+1 || fx >= C || fx < 2)
					continue;
				if (map[fy + 1][fx - 1] + map[fy + 2][fx] + map[fy + 1][fx + 1] == 0) {
					fy += 1;
				} else if ( map[fy][fx - 2] + map[fy - 1][fx - 1] + map[fy + 1][fx - 1] + map[fy + 2][fx - 1]
						+ map[fy + 1][fx - 2] == 0) { // 서쪽(왼쪽)
					exit = (exit + 3) % 4;
					fx -= 1;   //6
					fy += 1;    // 8
				} else if (map[fy][fx + 2] + map[fy - 1][fx + 1] + map[fy + 1][fx + 1] + map[fy + 2][fx + 1]
						+ map[fy + 1][fx + 2] == 0) { // 동쪽
					exit = (exit + 1) % 4;
					fx += 1;
					fy += 1;
				} else {
					if (fy < 4) { // 더이상 갈곳이 없는데 골렘이 맵 밖에 위치하면
						initMap();
						flag = 1;
						break; // 실수로 안넣었었음!!!!!
					} else { // 더이상 갈 공간이 없다면.
						// 현재 골렘의 출구 저장
						exitList[num] = new Gol(fy + dy[exit], fx + dx[exit]);
						break;

					}
				}
			}
			if (flag == 1)
				continue;
			map[fy][fx] = num;
			for (int i = 0; i < 4; i++) {
				int ny = fy + dy[i];
				int nx = fx + dx[i];
				map[ny][nx] = num;
			}
			BFS(fy, fx);
			num++;

		}
		System.out.println(sum);

	}

	private static void BFS(int fy, int fx) {
		Queue<Gol> q = new LinkedList<>();
		q.offer(new Gol(fy, fx));

		visited = new boolean[R + 13][C + 12];
		int max = -1;
		while (!q.isEmpty()) {
			Gol cur = q.poll();
			int n = map[cur.y][cur.x];

			int ey = exitList[n].y; // 현재 노드기준 출구
			int ex = exitList[n].x;

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (ny < 3 || visited[ny][nx] || map[ny][nx] == -99999 || map[ny][nx] == 0)
					continue;
				
				if (map[cur.y][cur.x] == map[ny][nx]) {
					q.offer(new Gol(ny, nx));
					max = Math.max(max, ny - 2);
					visited[ny][nx] = true;
				} else if (cur.y == ey && cur.x == ex) { // next칸이 현재 칸과 다른 골렘일경우? 현재 칸이 출구라면 가능
					q.offer(new Gol(ny, nx));
					max = Math.max(max, ny - 2);
					visited[ny][nx] = true;
				}
			}
		}
		sum += max;
	}

	private static void initMap() {
		map = new int[R + 13][C + 12]; // 요정기준 상단 -2에서 시작하므로, + 아래와 좌우에 경계를 -1로 세팅해줄것
		for (int i = 0; i < R + 4; i++) {
			map[i][0] = -99999;
			map[i][C + 1] = -99999;
		}
		for (int j = 0; j < C + 2; j++) {
			map[R + 3][j] = -99999;
		}
	}

	private static class Gol {
		int y;
		int x;

		public Gol(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
