package algorithms.src.searching;
/*
접근법
1. 불의 경로를 먼저 BFS로 돌린다
2. 사람의 경로를 BFS로 돌리면서 불map보다 작은경우만 ++
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b4179_불 {
    private static int N, sty, stx;
    static int fireY = -1;
    static int fireX = -1;
    private static int M;
    static char[][] map;
    static int[][] dist1, dist2;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        dist1 = new int[N][M];
        dist2 = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist1[i][j] = -1;
                dist2[i][j] = Integer.MAX_VALUE;
            }
        }


        int min = Integer.MAX_VALUE;
        visited = new boolean[N][M];
        Queue<Node> q = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();


        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (str.charAt(j) == 'J') {
                    q.add(new Node(i, j, 0));

                    dist1[i][j] = 0;
                } else if (str.charAt(j) == 'F') {
                    fireY = i;
                    fireX = j;
                    q2.add(new Node(fireY, fireX, 0));
                    dist2[i][j] = 0;
                }
            }
        }

        while (!q2.isEmpty()) {
            Node fire = q2.poll();
            for (int i = 0; i < 4; i++) {
                int ny = fire.y + dy[i];
                int nx = fire.x + dx[i];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] == '#' || dist2[ny][nx] != Integer.MAX_VALUE) continue;
                dist2[ny][nx] = fire.feet +1;
                q2.add(new Node(ny, nx, fire.feet + 1));
            }
        }

        while (!q.isEmpty()) {
            Node me = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = me.y + dy[i];
                int nx = me.x + dx[i];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    min = min < me.feet+1 ? min : me.feet+1;
                    continue;
                }

                if(map[ny][nx] == '#' || dist1[ny][nx] != -1) continue;
                if(dist2[ny][nx] > me.feet+1) {
                    dist1[ny][nx] = me.feet + 1;
                    q.add(new Node(ny, nx, me.feet + 1));
                }
            }
        }

        if(min == Integer.MAX_VALUE)     System.out.println("IMPOSSIBLE");
        else System.out.println(min);
    }


    private static class Node {
        int y;
        int x;
        int feet;
        Node(int y, int x,int feet) {
            this.y = y;
            this. x = x;
            this.feet = feet;
        }
    }
}
