package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.CONST;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;


public class StreeRANGE extends Stree {

	private Integer value;
	private Exp exp;
	private Type type;


	public StreeRANGE(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.exp = new CONST(value);
		this.type = new TypeExpression(Tag.INTEGER);
	}

	@Override
	public String toString() {
		return "StreeRANGE [value=" + value + "]";
	}

	@Override
	public Exp getExp(){
		return exp;
	}
	@Override
	public Type getType() throws StreeException {
		return type;
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		Type typeRight = getRight().getType();
		if((typeLeft != null) && (typeRight != null )){
			return typeLeft.assertEqual(typeRight);
		}
		else
			throw new StreeException("Type error while checking null types !");
	}

}
