import java.util.Arrays;
public class MatrixSorterTest {
 
    public static void main(String[] args) {
        int[][] source = {
                {9, 3, 7},
                {1, 8, 2},
                {6, 5, 4}
        };
 
        System.out.println("Вихідна матриця:");
        print(source);
 
        int[][] sorted = MatrixSorter.sort(source);
 
        System.out.println("\nВідсортована матриця:");
        print(sorted);
 
        // Демонстрація захисту від некоректних даних.
        System.out.println("\nПеревірка некоректного вводу:");
        try {
            MatrixSorter.sort(new int[][]{{1, 2, 3}, {4, 5, 6}}); // не квадратна
        } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено: " + e.getMessage());
        }
    }
 
    /**
     * Допоміжний метод виводу матриці у консоль.
     *
     * @param matrix матриця для друку
     */
    private static void print(int[][] matrix) {
        Arrays.stream(matrix)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }
}