package searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b2023_신기한소수 {

	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 1자리가 소수인 숫자들만 DFS돌리기
		dfs(2,1);
		dfs(3,1);
		dfs(5,1);
		dfs(7,1);
		
	}
	static void dfs(int num, int jarisu) {
		if(jarisu == N) {
			// 중간에 소수인지 조건은 생각해보기. *** 안해도 된다.
			System.out.println(num);
		}
		for(int i = 1; i <=9; i++) {
			if(isPrime(num * 10 + i)) {
				dfs(num * 10 + i, jarisu +1);
			}
		}
	}
	
	static boolean isPrime(int num) {
		for(int i = 2; i < num/2; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	

}
