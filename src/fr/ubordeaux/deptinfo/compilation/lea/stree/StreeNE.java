package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.BINOP;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeNE extends Stree {

	private BINOP exp;
	private Type type;

	public StreeNE(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		this.exp = new BINOP(BINOP.Code.NE, left.getExp(), right.getExp());
	}

	@Override
	public Exp getExp() {
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
		type = new TypeExpression(Tag.BOOLEAN);
		if ((typeLeft != null) && (typeRight != null) && ((typeLeft.getTag() == Tag.INTEGER)
				|| (typeLeft.getTag() == Tag.FLOAT) || (typeLeft.getTag() == Tag.STRING)))
			return typeLeft.assertEqual(typeRight);
		else
			throw new StreeException("Type error while checking null types !  :StreeNE");
	}

}
