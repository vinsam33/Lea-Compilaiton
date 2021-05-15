package fr.ubordeaux.deptinfo.compilation.lea.intermediate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

abstract public class CASE {
	private int id;
	private static int uniqId = 0;

	private Stm stm;
	private Exp exp;

	public CASE() {
		id = uniqId++;
	}

	public int getId() {
		return id;
	}

	public Stm getStm() {
		return stm;
	}

	public Exp getExp() {
		return exp;
	}

	public void toDot(StringBuffer str) {
		str.append("CASE_ " + id + " [shape=\"ellipse\", label=\""+ getDotLabel() +"\"];\n");
	}

	public String getDotLabel() {
		return this.getClass().getSimpleName();
	}
	
	public void toDotFile(String file) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write("digraph Stree {\n");
			StringBuffer str = new StringBuffer();
			toDot(str);
			out.write(str.toString());
			out.write("}\n");
			out.close();
		} catch (IOException e) {
			System.err.println("ERROR: build dot");
		}
	}

}
