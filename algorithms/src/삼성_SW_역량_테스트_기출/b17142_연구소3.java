package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class b17142_연구소3 {
    static int N, M, emptyCount;
    private static int[][] map;
    private static int[][] dist;
    private static ArrayList<int[]> virusPoint;
    private static Queue<int[]> q = new LinkedList<>();
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
                if      (map[i][j] == 2) virusPoint.add(new int[]{i, j});
                else if (map[i][j] == 0) emptyCount++;
            }
        }

        if (emptyCount == 0) {
            System.out.println(0);
            return;
        }

        combiVisited = new boolean[virusPoint.size()];
        // 조합 만들고 bfs돌리기
        combination(new int[M], 0, 0);

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

    }

    private static void combination(int[] combi, int start, int depth) {  // 오름차순 정렬을 위해 파라미터에 start 추가
        if (depth == M) {
            dist = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            for(int i : combi){
                int[] point = virusPoint.get(i);
                q.add(point);
                dist[point[0]][point[1]] = 0;
                visited[point[0]][point[1]] = true;
            }
            bfs();
            return;
        }
        for (int i = start; i < virusPoint.size(); i++) {
            if(combiVisited[i]) continue;
            combiVisited[i] = true;
            combi[depth] = i;
            combination(combi,i +1, depth+1);
            combiVisited[i] = false;
        }

    }

    private static void bfs() {
        int count = 0;
        int max = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N || map[ny][nx] == 1 || dist[ny][nx] != Integer.MAX_VALUE || visited[ny][nx]) {
                    continue;
                }
                visited[ny][nx] = true;
                if(map[ny][nx]== 2) {
                    dist[ny][nx] = dist[now[0]][now[1]] +1;
                    q.add(new int[]{ny, nx});
                }
//                else if (dist[now[0]][now[1]] + 1 < dist[ny][nx]) {
                else {
                    dist[ny][nx] = dist[now[0]][now[1]] + 1;
                    count ++;
                    max = max > dist[ny][nx] ? max : dist[ny][nx];
                    q.add(new int[]{ny, nx});
                }
            }
        }
        if (emptyCount == count) {
            ans = ans < max ? ans : max;
        }

    }
}
