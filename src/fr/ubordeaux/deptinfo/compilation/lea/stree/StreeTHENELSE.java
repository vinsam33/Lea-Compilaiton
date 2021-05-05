package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeTHENELSE extends Stree {

	private Stm stm;
	private Label labelTrue;
	private Label labelFalse;

	public StreeTHENELSE(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.labelTrue = new Label();
		this.labelFalse = new Label();
		this.stm = generateIntermediateCode();

	}

	public StreeTHENELSE(Stree left) throws TypeException, StreeException {
		this(left, null);
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		Label labelFin = new Label();
		if(getRight() != null) {
			return new SEQ(getLeft().getStm(),
					new SEQ(new JUMP(labelFin),
							new SEQ(new LABEL(labelFalse),
									new SEQ(getRight().getStm(), new LABEL(labelFin)))));
		}
		else {
			return new SEQ(getLeft().getStm(), new LABEL(labelFalse));
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

	@Override
	public Label getLabelTrue() {
		return labelTrue;
	}

	@Override
	public Label getLabelFalse() {
		return labelFalse;
	}

}
