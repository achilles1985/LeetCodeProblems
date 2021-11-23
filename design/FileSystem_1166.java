package design;

import java.util.HashMap;
import java.util.Map;

/**M
 * You are asked to design a file system which provides two functions:
 *     createPath(path, value): Creates a new path and associates a value to it if possible and returns True. Returns
 *     False if the path already exists or its parent path doesn't exist.
 *     get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English
 * letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 *
 * Implement the two functions.
 * Please refer to the examples for clarifications.
 *
 * Example 2:
 * Input:
 * ["FileSystem","createPath","createPath","get","createPath","get"]
 * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 * Output:
 * [null,true,true,2,false,-1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/leet", 1); // return true
 * fileSystem.createPath("/leet/code", 2); // return true
 * fileSystem.get("/leet/code"); // return 2
 * fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 * fileSystem.get("/c"); // return -1 because this path doesn't exist.
 *
 * Constraints:
 *     The number of calls to the two functions is less than or equal to 10^4 in total.
 *     2 <= path.length <= 100
 *     1 <= value <= 10^9
 */
public class FileSystem_1166 {

    private final Map<String, Integer> paths;

    public FileSystem_1166() {
        paths = new HashMap<>();
        paths.put("", -1);
    }

    // O(1) - time
    public boolean createPath(String path, int value) {
        if (path == null || path.isEmpty()) {
            return false;
        }
        String parent = path.substring(0, path.lastIndexOf("/"));
        if (!paths.containsKey(parent)) {
            return false;
        } else {
          return paths.putIfAbsent(path, value) == null;
        }
    }

    public int get(String path) {
        return paths.getOrDefault(path, -1);
    }

    public static void main(String[] args) {
        FileSystem_1166 fileSystem = new FileSystem_1166();

        System.out.println(fileSystem.createPath("/leet", 1)); // true
        System.out.println(fileSystem.createPath("/leet/code", 2)); // true
        System.out.println(fileSystem.get("/leet/code")); // 2
        System.out.println(fileSystem.createPath("/leet/code", 3)); // false
        System.out.println(fileSystem.get("/leet/code")); // 2
        System.out.println(fileSystem.createPath("/c/d", 1)); // false because the parent path "/c" doesn't exist.
        fileSystem.get("/c"); //-1 because this path doesn't exist.
    }
}
