package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.BINOP;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreePLUS extends Stree {

	private BINOP exp;
	private Type type;

	public StreePLUS(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		this.exp = new BINOP(BINOP.Code.PLUS, left.getExp(), right.getExp());
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
		type = typeLeft;

		if ((typeLeft != null) && (typeRight != null)) {
			if (typeLeft.assertEqual(new TypeExpression(Tag.STRING))) {
				return true;
			} else if (typeRight.assertEqual(new TypeExpression(Tag.STRING))) {
				type = typeRight;
				return true;
			}

			return typeLeft.assertEqual(typeRight) && (typeLeft.assertEqual(new TypeExpression(Tag.INTEGER))
					|| typeLeft.assertEqual(new TypeExpression(Tag.FLOAT)));
		} else
			throw new StreeException("Type error while checking null types !  :StreePLUS");
	}

}
