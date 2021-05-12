package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;

public class StreeFLOAT extends Stree {

	private Float value;
	private Exp exp;
	private Type type;

	public StreeFLOAT(Float value) throws TypeException, StreeException {
		super();
		this.value = value;
		this.type = new TypeExpression(Tag.FLOAT);
	}

	@Override
	public String toString() {
		return "StreeFLOAT  [value=" + value + "]";
	}

	public Type getType() throws StreeException {
		return type;
	}
	@Override
	public Exp getExp(){
		return exp;
	}

	@Override
	public boolean checkType(){
		return true;
	}

}
