package nylon.objects;

import nylon.exceptions.NylonRuntimeException;

/**
 * Created by Aedan Smith.
 */

public class NylonDouble implements NylonObject {
    
    private double value;
    
    public NylonDouble(double value){
        this.value = value;
    }

    @Override
    public int toInteger() throws NylonRuntimeException {
        return (int) this.value;
    }

    @Override
    public double toDouble() throws NylonRuntimeException {
        return this.value;
    }

    @Override
    public boolean toBoolean() throws NylonRuntimeException {
        return this.value != 0;
    }

    @Override
    public NylonDouble increment() throws NylonRuntimeException {
        this.value++;
        return this;
    }

    @Override
    public NylonDouble decrement() throws NylonRuntimeException {
        this.value--;
        return this;
    }

    @Override
    public NylonDouble concatenate(NylonObject nylonObject) throws NylonRuntimeException {
        return new NylonDouble(this.value + nylonObject.toInteger());
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public NylonDouble clone() {
        return new NylonDouble(value);
    }
    
}
