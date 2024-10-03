package algorithms.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b11505_구간곱 {
    private static long[] tree;
    static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노드 변경 곱
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int treeSize = 1;
        while (treeSize < N) {
            treeSize *= 2;
        }
        int offset = treeSize;
        treeSize *= 2;

        tree = new long[treeSize];

        Arrays.fill(tree, 1);
        for (int i = offset; i < offset + N; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        int i = treeSize-1;
        while (i != 1) {
            tree[i / 2] =tree[i / 2] * tree[i] % MOD;   //*= 쓰면 틀림
            i--;
        }


        for (int t = 0; t < M + K; t++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) + offset -1;
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {  // update
                tree[b] = (long) c;
                while (b > 1) {
                    b /= 2;
//                    tree[b] = tree[b * 2] % MOD * tree[b * 2 + 1] % MOD;
                    tree[b] = (tree[b * 2] * tree[b * 2 + 1]) % MOD;
                }
            } else if (a == 2) { // 구간곱
                getProduct(c, offset, b);
            }
        }

    }

    private static void getProduct(int c, int offset, int b) {
        c = c + offset - 1;
        long sum = 1;
        while (b <= c) {
            if (b % 2 == 1) {
//                sum *= tree[b] % MOD;
                sum = (sum * tree[b]) % MOD;
                b++;
            }
            b /= 2;
            if (c % 2 == 0) {
//                sum *= tree[c] % MOD;
                sum = (sum * tree[c]) % MOD;
                c--;
            }
            c /= 2;
        }
        System.out.println(sum);

    }
}
