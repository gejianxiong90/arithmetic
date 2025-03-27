package leetcode;

/**
 *
 * 1328. 破坏回文串
 尝试过
 中等
 相关标签
 相关企业
 提示
 给你一个由小写英文字母组成的回文字符串 palindrome ，请你将其中 一个 字符用任意小写英文字母替换，使得结果字符串的 字典序最小 ，且 不是 回文串。

 请你返回结果字符串。如果无法做到，则返回一个 空串 。

 如果两个字符串长度相同，那么字符串 a 字典序比字符串 b 小可以这样定义：在 a 和 b 出现不同的第一个位置上，字符串 a 中的字符严格小于 b 中的对应字符。例如，"abcc” 字典序比 "abcd" 小，因为不同的第一个位置是在第四个字符，显然 'c' 比 'd' 小。


 示例 1：

 输入：palindrome = "abccba"
 输出："aaccba"
 解释：存在多种方法可以使 "abccba" 不是回文，例如 "zbccba", "aaccba", 和 "abacba" 。
 在所有方法中，"aaccba" 的字典序最小。
 示例 2：

 输入：palindrome = "a"
 输出：""
 解释：不存在替换一个字符使 "a" 变成非回文的方法，所以返回空字符串。


 提示：

 1 <= palindrome.length <= 1000
 palindrome 只包含小写英文字母。
 */
public class breakPalindrome_1328 {


    /**
     * 方法一：贪心
     令 palindrome 的长度为 n，如果 n=1，那么无论怎么替换，它都是回文串，返回空字符串。对于 n>1，我们将 palindrome 分成两段，遍历前半段，如果某个字符不等于 ‘a’，那么将它替换成 ‘a’，得到的就是结果字符串。如果前半段的所有字符都等于 ‘a’，根据回文串的特性，后半段的所有字符都等于 ‘a’，将最后一个字符替换成 ‘b’，得到的就是结果字符串。

     作者：力扣官方题解
     链接：https://leetcode.cn/problems/break-a-palindrome/solutions/3081912/po-pi-hui-wen-chuan-by-leetcode-solution-neft/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param palindrome
     * @return
     */
    public static String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n == 1) {
            return "";
        }
        char[] data = palindrome.toCharArray();
        for (int i = 0; i * 2 + 1 < n; i++) {
            if (data[i] != 'a') {
                data[i] = 'a';
                return new String(data);
            }
        }
        data[n - 1] = 'b';
        return new String(data);
    }

    public static void main(String[] args) {
        String aba = breakPalindrome("aba");
    }
}
