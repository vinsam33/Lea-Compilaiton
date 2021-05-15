package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.LabelList;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeSWITCH extends Stree {

	private Stm stm;
	private LabelList labels;
	private ExpList exps;
	private boolean product;
	private boolean def;

	public StreeSWITCH(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		Label labelTrue = right.getLabelTrue();
		product = true;
		def = false;
		if(labelTrue != null) {
			product = false;
			labels = new LabelList(labelTrue);
			Exp exp = right.getExp();
			if(exp != null)
				exps = new ExpList(exp);
			else
				def = true;
		}
		else {
			labels = right.getLabelList();
			exps = right.getExpList();
		}

		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		//System.out.println(getRight().getLabelFin());
		//System.out.println(getRight().getLabelList().getHead());
		//System.out.println(getRight().getExpList().getHead());

		if(product) {
			LabelList ls = labels;
			Label tF;
			boolean def = true;


			ExpList exs = exps;
			Exp ex = exps.getHead();
			Label tT = labels.getHead();

			while(ls.getTail().hasNext()){
				ls = ls.getTail();
				exs = exs.getTail();
				//System.out.println(ls.getHead());
			}
			if(exs.hasNext())
				def = false;
			
			System.out.println(getLeft().getExp());
			Stm tests;
			tF = ls.getHead();
			if(def) {
				tests = new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), ex, tT, tF);
			}
			else {
				Label nl = new Label();

				tests = new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), ex, tT, nl);
				Label ntF = ls.getTail().getHead();
				tests = new SEQ(tests, new SEQ(new LABEL(nl), new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), exs.getTail().getHead(), tF, ntF)));
			}

			exs = exps;
			Label ntF;
			ls = labels;

			while(exs.hasNext()){
				ls = ls.getTail();
				tT = ls.getHead();
				System.out.println("tT " +tT);
				exs = exs.getTail();
				ntF = tF;
				if(!exs.hasNext()) {
					Label tFF = ls.getHead();
					if(tT.equals(tFF)){
						System.out.println("tFF " +tFF);
						tFF = ls.getHead();

					}
					tests = new SEQ(tests, new SEQ(new LABEL(ntF), new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), ex, tT, tFF)));
					//tests = getRight().getStm();
				}
				else {
					ex = exs.getHead();
					System.out.println("ex " +ex);
					tF = new Label();
					tests = new SEQ(tests, new SEQ(new LABEL(ntF), new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), ex, tT, tF)));
					//tests = getRight().getStm();
				}
			}

			return new SEQ(tests, getRight().getStm());
			//return getRight().getStm();
		}
		else {
			Label tF = new Label();
			if(def)
				return new SEQ(getRight().getStm(), new LABEL(tF));
			else {
				Label tT = labels.getHead();
				Exp ex = exps.getHead();
				return new SEQ(new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), ex, tT, tF), new SEQ(new SEQ(getRight().getStm(), new JUMP(tF)),new LABEL(tF)));
			}
		}

		/*LabelList ls = labels;
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
		}*/

		//System.out.println(tests);
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		String[] productTypes = getRight().getType().toString().split(" X ");
		String typeLeftS = typeLeft + "";
		boolean res = true;

		if (typeLeft != null) {
			if(product) {
				int n = productTypes.length;
				for(int i = 0; i < n; i++) {
					if(!typeLeftS.equals(productTypes[i]) && !productTypes[i].equals("void"))
						res = false;
				}
			}
			else {
				if(!def) {
					if(!typeLeftS.equals(productTypes[0])  && !productTypes[0].equals("void"))
						res = false;
				}
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
