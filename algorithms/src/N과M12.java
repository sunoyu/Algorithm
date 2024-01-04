import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/15666
public class N과M12 {

	private static int[] arr;
	private static int N;
	private static int M;
	private static int[] result;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[10];
		arr = new int[N];
		result = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		back(0,0);
		
	}

	private static void back(int lev, int start) {
		if(lev == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(result[i]+ " ");
			}
			System.out.println();
			return;
		}
		for(int i = start ; i < N; i++) {
			result[lev] = arr[i];
			if(visited[arr[i]]) continue;
			visited[arr[i]] = true;
			back(lev+1, i);
			visited = new boolean[10];

		}
	}

}
