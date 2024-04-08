package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 그래프_효율적인해킹 {

    static int N, M;
    static int[] count;
    static ArrayList<Integer>[] A;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            A[from].add(to);
        }

        count = new int[N + 1];

        for(int i = 1; i < N; i++){
            visited = new boolean[N + 1];
            bfs(i);
        }
        int max = -1;
        for(int i : count) {
            max = i > max ? i : max;
        }
        for (int i = 1; i < N + 1; i++) {
            if (count[i] == max) System.out.print(i + " ");
        }
    }

    private static void bfs(int x) {
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        while(!q.isEmpty()){
            int node = q.poll();
            for(int i : A[node]) {
                if(visited[i]) continue;
                visited[i] = true;
                count[i] ++;
                q.add(i);
            }
        }
    }
}
