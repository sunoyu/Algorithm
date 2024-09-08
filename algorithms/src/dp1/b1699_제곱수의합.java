package algorithms.src.dp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b1699_제곱수의합 {
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int num = Integer.parseInt(br.readLine());

        int[] dp = new int[num + 1];
        for (int i =1; i<= num; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                if (dp[i] > dp[i - j*j] + 1) {
                    dp[i] = dp[i - j*j] + 1;
                }
            }
        }
        System.out.println(dp[num]);

    }

}
