package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 그래프_이분그래프 {
    static int V,E;
    static boolean isNot;
    static int[] check;
    static boolean[] visited;
    static ArrayList<Integer>[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            visited = new boolean[V+1];
            check = new int[V+1];    // A그룹과 B그룹으로 나눌 건데, 숫자로 구분짓는게 편하므로 0과 1 그룹으로 나눠주자.
            isNot = false;          // *** 이걸 안해줘서 2번 틀렸음!
            A = new ArrayList[V+1];
            for(int v = 1; v <= V; v++) {
                A[v] = new ArrayList<>();
            }

            // 양방향 그래프로 작성해준다.
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                A[start].add(end);
                A[end].add(start);
            }

            // 2개의 그래프가 나오는 경우도 있을 수 있으므로 모든 노드에서 DFS탐색을 해준다. 단, 중간에 이진그래프가 아닌경우 종료.
            for (int i = 1; i <= V; i++) {
                DFS(i);
                if(isNot) break;
            }

            if(isNot) System.out.println("NO");
            else System.out.println("YES");
        }
    }

    private static void DFS(int start) {
            visited[start] = true; // 시작 노드는 방문 체크
            for (int i : A[start]) { // 다음 노드들 확인
                if (!visited[i]) { // 다음 노드가 방문했던 노드가 아니면
                    check[i] = (check[start] + 1) % 2;   // 현재 노드가 0인경우 다음 노드의 그룹은 1, 현재 노드가 1인경우? 0
                    DFS(i);
                } else {  // 방문했던 노드라면
                    if (check[i] == check[start]) { // 인접한 노드끼리 그룹이 같은지 체크
                        isNot = true;
                        break;
                    }
                }
            }
    }
}
