package algorithms.src.삼성_SW_역량_테스트_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b16235_나무재테크 {
    static int N, M, K;
    static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}; // 방향 벡터 (상, 상우, 우, 하우, 하, 하좌, 좌, 상좌)
    static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1}; // 방향 벡터 (상, 상우, 우, 하우, 하, 하좌, 좌, 상좌)
    static PriorityQueue<Tree> treeQueue = new PriorityQueue<>(); // 나무들을 관리하는 우선순위 큐 (나이가 어린 나무부터)
    static int[][] nutrients, addNutrients; // 현재 양분과 겨울에 추가될 양분 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nutrients = new int[N][N];
        addNutrients = new int[N][N];

        // 초기 양분 설정: 모든 칸에 5만큼의 양분이 있습니다.
        for (int i = 0; i < N; i++) {
            Arrays.fill(nutrients[i], 5);
        }

        // 겨울에 추가될 양분을 입력받습니다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                addNutrients[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무의 초기 상태를 입력받아 우선순위 큐에 추가합니다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            treeQueue.add(new Tree(y, x, age));
        }

        // K년 후 살아남은 나무의 수를 출력합니다.
        System.out.println(simulateSeasons());
    }

    // K년 동안의 계절 순환을 시뮬레이션합니다.
    private static int simulateSeasons() {
        for (int year = 0; year < K; year++) {
            springAndSummer(); // 봄과 여름을 처리합니다.
            fall();            // 가을을 처리합니다.
            winter();          // 겨울을 처리합니다.
        }
        return treeQueue.size(); // 살아남은 나무의 수를 반환합니다.
    }

    // 봄과 여름을 처리하는 메서드
    private static void springAndSummer() {
        PriorityQueue<Tree> nextQueue = new PriorityQueue<>();
        List<Tree> deadTrees = new ArrayList<>();

        // 봄: 나무가 자신의 나이만큼 양분을 먹고 나이가 1 증가합니다.
        while (!treeQueue.isEmpty()) {
            Tree tree = treeQueue.poll();
            if (nutrients[tree.y][tree.x] >= tree.age) {
                nutrients[tree.y][tree.x] -= tree.age;
                tree.age++;
                nextQueue.add(tree);
            } else {
                deadTrees.add(tree);
            }
        }

        // 여름: 봄에 죽은 나무가 양분으로 변합니다.
        for (Tree deadTree : deadTrees) {
            nutrients[deadTree.y][deadTree.x] += deadTree.age / 2;
        }

        treeQueue = nextQueue; // 다음 계절을 위한 나무 리스트 갱신
    }

    // 가을을 처리하는 메서드: 나무가 번식합니다.
    private static void fall() {
        List<Tree> newTrees = new ArrayList<>();

        // 나이가 5의 배수인 나무가 인접한 8개의 칸에 나이가 1인 나무를 번식시킵니다.
        for (Tree tree : treeQueue) {    // poll을 하는 것이 아니라 단순 접근만 하는 것!
            if (tree.age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int ny = tree.y + dy[i];
                    int nx = tree.x + dx[i];
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                        newTrees.add(new Tree(ny, nx, 1));
                    }
                }
            }
        }

        treeQueue.addAll(newTrees); // 새로운 나무를 기존 나무 리스트에 추가
    }

    // 겨울을 처리하는 메서드: 양분을 추가합니다.
    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutrients[i][j] += addNutrients[i][j];
            }
        }
    }

    // 나무 객체를 정의하는 클래스
    private static class Tree implements Comparable<Tree> {
        int y, x, age;

        Tree(int y, int x, int age) {
            this.y = y;
            this.x = x;
            this.age = age;
        }

        // 나이가 어린 순으로 정렬하기 위해 compareTo 메서드 구현
        @Override
        public int compareTo(Tree other) {
            return Integer.compare(this.age, other.age);
        }
    }
}

/*

        ### 추가된 주석 설명

        1. **클래스 및 변수 초기화**: 주요 변수와 초기 데이터를 설정하는 부분에 주석을 추가했습니다.
        2. **메서드 설명**: 각 메서드의 역할과 주요 로직을 설명하는 주석을 추가했습니다.
        3. **세부 로직 설명**: 봄, 여름, 가을, 겨울 각 계절의 로직에 대한 설명을 추가하여 가독성을 높였습니다.
        4. **트리 클래스**: 트리 클래스의 역할과 `compareTo` 메서드에 대한 설명을 추가했습니다.

        이 주석들을 통해 코드의 흐름과 각 부분의 역할을 쉽게 이해할 수 있을 것입니다.
        */
