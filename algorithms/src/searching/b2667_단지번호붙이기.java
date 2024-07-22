package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b2667_단지번호붙이기 {
    private static int N;
    private static int[][] map;
    private static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }

        int grpCount = 0;
        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    A.add(bfs(q));
                    grpCount ++;
                }
            }
        }

        System.out.println(grpCount);
        Collections.sort(A);
        for (int i : A) {
            System.out.println(i);
        }
    }

    private static int bfs(Queue<int[]> q) {
        int count = 1;
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || map[ny][nx] == 0) {
                    continue;
                }
                visited[ny][nx] = true;
                count++;
                q.add(new int[]{ny, nx});
            }
        }
        return count;
    }
}