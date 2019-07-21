package com.antenehnegash;

import java.util.ArrayList;

import java.util.List;

public class Calculator {

	public static void main(String[] args) {

		// System.out.println(add("12343264364", "253634645"));

		String number1 = "2364";

		String number2 = "253634646";

		//

		System.out.println(number1);

		System.out.println(number2);

		//

//		System.out.println(Long.parseLong(number2) / Long.parseLong(number1));

		System.out.println("Add: " + add(number2, number1));
		System.out.println("Subtract: " + subtract(number2, number1));
		System.out.println("Multiply: " + multiply(number2, number1));
		System.out.println("Divide: " + divide(number2, number1));

		// System.out.println(add("1", "9"));

	}

	public static String add(String n1, String n2) {

		String biggerNumber;

		String smallerNumber;

		if (n1.length() >= n2.length()) {

			biggerNumber = n1;

			smallerNumber = n2;

		} else {

			biggerNumber = n2;

			smallerNumber = n1;

		}

		String sum = "";

		int extra = 0;

		smallerNumber = prependZeros(smallerNumber, Math.abs(n1.length() - n2.length()));

		for (int i = biggerNumber.length() - 1; i >= 0; i--) {

			int s = Character.getNumericValue(biggerNumber.charAt(i))

					+ Character.getNumericValue(smallerNumber.charAt(i)) + extra;

			if (i == 0) {

				sum = s + sum;

			} else {

				sum = s % 10 + sum;

				extra = s / 10;

			}

		}

		return sum;

	}

	public static String multiply(String n1, String n2) {

		List<String> lineResults = new ArrayList<String>();

		for (int i = n1.length() - 1; i >= 0; i--) {

			String lineResult = "";

			int carry = 0;

			for (int j = n2.length() - 1; j >= 0; j--) {

				int x = Character.getNumericValue(n1.charAt(i));

				int y = Character.getNumericValue(n2.charAt(j));

				int r = x * y + carry;

				if (j == 0) {

					lineResult = r + lineResult;

				} else {

					lineResult = (int) (r % 10) + lineResult;

					carry = r / 10;

				}

			}

			lineResults.add(appendZeros(lineResult, n1.length() - i - 1));

		}

		String finalResult = "0";

		for (String lineResult : lineResults) {

			finalResult = add(finalResult, lineResult);

		}

		return finalResult;

	}

	public static String subtract(String n1, String n2) {

		String largerNumber;

		String smallerNumber;

		boolean negative;

		if (compare(n1, n2) == 0) {

			return "0";

		} else if (compare(n1, n2) == 1) {

			smallerNumber = prependZeros(n1, n2.length() - n1.length());

			largerNumber = n2;

			negative = true;

		} else {

			smallerNumber = prependZeros(n2, n1.length() - n2.length());

			largerNumber = n1;

			negative = false;

		}

		String result = "";

		int borrowed = 0;

		for (int i = largerNumber.length() - 1; i >= 0; i--) {

			int x = Character.getNumericValue(largerNumber.charAt(i)) + borrowed;

			int y = Character.getNumericValue(smallerNumber.charAt(i));

			borrowed = 0;

			if (x < y) {

				if (x != 0) {

					x += 10;

					borrowed--;

				} else {

					x = 9;

					borrowed--;

				}

			}

			result = x - y + result;

		}

		return negative ? "-" + "" + result : result;

	}

	public static String divide(String n1, String n2) {

		String i = "1";

		String result = "1";

		System.out.println("compare(n1, i)" + compare(n1, i));

		while (2 == compare(n1, i)) {

			String multiplyResult = multiply(i, n2);

			if (compare(n1, multiplyResult) == 0) {

				result = i;

				break;

			} else if (compare(n1, multiplyResult) == 1) {

				break;

			} else {

				result = i;

				i = add(i, "1");

			}

		}

		return result;

	}

	/**
	 * 
	 * @param number1
	 * 
	 * @param number2
	 * 
	 * @return 1 if number 1 is smaller, and 2 if number 2 is smaller, and 0 if both
	 * 
	 *         are equal
	 * 
	 */

	public static int compare(String number1, String number2) {

		if (number1.length() < number2.length()) {

			return 1;

		} else if (number1.length() > number2.length()) {

			return 2;

		} else {

			for (int i = 0; i < number1.length(); i++) {

				if (Character.getNumericValue(number1.charAt(i)) < Character.getNumericValue(number2.charAt(i))) {

					return 1;

				} else if (Character.getNumericValue(number1.charAt(i)) > Character

						.getNumericValue(number2.charAt(i))) {

					return 2;

				}

			}

			return 0;

		}

	}

	static String prependZeros(String smallerNumber, int difference) {

		String zeros = "";

		for (int i = 0; i < difference; i++) {

			zeros += "0";

		}

		return zeros + smallerNumber;

	}

	static String appendZeros(String number, int count) {

		String zeros = "";

		for (int i = 0; i < count; i++) {

			zeros += "0";

		}

		return number + zeros;

	}

}