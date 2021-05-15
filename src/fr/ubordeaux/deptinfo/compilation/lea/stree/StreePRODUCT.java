package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.LabelList;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreePRODUCT extends Stree {

	private Stm stm;
	private Type type;
	private Label labelFin;
	private ExpList exps;
	private LabelList labels;

	public StreePRODUCT(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.type = new TypeExpression(Tag.PRODUCT, left.getType(), right.getType());
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException{
		boolean defR = false;
		boolean caseL = false;
		boolean caseR = false;
		labelFin = new Label();

		if(getLeft().getLabelTrue() != null && getRight().getType().assertEqual(new TypeExpression(Tag.VOID))) {
			defR = true;
			caseL = true;
		}
		else if (getLeft().getLabelTrue() != null && getRight().getLabelTrue() != null) {
			caseL = true;
			caseR = true;
		}
		//System.out.println("defR " + defR);
		//System.out.println("caseL " + caseL);
		//System.out.println("caseR " + caseR);

		if((caseL && defR) || (caseL && caseR)) {
			Label l = getLeft().getLabelTrue();
			Label r = getRight().getLabelTrue();
			exps = new ExpList(getRight().getExp());
			if(caseR)
				exps = new ExpList(getRight().getExp(), exps);
			exps = new ExpList(getLeft().getExp(), exps);
			labels = new LabelList(labelFin);
			labels = new LabelList(r,labels);
			labels = new LabelList(l,labels);

			if(defR)
				return new SEQ(new SEQ(getLeft().getStm(), new JUMP(labelFin)), new SEQ(getRight().getStm(), new LABEL(labelFin)));

			return new SEQ(new SEQ(getLeft().getStm(), new JUMP(labelFin)), new SEQ(getRight().getStm(), new LABEL(labelFin)));
		}
		else {
			labels = getRight().getLabelList();
			Label l = getLeft().getLabelTrue();
			labels = new LabelList(l,labels);
			exps = getRight().getExpList();
			exps = new ExpList(getLeft().getExp(), exps);

			LabelList finL = labels;
			do{
				finL = finL.getTail();
			} while(finL.getTail() != null);
			labelFin = finL.getHead();

			return new SEQ(new SEQ(getLeft().getStm(), new JUMP(labelFin)), getRight().getStm());
		}

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
	public ExpList getExpList(){
		return exps;
	}

	@Override
	public LabelList getLabelList(){
		return labels;
	}

	@Override
	public Label getLabelFin(){
		return labelFin;
	}

	@Override
	public Label getLabelTrue() {
		return null;
	}

	@Override
	public Label getLabelFalse() {
		return null;
	}
}
