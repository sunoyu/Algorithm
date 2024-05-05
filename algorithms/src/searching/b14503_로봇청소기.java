package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b14503_로봇청소기 {
    private static int N;
    static int cnt;
    private static int M;
    static int[][] map;
    static int[] dy = {-1,0,1,0}; // 북 동 남 서
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());  // 방향

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cnt = 1;
        dfs(y, x, d);
        System.out.println(cnt);
    }

    private static void dfs(int y, int x, int d) {
        map[y][x] = 2;
        for (int i = 0; i < 4; i++) {
            d-=1;
            if(d == -1) d = 3;
            int ny = y + dy[d];
            int nx = x + dx[d];
            if(ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] == 1) continue;
            if (map[ny][nx] == 0) {
                cnt ++;
                dfs(ny, nx, d);
                return;     //일반적인 dfs는 가다가 길이 막히면 다시 되돌아와서 해당 위치부터 계산하지만, 이 문제는 후진할 때만 이전 길을 되돌가 가며 확인할 수 있으므로 return을 해서 다시 되돌아 와도 더 이상 움직이면 안된다.
            }
        }

        // 뒤로 간다. 뒤가 벽이면 끝낸다.
        int reverse = (d+2) % 4;
        int by = y + dy[reverse];
        int bx = x + dx[reverse];
        if(by >= 0 && by < N && bx >= 0 && bx < M && map[by][bx] != 1) {
            dfs(by, bx, d);
        }
    }

}
