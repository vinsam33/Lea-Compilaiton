package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeTHENELSE extends Stree {

	private Stm stm;

	public StreeTHENELSE(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.stm = generateIntermediateCode();
	}

	public StreeTHENELSE(Stree left) throws TypeException, StreeException {
		this(left, null);
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		Label label2 = new Label();
		if(getRight() != null) {
			return new SEQ(getLeft().getStm(),
					new SEQ(new LABEL(label2), getRight().getStm()));
		}
		else {
			return new SEQ(getLeft().getStm(), new LABEL(label2));
		}


	}

	@Override
	public boolean checkType() throws StreeException {
		return true;
	}

	@Override
	public Stm getStm() {
		return stm;
	}

}
