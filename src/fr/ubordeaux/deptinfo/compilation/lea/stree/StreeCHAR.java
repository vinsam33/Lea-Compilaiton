package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.CONST;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;


public class StreeCHAR extends Stree {

	private Integer value;
	private Exp exp;
	private Type type;

	public StreeCHAR(Integer value) throws TypeException, StreeException {
		super();
		this.value = value;
		this.exp = new CONST(value);
		this.type = new TypeExpression(Tag.CHAR);
	}

	@Override
	public String toString() {
		return "StreeCHAR [value=" + value + "]";
	}

	@Override
	public Exp getExp(){
		return exp;
	}

	@Override
	public boolean checkType(){
		return true;
	}

	public Type getType() throws StreeException {
		return type;
	}

}
