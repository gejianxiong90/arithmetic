import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache<K,V> {


    private int cap;
    private Map<K,V> cache;
    private LinkedList<K> order;


    public LRUCache(int cap){
        this.cap = cap;
        cache = new HashMap<K,V>();
        order = new LinkedList<K>();
    }


    public V get(K key){
        if(!cache.containsKey(key)){
            return null;
        }
        V v = cache.get(key);
        order.remove(key);
        order.addLast(key);
        return v;
    }

    public void put(K key,V value){
        if(cache.containsKey(key)){
            cache.put(key,value);
            order.remove(key);
            order.addLast(key);
        }else {
            if(cache.size() >= cap){
                K k = order.pollFirst();
                cache.remove(k);
            }
            cache.put(key,value);
            order.addLast(key);
        }
    }

    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache<>(3);

        lruCache.put("1", "one");
        lruCache.put("2", "two");
        lruCache.put("3", "three");

        System.out.println(lruCache.get("1")); // 输出: one

        lruCache.put("4", "four");
        System.out.println(lruCache.get("2")); // 输出: null (因为2是最久未使用的)
    }


}
