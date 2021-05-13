package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.BINOP;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeNOT extends Stree {

	private Exp exp;
	private Type type;

	public StreeNOT(Stree left) throws StreeException, TypeException {
		super(left);
		this.exp = new BINOP(BINOP.Code.NOT, left.getExp(), null);
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
		type = new TypeExpression(Tag.BOOLEAN);
		if (typeLeft != null)
			return typeLeft.assertBoolean();
		else
			throw new StreeException("Type error while checking null types ! :StreeNOT");
	}

}
