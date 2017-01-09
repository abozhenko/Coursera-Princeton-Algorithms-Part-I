import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Andrew on 1/9/2017.
 */
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int size = Integer.parseInt(args[0]);
//        int shuffledItems = 0;

        if (size > 0) {
            while (!StdIn.isEmpty()) {
                String str = StdIn.readString();
                rq.enqueue(str);
//                if (shuffledItems < size) {
//                    rq.enqueue(str);
//                    shuffledItems++;
//                } else {
//                    rq.dequeue();
//                    rq.enqueue(str);
//                }
            }

            for (int i = 0; i < size; i++) {
                StdOut.println(rq.dequeue());
            }
        }
    }
}
