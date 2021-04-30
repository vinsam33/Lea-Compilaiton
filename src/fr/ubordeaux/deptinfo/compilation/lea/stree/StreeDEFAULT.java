package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeDEFAULT extends Stree {

	private Stm stm;
	private Type type = new TypeExpression(Tag.VOID);

	public StreeDEFAULT(Stree left) throws TypeException, StreeException {
		super(left);
		//this.stm = generateIntermediateCode();
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
		Type typeLeft = getLeft().getType();
		if (typeLeft != null)
			return typeLeft.assertEqual(new TypeExpression(Tag.VOID));
		else
			throw new StreeException("Type error while checking null types !");
	}

}
