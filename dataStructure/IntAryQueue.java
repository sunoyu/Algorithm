package dataStructure;

public class IntAryQueue {
    private static int max; // capa
    private static int num; // number of data
    private static int[] que; // queue body
    public static void main(String[] args) {
        num = 0;
        max = 10;
        que = new int[max];
        que = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int dq = queue.dequeue(que);
        System.out.println(dq);

        for (int i : que) {
            System.out.print(i + " ");
        }

    }
    static class queue {

        public static int dequeue(int[] que) {
            int deq = que[0];
            for (int i = 1; i < que.length; i++) {
                que[i - 1] = que[i];
            }
            return deq;
        }
    }
}
