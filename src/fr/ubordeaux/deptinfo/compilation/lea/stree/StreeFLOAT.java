package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.NAME;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeFLOAT extends Stree {

	private Float value;
	private Exp exp;
	private static int count;
	private Type type;

	public StreeFLOAT(Float value) throws TypeException, StreeException {
		super();
		this.value = value;
		this.exp = new NAME(new Label("" + value));
		this.count++;
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
	public Exp getExp() {
		return exp;
	}

	@Override
	public boolean checkType() {
		return true;
	}

}
