package codewars;

public class createPhoneNumber {

    public static void main(String[] args) {
        int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}; // => returns "(123) 456-7890"


        System.out.println(createPhoneNumber(numbers));



    }

    public static String createPhoneNumber(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for(int i = 0; i<=2; i++) {
            sb.append(numbers[i]);
        }
        sb.append(") ");

        for (int i = 3; i <= 5; i++) {
            sb.append(numbers[i]);
        }
        sb.append("-");

        for (int i = 6; i <= 9; i++) {
            sb.append(numbers[i]);
        }

        return sb.toString();

    }




}
