package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 그래프_물통2251 {
    static boolean[][] visited;
    static int[] sender = {0, 0, 1, 1, 2, 2,};
    static int[] receiver = {1, 2, 0, 2, 0, 1};
    static boolean[] answer;
//    static int A, B, C;   // 6가지 경우의 수로 돌기 위해선 배열로 선언!
    static int[] capa = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        capa[0] = Integer.parseInt(st.nextToken());  // 8
        capa[1] = Integer.parseInt(st.nextToken());  // 9
        capa[2] = Integer.parseInt(st.nextToken());  // 10

        visited = new boolean[201][201];
        answer = new boolean[capa[2]+1];

        BFS();

        for (int i = 0; i <= capa[2]; i++) {    // C의 물이 0인경우도 있다!  3 2 1 일때
            if(answer[i]) System.out.print(i + " ");
        }
    }

    private static void BFS() {
        Queue<AB> q = new LinkedList<>();
        q.add(new AB(0, 0));
        visited[0][0] = true;    // A와 B가 0일 때(시작점)
        answer[capa[2]] = true;
        while (!q.isEmpty()) {
            AB ab = q.poll();
            int a = ab.a;
            int b = ab.b;
            int c = capa[2] - a - b;
            for (int i = 0; i < 6; i++) {   // 6way
                int[] next = {a, b, c};  // 다음 뎁스의 물통상황을 그리기 위한
                if(next[sender[i]] == 0) continue;

                next[receiver[i]] += next[sender[i]];   // 기존 리시버의 물양 + 센더의 물양
                next[sender[i]] = 0;

                if (next[receiver[i]] > capa[receiver[i]]) {   // 받는 물통이 넘치는 경우
                    next[sender[i]] = next[receiver[i]] - capa[receiver[i]];
                    next[receiver[i]] = capa[receiver[i]];
                }
                if(visited[next[0]][next[1]]) continue;
                visited[next[0]][next[1]] = true;
                q.add(new AB(next[0], next[1]));
                if(next[0] == 0) answer[next[2]] = true;
            }
        }


    }

    private static class AB {
        int a;
        int b;
        public AB(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}

