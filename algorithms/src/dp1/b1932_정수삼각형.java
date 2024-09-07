package algorithms.src.dp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1932_정수삼각형 {
    private static int N;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N][N];
        for (int i = N-1; i >= 0; i--) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N-i; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findMAx(1);
        System.out.println(dp[N-1][0]);

    }

    private static void findMAx(int depth) {
        if(depth == N) return;

        for (int i = 0; i < N - depth; i++) {
            dp[depth][i] += Math.max(dp[depth-1][i], dp[depth-1][i+1]);
        }
        findMAx(depth+1);
    }
}
