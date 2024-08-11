package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b19236_청소년상어 {
    static int max = -1;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        Fish[][] ocean = new Fish[4][4];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                ocean[i][j] = new Fish(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())-1, false);
            }
        }

        bt(0,0,ocean, 0);
        System.out.println(max);


    }

    private static void bt(int sy, int sx, Fish[][] originOcean, int sharkSize) {  // 상어 위치, 맵
        // 깊은복사 필수
        Fish[][] ocean = copy(originOcean);
        // 상어 식사
        sharkSize += ocean[sy][sx].size;
        int sharkDir = ocean[sy][sx].dir;
        max = Math.max(max, sharkSize);
        ocean[sy][sx] = new Fish(sharkSize, sharkDir, true);


        // 물고기 이동
        for (int n = 1; n <= 16; n++) {  //  1~16
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (ocean[i][j]!= null && ocean[i][j].size == n) {
                        for (int d = 0; d < 8; d++)  {
                            int dir = (ocean[i][j].dir + d) %8;
                            int ny = i + dy[dir];
                            int nx = j + dx[dir];
                            if (ny >= 0 && nx >= 0 && ny <4 && nx < 4 &&(ocean[ny][nx] == null || !ocean[ny][nx].isSha)) {
                                Fish tmp = ocean[ny][nx];
                                ocean[ny][nx] = new Fish(ocean[i][j].size, dir, false);
                                ocean[i][j] = tmp;
                                break;   // 방향을 찾았으므로
                            }
                        }
                    }
                }
            }
        }
        // 상어 이동
        for (int x = 1; x <= 3; x++) {
            int nsy = sy + dy[sharkDir] * x;
            int nsx = sx + dx[sharkDir] * x;
            if (nsy < 0 || nsx < 0 || nsy >= 4 || nsx >= 4 || ocean[nsy][nsx] == null) {
                continue;
            }
            Fish[][] nextOcean = copy(ocean);
            nextOcean[sy][sx] = null;
            bt(nsy, nsx, nextOcean, sharkSize);
        }
    }

    private static Fish[][] copy(Fish[][] ocean) {
        Fish[][] copy = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (ocean[i][j] != null) {
                    copy[i][j] = new Fish(ocean[i][j].size,ocean[i][j].dir, ocean[i][j].isSha);
                }

            }
        }
        return copy;

    }

    private static class Fish {
        int size;
        int dir;
        boolean isSha;

        public Fish(int size, int dir, boolean isSha) {
            this.size = size;
            this.dir = dir;
            this.isSha = isSha;
        }
    }
}
