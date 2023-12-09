import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1043
public class 유니온파인드3_거짓말 {
	private static int N;
	private static int M;
	private static int[] parent;
	private static int knows;
	private static int[] knowP;
	private static ArrayList<Integer> partyAL[];

	public static void main(String[] args) throws IOException {
		// 1.각 파티(ArrayList)마다 union을 걸어서 대표 노드를 설정 -> parent[]
		// 2.비밀을 아는 그룹의 대표 노드 설정   -> knowsP[] 
		// 1과2의 대표노드가 같은지 체크
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// input 1
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		partyAL = new ArrayList[M];
		parent = new int[N+1];
		// input2
		st = new StringTokenizer(br.readLine());
//		if(!st.hasMoreElements()) return;
		knows = Integer.parseInt(st.nextToken());

		if(knows == 0) {
			System.out.println(M);
			return;
		}
		knowP = new int[knows];

		for(int i = 0; i < knows; i++) {
			knowP[i] = Integer.parseInt(st.nextToken());
		}
		
		// 대표노드
		for(int i = 1; i < N; i++) {
			parent[i] = i;
		}
		
		// 파티별 멤버
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int partyLen = Integer.parseInt(st.nextToken());
			partyAL[i] = new ArrayList<Integer>();
			for(int j = 0; j<partyLen; j++) {
				partyAL[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// union-find 파티 멤버의 상관관계
		for(int i = 0; i< M; i++) {
			int first = partyAL[i].get(0);
			for(int j = 1; j < partyAL[i].size(); j++) {
				union(first, partyAL[i].get(j));
			}
		}
		
		// 진실을 아는 멤버와 멤버들의 관계
		int count = 0;
		for(int i = 0; i < M; i++) {
			boolean isPossible = true;
			int cur = partyAL[i].get(0);
			for(int j = 0; j < knows; j++) {
				if(find(knowP[j]) == find(cur)){
					isPossible = false;
					break;
				}
			}
			if(isPossible) count++;
		}
		System.out.println(count);

	}
	

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a!=b) parent[b] = a;
	}

	private static int find(int a) {
		if(parent[a] == a) 	return a;
		else return parent[a] = find(parent[a]);
	}
}	
