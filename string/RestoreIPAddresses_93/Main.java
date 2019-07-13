package string.RestoreIPAddresses_93;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.restoreIpAddresses("1232342341223")); //[] - too large string
        System.out.println(s.restoreIpAddresses("25525511135")); //["255.255.11.135", "255.255.111.35"]
        System.out.println(s.restoreIpAddresses("0000")); //["255.255.11.135", "255.255.111.35"]
    }
}
