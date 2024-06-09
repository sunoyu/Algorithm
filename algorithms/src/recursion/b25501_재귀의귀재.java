package dataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b25501_재귀의귀재 {
	static int n, len;
	static String s;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < n; i++) {
			s = br.readLine();
			len = s.length();
			
			rec(0, len-1);
		}
		
		
		
	}
	static void rec(int lev, int end) {
		if(lev == len/2) {
			System.out.println("1 " + (lev+1));
			return;
		}
		if(s.charAt(lev) != s.charAt(end)) {
			System.out.println("0 " + (lev+1));
			return;
		}

		rec(lev+1, end-1);
	}
}

