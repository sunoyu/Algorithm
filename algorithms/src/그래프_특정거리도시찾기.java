package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 그래프_특정거리도시찾기 {
    private static int M;
    private static int N;
    private static int K;
    private static int X;
    static int[] visited;
    static ArrayList<Integer>[] A;
    static ArrayList<Integer> answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        A = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            A[start].add(end);
        }
        visited = new int[N + 1];
        for(int i = 1 ; i <= N; i ++){   // 2번쨰 이후 턴에서 시작노드를 지목하게되면 방문한것으로 판단하지 않고 continue가 안먹히니.. -1로 설정해주고 visited 0으로 세팅
            visited[i] = -1;
        }
        answer = new ArrayList<>();
        bfs(X);
        for (int i = 1; i <= N; i++) {
            if (visited[i] == K) {
                answer.add(i);
            }
        }
        if (answer.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(answer);
            for (int i : answer) System.out.println(i);
        }
    }
    private static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] ++;
        while(!q.isEmpty()) {
            int node = q.poll();
            for (int i : A[node]) {
                if (visited[i] != -1) continue;
                q.add(i);
                visited[i] = visited[node] + 1;
            }
        }
    };
}
