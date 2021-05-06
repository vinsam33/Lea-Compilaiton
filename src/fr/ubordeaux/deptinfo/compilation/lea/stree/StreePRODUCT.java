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
		boolean vd = getRight().getType().assertEqual(new TypeExpression(Tag.VOID));
		boolean cas;
		if (getRight().getLabelTrue() == null || getRight().getLabelFalse() == null)
			cas = false;
		else
			cas = true;
			//cas = getRight().getLabelFalse().equals(getRight().getLabelTrue());

		if(vd || cas) {
			Label r = getRight().getLabelTrue();
			Label l = getLeft().getLabelTrue();
			if(cas && !vd) {
				exps = new ExpList(getRight().getExp());
				System.out.println(exps.getHead());
				exps = new ExpList(getLeft().getExp(), exps);
				System.out.println(exps.getHead());
			}
			if(vd) {
				exps = new ExpList(getLeft().getExp());
			}
			labelFin = new Label();
			labels = new LabelList(labelFin);
			labels = new LabelList(r,labels); // default/\fin
			labels = new LabelList(l,labels);

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
