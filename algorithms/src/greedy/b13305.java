package algorithms.src.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] dist = new long[N - 1];
        long[] node = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            node[i] = Integer.parseInt(st.nextToken());
        }
        long cost = 1000000001;
        long sum = 0;
        for (int i = 0; i < N-1; i++) {
            if(node[i] < cost) {
                cost = node[i];
            }
            sum += (cost * dist[i]);
        }

        System.out.println(sum);
    }
}

// 자바에서 int연산의 overflow는 long 으로 형변환 되지 않음
