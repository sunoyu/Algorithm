package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class b20055 {
    static int[] belt;
    static ArrayList<Integer> robot = new ArrayList<>();
    private static int N;
    private static int K;
    private static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        total = 2 * N;
        belt = new int[total];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < total; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int time = 0;

        while (K > 0) {
            time++;
            // rotate
            Collections.sort(robot);

            // 참고로 역순은 list.sort(Comparator.reverseOrder()); Collections.sort(list,
            // Collections.reverseOrder());
            rotate();

            Collections.sort(robot);
            moveRobot();

            if (belt[0] != 0 && !robot.contains(0)) {
                belt[0]--;
                if (belt[0] <= 0)
                    K--;
                robot.add(0);
            }

        }

        System.out.println(time);

    }

    private static void moveRobot() {

        if (robot.size() > 0) {
            // 로봇이동
            for (int i = robot.size() - 1; i >= 0; i--) {
                int nowRobot = robot.get(i);
                if (belt[nowRobot + 1] >= 1 && !robot.contains(nowRobot + 1)) { // 다음 칸이 이동 가능한 위치라면
                    belt[nowRobot + 1]--;
                    robot.set(i, nowRobot + 1);

                    if (belt[nowRobot + 1] == 0) { // 다음 칸에서 -1을 했을때 0이 되는경우?
                        K--;
                    }
                    if (nowRobot + 1 == N - 1) {
                        robot.remove(i);
                    }
                }
            }

        }
    }

    private static void rotate() {
        int tmp = belt[total - 1];
        for (int i = total - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
        }
        belt[0] = tmp;

        if (robot.size() > 0) {
            for (int i = robot.size() - 1; i >= 0; i--) {
                int moved = robot.get(i) + 1;
                robot.set(i, moved);

                if (robot.get(i) == N - 1) {
                    robot.remove(i);
                }
            }
        }
    }

}
