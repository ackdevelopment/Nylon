package nylon.objects;

import nylon.exceptions.InvalidActionException;
import nylon.exceptions.NylonRuntimeException;
import nylon.exceptions.UnconvertableTypeException;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public abstract class NylonFunction implements NylonObject {

    @SuppressWarnings("unchecked")
    public NylonStack apply(NylonStack superStack) throws NylonRuntimeException {
        if (superStack.size() != 0 && superStack.peek().getClass() == FunctionSkipObject.class) {
            superStack.pop();
            return new NylonStack();
        }

        NylonStack functionStack = new NylonStack();
        this.applyImpl(superStack, functionStack);

        NylonStack returnStack = new NylonStack();
        if (functionStack.size() != 0) {
            Class clazz = functionStack.peek().getClass();
            NylonObject object;
            while (functionStack.size() != 0) {
                object = functionStack.pop();
                if (object.getClass() == clazz)
                    returnStack.insertElementAt(object, 0);
                else
                    break;
            }
        }
        return returnStack;
    }

    protected abstract void applyImpl(NylonStack args, NylonStack returnStack) throws NylonRuntimeException;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public char toChar() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Character.class);
    }

    @Override
    public int toInteger() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Integer.class);
    }

    @Override
    public double toDouble() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Double.class);
    }

    @Override
    public boolean toBoolean() throws NylonRuntimeException {
        throw new UnconvertableTypeException(this, Boolean.class);
    }

    @Override
    public NylonString increment() throws NylonRuntimeException {
        throw new InvalidActionException("increment", this);
    }

    @Override
    public NylonString decrement() throws NylonRuntimeException {
        throw new InvalidActionException("decrement", this);
    }

    @Override
    public NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        if (nylonObject instanceof NylonFunction){
            return new NylonFunction() {
                @Override
                protected void applyImpl(NylonStack args, NylonStack returnStack)
                        throws NylonRuntimeException {
                    NylonFunction.this.applyImpl(args, returnStack);
                    returnStack.addAll(((NylonFunction) nylonObject).apply(returnStack));
                }

                @Override
                public String toString() {
                    return "ConcatenatedFunction[" + NylonFunction.this.toString() + ", " + nylonObject.toString() + "]";
                }
            };
        }
        throw new InvalidActionException("concatenate", this);
    }

    @Override
    public NylonFunction clone() {
        try {
            return (NylonFunction) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    @Override
    public Iterator<NylonObject> iterator(NylonStack nylonStack) throws NylonRuntimeException {
        return apply(nylonStack).iterator();
    }

    @Override
    public Iterator<NylonObject> reverseIterator(NylonStack nylonStack) throws NylonRuntimeException {
        return apply(nylonStack).reverseIterator(nylonStack);
    }

    @Override
    public int compareTo(NylonObject object, NylonStack nylonStack) throws NylonRuntimeException {
        throw new InvalidActionException("compare", this);
    }
}
