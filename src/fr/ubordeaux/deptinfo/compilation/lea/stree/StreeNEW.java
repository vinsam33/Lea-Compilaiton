package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;

public class StreeNEW extends Stree {

	private Type pointerType;

	public StreeNEW(Type pointerType, Stree left) throws TypeException, StreeException {
		super(left);
		this.pointerType = pointerType;
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		Type typeRight = getRight().getType();
		if((typeLeft != null) && (typeRight != null )){
			return typeLeft.getTag() == Tag.CLASS;
		}
		else
			throw new StreeException("Type error while checking null types ! :StreeNEW");
	}

}
