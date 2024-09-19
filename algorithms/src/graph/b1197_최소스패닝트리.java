package algorithms.src.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b1197_최소스패닝트리 {
    private static int[] parent;
    static int cnt, weight = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> q = new PriorityQueue<Edge>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            q.add(new Edge(s, e, v));
        }

        parent = new int[V + 1];  // 유니온 파인드
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        while (cnt < V - 1) {
            Edge edge = q.poll();
            if(union(edge.s, edge.e)) {
                weight += edge.v;
            };
        }
        System.out.println(weight);
    }

    private static boolean union(int a, int b) {
        if(find(a) != find(b)) {
            parent[find(b)] = find(a);
            cnt ++;
            return true;
        }
        return false;
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    private static class Edge implements Comparable<Edge> {
        int s;
        int e;
        int v;

        public Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(Edge o) {
            return this.v - o.v;
        }
    }
}
