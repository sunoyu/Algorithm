package algorithms.src.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3
* */
public class b1504_특정한최단경로 {
    private static int N;
    private static List<Edge>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Edge(e, w));
            list[e].add(new Edge(s, w));
        } // 양방향


        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());



        int[] distS = djik(1);
        int[] distV1 = djik(v1);
        int[] distV2 = djik(v2);

        // 2개의 경로 비교
        long route1 = (long) distS[v1] + distV1[v2] + distV2[N]; // 1->v1->v2->E
        long route2 = (long )distS[v2] + distV2[v1] + distV1[N]; // 1->v2->v1->E

        long min = Math.min(route2, route1);
        if (min >= Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(min);
    }

    private static int[] djik(int i) {
        PriorityQueue<Edge> q = new PriorityQueue<>();

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[i] = 0;
        
        q.offer(new Edge(i, 0));

        while (!q.isEmpty()) {
            Edge cur = q.poll();
            for (Edge next : list[cur.v]) {
                if (dist[cur.v] + next.w < dist[next.v]) {
                    dist[next.v] = dist[cur.v] + next.w;
                    q.offer(new Edge(next.v, dist[next.v]));
                }
            }
        }
        return dist;
    }

    private static class Edge implements Comparable<Edge> {

        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}
