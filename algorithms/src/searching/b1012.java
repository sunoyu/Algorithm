package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b1012 {

    private static int T;
    private static int[] dy = { -1, 1, 0, 0 };
    private static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            boolean[][] visited = new boolean[N][M];
            int[][] map = new int[N][M];

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
            }

            int cnt = 0 ;
            Queue<int[]> q = new LinkedList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (map[r][c] == 1 && !visited[r][c]) {
                        visited[r][c] = true;
                        q.add(new int[] { r, c });

                        while (!q.isEmpty()) {
                            int[] now = q.poll();
                            for (int d = 0; d < 4; d++) {
                                int ny = now[0] + dy[d];
                                int nx = now[1] + dx[d];

                                if(ny <0 ||nx <0 || ny >=N || nx >= M || visited[ny][nx] || map[ny][nx] == 0) continue;
                                visited[ny][nx] = true;
                                q.add(new int[] {ny,nx});
                            }
                        }
                        cnt++;

                    }
                }
            }
            System.out.println(cnt);
        }

    }

}
