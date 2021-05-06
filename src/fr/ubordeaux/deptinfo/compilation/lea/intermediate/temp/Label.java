package fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp;

import java.util.Objects;

/**
 * A Label represents an address in assembly language.
 */

public class Label {
	private String name;
	private static int count;

	/**
	 * Makes a new label that prints as "name". Warning: avoid repeated calls to
	 * <tt>new Label(s)</tt> with the same name <tt>s</tt>.
	 */
	public Label(String n) {
		name = n;
	}

	/**
	 * Makes a new label with an arbitrary name.
	 */
	public Label() {
		this("L" + count++);
	}

	public int getCount() {
		return(count);
	}

	@Override
	public String toString() {
		return name;
	}

	public void toDot(StringBuffer str) {
		str.append("Label_" + name + " [shape=\"ellipse\", label=\""+ name +"\"];\n");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Label label = (Label) o;
		return Objects.equals(name, label.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
