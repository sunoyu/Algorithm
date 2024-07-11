package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b5427_불 {
    private static int W;
    private static int H;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    private static Queue<Node> fireQ = new LinkedList<>();
    private static PriorityQueue<Node> runnerQ = new PriorityQueue<>();
    private static char[][] map;
    private static int[][] fireMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            fireQ = new LinkedList<>();
            runnerQ = new PriorityQueue<>();

            map = new char[H][W];
            fireMap = new int[H][W];
            for (int i = 0; i < H; i++) {
                Arrays.fill(fireMap[i], -1);
            }



            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == '@') runnerQ.add(new Node(i, j, 0));
                    else if (map[i][j] == '*') fireQ.add(new Node(i, j, 0));
                }
            }
            boolean[][] visited = new boolean[H][W];

            fire();

            int ans = run();
            if (ans == -1) {
                System.out.println("IMPOSSIBLE");
            } else System.out.println(ans);
        }
    }

    private static int run() {
        while (!runnerQ.isEmpty()) {
            Node node = runnerQ.poll();
            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if(ny < 0 || ny >= H || nx < 0 || nx >= W) return node.time+1;
                if(map[ny][nx] == '#') continue;
                if(fireMap[ny][nx] > node.time+1){
                    runnerQ.add(new Node(ny, nx, node.time + 1));
                }
            }
        }
        return -1;
    }

    private static void fire() {
        while (!fireQ.isEmpty()) {
            Node fire = fireQ.poll();
            fireMap[fire.y][fire.x] = fire.time;

            for (int i = 0; i < 4; i++) {
                int ny = fire.y + dy[i];
                int nx = fire.x + dx[i];
                if (ny < 0 || ny >= H || nx < 0 || nx >= W || fireMap[ny][nx] >= 0 || map[ny][nx] == '#') {
                    continue;
                }
                fireQ.add(new Node(ny, nx, fire.time + 1));
            }
        }

    }

    private static class Node implements Comparable<Node>{
        int y;
        int x;
        int time;

        public Node(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time ;
        }
    }

}
