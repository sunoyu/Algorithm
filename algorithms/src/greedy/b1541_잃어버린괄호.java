package algorithms.src.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b1541_잃어버린괄호 {
    public static void main(String[] args) throws IOException {
        // 그리디 관점에서, 값을 최소로 만드려면 큰수를 뺴줘야한다.
        // 따라서, 덧셈연산을 먼저 해주고, 빼기를 수행

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");

//        int sum = 0;
        int sum = Integer.MAX_VALUE;

        while (st.hasMoreTokens()) {
            int temp = 0;
            StringTokenizer step = new StringTokenizer(st.nextToken(), "+");
            while (step.hasMoreTokens()) {
                temp += Integer.parseInt(step.nextToken());
            }

            if(sum == Integer.MAX_VALUE) {   // 초기 값이라면
                sum =  temp;
            } else {
                sum -= temp;
            }
        }


        System.out.println(sum);

    }
}
