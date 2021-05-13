package fr.ubordeaux.deptinfo.compilation.lea.stree;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.MOVE;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeORAFF extends Stree {

	private Type type;
	private Stm stm;
	private Exp exp;

	public StreeORAFF(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.type = new TypeExpression(Tag.BOOLEAN);
		exp = new StreeOR(getLeft(), getRight()).getExp();
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException{
		return new MOVE(getLeft().getExp(), exp);
	}

	@Override
	public Stm getStm(){
		return stm;
	}

	@Override
	public Type getType() throws StreeException {
		return type;
	}

	

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		Type typeRight = getRight().getType();
		if((typeLeft != null) && (typeRight != null )){
			return typeLeft.assertEqual(typeRight) && typeLeft.assertBoolean();
		}
		else
			throw new StreeException("Type error while checking null types ! :StreeORAFF");
	}

}
