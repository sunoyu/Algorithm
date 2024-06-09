package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b15683_감시 {
    static int N, M, zero, cnt;
    static int MAX = Integer.MIN_VALUE;   // 0의 갯수에서 최대값을 뺴서 사각지대 최소값을 구할 것임
    static int[][] map;
    static int[] per;   // 순열을 담는 배열
    static int[] dy = {-1,0,1,0};   // 상 우 하 좌   90도 회전하는 형태로 구현
    static int[] dx = {0,1,0,-1};


    static ArrayList<CCTV> cctvArrayList = new ArrayList<>();

    private static boolean[][] visited;

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
                if(map[i][j] == 0) zero ++;
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvArrayList.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        per = new int[cctvArrayList.size()];
        permutation(0, cctvArrayList.size());

        // 0카운트
        int ans = zero - MAX;

        System.out.println(ans);

    }

    private static void permutation(int depth, int size) {
        if(depth == size) {
            cnt = 0;
            visited = new boolean[N][M];
            for(int i = 0 ; i < size; i++){
                cctvDir(cctvArrayList.get(i), per[i]);
            }
            MAX = MAX > cnt ? MAX : cnt;
            return;
        }

        for (int i = 0; i < 4; i++) {
            per[depth] = i;
            permutation(depth+1, size);
        }
    }

    static void cctvDir(CCTV cctv, int dir) {
        int cctvNum = cctv.num;

        switch (cctvNum) {
            case 1:
                if(dir ==  0) watch(cctv, 0);
                else if(dir ==  1) watch(cctv, 1);
                else if(dir ==  2) watch(cctv, 2);
                else if(dir ==  3) watch(cctv, 3);
                break;
            case 2:
                if(dir == 0 || dir == 2) {   // 좌 우 4가지 방향으로 맞추기 위해 0, 2로 설정해준다.
                    watch(cctv, 0);
                    watch(cctv, 2);
                }
                else if(dir == 1 || dir == 3) {
                    watch(cctv, 1);
                    watch(cctv, 3);
                }
                break;
            case 3:
                if(dir==0) {
                    watch(cctv, 0);
                    watch(cctv, 1);
                }
                else if(dir==1) {
                    watch(cctv, 1);
                    watch(cctv, 2);
                }
                else if(dir==2) {
                    watch(cctv, 2);
                    watch(cctv, 3);
                }
                else if(dir==3) {
                    watch(cctv, 0);
                    watch(cctv, 3);
                }
                break;
            case 4:
                if(dir==0) {
                    watch(cctv, 0);
                    watch(cctv, 1);
                    watch(cctv, 3);
                }
                else if(dir==1) {
                    watch(cctv, 0);
                    watch(cctv, 1);
                    watch(cctv, 2);
                }
                else if(dir==2) {
                    watch(cctv, 1);
                    watch(cctv, 2);
                    watch(cctv, 3);
                }
                else if(dir==3) {
                    watch(cctv, 0);
                    watch(cctv, 2);
                    watch(cctv, 3);
                }
                break;
            case 5:
                watch(cctv,0);
                watch(cctv,1);
                watch(cctv,2);
                watch(cctv,3);
        }
    }

    // BFS
    private static void watch(CCTV cctv, int d) {
        Queue<CCTV> q = new LinkedList<>();
        q.add(cctv);
        while (!q.isEmpty()) {
            CCTV now = q.poll();

            int ny = now.y + dy[d];
            int nx = now.x + dx[d];

            if(ny <0 || nx <0 || ny >=N || nx >= M || map[ny][nx] == 6) continue;

            if(map[ny][nx] == 0 && !visited[ny][nx]) cnt++;
            visited[ny][nx] = true;
            q.add(new CCTV(ny, nx, cctv.num));
        }

    }

    private static class CCTV {
        int y;
        int x;
        int num;

        public CCTV(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }
}