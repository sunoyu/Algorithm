package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b17142_연구소3 {
    static int N, M, emptyCount;
    private static int[][] map;
    private static int[][] dist;
    private static ArrayList<int[]> virusPoint;
    private static boolean[] combiVisited;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int ans = Integer.MAX_VALUE;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virusPoint = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virusPoint.add(new int[]{i, j});
                else if (map[i][j] == 0) emptyCount++;
            }
        }

        if (emptyCount == 0) {
            System.out.println(0);
            return;
        }

        combiVisited = new boolean[virusPoint.size()];
        // 조합 만들고 bfs 돌리기
        combination(new int[M], 0, 0);

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    // 조합을 생성하는 DFS + 백트래킹 함수
    private static void combination(int[] combi, int start, int depth) {
        if (depth == M) {
            dist = new int[N][N];
            visited = new boolean[N][N];
            Queue<int[]> q = new LinkedList<>();  // BFS 큐 초기화

            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            for (int i : combi) {
                int[] point = virusPoint.get(i);
                q.add(point);
                dist[point[0]][point[1]] = 0;
                visited[point[0]][point[1]] = true;
            }
            bfs(q);
            return;
        }
        for (int i = start; i < virusPoint.size(); i++) {
            if (combiVisited[i]) continue;
            combiVisited[i] = true;
            combi[depth] = i;
            combination(combi, i + 1, depth + 1);
            combiVisited[i] = false;
        }
    }

    // BFS를 이용한 바이러스 확산 및 최소 시간 계산 함수
    private static void bfs(Queue<int[]> q) {
        int count = 0;
        int max = 0;  // 초기값을 0으로 설정

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int y = now[0];
            int x = now[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || map[ny][nx] == 1 || visited[ny][nx]) {
                    continue;
                }

                visited[ny][nx] = true;
                dist[ny][nx] = dist[y][x] + 1;

                if (map[ny][nx] == 0) {
                    count++;
                    max = Math.max(max, dist[ny][nx]);
                }
                q.add(new int[]{ny, nx});
            }
        }

        if (emptyCount == count) {
            ans = Math.min(ans, max);
        }
    }
}
