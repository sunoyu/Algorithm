package algorithms.src.codeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 마법의숲탐색 {
    private static int R,C,K;
    // 요정을 기준으로 출구 위치
    static int[] exitY = {-1, 0, 1, 0};  // 북 동 남 서
    static int[] exitX = {0, -1, 0, 1};
    static boolean[][] visited;   // 요정이 맵 돌때
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R + 1][C + 1];
        visited = new boolean[R + 1][C + 1];

        for (int idx = 0; idx < K; idx++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int exit = Integer.parseInt(st.nextToken());

            Queue<Gol> q = new LinkedList<>();
            q.offer(new Gol(-1, x, exit));
            while (!q.isEmpty()) {
                // 가는 방향으로는 골렘의 길이가 +1 더 추가되야함.
                Gol now = q.poll();
                // 1. to south
                int sy = now.y + 1;
                int sx = now.x;
                int flag = 0;
                int num = 1;

                for (int i = 1; i < 4; i++) {  // r s l
                    if(sy >= R || sx <=1 || sx >= C) {
                        flag = 1;
                        break;
                    }else if (map[sy + exitY[i]][sx + exitX[i]] != 0) {  // 남쪽으로 이동한 요정을 기준으로 3방향에 골렘이 존재하면
                        flag = 1;
                        break;  // To east
                    }
                }
                if (flag == 0) {
                    q.offer(new Gol(sy, sx, now.exit));
                } else {
                    // 2. to east R
                    sy = now.y;
                    sx = now.x + 1;
                    flag = 0;
                    for(int i = 0; i < 2; i++) {  // 북 동 남
                        if(sy >= R || sx <=1 || sx >= C) {
                            flag = 1;
                            break;
                        } else if (map[sy + exitY[i]][sx + exitX[i]] != 0) {  // 오른쪽으로 이동한 요정을 기준으로 3방향에 골렘이 존재하면
                            flag = 1;
                            break;  // To east
                        }
                    }
                    if(flag == 0) {
                        q.offer(new Gol(sy, sx, (now.exit+1)%4));
                    } else {
                        // 3. to West L
                        sy = now.y;
                        sx = now.x - 1;
                        flag = 0;
                        for (int i = 0; i < 3; i++) {
                            if(sy >= R || sx <=1 || sx >= C) {
                                flag = 1;
                                break;
                            }
                            else if (map[sy + exitY[i]][sx + exitX[i]] != 0) {  // 왼쪽으로 이동한 요정을 기준으로 3방향에 골렘이 존재하면  / 그냥 북쪽도 함께
                                flag = 1;
                                break;
                            }
                        }
                        if(flag == 0) {
                            q.offer(new Gol(sy, sx, (now.exit + 5) % 4));
                        } else {
                            // 남 동 서 모두 통과를 못한 경우
                            if (now.y <= 1) {
                                // 골렘이 맵 밖에 있는 경우.
                                map = new int[R + 1][C + 1];
                                num = 1;
                            } else {
                                for (int i = 0; i < 4; i++) {
                                    int ny = now.y + exitY[i];
                                    int nx = now.x + exitX[i];
                                    if(ny < 1) continue;
                                    map[ny][nx] = num;
                                }
                                num++;
                            }
                        }

                    }


                }








            }

        }

    }

    private static class Gol {
        int y;
        int x;
        int exit;

        public Gol(int y, int x, int exit) {
            this.y = y;
            this.x = x;
            this.exit = exit;
        }
    }
}
