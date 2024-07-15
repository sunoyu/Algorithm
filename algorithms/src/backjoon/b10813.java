package algorithms.src.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b10813 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] socket = new int[N];

        for (int i = 0; i < N; i++) {
            socket[i] = i + 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int tmp = socket[s-1];
            socket[s - 1] = socket[e - 1];
            socket[e - 1] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : socket) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());

    }

}
