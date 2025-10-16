import java.beans.VetoableChangeListenerProxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsExample {
    public static void main(String[] args) {
        // List<int> -- will not work
        List<Integer> ints = List.of(10, 20, 30);

        Integer a = 10;  // implicitly java will add: ... = Integer.valueOf(10);
        Integer b = 20;
        Integer c = 30;

        // Map<String, MutVector> vectors = new HashMap<>();
        //
        // vectors.put("v1", new MutVector(10, 20));
        // vectors.put("v2", new MutVector(50, 60));
        //
        // final MutVector v3 = new MutVector(10, 20);
        // vectors.put("v3", v3);
        // vectors.put("v2", new MutVector(500, 600));
        // vectors.put("v4", null);
        //
        // System.out.println(vectors);
        // System.out.println(vectors.get("v1") == v3);
        // System.out.println(vectors.get("v3") == v3);
        // System.out.println(vectors.remove("v3"));
        // System.out.println(vectors);
        //
        // System.out.println(vectors.get("v3")); // -> null
        // System.out.println(vectors.getOrDefault("v3", new MutVector(0, 0))); // Will work but wrong because we always create new vector
        //
        // // Idiomatic approach:
        // MutVector v = vectors.get("v3");
        // if (v == null) {
        //     v = new MutVector(0, 0);
        //     vectors.put("v3", v);
        // }
        // System.out.println(v);
        //
        // vectors.keySet().remove("v2");
        // System.out.println(vectors.keySet());
        // System.out.println(vectors);
        //
        // System.out.println(vectors.values());
        //
        // final Collection<MutVector> values = vectors.values();
        // System.out.println(values);
        //
        // final Set<Map.Entry<String, MutVector>> entries = vectors.entrySet();
        // System.out.println(entries);
        //
        // for (Map.Entry<String, MutVector> entry : vectors.entrySet()) {
        //     System.out.println(entry.getKey() + " " + entry.getValue());
        // }

        // Set<String> strings = new LinkedHashSet<>(List.of("hello", "world", "a", "b", "c"));

        // Set<String> strings = new HashSet<>();
        //
        // System.out.println(strings.add("a"));
        // System.out.println(strings.add("b"));
        // System.out.println(strings.add("c"));
        // Set<String> second = Set.of("c", "d", "e");
        //
        // // strings.addAll(second);
        // strings.retainAll(second);
        // // strings.removeAll(second);
        //
        // System.out.println(strings);

        // List<String> strings = List.of("hello", "world", "a", "b", "c");
        //
        // System.out.println(strings.indexOf("a"));
        // System.out.println(strings.indexOf("A"));
        
        // String[] array = new String[] {"hello", "a1", "b20"};
        // List<String> strings = Arrays.asList(array);
        // strings.set(1, "world");
        // // strings.add("qqq");
        //
        // System.out.println(strings);
        // System.out.println(Arrays.toString(array));

        // List<String> strings = new LinkedList<>();
        // // LinkedList<String> strings = new LinkedList<>(); // Wrong: use List for and similar for collections
        // strings.add("hello");
        // strings.add("world");
        // strings.add("c");
        // strings.add("a");
        // strings.add("b");
        //
        // System.out.println(strings.add("c"));
        // System.out.println(strings.remove("c"));
        // System.out.println(strings);
        //
        // // for (String str : strings) {
        // //     System.out.println(str + " ");
        // // }
        // // System.out.println();
        //
        // for (Iterator<String> it = strings.iterator(); it.hasNext(); ) {
        //     String str = it.next();
        //     if (str.length() == 1) {
        //         it.remove();
        //     }
        // }
        // System.out.println(strings);

        // for (int i = 0; i < strings.size(); i++) {
        //     System.out.println(strings.get(i));
        // }

        // ArrayList<String> strings = new ArrayList<>();
        // strings.add("hello");
        // strings.add("world");
        // strings.add("c");
        // strings.add("a");
        // strings.add("b");
        //
        // System.out.println(strings.add("c"));
        // System.out.println(strings.remove("c"));
        // System.out.println(strings);
        //
        // // for (String str : strings) {
        // //     System.out.println(str + " ");
        // // }
        // // System.out.println();
        //
        // for (Iterator<String> it = strings.iterator(); it.hasNext(); ) {
        //     String str = it.next();
        //     if (str.length() == 1) {
        //         it.remove();
        //     }
        // }
        // System.out.println(strings);
        //
        // // for (int i = 0; i < strings.size(); i++) {
        // //     System.out.println(strings.get(i));
        // // }
    }
}
