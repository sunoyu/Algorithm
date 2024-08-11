package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b16234_인구이동 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        boolean[][] v;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            v = new boolean[N][N];
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (v[i][j]) continue;
                    v[i][j] = true;
                    int groupSum = map[i][j];
                    int cnt = 1;

                    // BFS
                    ArrayList<Node> a = new ArrayList<>();
                    Queue<Node> q = new LinkedList<>();
                    q.add(new Node(i, j));
                    a.add(new Node(i, j));
                    while (!q.isEmpty()) {
                        Node node = q.poll();
                        for (int d = 0; d < 4; d++) {
                            int ny = node.y + dy[d];
                            int nx = node.x + dx[d];
                            if (ny < 0 || nx < 0 || ny >= N || nx >= N || v[ny][nx]) continue;
                            int cha = Math.abs(map[node.y][node.x] - map[ny][nx]);
                            if (cha >= L && cha <= R) {
                                v[ny][nx] = true;
                                q.add(new Node(ny, nx));
                                a.add(new Node(ny, nx));
                                groupSum += map[ny][nx];
                                cnt++;
                                flag = true;
                            }
                        }
                    }

                    // arrayList 탐색
                    int newPop = groupSum / cnt;
                    for (Node n : a) {
                        map[n.y][n.x] = newPop;
                    }
                    cnt = 0;
                    groupSum = 0;
                }
            }
            if(flag) day++;
            else break;
        }

        System.out.println(day);
    }


    private static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
