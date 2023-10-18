package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기 {
	static boolean[] visited;
	static char[] arr, result;
	static int L, C;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visited = new boolean[50];
		arr = new char[C];
		result = new char[L];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		
		
		dfs(0,0);
		System.out.println(sb);
		
		
	}
	static void dfs(int idx, int lev) {
		if(lev == L) {
			int moum = 0;
			int jaum = 0;
			
			for(char i : result) {
				if(i == 'a'|| i == 'e' || i == 'i'|| i == 'o' || i == 'u') {
					moum +=1;
				}
				else jaum +=1;
			}
			
//			자음 2 모음 1개가 만족하면
			if(moum >= 1 && jaum >= 2) {
				for(char i : result) {
					sb.append(i);
				}
				sb.append("\n");
			}
			
			return;
		}
		
		for(int i = idx ; i < C; i++) {
			if(visited[arr[i]-'a']) continue;
			visited[arr[i]-'a'] = true;
			result[lev] = arr[i];
			dfs(i +1, lev +1);
			visited[arr[i]-'a'] = false;

		}
	}

}
