package fr.ubordeaux.deptinfo.compilation.lea.stree;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.MOVE;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeMINUSAFF extends Stree {

	private Type type;
	private Stm stm;
	private Exp exp;
	
	public StreeMINUSAFF(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.type = new TypeExpression(Tag.INTEGER);
		exp = new StreeMINUS(getLeft(), getRight()).getExp();
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException{
		return new MOVE(getLeft().getExp(), exp);
	}

	@Override
	public Type getType() throws StreeException {
		return type;
	}

	@Override
	public Stm getStm(){
		return stm;
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		Type typeRight = getRight().getType();
		type = typeLeft;
		if((typeLeft != null) && (typeRight != null )){
			return type.assertEqual(typeRight) 
				&& (type.assertEqual(new TypeExpression(Tag.INTEGER)) || type.assertEqual(new TypeExpression(Tag.FLOAT)));
		}
		else
			throw new StreeException("Type error while checking null types ! :StreeMINUSAFF");
	}

}
