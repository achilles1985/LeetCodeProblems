package trie;

import java.util.*;

/** M [marked]
 * You are given an array of strings products and a string searchWord.
 * Design a system that suggests at most three product names from products after each character of searchWord is typed.
 * Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix
 * return the three lexicographically minimums products.
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 *
 * Example 1:
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 *
 * Example 2:
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 *
 * Example 3:
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 *
 * Constraints:
 *     1 <= products.length <= 1000
 *     1 <= products[i].length <= 3000
 *     1 <= sum(products[i].length) <= 2 * 104
 *     All the strings of products are unique.
 *     products[i] consists of lowercase English letters.
 *     1 <= searchWord.length <= 1000
 *     searchWord consists of lowercase English letters.
 */
public class SearchSuggestionsSystem_1268 {

    public static void main(String[] args) {
        SearchSuggestionsSystem_1268 s = new SearchSuggestionsSystem_1268();
        System.out.println(s.suggestedProducts(new String[]{"mobile","mouse","moneypot","monitor","mousepad"}, "mouse"));
        //[["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
    }
    // O(prefix^2) - time, O(n) - space, n - number of nodes in a trie
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();
        for (String product: products) { // O(products*length)
            trie.add(product);
        }

        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) { // O(prefix + )
            String sub = searchWord.substring(0, i+1);
            List<String> words = trie.search(sub);
            res.add(words);
        }

        return res;
    }

    private static class Trie {
        TrieNode root = new TrieNode();

        void add(String word) {
            TrieNode curr = root;
            for (char c: word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
            }
            curr.isWord = true;
        }

        List<String> search(String prefix) {
            TrieNode curr = root;
            for (char c: prefix.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    return Collections.emptyList();
                }
                curr = curr.children.get(c);
            }

            return dfs(curr, prefix);
        }

        List<String> dfs(TrieNode node, String prefix) {
            List<String> res = new ArrayList<>();
            helper(node, prefix, res);

            return res;
        }

        void helper(TrieNode node, String word, List<String> res) {
            if (res.size() == 3) {
                return;
            }
            if (node.isWord) {
                res.add(word);
                //return; explore deeper, since m-o-u-s-e(true)-p-a-d(true)
            }
            for (int i = 0; i < 26; i++) {
                char c = (char)(i + 'a');
                if (node.children.containsKey(c)) {
                    helper(node.children.get(c), word + c, res);
                }
            }
/*            for (Map.Entry<Character, TrieNode> child: node.children.entrySet()) { // not correct because we need 'lexicographically minimums products'
                helper(child.getValue(), word + child.getKey(), res);
            }*/
        }
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord;
    }
}
