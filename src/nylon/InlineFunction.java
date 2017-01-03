package nylon;

import nylon.nylonobjects.NylonArray;
import nylon.nylonobjects.NylonFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class InlineFunction extends NylonFunction {
    public ArrayList<NylonFunction> functions = new ArrayList<>();

    public InlineFunction() {
    }

    public InlineFunction(NylonFunction... functions) {
        Collections.addAll(this.functions, functions);
    }

    @Override
    public NylonObject apply(Stack<NylonObject> stack) {
        NylonArray ret = new NylonArray();
        for (NylonFunction function : functions) {
            ret.add(function.apply(stack));
        }
        return ret;
    }

    @Override
    public String toString() {
        return format("InlineFunction" + functions.toString());
    }
}
