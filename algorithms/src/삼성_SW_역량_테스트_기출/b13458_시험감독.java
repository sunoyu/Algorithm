package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b13458_시험감독 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] classes = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            classes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());


        long numberOfManager = 0;    // 타입 체크!
        for (int cls : classes) {
            int student = cls - B;
            numberOfManager ++;
            if(student <=0) continue;

//            numberOfManager += Math.ceil((float) n / manager[1]);
            numberOfManager += (student + C - 1) / C;   // 올림의 또다른 방법
        }

        System.out.println(numberOfManager);
    }

}
