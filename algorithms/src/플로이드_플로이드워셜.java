import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 플로이드_플로이드워셜 {

	private static int N;
	private static int M;
	private static int[][] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		
		arr = new int[N][N];
		
		// 1. 플로이드 워셜을 위한 행렬 세팅
		for(int i = 0; i < N; i ++) {
			for(int j = 0; j< N; j++) {
				if(i == j) arr[i][j] = 0;
				else arr[i][j] = 10000001;    // 원소값이 최대 10만이라고 했으니
			}
		}
		
		// 2. testcase 입력 값 넣기
		for(int i = 0 ; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken());

			arr[s][e] = (arr[s][e] > v) ?      v     :      arr[s][e];
		}
		
		// 3. 플로이드 - 워셜 적용
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(arr[i][j] == 10000001) arr[i][j] = 0;
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

}
