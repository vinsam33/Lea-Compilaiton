package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeIF extends Stree {

	private Stm stm;

	public StreeIF(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		System.out.println(getRight().getId());
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		Label label1 = getRight().getLabelTrue();
		Label label2 = getRight().getLabelFalse();

		CJUMP.Op op;
		String binop = getLeft().getExp().getBinop().toString();
		switch(binop) {
			case (">="):
				op = CJUMP.Op.GE;
				break;
			case ("!="):
				op = CJUMP.Op.NE;
				break;
			case ("<"):
				op = CJUMP.Op.LT;
				break;
			case (">"):
				op = CJUMP.Op.GT;
				break;
			case ("<="):
				op = CJUMP.Op.LE;
				break;
			default:
				op = CJUMP.Op.EQ;
				break;
		}

		return new SEQ(new CJUMP(op, getLeft().getLeft().getExp(), getLeft().getRight().getExp(), label1, label2),
				new SEQ(new LABEL(label1), getRight().getStm()));

	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		if (typeLeft != null)
			return typeLeft.assertBoolean();
		else
			throw new StreeException("Type error while checking null types ! : StreeIF");
	}

	@Override
	public Stm getStm() {
		return stm;
	}

}
