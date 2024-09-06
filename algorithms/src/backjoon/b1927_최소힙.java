package algorithms.src.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class b1927_최소힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int next = Integer.parseInt(br.readLine());
            if(next == 0 ) {
                if(pq.size() != 0) System.out.println(pq.poll());
                else System.out.println(0);
            } else             pq.add(next);

        }

    }
}
