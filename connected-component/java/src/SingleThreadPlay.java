import java.util.*;

/**
 * Created by luwei on 8/3/14.
 */
public class SingleThreadPlay {
    Map<Integer, List<Integer>> mapper = new HashMap<Integer, List<Integer>>();
    List<SampleGraph.IntPair> reducer = new ArrayList<SampleGraph.IntPair>();
    int counter = 0;

    public Set<Integer> cc(List<SampleGraph.IntPair> pairs) {
        while (true) {
            // init
            mapper.clear();
            reducer.clear();
            counter = 0;
            map(pairs);
            System.out.print("Mapper: ");
            print(mapper);
            reduce();
            System.out.print("Reducer: ");
            print(reducer);
            pairs = copy(reducer);
            System.out.println("----");
            if (counter == 0) {
                break;
            }
        }

        // return cc center
        Set<Integer> c = new HashSet<Integer>();
        for (SampleGraph.IntPair p : reducer) {
            c.add(p.first);
        }
        return c;
    }

    private List<SampleGraph.IntPair> copy(List<SampleGraph.IntPair> reducer) {
        List<SampleGraph.IntPair> cp = new ArrayList<SampleGraph.IntPair>();
        for ( SampleGraph.IntPair p : reducer) {
            cp.add(new SampleGraph.IntPair(p.first, p.second));
        }
        return cp;
    }

    private void print(List<SampleGraph.IntPair> reducer) {
        String s = "";
        for (SampleGraph.IntPair p : reducer) {
            s += p.first + " -> " + p.second + ", ";
        }
        System.out.println(s);
    }

    public static void main(String args[]) {
        SingleThreadPlay p = new SingleThreadPlay();
        Set<Integer> c = p.cc(new SampleGraph().g);
        System.out.println(c);
    }

    private void reduce() {

        for (Map.Entry<Integer, List<Integer>> m : mapper.entrySet()) {
            Integer key = m.getKey();
            List<Integer> vs0 = m.getValue();
            Set<Integer> vs = new HashSet<Integer>(vs0);
            Integer min = Integer.MAX_VALUE;
            Integer max = Integer.MIN_VALUE;
            for (Integer ti : vs) {
                if (ti < min) {
                    min = ti;
                }
                if (ti > max) {
                    max = ti;
                }
            }
            if (min > key) {
                for (Integer ti : vs) {
                    emit(key, ti);
                }
            }
            else if (key > max) {
                // skip
            }
            else {
                counter++;
                emit(min, key);
                for (Integer ti : vs) {
                    if (ti != min) {
                        emit(min, ti);
                    }
                }
            }
        }
    }

    private void emit(Integer min, Integer ti) {
        reducer.add(new SampleGraph.IntPair(min, ti));
    }


    private void map(List<SampleGraph.IntPair> pairs) {
        // reverse pairs
        List<SampleGraph.IntPair> revPair = reverse(pairs);
        // merge pairs and revPairs
        List<SampleGraph.IntPair> merge = merge(pairs, revPair);
        // map
        for (SampleGraph.IntPair p : merge) {
            addKV(mapper, p.first, p.second);
        }
    }


    private void print(Map<Integer, List<Integer>> mapper) {
        for (Map.Entry<Integer, List<Integer>> m : mapper.entrySet()) {
            String s = m.getKey() + " -> ";
            for (Integer v : m.getValue()) {
                s += v + ", ";
            }
            System.out.println(s);
        }
    }

    private void addKV(Map<Integer, List<Integer>> mapper, Integer first, Integer second) {
        List<Integer> vs = mapper.get(first);
        if (vs== null) {
            vs = new ArrayList<Integer>();
            mapper.put(first, vs);
        }
        vs.add(second);
    }

    private List<SampleGraph.IntPair> merge(List<SampleGraph.IntPair> pairs, List<SampleGraph.IntPair> revPair) {
        List<SampleGraph.IntPair> merge = new ArrayList<SampleGraph.IntPair>();
        for(SampleGraph.IntPair p : pairs) {
            merge.add(p);
        }
        for(SampleGraph.IntPair p : revPair) {
            merge.add(p);
        }
        return merge;
    }

    private List<SampleGraph.IntPair> reverse(List<SampleGraph.IntPair> pairs) {
        List<SampleGraph.IntPair> revPair = new ArrayList<SampleGraph.IntPair>();
        for (SampleGraph.IntPair p : pairs) {
            revPair.add(new SampleGraph.IntPair(p.second, p.first));
        }
        return revPair;
    }
}
