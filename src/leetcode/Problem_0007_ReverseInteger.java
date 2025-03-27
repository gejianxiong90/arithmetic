package leetcode;

public class Problem_0007_ReverseInteger {

	public static int reverse(int x) {
		boolean neg = ((x >>> 31) & 1) == 1;
		x = neg ? x : -x;
		int m = Integer.MIN_VALUE / 10;
		int o = Integer.MIN_VALUE % 10;
		int res = 0;
		while (x != 0) {
			if (res < m || (res == m && x % 10 < o)) {
				return 0;
			}
			res = res * 10 + x % 10;
			x /= 10;
		}
		return neg ? res : Math.abs(res);
	}


	public String intToRoman(int num) {

		String[][] roman = {
				{"","I","II","III","IV","V","VI","VII","VIII","IX"},
				{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
				{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
				{"","M","MM","MMM"}};
		StringBuilder sb = new StringBuilder();
		return sb.append(roman[3][(num/1000) % 10]).
				append(roman[2][(num/100) % 10]).
				append(roman[1][(num /10) %10]).
				append(roman[0][num %10]).toString();
	}




}
