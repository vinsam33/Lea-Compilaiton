package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.BINOP;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeMINUS extends Stree {

	private Exp exp;
	private Type type;

	public StreeMINUS(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		this.exp = new BINOP(BINOP.Code.MINUS, left.getExp(), right.getExp());
	}

	public StreeMINUS(Stree left) throws StreeException, TypeException {
		super(left);
		this.exp = new BINOP(BINOP.Code.MINUS, left.getExp(), null);
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
		type = typeLeft;
		if ((typeLeft != null) && (typeRight != null))
			return type.assertEqual(typeRight) && (type.assertEqual(new TypeExpression(Tag.INTEGER)) || type.assertEqual(new TypeExpression(Tag.FLOAT)));
		else if ((typeLeft != null) && (typeRight == null))
			return type.assertEqual(new TypeExpression(Tag.INTEGER)) || type.assertEqual(new TypeExpression(Tag.FLOAT));
		else
			throw new StreeException("Type error while checking null types ! :StreeMINUS");
	}

}
