package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b9663_NQueen {
	static int N ,cnt;
	static int[] pos;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pos = new int[N];
		dfs(0);
		System.out.println(cnt);
		
	}
	
	static void dfs(int depth) {
		if(depth == N) {
			cnt ++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			
			pos[depth] = i;
			
			// 어떤 조건이 필요. 1. 현재 퀸과 비교해서 대각선에 존재하는지, 2. 같은 행에 존재하는지
			// 퀸을 놓을 수 있으면 재귀 호출
			if(isPossible(depth)) {
				dfs(depth +1);
			}
			
		}
	}
	static boolean isPossible(int depth) {
		// 1. 대각선상에 존재하는지
		for(int i = 0; i < depth; i++) {
			if(Math.abs(pos[depth]-pos[i]) == Math.abs(depth-i)) {
				return false;
			}
			//  2. 같은 행인지?
			else if(pos[depth] == pos[i]) {
				return false;
			}
		}
		return true;
	}

}
