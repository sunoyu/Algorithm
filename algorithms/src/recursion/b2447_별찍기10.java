package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b2447_별찍기10 {
	static int n ;
	static char[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		
	}
	
	
	static void rec(int no) {
		if(no == 1) return;
		
		if(no==3) {
			for(int i = no-3; i < no; i+=(no/3)) {
				// 0 ~ 8까지
				
				if(lev==4)
			}
		}
	
		
		rec(no/3);
		
	}

}
