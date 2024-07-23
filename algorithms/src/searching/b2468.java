package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2468 {
    static int N;
    private static int[][] map;
    private static boolean[][] visited;
    private static int highest;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        highest = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                highest = Math.max(map[i][j], highest);
            }
        }

        int MAX = Integer.MIN_VALUE;
        for (int h = 0; h < highest; h++) {
            cnt = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= h) visited[i][j] = true;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(new int[]{i, j});
                    }
                }
            }
            MAX = Math.max(MAX, cnt);
        }
        System.out.println(MAX);
    }

    private static void bfs(int[] node) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{node[0], node[1]});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) {   // 조건 처리는 순서대로 됨. visited가 먼저 작성되면 OutOfBound

                    continue;
                }
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
            }
        }
        cnt ++;

        return;
    }
}