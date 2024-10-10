package algorithms.src.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b1261_알고스팟 {
    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        q.offer(new Node(0, 0, 0));
        int[][] dist = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || ny >= M || nx < 0 || nx >= N) {
                    continue;
                }
                if(map[cur.y][cur.x] + cur.w < dist[ny][nx]) {
                    dist[ny][nx] = map[cur.y][cur.x] + cur.w;
                    q.offer(new Node(ny, nx, dist[ny][nx]));
                }
            }
        }
        System.out.println(dist[M - 1][N - 1]);

    }

    private static class Node implements Comparable<Node> {
        int y;
        int x;
        int w;

        public Node(int y, int x, int w) {
            this.y = y;
            this.x = x;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }
}
