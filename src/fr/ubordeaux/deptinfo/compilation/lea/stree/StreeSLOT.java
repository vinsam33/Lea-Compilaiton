package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeSLOT extends Stree {

	private String name;
	private Exp exp;

	public StreeSLOT(Stree left, String name) throws TypeException, StreeException {
		super(left);
		this.name = name;

	}

	@Override
	public Exp getExp() {
		return exp;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + '(' + getLeft().toString() + '.' + name + ')';
	}

	@Override
	public boolean checkType() throws StreeException {
		return true;
	}

}
