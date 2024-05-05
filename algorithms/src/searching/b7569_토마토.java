package algorithms.src.searching;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class b7569_토마토 {
    private static int N;
    private static int H;
    private static int M;
    static int rest;

    static int[] dz = {-1,1,0,0,0,0};
    static int[] dy = {0,0,0,0,1,-1};
    static int[] dx = {0,0,-1,1,0,0};

    static Queue<Tomato> q;
    static int[][][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());   // 가로 칸수 x
        N = Integer.parseInt(st.nextToken());  // 세로 칸 수 y
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];
        visited = new boolean[H][N][M];

        q = new LinkedList<>();

        for(int z= 0; z < H; z++) {
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++) {
                    map[z][i][j] = Integer.parseInt(st.nextToken());
                    if(map[z][i][j]==0) {
                        rest ++;
                    } else if(map[z][i][j]==1) {
                        q.add(new Tomato(z,i,j,0));
                        visited[z][i][j] = true;
                    }
                }
            }
        }


        if(rest ==0){
            System.out.println(0);
            return;
        }
		BFS();


    }

    private static void BFS() {
        while(!q.isEmpty()) {
            Tomato now = q.poll();
            for(int i = 0; i < 6; i++) {
                int nz = now.z + dz[i];
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (nz < 0 || nz >= H || ny < 0 || ny >= N || nx < 0 || nx >= M || visited[nz][ny][nx] || map[nz][ny][nx] == -1) {
                   continue;
                }
                visited[nz][ny][nx] = true;
                rest --;
                if(rest == 0) {
                    System.out.println(now.day+1);
                    return;
                }
                q.add(new Tomato(nz, ny, nx, now.day + 1));
            }
        }
        System.out.println(-1);

    }


    private static class Tomato{
        int z;
        int y;
        int x;
        int day;
        public Tomato(int z, int y, int x, int day) {
            this.z = z;
            this.y =y;
            this.x =x;
            this.day=day;
        }
    }
}