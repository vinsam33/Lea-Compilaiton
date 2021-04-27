package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeBOOLEAN extends Stree{

    private Boolean value;

    public StreeBOOLEAN(Boolean value) throws TypeException, StreeException {
        super();
        this.value = value;
    }
}
