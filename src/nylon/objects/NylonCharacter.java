package nylon.objects;

import nylon.exceptions.NylonRuntimeException;
import nylon.exceptions.UnconvertableTypeException;

import java.util.Iterator;

/**
 * Created by Aedan Smith.
 */

public class NylonCharacter implements NylonObject {

    private char value;

    public NylonCharacter(char value){
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public char toChar() throws NylonRuntimeException {
        return value;
    }

    @Override
    public int toInteger() {
        return this.value;
    }

    @Override
    public double toDouble() throws NylonRuntimeException {
        return this.value;
    }

    @Override
    public boolean toBoolean() throws NylonRuntimeException {
        if (value == 't')
            return true;
        if (value == 'f')
            return false;
        throw new UnconvertableTypeException(this, Boolean.class);
    }

    @Override
    public NylonCharacter increment() {
        this.value++;
        return this;
    }

    @Override
    public NylonCharacter decrement() {
        this.value--;
        return this;
    }

    @Override
    public NylonString concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        return new NylonString(value + nylonObject.toString());
    }

    @Override
    public NylonCharacter clone() {
        return new NylonCharacter(value);
    }


    public char getValue() {
        return value;
    }

    @Override
    public Iterator<NylonObject> iterator(NylonStack nylonStack) throws NylonRuntimeException {
        return new Iterator<NylonObject>() {
            char i = 0;

            @Override
            public boolean hasNext() {
                return i < value;
            }

            @Override
            public NylonDouble next() {
                return new NylonDouble(i++);
            }
        };
    }

    @Override
    public Iterator<NylonObject> reverseIterator(NylonStack nylonStack) throws NylonRuntimeException {
        return new Iterator<NylonObject>() {
            char i = value;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public NylonDouble next() {
                return new NylonDouble(--i);
            }
        };
    }

    @Override
    public int compareTo(NylonObject object, NylonStack nylonStack) throws NylonRuntimeException {
        return Character.compare(value, object.toChar());
    }
}
