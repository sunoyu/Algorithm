package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 
4 2
9 8 7 1
*/
public class bNM과M6 {
	private static int N;
	private static int M;
	private static int[] arr;
	static StringBuilder sb = new StringBuilder();
	private static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		
		st= new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		back(0,0);
		System.out.println(sb);
	}
	
	static void back(int depth, int start) {
		if(depth == M) {
			for(int i : result) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i = start; i < N; i++) {
			result[depth]= arr[i];
			back(depth+1, i+1);
		}
	}

}
