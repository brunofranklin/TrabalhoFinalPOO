package com.projeto.functions;
import java.util.Scanner;

public class pinput {
	public static String readString(String text) {
		return readString(text, 1, false, 0);
	}
	
	public static String readString(String text, int type) {
		return readString(text, type, false, 0);
	}
	
	public static String readString(String text, boolean notNull) {
		return readString(text, 1, notNull, 0);
	}
	
	public static String readStringf(String text, Object ...args) {
		return readString(text, 2, false, args);
	}
	
	public static String readStringf(String text, int type, Object ...args) {
		return readString(text, 2, false, args);
	}
	
	public static String readString(String text, int type, boolean notNull, Object ...args) {
		String ret = "";
		boolean ver = true;
		
		Scanner input;
		while (ver) {
			print.l(text, type, args);
			input = new Scanner(System.in);
			try {
				ret = input.nextLine();
				if (notNull == false || !ret.isEmpty()) {
					ver = false;
				}
			} catch (Exception e) {
				System.out.println("DIGITE UMA STRING VÁLIDA.");
				print.division();
			}
		}
		
		return ret;
	}
	
	public static int readInt(String text) {
		return readInt(text, 1, 0);
	}
	
	public static int readInt(String text, int type) {
		return readInt(text, type, 0);
	}
	
	public static int readIntf(String text, Object ...args) {
		return readInt(text, 2, args);
	}
	
	public static int readInt(String text, int type, Object ...args) {
		int ret = 0;
		boolean ver = true;
		
		Scanner input;
		while (ver) {
			print.l(text, type, args);
			input = new Scanner(System.in);
			try {
				ret = input.nextInt();
				ver = false;
			} catch (Exception e) {
				System.out.println("DIGITE UM NÚMERO INTEIRO VÁLIDO.");
				print.division();
			}
		}
		
		return ret;
	}

	public static double readDouble(String text) {
		return readDouble(text, 1, 0);
	}
	
	public static double readDouble(String text, int type) {
		return readDouble(text, type, 0);
	}
	
	public static double readDoublef(String text, Object ...args) {
		return readDouble(text, 2, args);
	}
	
	public static double readDouble(String text, int type, Object ...args) {
		double ret = 0.0;
		boolean ver = true;
		
		Scanner input;
		while (ver) {	
			print.l(text, type, args);
			input = new Scanner(System.in);
			try {
				ret = input.nextDouble();
				ver = false;
			} catch (Exception e) {
				System.out.println("DIGITE UM NÚMERO VÁLIDO.");
				print.division();
			}
		}
		
		return ret;
	}
}
