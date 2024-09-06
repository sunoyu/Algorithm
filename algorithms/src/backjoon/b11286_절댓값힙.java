package algorithms.src.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class b11286_절댓값힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Abs> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int next = Integer.parseInt(br.readLine());
            if(next == 0 ) {
                if(pq.size() != 0) {
                    Abs a = pq.poll();
                    System.out.println(a.origin);
                }
                else System.out.println(0);
            } else             pq.add(new Abs(next, Math.abs(next)));

        }

    }

    private static class Abs implements Comparable<Abs>{
        int origin;
        int abs;

        public Abs(int origin, int abs) {
            this.origin = origin;
            this.abs = abs;
        }

        @Override
        public int compareTo(Abs o) {
            if(this.abs !=o.abs){
                return this.abs - o.abs;
            } else return this.origin - o.origin;

        }
    }
}
