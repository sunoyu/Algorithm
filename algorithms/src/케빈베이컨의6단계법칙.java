import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1389
public class 케빈베이컨의6단계법칙 {

	private static int[][] arr;
	private static int N;
	private static int M, miner;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				arr[i][j] = i==j ? 0 : 1000000;
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[s][e] = 1;
			arr[e][s] = 1;

		}
		
		// 플로이드-워셜
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		int min = 1000000;
		miner = 1000;
		for(int i = 1; i <= N; i++) {
			int sum = 0;
			for(int j = 1; j <= N; j++) {
				if(arr[i][j] == 1000000) arr[i][j] = 0;
				sum += arr[i][j];
			}
			if(min == sum) continue;    // 케빈 베이컨의 수가 가장 작은 사람을 출력한다. 그런 사람이 여러 명일 경우에는 번호가 가장 작은 사람을 출력한다.
			if(min >= sum) {
				min = sum;
				miner = i;
			}
		}
		System.out.println(miner);
		
		
	}

}
