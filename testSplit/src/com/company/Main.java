package com.company;

public class Main {

	public static void main(String[] args) {
		//String test = "Hello / user 2";
		//String[] nick = test.split("/");
		//System.out.println(test.contains("Hell /"));
		//System.out.println(nick[1]);
		System.out.println(spinWords("Hey fellow warriors"));
		System.out.println(spinWords( "This is a test") );
		System.out.println( spinWords( "This is a test[]" ));
		//expected:<This is a test[]> but was:<This is a test[ ]>


	}

	public static String spinWords(String sentence) {
		String spinSentence = "";
		if (sentence.contains(" ")) {
			String[] array = sentence.split(" ");
			for (int i = 0; i < array.length; i++) {
				if (array[i].length() >= 5) {

					array[i] = new StringBuffer(array[i]).reverse().toString();
					if (i < array.length - 1) {
						spinSentence += array[i] + " ";
					} else spinSentence += array[i];
				} else {
					if (i < array.length - 1) {
						spinSentence += array[i] + " ";
					} else spinSentence += array[i];

				}
			}
			return spinSentence;
		} else {
			if (sentence.length() >= 5) {
				return new StringBuffer(sentence).reverse().toString();
			} else return sentence;
		}
	}
}
/*
public static String spinWords(String sentence) {
		String spinWord = "";
		if (sentence.contains(" ")) {
			String[] array = sentence.split(" ");
			for (int i = 0; i < array.length;i++) {
				if (array[i].length() >= 5) {
					array[i] = new StringBuffer(array[i]).reverse().toString();

				}
				if(i < array.length - 1) {
					spinWord = (new StringBuilder()).append(array[i]).append(" ").toString();
				}else spinWord = (new StringBuilder()).append(array[i]).toString();
			}
			return spinWord;

		} else {
			return new StringBuffer(sentence).reverse().toString();
		}
 */
/*
works but fail with []

	public static String spinWords(String sentence) {
		String spinSentence = "";
		if (sentence.contains(" ")) {
			String[] array = sentence.split(" ");
			for (int i = 0; i < array.length; i++) {
				if (array[i].length() >= 5) {
					array[i] = new StringBuffer(array[i]).reverse().toString();
					if (i < array.length - 1) {
						spinSentence += array[i] + " ";
					} else spinSentence += array[i];
				} else {
					if (i < array.length) {
						spinSentence += array[i] + " ";
					} else spinSentence += array[i];

				}
			}
			return spinSentence;
		} else {
			if (sentence.length() >= 5) {
				return new StringBuffer(sentence).reverse().toString();
			} else return sentence;
		}
	}
 */
