package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeCASE extends Stree {

	private Stm stm;
	private Type type;
	private Label labelTrue;

	public StreeCASE(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.type = new TypeExpression(Tag.NAME, left.getType()+"");
		this.labelTrue = new Label();
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		return new SEQ(new LABEL(labelTrue), getRight().getStm());
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		if (typeLeft != null)
			return true;
		else
			throw new StreeException("Type error while checking null types !  :StreeCASE");
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
	public Label getLabelTrue() {
		return labelTrue;
	}

	@Override
	public Label getLabelFalse() {
		return labelTrue;
	}

	@Override
	public Exp getExp() throws StreeException {
		return getLeft().getExp();
	}
}
