package algorithms.src.단기간성장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class b1655_가운데를말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            int input = Integer.parseInt(br.readLine());
            arr.add(input);
            arr.sort(Comparator.naturalOrder());
            int num;
            if (i % 2 == 0) {
                num = arr.get(arr.size()/2 -1);
            } else {
                num = arr.get(arr.size()/2);
            }
            System.out.println(num);
        }
    }
}
