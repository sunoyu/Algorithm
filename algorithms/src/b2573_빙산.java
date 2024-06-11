package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2573_빙산 {

    private static int M;
    private static int N;
    private static int[][] map;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
    private static int piece;
    private static int year;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 반복문 안에서
        while (true) {
            // 1.. DFS로 빙산 갯수 체크
            piece = 0;
            boolean[][] visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && map[i][j] > 0) {
                        dfs(i, j, visited);
                        piece++;
                    }
                }
            }
            // 빙산 갯수가 2개로 되면 year을 뱉는다

            if (piece >= 2) {
                break;
            }
            // 끝까지 분리되지 않으면 year = 0
            else if (piece == 0) {
                year = 0;
                break;
            }
            // BFS 로 빙산녹이기
            bfs();
            year++;
        }

        System.out.println(year);
    }

    private static void bfs() {
        Queue<IceBerg> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    q.add(new IceBerg(i, j));
                    visited[i][j] = true;
                }
            }

        }

        while (!q.isEmpty()) {
            IceBerg now = q.poll();


            // 4방향 중 0이 몇개인지 체크
            int around = 0;
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] != 0) {
                    continue;
                }
                around++;
            }

            if (around > map[now.y][now.x])
                map[now.y][now.x] = 0;
            else
                map[now.y][now.x] -= around;
        }

    }

    private static void dfs(int y, int x, boolean[][] visited) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] <= 0)
                continue;
            dfs(ny, nx, visited);
        }

    }

    private static class IceBerg {
        int y;
        int x;

        public IceBerg(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
