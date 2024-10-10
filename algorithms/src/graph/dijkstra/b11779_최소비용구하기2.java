package algorithms.src.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b11779_최소비용구하기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Edge>[] list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[s].add(new Edge(e, w));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> q = new PriorityQueue<Edge>();
        q.offer(new Edge(s, 0));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        int[] route = new int[n + 1];

        while (!q.isEmpty()) {
            Edge cur = q.poll();
            if (cur.w > dist[cur.v]) {
                continue;    // 핵심! 티스토리에 게시
            }
            for (Edge next : list[cur.v]) {
                if (dist[cur.v] + next.w < dist[next.v]) {
                    dist[next.v] = dist[cur.v] + next.w;
                    route[next.v] = cur.v;
                    q.offer(new Edge(next.v, dist[next.v]));
                }
            }
        }
        int d = e;
        int cnt = 1;
        List<Integer> ans = new ArrayList<>();
        ans.add(e);
        while(d!=s) {
            ans.add(route[d]);
            d = route[d];
            cnt++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = ans.size()-1; i >= 0; i--) {
            sb.append(ans.get(i)).append(" ");
        }
        System.out.println(dist[e]);
        System.out.println(cnt);
        System.out.println(sb);
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
