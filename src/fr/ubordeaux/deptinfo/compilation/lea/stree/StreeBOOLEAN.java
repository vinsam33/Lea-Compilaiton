package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeBOOLEAN extends Stree{

    private Boolean value;
    private Type type;

    public StreeBOOLEAN(Boolean value) throws TypeException, StreeException {
        super();
        this.value = value;
    }
}
