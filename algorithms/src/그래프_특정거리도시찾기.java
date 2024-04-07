package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 그래프_특정거리도시찾기 {
    private static int M;
    private static int N;
    private static int K;
    private static int X;
    static int[] visited;
    static ArrayList<Integer>[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        X = Integer.parseInt(br.readLine());

        visited = new int[N];
        A = new ArrayList[N];
        for(int i = 0; i < M; i++){

        }


    }
}
