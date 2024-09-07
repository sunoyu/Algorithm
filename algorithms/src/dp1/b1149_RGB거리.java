package algorithms.src.dp1;

import sun.nio.cs.ext.MacHebrew;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1149_RGB거리 {
    static int RED = 0;
    static int GREEN = 1;
    static int BLUE = 2;
    static int N;
    static int[][] cost, DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cost = new int[N][3];
        DP = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][RED] = Integer.parseInt(st.nextToken());
            cost[i][GREEN] = Integer.parseInt(st.nextToken());
            cost[i][BLUE] = Integer.parseInt(st.nextToken());
        }

        DP[0][RED] = cost[0][RED];
        DP[0][GREEN] = cost[0][GREEN];
        DP[0][BLUE] = cost[0][BLUE];

        for (int i = 1; i < N; i++) {
            for (int color = 0; color < 3; color++) {
                if(color == RED) {
                    DP[i][color] = Math.min(DP[i-1][GREEN], DP[i-1][BLUE]) + cost[i][color];
                } else if (color == GREEN) {
                    DP[i][color] = Math.min(DP[i - 1][RED], DP[i - 1][BLUE]) + cost[i][color];
                } else {
                    DP[i][color] = Math.min(DP[i - 1][RED], DP[i - 1][GREEN]) + cost[i][color];

                }
            }
        }

        System.out.println(Math.min(DP[N - 1][BLUE], Math.min(DP[N - 1][RED], DP[N - 1][GREEN])));

    }
}
