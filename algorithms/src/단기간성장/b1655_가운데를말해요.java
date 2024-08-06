package algorithms.src.단기간성장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class b1655_가운데를말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (minHeap.size() >= maxHeap.size()) {
                maxHeap.add(input);    // 중앙값을 효율적으로 구하려면 최대힙에 우선 삽입, 예로 첫번 째 값이 들어오면 해당값이 중앙값
            } else minHeap.add(input);

            if (!minHeap.isEmpty() && !maxHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int maxRoot = maxHeap.poll();
                int minRoot = minHeap.poll();
                maxHeap.add(minRoot);
                minHeap.add(maxRoot);
            }
            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
