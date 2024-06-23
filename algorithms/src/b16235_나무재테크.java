package algorithms.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b16235_나무재테크 {
    static int N, M, K;
    static boolean flag;
    // 상 상우 우 하우 하 하좌 좌 상좌
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static PriorityQueue<Tree> q = new PriorityQueue<>();

    static int[][] A, map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = 5;
            }
        }

        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 나무의 위치
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            q.add(new Tree(y-1, x-1, age, 0, true));
        }

        System.out.println(BFS());

    }


    private static int BFS() {

        while (!q.isEmpty()) {
            Tree tree = q.poll();

            int ty = tree.y;
            int tx = tree.x;
            int age = tree.age;
            boolean alive = tree.alive;
            int weather = tree.weather % 4;

            if(tree.weather == K *4) {
                return q.size() +1;
            }

            switch (weather) {
                case 0 : // in spring
                    flag = false;
                    if(map[ty][tx] >= age) {
                        map[ty][tx] -= age;
                        age+=1;
                    } else alive = false;

                    q.add(new Tree(ty, tx, age, tree.weather+1, alive));
                    break;

                case 1:  // in summer , if false? age /2 +
                    if(!alive) map[ty][tx] += age/2;
                    else q.add(new Tree(ty, tx, age, tree.weather+1, alive));
                    break;

                case 2: // fall , 나무 번식
                    if(age %5 == 0) {
                        for (int i = 0; i < 8; i++) {
                            int ny = ty + dy[i];
                            int nx = tx + dx[i];
                            if (ny < 0 || ny >=N || nx < 0 || nx >= N) continue;
                            q.add(new Tree(ny, nx, 1, tree.weather+1, true));
                        }
                    }
                    q.add(new Tree(ty, tx, age, tree.weather+1, alive));

                    break;

                case 3: // winter, 양분 채워넣기
                    if(!flag) {
                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < N; j++) {
                                map[i][j] += A[i][j];
                            }
                        }
                        flag = true;
                    }
                    q.add(new Tree(ty, tx, age, tree.weather+1, alive));

                    break;
            }
        }

    return -1;
    }


    private static class Tree implements Comparable<Tree> {
        int y;
        int x;
        int age;
        int weather;
        boolean alive;

        public Tree(int y, int x, int age, int weather, boolean alive) {
            this.y = y;
            this.x = x;
            this.age = age;
            this.weather = weather;
            this.alive = alive;
        }

        @Override
        public int compareTo(Tree o) {
            if(this.weather != o.weather) {   // 1. 계절순으로
                return Integer.compare(this.weather, o.weather);
//                return this.weather - o.weather;
            } else
                return Integer.compare(this.age, o.age);
//                return this.age - o.age;   // 어린 나무부터 먹이자.

        }
    }
}