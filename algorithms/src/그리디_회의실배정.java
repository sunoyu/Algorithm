package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1931
public class 그리디_회의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] A = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {     // 종료시간이 같은 경우
                    return o1[0]-o2[0];   // 시작 시간이 오름차순으로
                }
                return o1[1]-o2[1];    // 종료시간 오름차순
            }
        });
        int cnt = 0;
        int end = -1;
        for (int i = 0; i < N; i++) {
            if(A[i][0] >= end) {
                end = A[i][1];
                cnt ++;
            }
        }
        System.out.println(cnt);




    }
}
