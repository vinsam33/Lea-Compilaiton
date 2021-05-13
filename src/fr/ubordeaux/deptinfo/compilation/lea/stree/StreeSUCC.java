package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeSUCC extends Stree {

	public static final Boolean RIGHT = false;
	public static final Boolean LEFT = true;
	private Boolean rank;
	private Type type;

	public StreeSUCC(Stree left, Boolean rank) throws TypeException, StreeException {
		super(left);
		this.rank = rank;
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		type = typeLeft;
		if (typeLeft != null){
			return typeLeft.assertEqual(new TypeExpression(Tag.INTEGER)) || typeLeft.assertEqual(new TypeExpression(Tag.FLOAT));
		}
		else{
			throw new StreeException("Type error while checking null types !");
		}
	}

	@Override
	public Type getType() throws StreeException {
		return type;
	}

}
