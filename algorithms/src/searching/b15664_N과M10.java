package algorithms.src.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class b15664_N과M10 {
    private static int N;
    private static int M;
    private static int[] arr, ans;
    private static boolean[] visited;
    public static LinkedHashSet<String> set; // 중복값 제거!

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        ans = new int[M];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i< N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        set = new LinkedHashSet<>();
        Arrays.sort(arr);
        bt(0,0);
        set.forEach(System.out::println);    // 메서드 레퍼런스 방식으로 출력!


    }

    private static void bt(int n, int lev) {
        if(lev == M){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                sb.append(ans[i]).append(" ");
            }
            set.add(sb.toString());
            return;
        }


        for (int i = n; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            ans[lev] = arr[i];
            bt(i+1, lev +1);
            visited[i] = false;
        }


    }
}
