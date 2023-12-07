import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1717
public class 유니온파인드1_집합의표현 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int how = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(how == 0) { // 합집합
				union(a,b);
			}else {			// 동일한 집합의 원소인지 체크하기
				if(checkSame(a, b)) {
					System.out.println("YES");
				}else 	System.out.println("NO");
				
			}
			
		}
		
	}
	private static boolean checkSame(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return true;
		else       return false;
		
	}
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) parent[b] = a;
		
	}
	private static int find(int i) {
		if(i == parent[i]) {
			return i;
		}else {
			return parent[i] = find(parent[i]);    // 재귀 함수 형태로 찾아서 부모루트를 찾는다. 최초 find를 한 행렬원소에 찾은 부모값을 넣어줌.
		}
	}

}
