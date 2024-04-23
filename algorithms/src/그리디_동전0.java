package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 그리디_동전0 {
    private static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] money = new int[N];
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
//        N-=1;
        while(N-- > 0) {
            if(M >= money[N]) {
                cnt += M / money[N];
                M %= money[N];
            }
        }
        System.out.println(cnt);

    }


}
