package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.LabelList;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Temp;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.TempList;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreePRODUCT extends Stree {

	private Stm stm;
	private Type type;
	private Label labelFin;
	private LabelList labels;
	private TempList temps;

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


		if(getLeft().getLabelTrue() != null && getRight().getType().assertEqual(new TypeExpression(Tag.VOID))) {
			//If the initial product is case and default.
			defR = true;
			caseL = true;
		}
		else if (getLeft().getLabelTrue() != null && getRight().getLabelTrue() != null) {
			//If the initial product is case and case.
			caseL = true;
			caseR = true;
		}

		if(caseL && defR) {
			labelFin = new Label();
			Label r = getLeft().getLabelFalse();
			temps = new TempList(getLeft().getTemp());
			labels = new LabelList(labelFin);
			labels = new LabelList(r,labels);

			return new SEQ(new SEQ(getLeft().getStm(), new JUMP(labelFin)),new SEQ(new SEQ( new LABEL(r), getRight().getStm()), new LABEL(labelFin)));
		}
		else if(caseL && caseR) {
			labelFin = getRight().getLabelFalse();
			Label r = getLeft().getLabelFalse();
			Temp t1 = getRight().getTemp();
			Temp t2 = getLeft().getTemp();
			temps = new TempList(t1);
			temps = new TempList(t2, temps);

			TEMP tmp_stm1 = new TEMP(t1);
			TEMP tmp_stm2 = new TEMP(t2);
			Stm tmp = new MOVE(tmp_stm1, tmp_stm2);

			labels = new LabelList(labelFin);
			labels = new LabelList(r,labels);

			return new SEQ(new SEQ(getLeft().getStm(), new JUMP(labelFin)),new SEQ(new SEQ(new SEQ(new LABEL(r), tmp), new SEQ(getRight().getStm(), new JUMP(labelFin))), new LABEL(labelFin)));
		}
		else {
			labels = getRight().getLabelList();
			Label r = getLeft().getLabelFalse();
			labels = new LabelList(r, labels);
			labelFin = getRight().getLabelFin();
			temps = getRight().getTempList();
			TEMP tmp_stm1 = new TEMP(temps.getHead());
			TEMP tmp_stm2 = new TEMP(getLeft().getTemp());
			temps = new TempList(getLeft().getTemp(), temps);
			Stm tmp = new MOVE(tmp_stm1, tmp_stm2);

			return new SEQ(new SEQ(getLeft().getStm(), new JUMP(labelFin)), new SEQ(new SEQ(new LABEL(r), tmp), getRight().getStm()));
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

	@Override
	public TempList getTempList() {
		return temps;
	}

}
