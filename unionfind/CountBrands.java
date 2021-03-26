package unionfind;

import java.util.*;

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

        System.out.println(s.topBrandsDFS(Arrays.asList(
                new Brand(0,null,5),
                new Brand(1,0,4),
                new Brand(2,1,4),
                new Brand(3,null,3),
                new Brand(4,null,3),
                new Brand(5,4,2)), 2)); //[Brand{id=4, parentId=null, count=5}, Brand{id=0, parentId=null, count=13}]
    }

    // O(n*log(k)) - time, O(n) - space, n - number of brands
    public List<Brand> topBrandsDFS(List<Brand> brands, int k) {
        Map<Integer, List<Brand>> graph = new HashMap<>();
        Map<Integer, Brand> idToBrand = new HashMap<>();
        for (Brand brand: brands) { //n
            Integer parent = brand.parentId;
            Integer id = brand.id;
            idToBrand.put(id, brand);
            if (parent != null) {
                graph.computeIfAbsent(parent, key -> new ArrayList<>()).add(brand);
                graph.computeIfAbsent(id, key -> new ArrayList<>()).add(brand);
            } else {
                graph.computeIfAbsent(id, key -> new ArrayList<>()).add(brand);
            }
        }
        Set<Integer> seen = new HashSet<>();
        List<List<Brand>> groups = new ArrayList<>();
        for (Integer vertex: graph.keySet()) { //n
            if (!seen.contains(vertex)) {
                List<Brand> nodes = new ArrayList<>();
                dfs(vertex, nodes, graph, seen, idToBrand);
                if (!nodes.isEmpty()) {
                    groups.add(nodes);
                }
            }
        }
        List<Brand> parents = new ArrayList<>();
        for (List<Brand> group: groups) {
            int count = group.stream().map(Brand::getCount).mapToInt(i -> i).sum();
            Brand parent = group.stream().filter(b -> b.parentId == null).findFirst().get();
            parent.count = count;
            parents.add(parent);
        }
        Queue<Brand> minHeap = new PriorityQueue<>(Comparator.comparing(Brand::getCount));
        for (Brand parent: parents) { //n
            minHeap.add(parent); //log(k)
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        List<Brand> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        return result;
    }

    private void dfs(Integer vertex, List<Brand> nodes, Map<Integer, List<Brand>> graph, Set<Integer> seen, Map<Integer, Brand> idToBrand) {
        if (seen.contains(vertex)) {
            return;
        }
        nodes.add(idToBrand.get(vertex));
        seen.add(vertex);
        for (Brand child: graph.getOrDefault(vertex, Collections.emptyList())) {
            dfs(child.id, nodes, graph, seen, idToBrand);
        }
    }

    // O(n*log(k)) - time, O(n) - space, n - number of brands
    public List<Brand> topBrands(Integer[][] brands, int k) {
        if (k == 0 || brands == null || brands.length == 0) {
            return Collections.emptyList();
        }

        Map<Brand, Integer> brandToIdx = new HashMap<>();
        int count = 0;
        DisjointSet ds = new DisjointSet(brands.length);
        for (Integer[] brand: brands) { //n if UF used with path compression and union-by-rank
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
        Map<Integer, List<Brand>> idToBrands = new HashMap<>();
        for (Brand brand: brandToIdx.keySet()) {
            int idx = ds.find(brandToIdx.get(brand));
            idToBrands.computeIfAbsent(idx, key->idToBrands.getOrDefault(key, new ArrayList<>())).add(brand);
        }
        List<Brand> rootBrands = new ArrayList<>();
        for (Map.Entry<Integer, List<Brand>> entry: idToBrands.entrySet()) {
            int counter = entry.getValue().stream().mapToInt(b->b.count).sum();
            Brand root = entry.getValue().stream().filter(b -> b.parentId == null).findFirst().get();
            root.count = counter;
            rootBrands.add(root);
        }

        Queue<Brand> minHeap = new PriorityQueue<>((b1, b2) -> b1.count - b2.count); //n*log(k)
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

        public int getCount() {
            return count;
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
