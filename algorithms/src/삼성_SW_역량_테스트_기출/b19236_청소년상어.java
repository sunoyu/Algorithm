package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b19236_청소년상어 {
    static int max = -1;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static Fish[] fish = new Fish[17];
    static int[][] map;
    public static void main(String[] args) throws IOException {
        map = new int[4][4];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                fish[num] = new Fish(i,j,Integer.parseInt(st.nextToken())-1,true);
                map[i][j] = num;
            }
        }
        fish[map[0][0]].isAlive = false;
        int firstSize = map[0][0];
        int sharkDir = fish[map[0][0]].dir;
        map[0][0] = -1;
        dfs(0,0, sharkDir,firstSize);
        System.out.println(max);
    }

    private static void dfs(int sy, int sx, int sd, int size) {
        max = Math.max(max, size);
        // 맵 복사 -> dfs 를 빠져 나올 때 복구해주는 역할
        int[][] tempMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, 4);
        }
        // fish 복사 -> 상기 동일
        Fish[] tempFish = new Fish[17];
        for (int i = 1; i < 17; i++) {
            tempFish[i] = new Fish(fish[i].y, fish[i].x, fish[i].dir, fish[i].isAlive);
        }
        // 물고기 이동
        // 1~16번 물고기의 정보를 가져온 후 물고기의 방향에 다른 물고기가 있는지 확인, 있다면 크로스, 상어거나/없다면 방향전환, 방향전환 후에도 없다면 패스~
        moveFish();

        // 상어이동  최대 3번의 dfs
        for (int i = 1; i < 4; i++) {
            int nsy = sy + dy[sd] * i;
            int nsx = sx + dx[sd] * i;
            if (nsy < 0 || nsx < 0 || nsy >= 4 || nsx >= 4) break;   // 실수로 return으로 해둬서 틀렸다.
            if(map[nsy][nsx] == 0) {
                continue;
            }
            int eatenFish = map[nsy][nsx];
            int nextDir = fish[eatenFish].dir;
            fish[eatenFish].isAlive = false;
            map[sy][sx] = 0;
            map[nsy][nsx] = -1;
            dfs(nsy, nsx, nextDir,size + eatenFish);

            // DFS에서 빠져 나오고 복구
            map[sy][sx] = -1;
            map[nsy][nsx] = eatenFish;
            fish[map[nsy][nsx]].isAlive = true;
        }

        // dfs가 완전히 빠져 나갈때 map과 fish복구
        for (int i = 0; i < 4; i++) {
            System.arraycopy(tempMap[i], 0, map[i], 0, 4);
        }
        for (int i = 1; i <= 16; i++) {
            fish[i].y = tempFish[i].y;
            fish[i].x = tempFish[i].x;
            fish[i].dir = tempFish[i].dir;
            fish[i].isAlive = tempFish[i].isAlive;
        }
    }

    private static void moveFish() {
        for (int i = 1; i < 17; i++) {
            if(!fish[i].isAlive) continue;
            int y = fish[i].y;
            int x= fish[i].x;
            int dir = fish[i].dir;

            for (int n = 0; n < 8; n++) {  // 8번째는 자기 자신에게 돌아오는 것이므로 7까지 ++처리
                int d = (dir + n) % 8;
                fish[i].dir = d;
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4 || map[ny][nx] == -1) {
                    continue;
                }
                if (map[ny][nx] == 0) {  // 빈칸인 경우
                    map[ny][nx] = i;
                    map[y][x] = 0;
                    fish[i].y = ny;
                    fish[i].x = nx;
                    break;
                } else{
                // 맞바꿈
                    int tmp = map[ny][nx];
                    map[ny][nx] = map[y][x];
                    map[y][x] = tmp;

                    fish[tmp].y = y;
                    fish[tmp].x = x;
                    fish[i].y = ny;
                    fish[i].x = nx;
                    break;
                }
            }
        }
    }


    private static class Fish {
        int y;
        int x;
        int dir;
        boolean isAlive;

        public Fish(int y, int x, int dir, boolean isAlive) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }
}
