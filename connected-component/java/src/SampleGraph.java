import java.util.ArrayList;
import java.util.List;

/**
 * Created by luwei on 8/3/14.
 */
public class SampleGraph {
    public static class Pair<T> {
        public T first;
        public T second;

        public Pair(T f, T s) {
            first = f;
            second = s;
        }
    }

    public static class IntPair extends Pair<Integer> {
        public IntPair(int f, int s) {
            super(f, s);
        }
    }

    public  List<IntPair> g = new ArrayList<IntPair>();

    public SampleGraph() {
        g.add(new IntPair(1, 6));
        g.add(new IntPair(6, 7));
        g.add(new IntPair(7, 2));
        g.add(new IntPair(1, 2));

        g.add(new IntPair(3, 8));
        g.add(new IntPair(4, 9));
        g.add(new IntPair(9, 8));
        g.add(new IntPair(8, 12));
        g.add(new IntPair(9, 13));
        g.add(new IntPair(12, 15));
        g.add(new IntPair(11, 15));
        g.add(new IntPair(15, 16));
        g.add(new IntPair(23, 16));
        g.add(new IntPair(22, 15));
        g.add(new IntPair(21, 15));
        g.add(new IntPair(21, 24));
        g.add(new IntPair(23, 29));
        g.add(new IntPair(24, 25));
        g.add(new IntPair(24, 26));
        g.add(new IntPair(26, 27));
        g.add(new IntPair(26, 28));
        g.add(new IntPair(28, 30));
        g.add(new IntPair(30, 29));
        g.add(new IntPair(30, 31));
        g.add(new IntPair(31, 32));
        g.add(new IntPair(31, 33));

        g.add(new IntPair(5, 10));
        g.add(new IntPair(10, 14));
        g.add(new IntPair(14, 17));
        g.add(new IntPair(18, 17));
        g.add(new IntPair(19, 17));
        g.add(new IntPair(18, 19));
        g.add(new IntPair(18, 20));
        g.add(new IntPair(19, 34));
        g.add(new IntPair(34, 35));
        g.add(new IntPair(34, 36));



    }


}
