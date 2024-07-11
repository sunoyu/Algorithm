package algorithms.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 복기_종이접기0710 {

    // 종이 접는 함수
    public static boolean[][] foldPaper(boolean[][] paper, int direction, int length, int W, int H) {
        boolean[][] foldedPaper = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                foldedPaper[i][j] = paper[i][j];
            }
        }

        if (direction == 0) { // 가로 방향으로 접기
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < length; j++) {
                    foldedPaper[i][j] = !foldedPaper[i][j];
                }
            }
        } else if (direction == 1) { // 세로 방향으로 접기
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < W; j++) {
                    foldedPaper[i][j] = !foldedPaper[i][j];
                }
            }
        }

        return foldedPaper;
    }

    // 구멍의 순서 계산 함수
    public static int calculateSerialNumber(boolean[][] foldedPaper, int sx, int sy, int ax, int ay) {
        int foldedH = foldedPaper.length;
        int foldedW = foldedPaper[0].length;

        int serialNumber = 0;
        if (sx == 1) { // 행우선으로 구멍 계산
            for (int j = 0; j < foldedW; j++) {
                if (foldedPaper[sy-1][j]) {
                    serialNumber++;
                }
                if (j + 1 == ax) {
                    break;
                }
            }
        } else { // 열우선으로 구멍 계산
            for (int i = 0; i < foldedH; i++) {
                if (foldedPaper[i][ax-1]) {
                    serialNumber++;
                }
                if (i + 1 == sx) {
                    break;
                }
            }
        }

        return serialNumber;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 첫 줄 입력 처리
        int N = scanner.nextInt(); // 접는 횟수
        int W = scanner.nextInt(); // 초기 가로 길이
        int H = scanner.nextInt(); // 초기 세로 길이

        // 초기 종이 상태 생성
        boolean[][] paper = new boolean[H][W];

        // 접힌 정보 입력 받기
        List<int[]> folds = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int direction = scanner.nextInt();
            int length = scanner.nextInt();
            folds.add(new int[]{direction, length});
        }

        // 구멍을 뚫을 좌표와 구멍의 위치 입력 받기
        int sx = scanner.nextInt(); // 구멍을 뚫을 좌표 sx
        int sy = scanner.nextInt(); // 구멍을 뚫을 좌표 sy
        int ax = scanner.nextInt(); // 구멍의 위치 ax
        int ay = scanner.nextInt(); // 구멍의 위치 ay

        // 종이 접기
        for (int[] fold : folds) {
            int direction = fold[0];
            int length = fold[1];
            paper = foldPaper(paper, direction, length, W, H);
        }

        // 결과 계산
        int result = calculateSerialNumber(paper, sx, sy, ax, ay);

        // 결과 출력
        System.out.println(result);

        scanner.close();
    }
}