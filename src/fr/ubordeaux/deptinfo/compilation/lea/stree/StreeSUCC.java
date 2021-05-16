package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Temp;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;

public class StreeSUCC extends Stree {

	public static final Boolean RIGHT = false;
	public static final Boolean LEFT = true;
	private Boolean rank;
	private Type type;
	private Exp exp;
	private Stm stm;

	public StreeSUCC(Stree left, Boolean rank) throws TypeException, StreeException {
		super(left);
		this.rank = rank;
		this.exp = new StreePLUS(left, new StreeINTEGER(1)).getExp();
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		if (rank)
			return new MOVE(getLeft().getExp(), exp);
		else
			return new MOVE(exp, getLeft().getExp());
	}

	@Override
	public Exp getExp() {
		return exp;
	}

	@Override
	public Stm getStm() {
		return stm;
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		type = typeLeft;
		if (typeLeft != null) {
			return typeLeft.assertEqual(new TypeExpression(Tag.INTEGER))
					|| typeLeft.assertEqual(new TypeExpression(Tag.FLOAT));
		} else {
			throw new StreeException("Type error while checking null types ! :StreeSUCC");
		}
	}

	@Override
	public Type getType() throws StreeException {
		return type;
	}

}
