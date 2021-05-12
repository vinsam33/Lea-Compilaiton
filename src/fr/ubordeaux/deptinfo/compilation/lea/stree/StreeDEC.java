package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;

public class StreeDEC extends Stree {

	public static final Boolean RIGHT = false;
	public static final Boolean LEFT = true;
	private Boolean rank;
	private Type type;

	public StreeDEC(Stree left, Boolean rank) throws TypeException, StreeException {
		super(left);
		this.rank = rank;
		this.type = new TypeExpression(Tag.INTEGER);
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		type = typeLeft;
		if (typeLeft != null){
			if ((typeLeft.assertEqual(new TypeExpression(Tag.INTEGER)))  || (typeLeft.assertEqual(new TypeExpression(Tag.FLOAT))) )
				return true;
		}
		else{
			throw new StreeException("Type error while checking null types !");
		}
		return false;
	}
}
