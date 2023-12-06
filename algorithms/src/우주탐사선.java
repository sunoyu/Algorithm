import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 우주탐사선 {

	private static int N;
	private static int start, sum, min;
	private static boolean[] visited;
	private static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		arr = new int[N][N];
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 플로이드-워셜로 시작점 마다 최소 거리 행렬로 바꿔주기.
		for(int k = 0; k < N; k++) {
			for(int i = 0 ; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
				}
			}		
		}
		
		min = 1000000;
		visited[start] = true;
		back(start, 0);
		System.out.println(min);

		
		
		
	}
	static void back(int now, int lev) {
		if(lev == N-1) {
			min = Math.min(sum, min);
			return;
		}
		for(int i = 0 ; i < N; i++) {
			if(visited[i]) continue;
			if(now == i) continue;
			visited[i] = true;
			sum += arr[now][i];
			back(i, lev+1);
			visited[i] = false;
			sum -= arr[now][i];
		}
	}

}
