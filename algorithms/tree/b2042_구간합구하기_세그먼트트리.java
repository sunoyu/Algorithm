package algorithms.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2042_구간합구하기_세그먼트트리 {
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N M K
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int size = 1;
        while (size < N) {
            size *= 2;
        }

        int offset = size;   // 리프 노드 시작점
        size *= 2; // 실제 노드갯수 +1개임

        tree = new long[size];

        // 트리에 담기
        for (int i = 0; i < N; i++) {
            tree[i + offset] = Long.parseLong(br.readLine());
        }

        // 트리 세팅
        setTree(size-1);


        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            long s = offset + b - 1;
            long e = offset + c - 1;


            // a==1 b번쨰 수를 C로 바꿈
            if (a == 1) {
                int idx = (int)s;
                long diff = c - tree[idx];
                tree[idx] = c;

                while (idx != 1) {
                    tree[idx / 2] += diff;
                    idx /= 2;
                }
            }
            // a==2 b부터 c까지 구간합
            else if (a == 2) {
                query((int)s, (int)e);
            }
        }







    }

    private static void query(int left, int right) {
        long sum = 0;
        while (left <= right) {
            if (left % 2 == 1) { // 한쪽만 가지고 있다는 말

                sum += tree[left++];
            }
            if (right % 2 == 0) {
                sum += tree[right--];
            }
            left /= 2;
            right /= 2;
        }
        System.out.println(sum);
    }

    private static void setTree(int i) {
        while (i != 1) {
            tree[i / 2] += tree[i];
            i--;
        }
    }
}
