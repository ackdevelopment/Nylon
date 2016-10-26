package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

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
    public int toInteger() {
        return value;
    }

    @Override
    public double toDouble() throws NylonRuntimeException {
        return value;
    }

    @Override
    public boolean toBoolean() throws NylonRuntimeException {
        if (value == 't')
            return true;
        if (value == 'f')
            return false;
        throw new NylonRuntimeException("Could not convert character to boolean (character '" + value + "'");
    }

    @Override
    public NylonObject increment() {
        this.value++;
        return this;
    }

    @Override
    public NylonObject decrement() {
        this.value--;
        return this;
    }

    @Override
    public NylonObject concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        return new NylonCharacter((char) (nylonObject.toInteger() + this.value));
    }

    @Override
    public NylonCharacter clone() {
        return new NylonCharacter(value);
    }

}