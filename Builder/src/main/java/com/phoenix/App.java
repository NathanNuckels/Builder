package com.phoenix;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App{
	private static Result check(Process p){
		Scanner s = new Scanner(p.getInputStream());
		StringBuilder r = new StringBuilder();
		while(s.hasNextLine()){
			r.append(s.nextLine());
		}
		if (r.toString().contains("BUILD SUCCESS")){
			return Result.PASSED;
		} else if (r.toString().contains("BUILD FAILURE")){
			return Result.FAILED;
		} else {
			return Result.UNKNOWN;
		}
	}

	public static void main(String[] args) throws IOException {
		ArrayList<String> cmd = new ArrayList<>();
		String dir = args[0];
		cmd.add("compile");
		cmd.add("test");
		cmd.add("package");
		cmd.add("site");
		ArrayList<Result> results = new ArrayList<>();
		Process p;
		for (int i=0;i<cmd.size();i++) {
			if (new File(dir).exists()) {
				ProcessBuilder mvn = new ProcessBuilder("mvn", cmd.get(i));
				mvn.directory(new File(dir));
				p = mvn.start();
				while (p.isAlive()) {
				} //wait for process to end
				results.add(check(p));
				if (check(p) == Result.FAILED) {
					break;
				}
			} else {
				System.out.print(dir);
				System.out.println("Does not exist.");
			}
		}
		StringBuilder out= new StringBuilder();
		for (int i=0;i<results.size();i++){
			out.append("![").append(cmd.get(i).substring(0, 1).toUpperCase()).append(cmd.get(i).substring(1)).append("](https://img.shields.io/badge/").append(cmd.get(i).substring(0, 1).toUpperCase()).append(cmd.get(i).substring(1)).append("-");
			switch (results.get(i)){
				case PASSED:
					out.append("OK-brightgreen)\n\n");
					break;
				case FAILED:
					out.append("FAILED-red)\n\n");
					break;
				case UNKNOWN:
					out.append("Unknown-lightgrey)\n\n");
			}
		}
		System.out.println(out);
	}
}
