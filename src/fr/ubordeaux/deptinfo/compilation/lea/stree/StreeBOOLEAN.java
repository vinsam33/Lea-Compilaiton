package fr.ubordeaux.deptinfo.compilation.lea.stree;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.NAME;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeBOOLEAN extends Stree{

    private Boolean value;
    private Type type;
    private NAME exp;
    private static int count;

    public StreeBOOLEAN(Boolean value) throws TypeException, StreeException {
        super();
        this.value = value;
        if(value)
            this.exp = new NAME(new Label("true"));
        else
            this.exp = new NAME(new Label("false"));
        count++;
        this.type = new TypeExpression(Tag.BOOLEAN);
    }
}
