package algorithms.src.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// belman pord
/*
첫째 줄에 도시의 수 N과 시작 도시, 도착 도시 그리고 교통 수단의 개수 M이 주어진다. 둘째 줄부터 M개의 줄에는 교통 수단의 정보가 주어진다. 교통 수단의 정보는 “시작 끝 가격”과 같은 형식이다. 마지막 줄에는 오민식이 각 도시에서 벌 수 있는 돈의 최댓값이 0번 도시부터 차례대로 주어진다.
N과 M은 50보다 작거나 같고, 돈의 최댓값과 교통 수단의 가격은 1,000,000보다 작거나 같은 음이 아닌 정수이다.
*/
public class b1219_오민식의고민 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력: 도시수, 시작도시, 도착도시, 교통수단 갯수
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        // 노드넘버는 0~N
        Edge[] edge = new Edge[T];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edge[i] = new Edge(s, e, w);
        }

        // 도시에서 벌 수 있는 돈
        int[] money = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        int[] earning = new int[N];
        Arrays.fill(earning, Integer.MIN_VALUE);
        earning[S] = money[S]; // 출발지는 0으로

        // 최단거리가 아닌 최대 이윤을 남기는 문제이다. 따라서, 기본 배열 세팅을 최댓값이아닌 최솟값으로 세팅
        for (int i = 0; i < N +100; i++) {
            for (int j = 0; j < T; j++) {
                int s = edge[j].s;
                int e = edge[j].e;
                int w = edge[j].w;
                if(earning[s] == Integer.MIN_VALUE) continue;

                else if(earning[s] == Integer.MAX_VALUE) earning[e] = Integer.MAX_VALUE;

                // 이윤 배열 업데이트
                else if(earning[s] + money[e] - w > earning[e]) {
                    earning[e] = earning[s] + money[e] - w;

                    if(i >= N-1) earning[e] = Integer.MAX_VALUE;   // 양수 싸이클
                }
            }
        }

        if(earning[E] == Integer.MIN_VALUE) {
            System.out.println("gg");
        } else if(earning[E] == Integer.MAX_VALUE){
            System.out.println("Gee");
        } else System.out.println(earning[E]);

        // 음수 사이클이 아닌 양수사이클을 찾아야한다. 최단거리를 구할때 쓰는 조건인 V-1과 다른 접근

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
