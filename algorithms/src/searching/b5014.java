package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b5014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int f = Integer.parseInt(st.nextToken());

        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());

        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[] dy = {u, -d};

        boolean[] visited = new boolean[f + 1];


        Queue<int[]> q = new LinkedList<>();
        visited[s] = true;
        q.add(new int[]{s, 0});

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            if (arr[0] == g) {
                System.out.println(arr[1]);
                return;
            }
            int now = arr[0];
            int lev = arr[1];

            for (int i : dy) {
                int ny = now + i;
                if (ny == g) {
                    System.out.println(lev+1);
                    return;
                }
                if(ny <=0 || ny > f || visited[ny]) continue;
                visited[ny] = true;
                q.add(new int[]{ny, lev + 1});
            }
        }
        System.out.println("use the stairs");
    }
}
