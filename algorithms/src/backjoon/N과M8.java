package algorithms.src.backjoon;

import jdk.internal.util.xml.impl.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N과M8 {
    static int n,m;
    static int[] arr, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        result = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        dfs(0, 0);
        System.out.println(sb);
    }
    static StringBuilder sb = new StringBuilder();
    static void dfs(int now, int depth){
        if(depth == m){
            for(int var : result){
                sb.append(var).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = now; i < n; i++){
            result[depth] = arr[i];
            dfs(i,depth+1);
        }
    }
}
