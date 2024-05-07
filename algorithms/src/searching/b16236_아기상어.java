package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


// 중요
// 아기상어가 물고기를 먹으면 Q를 초기화, visited도 초기화
public class b16236_아기상어 {
    static int N;
    static Edge edge;
    static int[][] map;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static boolean[][] visited;
    static int size, eat, y, x;
    static int minDay = 0;
//    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==9) {
                    edge = new Edge(i, j, 0);
                    map[i][j] = 0;
                };
            }
        }



        BFS();
        System.out.println(minDay);


    }

    private static void BFS() {
        size = 2;
        eat = 0;

        while (true) {
            boolean isEat = false;
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(edge.y, edge.x, 0));
            visited = new boolean[N][N];
            visited[edge.y][edge.x] = true;
            while (!pq.isEmpty()) {
                edge = pq.poll();

                if(map[edge.y][edge.x] < size && map[edge.y][edge.x] !=0) {
                    map[edge.y][edge.x] = 0;
                    eat ++;
                    minDay += edge.day;
                    isEat = true;
                    break;
                }


                for (int i = 0; i < 4; i++) {
                    int ny = edge.y + dy[i];
                    int nx = edge.x + dx[i];

                    if (ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] || map[ny][nx] > size) continue;
                    visited[ny][nx] = true;
                    pq.add(new Edge(ny, nx, edge.day + 1));

                    // 먹이를 먹으면 BFS 재귀


                }
            }
            if (!isEat) {
                break;
            }
            if(eat == size) {
                eat = 0;
                size ++;
            }
        }

    }

    private static class Edge implements Comparable<Edge> {
        int y;
        int x;
        int day;
        Edge(int y, int x, int day) {
            this.y = y;
            this.x = x;
            this.day = day;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.day != o.day) {
                return this.day - o.day;   // 날짜 오름차순
            } else if (this.y != o.y) {
                return this.y - o.y;   // 날짜가 같으면 위쪽에 있는 거 부터(y 오름차순)
            } else return this.x - o.x;

        }
    }
}
