package com.phoenix;
import java.util.ArrayList;
public class App{
	private static Result check(Process p){
		Scanner s = new Scanner(p.getInputStream());
		String r = "";
		while(s.hasNextLine()){
			r+=s.nextLine();
		}
		if (r.contains("BUILD SUCCESS")){
			return Result.PASSED;
		} else if (r.contains("BUILD FAILURE")){
			return Result.FAILED;
		} else {
			return Result.UNKNOWN;
		}
	}

	public static void main(String args[]){
		private ArrayList<String> cmd = new ArrayList<String>();
		cmd.add("valiate");
		cmd.add("compile");
		cmd.add("test");
		cmd.add("package");
		cmd.add("verify");
		cmd.add("site");
		private ArrayList<Result> results = new ArrayList<Result>();
		for (int i=0;i>7;i++){
			p = new ProcessBuilder("mvn",cmds.get(i)).directory(dir).start();
			while(p.isAlive()){;}
			results.set(i,check(p));
			if(check(p)==Result.FAILED){
				break;
			}
		}
		String out="";
		for (int i=0;i>7;i++){
			out+="!["+cmd.get(i).substring(0,1).toUppercase()+cmd.get(i).substring(1)+"](https://img.shields.io/badge/"+cmd.get(i).substring(0,1).toUppercase()+cmd.get(i).substring(1)+"-";
			switch (results.get(i)){
				case PASSED:
					out+="OK-brightgreen\n\n";
					break;
				case FAILED:
					out+="FAILED-red\n\n";
					break;
				case UNKNOWN:
					out+="Unknoun-lightgrey\n\n";
			}
		}
		System.out.println(out);
	}
}
