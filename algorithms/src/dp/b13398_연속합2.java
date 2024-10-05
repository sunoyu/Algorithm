package algorithms.src.dp;

import java.io.*;
import java.util.StringTokenizer;

public class b13398_연속합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] L = new int[N];
        int[] R = new int[N];

        L[0] = arr[0];
        int MAX = L[0];

        for (int i = 1; i < N; i++) {
            L[i] = Math.max(arr[i], L[i - 1] + arr[i]);
            MAX = Math.max(MAX, L[i]);
        }

        R[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            R[i] = Math.max(arr[i], R[i + 1] + arr[i]);
        }

        // 삭제 로직
        for (int i = 1; i < N - 1; i++) {
            MAX = Math.max(MAX, L[i - 1] + R[i + 1]);
        }
        bw.write(MAX + "");
        bw.flush();


    }
}
