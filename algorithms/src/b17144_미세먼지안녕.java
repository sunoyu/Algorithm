package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b17144_미세먼지안녕 {
    static int R, C,T;
    static int[][] map, dusted;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    private static int[] cleaner;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        cleaner = new int[2];

        int cleanerIdx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    cleaner[cleanerIdx++] = i;    // y,0에 위치하므로
                }
            }
        }

        for (int t = 0; t < T; t++) {
            dust();
            airCleaner();
        }

        System.out.println(totalDust());
    }

    private static int totalDust() {
        int total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] > 0) total += map[i][j];
            }
        }
        return total;
    }

    private static void dust() {
        dusted = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int spreadCount = 0;
                int dustAmount = map[i][j];
                if (map[i][j] > 0) {
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        if (ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] == -1) continue;
                        dusted[ny][nx] += dustAmount / 5;
                        spreadCount ++;
                    }
                    dusted[i][j] += dustAmount - (dustAmount / 5) * spreadCount;
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] != -1) {
                    map[i][j] = dusted[i][j];
                }
            }
        }
    }

    private static void airCleaner() {
        int top = cleaner[0];
        int bottom = cleaner[1];

        // 위쪽 (반시계)
        // 1. 아래방향 (으로 옮기면 한칸이 빌테니 ) 2. 왼쪽으로 3. 위 4. 오른쪽으로 5. 4번에서 반영이 안될 공기청정기 바로 옆에 있는 녀석을 0으로 만들어준다.
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i-1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < top; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[top][i] = map[top][i-1];
        }
        map[top][1] = 0;



        // 아래쪽 (시계방향)   위,  좌, 아래, 우
        for (int i = bottom + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < C -1; i++) {
            map[R - 1][i] = map[R-1][i+1];
        }
        for (int i = R - 1; i > top; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        for (int i = C - 1; i > 0; i--) {
            map[bottom][i] = map[bottom][i-1];
        }
        map[bottom][1] = 0;

    }
}
