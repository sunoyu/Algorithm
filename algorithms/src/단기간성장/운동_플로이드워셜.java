package algorithms.src.단기간성장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 운동_플로이드워셜 {
    private static int[][] floydMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        floydMap = new int[V][V];
        int INF = 100000000;
        // 초기화 1
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if(i==j) floydMap[i][j] = 0;
                else floydMap[i][j] = INF;  // 무한대로 세팅
            }
        }
        // 초기화 2
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            floydMap[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = Integer.parseInt(st.nextToken());
        }

        // 플로이드 워셜
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    floydMap[i][j] = Math.min(floydMap[i][j], floydMap[i][k] + floydMap[k][j]);
                }
            }
        }
        int min = INF;
        for (int i = 0; i < V; i++) {
            int tmp = 0;
            for (int j = 0; j < V; j++) {
                if (i != j && floydMap[i][j] != INF && floydMap[j][i] != INF) {
                    tmp = floydMap[i][j] + floydMap[j][i];
                    min = Math.min(min, tmp);
                }
            }
        }
        if(min == INF) System.out.println(-1);
        else System.out.println(min);
    }
}
