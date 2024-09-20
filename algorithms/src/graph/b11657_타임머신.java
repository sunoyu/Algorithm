package algorithms.src.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b11657_타임머신 {
    public static void main(String[] args) throws IOException {

        // N, M, update테이블, 엣지리스트, 엣지클래스(s,e,w)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        long[] update = new long[V + 1];

        update[1] = 0;
        for (int i = 2; i <= V; i++) {
            update[i] = Integer.MAX_VALUE;
        }

        Edge[] edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(s, e, w);
        }

        for (int i = 1; i <= V - 1; i++) {
            for (int j = 0; j < E; j++) {
                int s = edgeList[j].s;
                int e = edgeList[j].e;
                int w = edgeList[j].w;

                // 시작점이 무한대인지 반드시 체크!!!
                update[e] =
                        update[s] != Integer.MAX_VALUE && (update[e] > update[s] + w) ?
                                update[s] + w : update[e];

            }
        }

        // 음수사이클 체크
        for (int j = 0; j < E; j++) {
            int s = edgeList[j].s;
            int e = edgeList[j].e;
            int w = edgeList[j].w;

            if (update[s] != Integer.MAX_VALUE && update[e] > update[s] + w) {
                System.out.println(-1);
                return;
            }
        }

        for (int i = 2; i <= V; i++) {
            if (update[i] == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(update[i]);
            }
        }
    }

    private static class Edge {
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}
