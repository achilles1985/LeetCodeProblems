package unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

import org.omg.CORBA.INTERNAL;

/**
 * Find top k root brands with the most number of products
 */
/*
Brand(id, parentId, products)
 */
public class CountBrands {

    public static void main(String[] args) {
        CountBrands s = new CountBrands();
        System.out.println(s.topBrands(new Integer[][]{
                        new Integer[]{0,null,5},
                        new Integer[]{1,0,4},
                        new Integer[]{2,1,4},
                        new Integer[]{3,null,3},
                        new Integer[]{4,null,3},
                        new Integer[]{5,4,2}}, 2)); //[Brand{id=4, parentId=null, count=5}, Brand{id=0, parentId=null, count=13}]
    }

    // O(n*log(k)) - time, O(n) - space, n - number of brands
    public List<Brand> topBrands(Integer[][] brands, int k) {
        if (k == 0 || brands == null || brands.length == 0) {
            return Collections.emptyList();
        }

        Map<Brand, Integer> brandToIdx = new HashMap<>();
        int count = 0;
        DisjointSet ds = new DisjointSet(brands.length);
        for (Integer[] brand: brands) {
            Brand b = new Brand(brand[0], brand[1], brand[2]);
            if (!brandToIdx.containsKey(b)) {
                brandToIdx.put(b, count++);
            }
            if (b.parentId == null) {
                ds.union(b.id, b.id);
            } else {
                ds.union(b.parentId, b.id);
            }
        }
        Map<Integer, List<Brand>> idsToBrands = new HashMap<>();
        for (Brand brand: brandToIdx.keySet()) {
            int idx = ds.find(brandToIdx.get(brand));
            idsToBrands.computeIfAbsent(idx, key->idsToBrands.getOrDefault(key, new ArrayList<>())).add(brand);
        }
        List<Brand> rootBrands = new ArrayList<>();
        for (Map.Entry<Integer, List<Brand>> entry: idsToBrands.entrySet()) {
            int counter = entry.getValue().stream().mapToInt(b->b.count).sum();
            Brand root = entry.getValue().stream().filter(b -> b.parentId == null).findFirst().get();
            root.count = counter;
            rootBrands.add(root);
        }

        Queue<Brand> minHeap = new PriorityQueue<>((b1, b2) -> b1.count - b2.count);
        for (Brand b: rootBrands) {
            minHeap.add(b);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }

        List<Brand> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.remove());
        }
        return result;
    }

    private static class Brand {
        int id;
        Integer parentId;
        int count;

        public Brand(int id, Integer parentId, int count) {
            this.id = id;
            this.parentId = parentId;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Brand brand = (Brand) o;
            return id == brand.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Brand{" +
                    "id=" + id +
                    ", parentId=" + parentId +
                    ", count=" + count +
                    '}';
        }
    }

    private static final class DisjointSet {
        int[] parent;

        public DisjointSet(int size) {
            parent = new int[size*2]; // size*2 in case all parentIds are unique. Probably just size would be fine.
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);
            parent[p2] = p1;
        }
    }
}
