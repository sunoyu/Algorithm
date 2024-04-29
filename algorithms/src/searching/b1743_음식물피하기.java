package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b1743_음식물피하기 {
    static int N,M,K;
    static int max = Integer.MIN_VALUE;
    static int cnt;
    static int[][] arr;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];


        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            arr[y][x] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if(arr[i][j] == 0) continue;
                cnt = 1;
                BFS(new Node(i,j));
                max = max > cnt ? max : cnt;
            }
        }
        System.out.println(max);


    }

    private static void BFS(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        visited[node.y][node.x] = true;
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if(ny < 1 || ny >N || nx <1 || nx >M || visited[ny][nx] || arr[ny][nx] == 0) continue;
                visited[ny][nx] = true;
                cnt ++;
                q.add(new Node(ny, nx));
            }

        }


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
