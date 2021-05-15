package fr.ubordeaux.deptinfo.compilation.lea.stree;

import javax.swing.text.html.HTML.Tag;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.LabelList;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeSWITCH extends Stree {

	private Stm stm;
	private  Exp expr;
	private Stm defaultStm;


	// switch (left)
	// { right}

	public StreeSWITCH(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.expr = left.getExp();
		this.defaultStm = right.getStm();
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		System.out.println(getRight().getLabelFin());

		String result = "";
		// label1:
		//  case_1:  si exp== val_case1 goto code_case1 sinon case_2
		//       code_case 1 : ..... (code)
		//						goto label_fin
		//  case_2:  si exp== val_case2 goto code_case2 sinon case_3 
		//          code_case 2 : ..... (code)
		//						goto label_fin
		//    .......
		//   default : ...... (code)
		
		//  label_fin

		Label label_fin = new Label();
		Label label_default = new Label();

		Stm code;
		if(defaultStm!=null){
			code = new SEQ( new LABEL(label_default), new SEQ( defaultStm, new LABEL(label_fin) )  );
		}else{
			code = new LABEL (label_fin) ;
		}

		CaseList cases = getRight().getCaseList();
		cases = cases.getPrev();
		CaseList fin = cases;

		Label label_case_i , label_case_prev_i = new Label();
		while(cases.getPrev() != fin){

			CASE case_i = cases.getHead();
			
			SEQ jump_fin = new SEQ(case_i.getStm(), new JUMP(label_fin)  );

			label_case_i = label_case_prev_i;

			label_case_prev_i = new Label();

			SEQ do_case = new SEQ( new LABEL (label_case_i), jump_fin  );

			SEQ if_case = new SEQ( new CJUMP(CJUMP.Op.EQ, getExp(), case_i.getExp(), label_case_i, label_case_prev_i), do_case  );
			
			
			code = new SEQ(if_case, code);

			cases = cases.getPrev();
			
		}
	
		return code;
	
	}

	@Override
	public boolean checkType() throws StreeException {

		// expr.getType().assertEqual(new Type());  // tester si c'est un entier

   		//expr.getType().assertEqual(new Type());  // tester si c'est un enum

		
		CaseList cases = getRight().getCaseList();
		cases = cases.getPrev();
		CaseList fin = cases;
		while(cases.getPrev() != fin){

			//cases.getHead().getStm().getType().assertEqual(expr.getType());
			
		}
	}

	@Override
	public Stm getStm() {
		return stm;
	}

	public Exp getExp() {
		return expr;
	}
}