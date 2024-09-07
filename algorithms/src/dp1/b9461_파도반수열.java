package algorithms.src.dp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b9461_파도반수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        long[] calc = new long[101];

        calc[0] = 1;
        calc[1] = 1;
        calc[2] = 1;
        calc[3] = 2;

        for (int i = 4; i < 100; i++) {
            calc[i] = calc[i - 2] + calc[i - 3];
        }
        for (int t = 0; t < T; t++) {
            System.out.println(calc[(Integer.parseInt(br.readLine()))-1]);
        }

    }

}
