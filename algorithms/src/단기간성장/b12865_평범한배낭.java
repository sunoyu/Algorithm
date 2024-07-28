package algorithms.src.단기간성장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b12865_평범한배낭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물건 개수
        int W = Integer.parseInt(st.nextToken()); // 최대 무게

        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][W+1];   // dp[0][]은 아무 물건도 넣지 않았을때

        for (int i = 1; i <= N; i++) {
            for (int w = 0; w <= W; w++) {
                if (w >= weight[i]) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weight[i]] + value[i]);
                } else dp[i][w] = dp[i - 1][w];
            }
        }
        System.out.println(dp[N][W]);


    }
}
