import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {

    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> saq = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        ArrayDequeSolution<String> methods = new ArrayDequeSolution<>();

        while (true) {
            double uniform = StdRandom.uniform();
            int number = StdRandom.uniform(10);

            if (uniform < 0.25) {
                saq.addFirst(number);
                ads.addFirst(number);
                methods.addLast("addFirst(" + number + ")\n");
            } else if (uniform < 0.5) {
                saq.addLast(number);
                ads.addLast(number);
                methods.addLast("addLast(" + number + ")\n");
            } else if (uniform < 0.75) {
                if (!saq.isEmpty() && !ads.isEmpty()) {
                    methods.addLast("removeFirst()\n");
                    assertEquals(getMethodSequence(methods), ads.removeFirst(), saq.removeFirst());
                }
            } else {
                if (!saq.isEmpty() && !ads.isEmpty()) {
                    methods.addLast("removeLast()\n");
                    assertEquals(getMethodSequence(methods), ads.removeFirst(), saq.removeFirst());
                }
            }
        }

    }
    private String getMethodSequence(ArrayDequeSolution<String> deque){
        StringBuilder builder = new StringBuilder();
        int size = deque.size();
        // while (!deque.isEmpty()) {
        //     builder.append(deque.removeFirst());
        // }
        for (int i = 0; i < size; i++) {
            builder.append(deque.get(i));
        }
        return builder.toString();
    }
}
