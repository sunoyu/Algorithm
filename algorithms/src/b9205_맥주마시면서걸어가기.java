package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b9205_맥주마시면서걸어가기 {
    private static int T;
    private static int N;
    private static ArrayList<Pos> list;
    private static ArrayList<Integer>[] edge;

    private static boolean[] visited;
    private static boolean happy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            visited = new boolean[N + 2];
            happy = false;
            edge = new ArrayList[N+2];
            for (int i = 0; i < N + 2; i++) {
                edge[i] = new ArrayList();
            }

            for (int i = 0; i < N + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                list.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));   // x , y 입력
            }

            // 대조하면서 양방향 그래프 만들기
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    Pos first = list.get(i);
                    Pos second = list.get(j);

                    int mhDist = Math.abs(first.x - second.x) + Math.abs(first.y - second.y);
                    if(mhDist > 1000) continue;

                    edge[i].add(j);
                    edge[j].add(i);
                }
            }

            BFS();
            System.out.println(happy ? "happy" : "sad");
        }
    }

    private static void BFS() {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        while (!q.isEmpty()) {
            int now = q.poll();
            for(int next : edge[now]) {
                if(visited[next]) continue;
                visited[next] = true;
                if (next == list.size() - 1) {
                    happy =  true;
                    return;
                }
                q.add(next);
            }
        }
    }

    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
