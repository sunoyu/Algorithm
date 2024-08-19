package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b21608_상어초등학교 {
    private static class Student implements Comparable<Student> {
        int y;
        int x;
        int like;
        int blank;

        public Student(int y, int x, int like, int blank) {
            super();
            this.y = y;
            this.x = x;
            this.like = like;
            this.blank = blank;
        }

        @Override
        public int compareTo(Student o) {
            if (o.like != this.like) {
                return o.like - this.like;
            }
            if (o.blank != this.blank) {
                return o.blank - this.blank;
            }
            if (o.y != this.y)
                return this.y - o.y;
            return this.x - o.x;
        }
    }

    private static int N;
    private static int[][] map;
    private static int[] who;
    private static ArrayList<Integer>[] A;
    static int[] dy = { -1, 0, 0, 1 };
    static int[] dx = { 0, -1, 1, 0 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int stdNum = N * N;
        map = new int[N][N];
        who = new int[stdNum];
        A = new ArrayList[stdNum + 1];

        for (int i = 1; i <= stdNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            A[now] = new ArrayList<Integer>();
            who[i - 1] = now;
            for (int j = 0; j < 4; j++) {
                A[now].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 1~N2 학생 순서대로 돌려준다.
        for (int s = 0; s < stdNum; s++) {
            int nowStd = who[s];
            findMySeat(nowStd);
        }

        // 맵이 완성됐을때,

        int sum =0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                        continue;
                    }
                    for (int like : A[map[i][j]]) {
                        if (like == map[ny][nx]) {
                            count++;
                            break;
                        }
                    }
                }

                switch (count) {
                    case 1:
                        sum += 1;
                        break;
                    case 2:
                        sum += 10;
                        break;
                    case 3:
                        sum += 100;
                        break;
                    case 4:
                        sum += 1000;
                        break;

                }
            }
        }
        System.out.println(sum);

    }

    private static void findMySeat(int nowStd) {
        PriorityQueue<Student> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]!=0) continue;
                // 상좌우하 고고
                int blank = 0;
                int like = 0;

                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                        continue;
                    }
                    if (map[ny][nx] == 0) {
                        blank++;
                    } else if (map[ny][nx] > 0) {
                        for (int likeBoy : A[nowStd]) {
                            like += map[ny][nx] == likeBoy ? 1 : 0;
                            // 시간초과나면 줄여주자.
                        }
                    }
                }
                pq.add(new Student(i, j, like, blank));

            }
        }

        // 맵 한바퀴 돌고
        if (!pq.isEmpty()) {
            Student std = pq.poll();
            map[std.y][std.x] = nowStd;
        }
        return;
    }
}
