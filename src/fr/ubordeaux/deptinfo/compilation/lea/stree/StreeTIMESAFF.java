package fr.ubordeaux.deptinfo.compilation.lea.stree;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.MOVE;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeTIMESAFF extends Stree {
	private Type type;
	private Stm stm;
	private Exp exp;

	public StreeTIMESAFF(Stree left, Stree right) throws TypeException, StreeException {
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
		type = typeLeft;
		if((typeLeft != null) && (typeRight != null )){
			
			return typeLeft.assertEqual(typeRight) 
				&& (typeLeft.assertEqual(new TypeExpression(Tag.INTEGER)) || typeLeft.assertEqual(new TypeExpression(Tag.FLOAT)));
		}
		else
			throw new StreeException("Type error while checking null types !");
	}

}
