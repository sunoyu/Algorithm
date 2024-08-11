package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상어테스트 {
    static int max = 0;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Fish[][] ocean = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int size = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                ocean[i][j] = new Fish(size, dir, false);
            }
        }

        // 초기 상어의 위치는 (0, 0)으로 가정하고 시작합니다.
        Fish[][] initialOcean = copyOcean(ocean);
        int initialSharkSize = initialOcean[0][0].size;
        int initialSharkDir = initialOcean[0][0].dir;
        initialOcean[0][0] = new Fish(initialSharkSize, initialSharkDir, true);
        backtrack(0, 0, initialOcean, initialSharkSize);
        System.out.println(max);
    }

    private static Fish[][] copyOcean(Fish[][] ocean) {
        Fish[][] copy = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (ocean[i][j] != null) {
                    copy[i][j] = new Fish(ocean[i][j].size, ocean[i][j].dir, ocean[i][j].isSha);
                }
            }
        }
        return copy;
    }

    private static void backtrack(int sy, int sx, Fish[][] originOcean, int sharkSize) {
        Fish[][] ocean = copyOcean(originOcean); // 원본 ocean을 복사
        int sharkDir = ocean[sy][sx].dir;
        sharkSize += ocean[sy][sx].size;
        max = Math.max(max, sharkSize); // 최대 크기 갱신
        ocean[sy][sx] = new Fish(sharkSize, sharkDir, true); // 상어의 상태 업데이트

        // 물고기 이동
        for (int n = 1; n <= 16; n++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (ocean[i][j] != null && ocean[i][j].size == n) {
                        Fish fish = ocean[i][j];
                        for (int d = 0; d < 8; d++) {
                            int dir = (fish.dir + d) % 8;
                            int ny = i + dy[dir];
                            int nx = j + dx[dir];
                            if (ny >= 0 && nx >= 0 && ny < 4 && nx < 4 && (ocean[ny][nx] == null || !ocean[ny][nx].isSha)) {
                                Fish tmp = ocean[ny][nx];
                                ocean[ny][nx] = new Fish(fish.size, dir, false);
                                ocean[i][j] = tmp;
                                break;
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
            Fish[][] nextOcean = copyOcean(ocean);
            nextOcean[sy][sx] = null; // 상어의 현재 위치를 null로 설정
            backtrack(nsy, nsx, nextOcean, sharkSize);
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
