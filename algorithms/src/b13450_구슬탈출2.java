package algorithms.src;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b13450_구슬탈출2 {
    static int N,M;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static char[][] board;
    static boolean[][][][] visited; // 키포인트!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][N][M]; // 4차원이 아닌 2차원 2개를 동시에 체크하는 방문배열이라고 보면 된다.   R이 (1,0)이고 B가 (0,1)인 경우 -> [1][0][0][1]

        int ry = 0, rx = 0, by = 0, bx = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    ry = i;
                    rx = j;
                } else if (board[i][j] == 'B') {
                    by = i;
                    bx = j;
                }
            }
        }
        System.out.println(bfs(ry, rx, by, bx));


    }

    private static int bfs(int ry, int rx, int by, int bx) {
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(ry, rx, by, bx, 0));

        while (!q.isEmpty()) {
            Position pos = q.poll();

            if(pos.depth >= 10) return -1;   // 10초 이하를 헷갈리지 말자. 최종 리턴 할 때 depth를 +1 하므로 조건연산자를 >가 아닌 >= 인 경우로 해주어야한다.

            for (int i = 0; i < 4; i++) {
                int[] rolledR = roll(pos.ry, pos.rx, dy[i], dx[i]);
                int[] rolledB = roll(pos.by, pos.bx, dy[i], dx[i]);

                int nry = rolledR[0], nrx = rolledR[1], rDist = rolledR[2];
                int nby = rolledB[0], nbx = rolledB[1], bDist = rolledB[2];

                if(board[nby][nbx] == 'O') continue; // Blue가 구멍에 빠졌을 때
                if(board[nry][nrx] == 'O') return pos.depth +1;

                if (nry == nby && nrx == nbx) {   // 구슬 2개가 같은 위치에 존재하면?
                    // 이동 거리를 비교한다. 이동거리가 큰게 더 늦게 왔다는 말이므로 -1해줌
                    if (rDist > bDist) {
                        nry -= dy[i];
                        nrx -= dx[i];
                    } else {
                        nby -= dy[i];
                        nbx -= dx[i];
                    }
                }

                if(visited[nry][nrx][nby][nbx]) continue;
                visited[nry][nrx][nby][nbx] = true;
                q.add(new Position(nry, nrx, nby, nbx, pos.depth+1));
            }
        }


        return -1;
    }

    private static int[] roll(int y, int x, int dy, int dx) {
        int dist = 0;
        while(board[y+dy][x+dx] != '#' && board[y][x] != 'O') {    // 다음이 벽이 아니여야 하므로
            y += dy;
            x += dx;
            dist ++;
        }
        return new int[]{y, x, dist};
    }

    private static class Position {
        int ry;
        int rx;
        int by;
        int bx;
        int depth;
        public Position(int ry, int rx, int by, int bx, int depth) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.depth = depth;
        }
    }
}