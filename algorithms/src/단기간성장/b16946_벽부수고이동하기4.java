package algorithms.src.단기간성장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b16946_벽부수고이동하기4 {
    private static int N,M;
    private static int[][] map, grpMap;
    private static int[] group;
    private static Queue<Node> q;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        grpMap = new int[N][M];
        group = new int[500000];

        // 맵 그리기
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        // map[]을 순회하면서 0이면, BFS를 돌면서 인접한 칸이 0인 경우 grpMap[]에 그룹id를 찍고
        // 해당 그룹id를 카운트 한 뒤에 group[]에 담아준다.
        visited = new boolean[N][M];
        int groupId = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j])  {
                    groupId ++;
                    int count = setGroupId(i, j, groupId);
                    group[groupId] = count;
                }
            }
        }

        makeAns();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int setGroupId(int y, int x, int groupId) {
        q = new LinkedList<>();
        q.add(new Node(y, x));
        grpMap[y][x] = groupId;
        visited[y][x] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M || grpMap[ny][nx] !=0 || map[ny][nx] !=0 ) continue;
                grpMap[ny][nx] = groupId;
                visited[ny][nx] = true;
                q.add(new Node(ny, nx));
                cnt++;
            }

        }
        return cnt;
    }

    private static void makeAns() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    HashSet<Integer> set = new HashSet<>();     // 4방향이 같은 그룹인 경우를 대비하여 중복을 피한 set이용
                    // 상하좌우
                    for (int x = 0; x < 4; x++) {
                        int ny = i + dy[x];
                        int nx = j + dx[x];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= M || grpMap[ny][nx] == 0) {
                            continue;
                        }
                        set.add(grpMap[ny][nx]);
                    }
                    int sum = 1;
                    for(int s : set){
                        sum += group[s];
                    }
                    map[i][j] = sum%10;
                }
            }
        }
    }

    private static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }
}
