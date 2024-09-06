package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StartTaxi {
    private static int N, M;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int[][] map;
    private static Dest[] dest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int gas = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dest = new Dest[M + 2];  // 손님 번호는 2부터 시작

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int startY = Integer.parseInt(st.nextToken()) - 1;
        int startX = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 2; i <= M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int guestY = Integer.parseInt(st.nextToken()) - 1;
            int guestX = Integer.parseInt(st.nextToken()) - 1;
            int destY = Integer.parseInt(st.nextToken()) - 1;
            int destX = Integer.parseInt(st.nextToken()) - 1;
            map[guestY][guestX] = i;
            dest[i] = new Dest(destY, destX);
        }

        if (solve(startY, startX, gas)) {
            System.out.println(gas);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean solve(int startY, int startX, int gas) {
        for (int i = 0; i < M; i++) {
            Node guest = findGuest(startY, startX, gas);
            if (guest == null || guest.gas <= 0) return false;
            map[guest.y][guest.x] = 0;

            Node destination = findDestination(guest);
            if (destination == null || destination.gas < 0) return false;

            startY = destination.y;
            startX = destination.x;
            gas = destination.gas;
        }
        return true;
    }

    private static Node findGuest(int y, int x, int gas) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(y, x, gas, 0));
        boolean[][] visited = new boolean[N][N];
        visited[y][x] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (map[node.y][node.x] > 1) {
                return new Node(node.y, node.x, node.gas, map[node.y][node.x]);
            }

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if (ny >= 0 && nx >= 0 && ny < N && nx < N && !visited[ny][nx] && map[ny][nx] != 1) {
                    visited[ny][nx] = true;
                    pq.add(new Node(ny, nx, node.gas - 1, 0));
                }
            }
        }
        return null;
    }

    private static Node findDestination(Node guest) {
        Queue<Node> q = new LinkedList<>();
        q.add(guest);
        boolean[][] visited = new boolean[N][N];
        visited[guest.y][guest.x] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.y == dest[node.guest].y && node.x == dest[node.guest].x) {
                return new Node(node.y, node.x, node.gas + (guest.gas - node.gas) * 2, 0);
            }

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if (ny >= 0 && nx >= 0 && ny < N && nx < N && !visited[ny][nx] && map[ny][nx] != 1) {
                    visited[ny][nx] = true;
                    q.add(new Node(ny, nx, node.gas - 1, node.guest));
                }
            }
        }
        return null;
    }

    private static class Node implements Comparable<Node> {
        int y, x, gas, guest;

        public Node(int y, int x, int gas, int guest) {
            this.y = y;
            this.x = x;
            this.gas = gas;
            this.guest = guest;
        }

        @Override
        public int compareTo(Node o) {
            if (this.gas != o.gas) return o.gas - this.gas;
            if (this.y != o.y) return this.y - o.y;
            return this.x - o.x;
        }
    }

    private static class Dest {
        int y, x;

        public Dest(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
