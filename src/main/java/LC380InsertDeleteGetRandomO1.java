import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LC380InsertDeleteGetRandomO1 {
    /**
     * insert(val): Inserts an item val to the set if not already present.
     * remove(val): Removes an item val from the set if present.
     * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
     *
     */

    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;
    Random random;
    /** Initialize your data structure here. */
    public LC380InsertDeleteGetRandomO1() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        if (index < list.size() - 1) {
            // swap the index with the last element of
            int lastOne = list.get(list.size() - 1);
            list.set(index, lastOne);
            map.put(lastOne, index);
        }
        map.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

    public static void main(String[] args) {
        //Your LC380InsertDeleteGetRandomO1 object will be instantiated and called as such:
        LC380InsertDeleteGetRandomO1 obj = new LC380InsertDeleteGetRandomO1();
        boolean param_1 = obj.insert(1);
        boolean param_2 = obj.insert(2);
        boolean param_3 = obj.insert(3);
        boolean param_4 = obj.remove(4);
        System.out.println(param_1);
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
        int param_5 = obj.getRandom();
        System.out.println(param_5);
    }
}
