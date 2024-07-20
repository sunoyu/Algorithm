package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B13913_숨바꼭질4 {
    private static int s, e;
    static int MAX = 100000;
    static int[] parent = new int[MAX+1];  // 부모 노드를 담아줌. 첫 테스트 케이스가 100000인지 MAX로 해주면 런타임 에러 (ArrayIndexOutOfBounds) 발생
    static boolean[] visited = new boolean[MAX+1];
    static Queue<Integer> q = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        q.add(s);
        visited[s] = true;

        bfs();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(e);
        int time = 0;
        for (int i = e; i != s;) {
            list.add(parent[i]);
            time ++;
            i = parent[i];
        }
        Collections.reverse(list);
        System.out.println(time);
        for (int i : list) {
            System.out.print(i + " ");
        }
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : new int[]{current - 1, current + 1, current * 2}) {
                if (next >= 0 && next <= MAX && !visited[next]) {
                    parent[next] = current;
                    visited[next] = true;
                    if (next == e) return;
                    q.add(next);
                }
            }
        }
    }
}
