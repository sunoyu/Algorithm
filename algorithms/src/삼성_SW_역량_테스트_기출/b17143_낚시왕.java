package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class b17143_낚시왕 {
    static int N, M, S, sum;
    static Shark[][] map;
    static Queue<Shark> q;
    static Dir[] d = {new Dir(-1, 0), new Dir(1, 0), new Dir(0, 1), new Dir(0, -1)}; // up down right left
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());  // 상어수

        map = new Shark[N][M];
//        q = new LinkedList<>();

        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int pace = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken())-1;
            int size = Integer.parseInt(st.nextToken());
            map[y][x] = new Shark(pace, dir, size);
//            q.add(new Shark(pace, dir, size));
        }

        for (int i = 0; i < M; i++) {
            take(i);
            map = moveShark();   // map 을 재세팅
        }
        System.out.println(sum);
    }

    private static Shark[][] moveShark() {
        Shark[][] tmp = new Shark[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != null) {
                    int ny = i;
                    int nx = j;
                    for (int t = 0; t < map[i][j].pac; t++) {
                        Dir dir = d[map[i][j].d];
                        ny+= dir.dy;
                        nx+= dir.dx;
                        if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                            if(map[i][j].d == 0) map[i][j].d = 1;
                            else if(map[i][j].d == 1) map[i][j].d = 0;
                            else if(map[i][j].d == 2) map[i][j].d = 3;
                            else if(map[i][j].d == 3) map[i][j].d = 2;
                            ny += d[map[i][j].d].dy * 2;
                            nx += d[map[i][j].d].dx * 2;
                        }
                    }
                    if (tmp[ny][nx] != null) {     // 이미 상어가 있는 상태라면 크기 비교 후 서열정리
                        if(tmp[ny][nx].sz < map[i][j].sz) tmp[ny][nx] = map[i][j];
                    }
                    else
//                    tmp[ny][nx] = new Shark(map[i][j].pac, map[i][j].d, map[i][j].sz);
                    tmp[ny][nx] = map[i][j];
                }
            }
        }
        return tmp;
    }

    private static void take(int pos) {
        for(int i= 0 ; i < N; i ++){
            if (map[i][pos] != null) {
                sum += map[i][pos].sz;
                map[i][pos] = null;
                
                return;
            }
        }
    }

    private static class Shark {
//        int y;
//        int x;
        int pac;
        int d;
        int sz;
        public Shark( int pac, int d, int sz) {
//            this.y = y;
//            this.x = x;
            this.pac = pac;
            this.d = d;
            this.sz = sz;
        }
    }

    private static class Dir {
        int dy;
        int dx;

        public Dir(int dy, int dx) {
            this.dy = dy;
            this.dx = dx;
        }
    }
}
