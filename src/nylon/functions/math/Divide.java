package nylon.functions.math;

import nylon.exceptions.NylonRuntimeException;
import nylon.objects.NylonFunction;
import nylon.objects.NylonDouble;
import nylon.objects.NylonStack;

/**
 * Created by Aedan Smith.
 */

public class Divide extends NylonFunction {

    public Divide() {
        super(2);
    }

    @Override
    protected void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException {
        if (args.size() == 0)
            throw new NylonRuntimeException("Cannot divide null arguments.");
        else if (args.size() == 1)
            throw new NylonRuntimeException("Cannot divide one argument.");
        else if (args.size() == 2)
            returnStack.add(new NylonDouble(args.lastElement().toDouble() / args.lastElement().toDouble()));
        else
            // Should never hit
            throw new NylonRuntimeException("Internal error: Too many arguments given.");
    }

}
