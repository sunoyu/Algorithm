package algorithms.src.삼성_SW_역량_테스트_기출;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class b19238_스타트택시 {
    private static int N, M;
    private static int[] dy = {-1, 1, 0, 0,};
    private static int[] dx = {0, 0, -1, 1};

    static int[][] map;
    static Dest[] dest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int gas = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dest = new Dest[M + 2];  // 벽이 1이므로 손님넘버는 2부터 시작하자.

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int sty = Integer.parseInt(st.nextToken());
        int stx = Integer.parseInt(st.nextToken());


        for (int i = 2; i <= M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = i;
            dest[i] = new Dest(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int cnt = 0;
        Node start = new Node(sty, stx, gas, 0);

        while (true) {
            Node fg = findGuest(start.y, start.x, start.gas);
            if (fg != null) {
                Node fd = findDest(fg);
                if (fd != null) {
                    cnt++;
                    start = fd;
                    if (cnt == M) {
                        System.out.println(fd.gas);
                        return;
                    }
                } else {
                    System.out.println(-1);
                    return;
                }
            } else {
                System.out.println(-1);
                return;
            }
        }

    }


    private static Node findDest(Node fg) {
        Queue<Node> q = new LinkedList<>();
        q.add(fg);

        boolean[][] visited = new boolean[N][N];

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || map[ny][nx] == 1) {
                    continue;
                }
                visited[ny][nx] = true;

                if (ny == dest[node.guest].y && nx == dest[node.guest].x) {
                    return new Node(ny, nx, (node.gas - 1) * 2, 0);
                } else q.add(new Node(ny, nx, node.gas - 1, node.guest));
            }
        }
        return null;
    }

    private static Node findGuest(int y, int x, int gas) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(y, x, gas, 0));
        boolean[][] visited = new boolean[N][N];

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || map[ny][nx] == 1) {
                    continue;
                }
                visited[ny][nx] = true;

                if (map[ny][nx] > 1) {   // 벽이 아닌경우
                    return new Node(ny, nx, node.gas - 1, map[ny][nx]);
                } else pq.add(new Node(ny, nx, node.gas - 1, 0));
            }
        }
        return null;
    }




    private static class Node implements Comparable<Node> {
        int y;
        int x;
        int gas;
        int guest;

        public Node(int y, int x, int gas, int guest) {
            this.y = y;
            this.x = x;
            this.gas = gas;
            this.guest = guest;
        }

        @Override
        public int compareTo(Node o) {
            if (o.gas != this.gas) {
                return o.gas - this.gas;
            } else if (o.y != this.y) {
                return this.y - o.y;
            } else return this.x - o.x;
        }
    }

    private static class Dest {
        int y ;
        int x;

        public Dest(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
