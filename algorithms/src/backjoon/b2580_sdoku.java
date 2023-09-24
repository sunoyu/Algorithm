package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2580_sdoku {
	static int[][] arr = new int[9][9];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		
		for(int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		sdoku(0,0);
		System.out.println(sb);
	}
	
	static void sdoku(int row, int col) {
		// 조건 만들기 -> 조건의 경우가 3가지나 되니 함수로 파는게 좋을듯함.
		
		// 한 행 탐색이 끝나면 다음 행으로
		if(col == 9) {
			sdoku(row + 1 , 0);
			return;
		}
		
		if(row == 9) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sb.append(arr[i][j]).append(" ");
				}
				sb.append("\n");
			}
			return;
		}
		
		if(arr[row][col] == 0) {
			for(int i = 1; i <= 9; i++) {
				if(possibiity(row, col, i)) {
					arr[row][col] = i;
					continue;
				}
			}
		}
		sdoku(row, col+1);

	}
	
	static boolean possibiity(int r, int c, int num) {
		
		// 3*3 박스 시작점
		int kR = (r/3) * 3;
		int kC = (c/3) * 3;

		
		// 같은 행에 있는지
		for(int i = 0; i < 9; i++) {
			if(arr[r][i] == num) {
				return false;
			}
		}
		// 같은 열에 있는지 체크
		for(int i = 0; i < 9; i++) {
			if(arr[i][c] == num) {
				return false;
			}
		}
		
		for(int i = kR; i < kR+3; i++) {
			for(int j = kC; j < kC+3; j++) {
				if(arr[i][j] == num) {
					return false;
				}
			}
		}
		return true;
		
	}

}
