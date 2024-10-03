package algorithms.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b10868_최소값_세그먼트트리 {
    private static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        // 트리 사이즈 정하기
        int treeSize = 1;
        int offset;
        while(treeSize < N) {
            treeSize *= 2;
        }
        offset = treeSize;
        treeSize *= 2;


        // 트리 초기화 - 최소값을 구해야하니 리프노드 중 빈 노드들은 최대값으로 세팅해준다.
        tree = new long[treeSize];
        Arrays.fill(tree, Long.MAX_VALUE);
        for (int i = offset; i < offset+N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }
        setTree(treeSize);


        // 최소값 구하기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) + offset -1;
            int e = Integer.parseInt(st.nextToken()) + offset-1;

            long mini = getMini(s, e);
            System.out.println(mini);

        }


    }

    private static long getMini(int s, int e) {
        long min = Long.MAX_VALUE;
        while (s <= e) {
            if (s % 2 == 1) {
                min = Math.min(min, tree[s]);
                s++;
            }
            if (e % 2 == 0) {
                min = Math.min(min, tree[e]);
                e--;
            }
            s /= 2;
            e /= 2;
        }
        return min;
    }

    private static void setTree(int treeSize) {
        int idx = treeSize - 1;
        while(idx !=0) {
            tree[idx / 2] = Math.min(tree[idx / 2], tree[idx]);
            idx--;
        }
    }
}
