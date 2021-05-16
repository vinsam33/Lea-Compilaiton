package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.TempList;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeSWITCH extends Stree {

	private Stm stm;
	private TempList temps;
	private boolean product;
	private boolean def;

	public StreeSWITCH(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		Label labelFin = right.getLabelFin();
		product = true;
		def = false;
		if(labelFin == null) {
			product = false;
			Label labelTrue = right.getLabelTrue();
			if(labelTrue == null)
				def = true;
		}

		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {

		if(product) {
			temps = getRight().getTempList();
			TEMP tmp_stm = new TEMP(temps.getHead());
			Stm tmp = new MOVE(tmp_stm,getLeft().getExp());
			return new SEQ(tmp, getRight().getStm());
		}
		else {
			if(def)
				return getRight().getStm();
			else {
				temps = new TempList(getRight().getTemp());
				TEMP tmp_stm = new TEMP(temps.getHead());
				Stm tmp = new MOVE(tmp_stm,getLeft().getExp());
				return new SEQ(tmp, new SEQ(getRight().getStm(), new LABEL(getRight().getLabelFalse())));
			}
		}
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		String[] productTypes = getRight().getType().toString().split(" X ");
		String typeLeftS = typeLeft + "";
		int n = productTypes.length;
		boolean res = true;

		if (typeLeft != null) {
			if(n > 1) {
				for(int i = 0; i < n; i++) {
					if(!typeLeftS.equals(productTypes[i]) && !productTypes[i].equals("void"))
						res = false;
				}
			}
			else {
				if(!typeLeftS.equals(productTypes[0]) && !productTypes[0].equals("void"))
					res = false;
			}
			return res;
		}
		else
			throw new StreeException("Type error while checking null types !");
	}

	@Override
	public Stm getStm() {
		return stm;
	}

}
