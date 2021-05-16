package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Temp;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeCASE extends Stree {

	private Stm stm;
	private Type type;
	private Label labelTrue; // case label
	private Label labelFalse;// other case, default, fin
	private Exp exp;
	private Temp tmp;

	public StreeCASE(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.type = new TypeExpression(Tag.NAME, left.getType() + "");
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		this.exp = getLeft().getExp();
		this.labelTrue = new Label();
		this.labelFalse = new Label();
		this.tmp = new Temp();
		TEMP tmp_stm = new TEMP(tmp);
		return new SEQ(new CJUMP(CJUMP.Op.EQ, tmp_stm, exp, labelTrue, labelFalse),
				new SEQ(new LABEL(labelTrue), getRight().getStm()));
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
		Type typeLeft = getLeft().getType();
		if (typeLeft != null)
			return true;
		else
			throw new StreeException("Type error while checking null types !");
	}

	@Override
	public Label getLabelTrue() {
		return labelTrue;
	}

	@Override
	public Label getLabelFalse() {
		return labelFalse;
	}

	@Override
	public Label getLabelFin() {
		return null;
	}

	@Override
	public Exp getExp() throws StreeException {
		return exp;
	}

	@Override
	public Temp getTemp() {
		return tmp;
	}

}
