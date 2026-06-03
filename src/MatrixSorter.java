import java.util.Arrays;

/**
 * Утилітарний клас для сортування квадратної матриці.
 * <p>
 * Завдання: відсортувати квадратну матрицю порядку {@code N} у порядку
 * зростання елементів за обходом «зліва-направо, зверху-вниз». Тобто після
 * сортування найменший елемент опиняється у позиції [0][0], а найбільший —
 * у правому нижньому куті [N-1][N-1].
 * <p>
 * <b>Вихідні дані:</b> квадратна матриця цілих чисел {@code int[N][N]}.<br>
 * <b>Результат:</b> нова матриця того ж порядку з тими ж елементами,
 * розташованими у відсортованому порядку.
 * <p>
 * Реалізацію виконано без явних циклів — за допомогою методів класу
 * {@link java.util.Arrays} та потоків {@link java.util.stream.IntStream}.
 */
public final class MatrixSorter {

    /** Клас суто утилітарний — заборонити створення екземплярів. */
    private MatrixSorter() {
    }

    /**
     * Сортує квадратну матрицю у порядку зростання (зліва-направо, зверху-вниз).
     * <p>
     * Алгоритм складається з трьох кроків:
     * <ol>
     *     <li>«розпрямлення» матриці у одновимірний масив;</li>
     *     <li>сортування одновимірного масиву;</li>
     *     <li>розкладання відсортованого масиву назад у матрицю порядку N.</li>
     * </ol>
     * Вихідна матриця не змінюється — повертається нова.
     *
     * @param matrix квадратна матриця цілих чисел (N x N)
     * @return нова відсортована матриця того ж порядку
     * @throws IllegalArgumentException якщо матриця порожня або не квадратна
     */
    public static int[][] sort(int[][] matrix) {
        validateSquare(matrix);

        final int order = matrix.length;

        // 1. Розпрямлення: усі рядки зливаються в один масив.
        int[] flattened = Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .toArray();

        // 2. Сортування одновимірного масиву за зростанням.
        Arrays.sort(flattened);

        // 3. Розкладання назад у матрицю: рядок i — це шматок [i*N, i*N + N).
        int[][] result = new int[order][order];
        Arrays.setAll(result,
                row -> Arrays.copyOfRange(flattened, row * order, row * order + order));

        return result;
    }

    /**
     * Перевіряє, що матриця не {@code null}, не порожня та є квадратною.
     *
     * @param matrix матриця для перевірки
     * @throws IllegalArgumentException якщо умови не виконуються
     */
    private static void validateSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Матриця не може бути порожньою");
        }
        final int order = matrix.length;
        boolean square = Arrays.stream(matrix).allMatch(row -> row.length == order);
        if (!square) {
            throw new IllegalArgumentException("Матриця має бути квадратною (N x N)");
        }
    }
}