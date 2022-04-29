package com.phoenix;
public class App{
	public static void main(String[] args){
		private Handler m = new Handler();
		private Result validate=Result.UNKNOWN;
		private Result compile=Result.UNKNOWN;
		private Result test=Result.UNKNOWN;
		private Result pack=Result.UNKNOWN;
		private Result verify=Result.UNKNOWN;
		private Result site=Result.UNKNOWN;
		private String shields = "";

		Process p = new ProcessBuilder("mvn","validate").directory(new File(args[0]).start());
		System.out.print("Step 1 - validate... ");
		while (p.isAlive()){;}
		
