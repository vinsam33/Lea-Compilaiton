package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeSWITCH extends Stree {

	private Stm stm;

	public StreeSWITCH(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		// label1:
		// 	code du test
		//  goto label2 si == 0 sinon goto label3
		// label2:
		//  corps de la boucle
		//  goto label 1
		// label3:
		//  fin
		return new SEQ(new LABEL(label1),
				new SEQ(new CJUMP(CJUMP.Op.EQ, getLeft().getExp(), new CONST(0), label2, label3),
						new SEQ(new LABEL(label2),
								new SEQ(getRight().getStm(),
										new SEQ(new JUMP(label1),
												new LABEL(label3))))));

	}

}
