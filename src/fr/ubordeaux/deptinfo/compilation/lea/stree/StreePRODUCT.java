package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreePRODUCT extends Stree {

	private Stm stm;
	private Type type;
	private Label labelTrue;
	private Label labelFalse;


	public StreePRODUCT(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.type = new TypeExpression(Tag.PRODUCT, left.getType(), right.getType());
		if(right.getType().assertEqual(new TypeExpression(Tag.VOID)))
			this.labelFalse = right.getLabelFalse();
		else
			this.labelFalse = right.getLabelTrue();
		this.labelTrue = new Label();
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		return new SEQ(new LABEL(labelTrue),
						new SEQ (new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), new CONST(0), getLeft().getLabelFalse(), labelFalse),
							new SEQ(getLeft().getStm(), getRight().getStm())));
	}

	@Override
	public Stm getStm() {
		return stm;
	}

	@Override
	public Type getType() throws StreeException {
		return type;
	}
	@Override
	public boolean checkType() throws StreeException {
		return true;
	}

	@Override
	public Label getLabelFalse() {
		return labelFalse;
	}

	@Override
	public Label getLabelTrue() {
		return labelTrue;
	}
	
}
