package fr.ubordeaux.deptinfo.compilation.lea.stree;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.MOVE;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeDIVAFF extends Stree {


	private Type type;
	private Stm stm;
	private Exp exp;

	public StreeDIVAFF(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		exp = new StreeDIV(getLeft(), getRight()).getExp();
		this.type = new TypeExpression(Tag.INTEGER);
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException{
		return new MOVE(getLeft().getExp(), exp);
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
		if((typeLeft != null) && (typeRight != null ))
		return typeLeft.assertEqual(typeRight) 
			&& (typeLeft.assertEqual(new TypeExpression(Tag.INTEGER)) || typeLeft.assertEqual(new TypeExpression(Tag.FLOAT)));
		else
			throw new StreeException("Type error while checking null types !");
	}

	@Override
	public Stm getStm() {
		return stm;
	}

}
