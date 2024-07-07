package algorithms.src.삼성_SW_역량_테스트_기출;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS로 풀이한다.
// 상하좌우를 돌아가면서 Depth가 5일 때 리턴해주기.
// move함수를 실행하고 newMap을 리턴해준다.
public class b12100_2048 {
    static int N;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int MAX = Integer.MIN_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(map, 0);

    }

    private static void dfs(int[][] map, int depth) {
//        move 함수를 통해 상하좌우  돌리고 dfs 재귀
        if(depth == 5) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            int[][] newMap = move(map, i);
            dfs(newMap, depth + 1);
        }

    }

    private static int[][] move(int[][] map, int dir) {
        int[][] newMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            newMap[i] = map[i].clone();
        }

        boolean[][] merged = new boolean[N][N]; // 해당 구역에서 머지를 하면 또 머지 불가

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if(dir == 0) {

                }
            }
        }




        return newMap;
    }
}
