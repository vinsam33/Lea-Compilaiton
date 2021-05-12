package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeBANDAFF extends Stree {

	private Type type;
	public StreeBANDAFF(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.type = new TypeExpression(Tag.INTEGER);
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
