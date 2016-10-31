package nylon.objects;

import nylon.exceptions.InvalidActionException;
import nylon.exceptions.NylonRuntimeException;
import nylon.exceptions.UnconvertableTypeException;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public class NylonString implements NylonObject {

    private String value;

    public NylonString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public char toChar() throws NylonRuntimeException {
        if (value.length() == 1){
            return value.charAt(0);
        } else {
            throw new UnconvertableTypeException(this, Character.class);
        }
    }

    @Override
    public int toInteger() throws NylonRuntimeException {
        try {
            return Integer.parseInt(value);
        } catch (Exception e){
            throw new UnconvertableTypeException(this, Integer.class);
        }
    }

    @Override
    public double toDouble() throws NylonRuntimeException {
        try {
            return Double.parseDouble(value);
        } catch (Exception e){
            throw new UnconvertableTypeException(this, Double.class);
        }
    }

    @Override
    public boolean toBoolean() throws NylonRuntimeException {
        if (value.equals("t") || value.equals("true"))
            return true;
        if (value.equals("f") || value.equals("false"))
            return false;
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
    public NylonString concatenate(NylonObject nylonObject) {
        return new NylonString(this.value + nylonObject.toString());
    }

    @Override
    public NylonString clone() {
        return new NylonString(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public Iterator<NylonObject> iterator(NylonStack nylonStack) {
        return new Iterator<NylonObject>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < value.length();
            }

            @Override
            public NylonCharacter next() {
                return new NylonCharacter(value.charAt(i++));
            }
        };
    }

    @Override
    public Iterator<NylonObject> reverseIterator(NylonStack nylonStack) throws NylonRuntimeException {
        return new Iterator<NylonObject>() {
            int i = value.length();

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public NylonCharacter next() {
                return new NylonCharacter(value.charAt(--i));
            }
        };
    }

    @Override
    public int compareTo(NylonObject object, NylonStack nylonStack) throws NylonRuntimeException {
        return value.compareTo(object.toString());
    }
}
