package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreePRODUCT extends Stree {

	private Stm stm;
	private Type type;

	public StreePRODUCT(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		System.out.println("!!!!");
	}

	@Override
	public Stm getStm() {
		return stm;
	}

	@Override
	public Type getType() throws StreeException {
		return type;
	}
	
}
