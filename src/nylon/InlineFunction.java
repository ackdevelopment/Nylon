package nylon;

import nylon.nylonobjects.NylonFunction;
import nylon.nylonobjects.NylonList;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class InlineFunction extends NylonFunction {
    public ArrayList<NylonFunction> functions = new ArrayList<>();

    @Override
    public NylonObject apply(Stack<NylonObject> stack) {
        NylonList ret = new NylonList();
        for (NylonFunction function : functions) {
            ret.add(function.apply(stack));
        }
        return ret;
    }

    @Override
    public String toString() {
        if (functions.size() == 0)
            return "EmptyFunction";

        String s = "InlineFunction" + functions.toString(), v = "";
        int tDepth = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                v += s.charAt(i);
                tDepth++;
                v += '\n';
                for (int k = 0; k < tDepth; k++) {
                    v += '\t';
                }
            } else if (s.charAt(i) == ']') {
                tDepth--;
                v += '\n';
                for (int k = 0; k < tDepth; k++) {
                    v += '\t';
                }
                v += s.charAt(i);
            } else if (s.charAt(i) == ',') {
                v += s.charAt(i);
                v += '\n';
                for (int k = 0; k < tDepth; k++) {
                    v += '\t';
                }
                i++;
            } else if (s.charAt(i) != '\n' && s.charAt(i) != '\t') {
                v += s.charAt(i);
            }
        }
        return v;
    }
}
