package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b19236_청소년상어 {
    static int max = -1;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        Fish[][] ocean = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (int j = 0; j < 4; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                ocean[i][j] = new Fish(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())-1, false);
            }
        }

        bt(0,0,ocean, 0);
        System.out.println(max);


    }

    private static void bt(int sy, int sx, Fish[][] ocean, int sharkSize) {  // 상어 위치, 맵
        // 상어 식사
        sharkSize += ocean[sy][sx].size;
        max = max > sharkSize ? max : sharkSize;
        ocean[sy][sx] = new Fish(sharkSize, ocean[sy][sx].dir ,true);

        // 물고기 이동
        for (int n = 1; n <= 16; n++) {  //  1~16
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (i!=sy && j!=sx && ocean[i][j].size == n) {
                        int pointer = ocean[i][j].dir;
                        for (int d = 0; d < 8; d++)  {
                                int ny = i + dy[pointer];
                                int nx = j + dx[pointer];
                                if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4 || ocean[ny][nx].isSha) {
                                    pointer++;
                                    if(pointer ==8) pointer = 0;
                                } else {
                                    Fish tmp = new Fish(ocean[i][j].size, pointer, false);
                                    ocean[ny][nx] = ocean[i][j];
                                    ocean[i][j] = tmp;
                                }
                        }
                    }

                    // 상어 이동
                    ocean[sy][sx] = null;
                    for (int x = 1; x <= 3; x++) {
                        int nsy = sy + dy[ocean[sy][sx].dir] * x;
                        int nsx = sx + dx[ocean[sy][sx].dir] * x;
                        if (nsy < 0 || nsx < 0 || nsy >= 4 || nsx >= 4) {
                            continue;
                        }
                        bt(nsy, nsx, ocean, sharkSize);
                    }
                }
            }
        }


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
