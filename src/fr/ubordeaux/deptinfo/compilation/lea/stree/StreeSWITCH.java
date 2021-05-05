package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeSWITCH extends Stree {

	private Stm stm;

	public StreeSWITCH(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);

		System.out.println(right.getType());
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		return getRight().getStm();
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
