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
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    static int[][] map;
    static Dest[] dest;
    private static int sty, stx;
    private static int guest, gas;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        gas = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dest = new Dest[M + 2];  // 벽이 1이므로 손님넘버는 2부터 시작하자.

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());  // 시작점

        sty = Integer.parseInt(st.nextToken())-1;
        stx = Integer.parseInt(st.nextToken())-1;


        for (int i = 2; i <= M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = i;
            dest[i] = new Dest(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        }
        // 맵 그리기 완료

        // 택시 운행 시작
        int cnt = 0;
//        Node start = new Node(sty, stx, gas, 0);   // 5,4

        // 1. 승객을 태우러 간다. -> 태웠으면 탑승위치, 가스 저장 후 true 반환, 끝까지 갔는데도 못태웠으면 false
        // 2. 승객을 태운 택시가 목적지를 향해간다. -> 도착하면? 승객을 태우고 목적지까지 가면서 사용한 연료량의 2배 충전,


        while (true) {
//            boolean isTake = (findGuest(start.y, start.x, start.gas));
            boolean isTake = (findGuest(sty, stx, gas));
            int gas1 = gas;  // 손님을 태울 때 까지 연료를 쓰고 남은 양.
            if (isTake) {
                boolean isArrive = findDest(new Node(sty, stx, gas, guest));   // 손님을 도착지까지 데려다주고 남은 연료량이 gas에 .
                if (isArrive) {
                    gas += (gas1 - gas) * 2;
                    cnt++;
                    if (cnt == M) {
                        System.out.println(gas);
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


    private static boolean findGuest(int y, int x, int restGas) {    // boolean이 나으려나? 그렇겠다.
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(y, x, restGas, 0));
        boolean[][] visited = new boolean[N][N];
        visited[y][x] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || map[ny][nx] == 1 || node.gas - 1 == 0) {
                    continue;
                }
                visited[ny][nx] = true;

                if (map[ny][nx] > 1) {   // 다음칸에 손님이 있는경우
                    sty = ny;
                    stx = nx;
                    gas = node.gas - 1;
                    guest = map[ny][nx];
                    map[ny][nx] = 0; // 빈공간으로 만들어주고 (태워주고)
                    return true;
//                    return new Node(ny, nx, node.gas - 1, map[ny][nx]);
                } else pq.add(new Node(ny, nx, node.gas - 1, 0));
            }
        }
        return false;
    }

    private static boolean findDest(Node fg) {
        Queue<Node> q = new LinkedList<>();
        q.add(fg);

        boolean[][] visited = new boolean[N][N];
        visited[fg.y][fg.x] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx] || map[ny][nx] == 1 || node.gas - 1 == -1) {   // 다음칸에 도달했을때 연료가 0이 남았어도 2배로 충전이 되기때문에 '-1일 경우 실패인 것으로 세팅'
                    continue;
                }
                visited[ny][nx] = true;

                if (ny == dest[guest].y && nx == dest[guest].x) {
                    sty= ny;
                    stx = nx;
                    gas = node.gas-1;    // 가스 수식 넣기     =>  손님태우고 목적지까지 가는동안 사용한 가스 *2 더해주기
                    guest = 0;
                    return true;
                } else q.add(new Node(ny, nx, node.gas - 1, node.guest));
            }
        }
        return false;
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
