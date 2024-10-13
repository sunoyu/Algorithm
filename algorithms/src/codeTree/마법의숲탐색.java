package algorithms.src.codeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
6 5 6
2 3
2 0
4 2
2 0
2 0
2 2
*/

public class 마법의숲탐색 {
    private static int R,C,K, num,sum;

    // 요정을 기준으로 출구 위치
    static int[] dy = {-1, 0, 1, 0};  // 북 동 남 서
    static int[] dx = {0, 1, 0, -1};
    static boolean[][] visited;   // 요정이 맵 돌때
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        initMap();
        for (int idx = 0; idx < K; idx++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int exit = Integer.parseInt(st.nextToken());

            int fy = 0;
            int fx = x;

            while (true) {
                // to South
                if (map[fy + 1][fx - 1] + map[fy + 2][fx] + map[fy + 1][fx + 1] == 0) {
                    fy += 1;
                } else if (map[fy][fx - 2] + map[fy - 1][fx- 1] + map[fy + 1][fx - 1] + map[fy + 2][fx - 1] + map[fy + 1][fx - 2] == 0) { // 서쪽(왼쪽)
                    exit = (exit + 5) % 4;
                    fx -= 1;
                } else if (map[fy][fx + 2] + map[fy - 1][fx + 1] + map[fy + 1][fx + 1] + map[fy + 2][fx + 1] + map[fy + 1][fx + 2] == 0) { // 동쪽
                    exit = (exit + 1) % 4;
                    fx += 1;
                } else {
                    if(fy < 2) {
                        initMap();
                    } else {
                        break;

                        }

                    }
                }
            map[fy][fx] = num + 1;
            for (int i = 0; i < 4; i++) {
                int ny = fy + dy[i];
                int nx = fx + dx[i];
                map[ny][nx] = num + 1;
            }
            BFS(fy, fx, exit);
            num++;

        }
        System.out.println(sum);



    }

    private static void BFS(int fy, int fx, int exit) {
        Queue<Gol> q = new LinkedList<>();
        q.offer(new Gol(fy, fx));
        int ey = fy + dy[exit];
        int ex = fx + dx[exit];
        visited = new boolean[R + 3][C + 2];
        int max = -1;
        while (!q.isEmpty()) {
            Gol cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if(visited[ny][nx] || map[ny][nx] == -1) continue;
                visited[ny][nx] = true;
                if (map[cur.y][cur.x] == map[ny][nx]) {
                    q.offer(new Gol(ny, nx));
                    max = Math.max(max, ny) -1;
                } else if(cur.y == ey && cur.x == ex) {
                    q.offer(new Gol(ny, nx));
                    max = Math.max(max, ny) -1;
                } else continue;
            }
        }
        sum += max;
    }

    private static void initMap() {
        map = new int[R + 3][C + 2];   // 요정기준 상단 -2에서 시작하므로,  + 아래와 좌우에 경계를 -1로 세팅해줄것
        for (int i = 0; i < R+3; i++) {
            map[i][0] = -1;
            map[i][C + 1] = -1;
        }
        for (int j = 0; j < C + 2; j++) {
            map[R + 2][j] = -1;
        }
    }

    private static class Gol {
        int y;
        int x;

        public Gol(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
