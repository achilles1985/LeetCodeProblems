package unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * M
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a
 * name, and the rest of the elements are emails representing emails of the account.
 * <p>
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some
 * email that is common to both accounts. Note that even if two accounts have the same name, they may belong to
 * different people as people could have the same name. A person can have any number of accounts initially, but all
 * of their accounts definitely have the same name.
 * <p>
 * After merging the accounts, return the accounts in the following format: the first element of each account is the
 * name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John",
 * "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail
 * .com"], ["Mary", "mary@mail.com"]]
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John',
 * 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * <p>
 * Note:
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 */
/*
    Tips: Treat each account as a separate set. While traversing through accounts, union sets if there are the same email on both of them.
 */
public class AccountsMerge_721 {

    public static void main(String[] args) {
        AccountsMerge_721 s = new AccountsMerge_721();
        System.out.println(s.accountsMerge(Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com", "john1@mail.com"),
                Arrays.asList("Mary", "mary@mail.com")))); //[["John", 'john00@mail.com', 'john_newyork@mail.com',
        // 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
    }

    // O(n*m) - time, n - number of accounts, m - number of emails
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.isEmpty()) {
            return Collections.emptyList();
        }
        Map<String, Integer> emailToId = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int count = 0;
        DisjointSet ds = new DisjointSet();
        for (int i = 0; i < accounts.size(); i++) {
            String name = accounts.get(i).get(0);
            for (int j = 1; j < accounts.get(i).size(); j++) {
                if (!emailToId.containsKey(accounts.get(i).get(j))) {
                    emailToId.put(accounts.get(i).get(j), count++);
                }
                emailToName.put(accounts.get(i).get(j), name);
                ds.union(emailToId.get(accounts.get(i).get(1)), emailToId.get(accounts.get(i).get(j)));
            }
        }

        List<List<String>> result = new ArrayList<>();
        Map<Integer, List<String>> idToEmails = new HashMap<>();
        for (String email: emailToId.keySet()) {
            int id = ds.find(emailToId.get(email));
            idToEmails.computeIfAbsent(id, k -> idToEmails.getOrDefault(k, new ArrayList<>())).add(email);
        }
        for (Map.Entry<Integer, List<String>> entry: idToEmails.entrySet()) {
            Collections.sort(entry.getValue());
            entry.getValue().add(0, emailToName.get(entry.getValue().get(0)));
            result.add(entry.getValue());
        }

        return result;
    }

    private static final class DisjointSet {
        int[] parent;

        public DisjointSet() {
            int size = 10001;
            parent = new int[size];
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
