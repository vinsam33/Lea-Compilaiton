package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeDEFAULT extends Stree {

	private Stm stm;
	private Type type;
	private Label labelTrue;

	public StreeDEFAULT(Stree left) throws TypeException, StreeException {
		super(left);
		this.type = new TypeExpression(Tag.VOID);
		this.labelTrue = new Label();
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		return new SEQ(new LABEL(labelTrue), getLeft().getStm());
	}

	@Override
	public Stm getStm() {
		return stm;
	}

	@Override
	public Type getType() throws StreeException { return type; }

	@Override
	public boolean checkType() throws StreeException { return true; }

	@Override
	public Label getLabelTrue() {
		return labelTrue;
	}

	@Override
	public Label getLabelFalse() {
		return labelTrue;
	}

	@Override
	public Exp getExp() throws StreeException { return null; }

}
