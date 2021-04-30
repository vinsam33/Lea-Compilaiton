package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeIF extends Stree {

	private Stm stm;

	public StreeIF(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		Label label1 = new Label();
		String l1S = label1 + "";
		String[] l1SM = l1S.split("L");
		int nL1S = Integer.parseInt(l1SM[1]) - 1;
		Label label2 = new Label("L" + nL1S);
		return new SEQ(new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), new CONST(0), label1, label2),
				new SEQ(new LABEL(label1), getRight().getStm()));

	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		if (typeLeft != null)
			return typeLeft.assertBoolean();
		else
			throw new StreeException("Type error while checking null types !");
	}

	@Override
	public Stm getStm() {
		return stm;
	}

}
