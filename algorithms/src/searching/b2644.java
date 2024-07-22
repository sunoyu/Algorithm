package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b2644 {
    static int s, e, dist;
    static boolean isGoal = false;
    static ArrayList<Integer>[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int all = Integer.parseInt(br.readLine());
        parent = new ArrayList[all+1];
        for (int i = 1; i <= all; i++) {
            parent[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            parent[p].add(c);
            parent[c].add(p);
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[all + 1];
        q.add(new int[]{s,0});   // 시작점, 촌수

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            for (int i : parent[arr[0]]) {
                if (i == e) {
                    System.out.println(arr[1] + 1);
                    return;
                }
                if(visited[i]) continue;
                visited[i] = true;
                q.add(new int[]{i, arr[1] + 1});
            }
        }
        System.out.println(-1);


    }

}
