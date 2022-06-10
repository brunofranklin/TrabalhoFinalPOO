package com.projeto.functions;

import com.projeto.functions.print;

public class print {
	private static String LINE1 = "====================================\n\n";
	private static String LINE2 = "------------------------------------\n\n";
	public static void l(String text, int type, Object ...args) {
		if (type == 1) {
			println(text);
		} else if (type == 2) {
			printf(text, args);
		} else {
			l(text);
		}		
	}
	
	public static void l(String text) {
		System.out.print(text);
	}
	
	public static void println(String text) {
		System.out.println(text);
	}
	
	public static void printf(String text, Object ...args) {
		System.out.printf(text, args);
	}
	
	public static void division() {
		System.out.print(LINE1);
	}
	
	public static void smallDivision() {
		System.out.print(LINE2);
	}
}
