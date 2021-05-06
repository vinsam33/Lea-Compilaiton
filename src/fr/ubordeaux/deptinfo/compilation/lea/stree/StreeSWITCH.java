package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.LabelList;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeSWITCH extends Stree {

	private Stm stm;
	private LabelList labels;
	private ExpList exps;

	public StreeSWITCH(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		labels = getRight().getLabelList();
		exps = getRight().getExpList();
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		System.out.println(getRight().getLabelFin());
		//System.out.println(getRight().getLabelList().getHead());
		//System.out.println(getRight().getExpList().getHead());
		LabelList ls = labels;
		Label tT = labels.getHead();
		Label tF = new Label();
		LabelList defL = labels;
		do{
			defL = defL.getTail();
		} while(defL.getTail().getTail() != null);
		if(exps.getTail() == null)
			tF = defL.getHead();
		ExpList exs = exps;
		Exp ex = exps.getHead();
		if(tT.equals(tF)){
			tF = defL.getTail().getHead();
		}
		Stm tests = new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), ex, tT, tF);
		Label ntF;
		//Stm tests = new LABEL(tF);
		//System.out.println(ls.getHead());
		//System.out.println(exs.getHead());
		/*while(exs != null){
			tT = ls.getHead();
			exs = exs.getTail();
			if(exs != null) {
				ls = ls.getTail();
				ex = exs.getHead();
				if(exs.getTail() == null) {
					tF = defL.getHead();
					tests = new SEQ(tests, new SEQ(new LABEL(tF), new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), ex, tT, tF)));
				}
				else {
					tests = new SEQ(tests, new SEQ(new LABEL(tF), new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), ex, tT, tF)));
					tF = new Label();
				}
			}
		}*/

		while(exs.getTail() != null){
			ls = ls.getTail();
			tT = ls.getHead();
			exs = exs.getTail();
			ex = exs.getHead();
			ntF = tF;
			if(exs.getTail() == null) {
				Label tFF = defL.getHead();
				if(tT.equals(tFF)){
					tFF = defL.getTail().getHead();
				}
				tests = new SEQ(tests, new SEQ(new LABEL(ntF), new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), ex, tT, tFF)));
			}
			else {
				tF = new Label();
				tests = new SEQ(tests, new SEQ(new LABEL(ntF), new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), ex, tT, tF)));
			}
		}

		System.out.println(tests);
		return new SEQ(tests, getRight().getStm());
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		String[] productTypes = getRight().getType().toString().split(" X ");
		String typeLeftS = typeLeft + "";
		boolean res = true;
		int n = productTypes.length;

		if (typeLeft != null) {
			for(int i = 0; i < n; i++) {
				if(!typeLeftS.equals(productTypes[i]) && !productTypes[i].equals("void"))
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
