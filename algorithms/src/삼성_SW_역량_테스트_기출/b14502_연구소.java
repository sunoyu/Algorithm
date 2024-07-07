package algorithms.src.삼성_SW_역량_테스트_기출;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b14502_연구소 {

    private static class Virus {
        int y;
        int x;

        public Virus(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int N;
    private static int M;
    static int MAX = Integer.MIN_VALUE;
    private static int[][] map, copyMap;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {

        init();

        makeWall();

        System.out.println(MAX);

    }

    private static void makeWall() {
        copyMap = new int[N][M];
        dfs(0);
    }

    private static void dfs(int depth) {
        // 벽을 3개 만들면 BFS로 점령 시작!
        if (depth == 3) {

            // 2차원 배열을 복사하는 방법
            for(int i = 0; i <N; i++) {
                copyMap[i] = map[i].clone();  // 한행씩 카피를 해야한다. 깊은 복사
            }
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1; // 벽으로
                    dfs(depth + 1); // depth == 3 -> bfs gogo
                    map[i][j] = 0; // 다시 벽없애기
                }
            }
        }

    }

    private static void bfs() {
        Queue<Virus> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 2)
                    q.add(new Virus(i, j));
            }
        }

        while (!q.isEmpty()) {
            Virus now = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M || copyMap[ny][nx] != 0)
                    continue;
                copyMap[ny][nx] = 2;
                q.add(new Virus(ny, nx));
            }
        }

        checkMax();
    }

    private static void checkMax() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0)
                    cnt++;
            }
        }

        MAX = MAX > cnt ? MAX : cnt;
    }

    private static void init() throws IOException {

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
    }

}

