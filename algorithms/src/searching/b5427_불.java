package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class b5427_불 {
    private static int W;
    private static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            char[][] map = new char[H][W];

            Queue<Node> fireQ = new LinkedList<>();
            PriorityQueue<Node> runnerQ = new PriorityQueue<>();

            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == '@') runnerQ.add(new Node(i, j, 0));
                    else if (map[i][j] == '*') fireQ.add(new Node(i, j, 0));
                }
            }

            run();

        }




    }

    private static void run() {

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
