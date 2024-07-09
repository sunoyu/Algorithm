package algorithms.src.삼성_SW_역량_테스트_기출; // GPT

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
                MAX = MAX > map[i][j] ? MAX : map[i][j];
            }
        }

        dfs(map, 0);

        System.out.println(MAX);

    }

    private static void dfs(int[][] map, int depth) {
//        move 함수를 통해 상하좌우  돌리고 dfs 재귀
        if(depth == 5) {    // 최대 5번 이동이므로
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

        boolean[][] merged = new boolean[N][N]; // 해당 구역에서 머중지를 하면 또 머지 불가
        if(dir == 0 || dir ==1) {  // 상 하 인 경우
            for (int x = 0; x < N; x++) {
                int start = dir == 0 ? 0 : N - 1;   // 상이나 하는 어차피 x를 기준으로 위에서 부터인지 아래서 부터인지만 바뀌믜로..
                int end = dir == 0 ? N : -1;      // 아래 For문 컨디션이 != 이므로
                int direction = dir == 0 ? 1 : -1;    // 보드에서 위로 하나씩 올라가면서 밑으로-1 내려 줄 것

                for (int y = start; y != end ; y+=direction) {
                    if(newMap[y][x] == 0) continue;
                    int py = y;

                    while (true) {   // 쭉쭊 가야하므로 포인터가 1개 더 있어야한다.
                        int ny = py + dy[dir];
                        if(ny < 0 || ny >= N) break;

                        if(newMap[py][x] == newMap[ny][x] && !merged[ny][x]) {
                            newMap[ny][x] *= 2;
                            newMap[py][x] = 0;
                            merged[ny][x] = true;
                            if(newMap[ny][x] > MAX) MAX = newMap[ny][x];
                            break;
                        } else if (newMap[ny][x] == 0) {
                            newMap[ny][x] = newMap[py][x];
                            newMap[py][x] = 0;
                            py = ny;
                        } else break;
                    }
                }
            }
        }
        else {   // 좌 우 인 경우, y는 그대로 두고 x만 무빙
            for (int y = 0; y < N; y++) {
                int start = dir == 2 ? 0 : N - 1;
                int end = dir == 2 ? N : -1;
                int direction = dir == 2 ? 1 : -1;  // 좌 인경우 맨 왼쪽부터 오른쪽으로 한칸씩 이동하면서 왼쪽으로 떙김

                for (int x = start; x != end; x += direction) {
                    int px = x;
                    if(newMap[y][x] == 0) continue;

                    while (true) {
                        int nx = px + dx[dir];
                        if(nx < 0 || nx >= N) break;
                        if (newMap[y][px] == newMap[y][nx] && ! merged[y][nx]) {
                            newMap[y][px] = 0;
                            newMap[y][nx] *= 2;
                            merged[y][nx] = true;
                            if(newMap[y][nx] > MAX) MAX = newMap[y][nx];
                            break;
                        } else if (newMap[y][nx] == 0) {
                            newMap[y][nx] = newMap[y][px];
                            newMap[y][px] = 0;
                            px = nx;
                        } else break;
                    }
                }
            }
        }
        return newMap;
    }
}
