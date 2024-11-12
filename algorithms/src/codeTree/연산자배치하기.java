package codeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자배치하기 {
	static int N, sum;
	static boolean[] visited;
	static int[] comb; // 연산자
	static int[] num;
	
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		visited = new boolean[N-1]; // comb의 방문배열
		num = new int[N];
		comb = new int[N-1];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			int countComb = Integer.parseInt(st.nextToken()); // + - * 의 갯수
			for (int j = cnt; j < countComb + cnt; j++) {
				comb[j] = i; // 1 1 0 이라면 0 0 // 2 2 1 이라면 0 0 1 1 2
			}
			cnt +=countComb;
		}

		sum = num[0];
		bt(1); // lev, sum
		StringBuilder sb = new StringBuilder();
		sb.append(min).append(" ").append(max);
		System.out.println(sb);
	}

	private static void bt(int lev) {
		if (lev == N) {
			min = Math.min(min, sum);  
			max = Math.max(max, sum);  

			return;
		}

		for (int i = 0; i < N-1; i++) {
			if (visited[i])
				continue;
			visited[i] = true;

			switch (comb[i]) {
			case 0:
				sum += num[lev];
				break;
			case 1:
				sum -= num[lev];
				break;
			case 2:
				sum *= num[lev];
				break;
			}

			bt(lev + 1);
			visited[i] = false;
			// 원복 
			switch (comb[i]) {
			case 0:
				sum -= num[lev];
				break;
			case 1:
				sum += num[lev];
				break;
			case 2:
				sum /= num[lev];
				break;
			}

		}
	}

}
