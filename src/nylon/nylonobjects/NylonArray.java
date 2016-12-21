package nylon.nylonobjects;

import nylon.NylonObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by Aedan Smith.
 */

public class NylonArray extends NylonObject<Vector<NylonObject>> implements Collection<NylonObject> {
    public NylonArray() {
        super(new Vector<>(), Type.ARRAY);
    }

    public NylonArray(NylonObject object) {
        super(new Vector<>(), Type.ARRAY);
        this.value.add(object);
    }

    public NylonArray(Collection<NylonObject> stack) {
        super(new Vector<>(), Type.ARRAY);
        this.value.addAll(stack);
    }

    @Override
    public int size() {
        return this.value.size();
    }

    @Override
    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.value.contains(o);
    }

    @Override
    public Iterator<NylonObject> iterator() {
        return this.value.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.value.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.value.toArray(a);
    }

    @Override
    public boolean add(NylonObject nylonObject) {
        return this.value.add(nylonObject);
    }

    @Override
    public boolean remove(Object o) {
        return this.value.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.value.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends NylonObject> c) {
        return this.value.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.value.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.value.retainAll(c);
    }

    @Override
    public void clear() {
        this.value.clear();
    }

    @Override
    public boolean toBoolean(Stack<NylonObject> stack) {
        return !isEmpty() && this.value.lastElement().toBoolean(stack);
    }

    @Override
    public double toDouble(Stack<NylonObject> stack) {
        return this.size();
    }

    @Override
    public Iterator<NylonObject> toIterator(Stack<NylonObject> stack) {
        return this.iterator();
    }

    @Override
    public NylonArray toArray(Stack<NylonObject> stack) {
        return this;
    }

    @Override
    public NylonString toNylonString(Stack<NylonObject> stack) {
        String s = "";
        for (NylonObject nylonObject : this) {
            s += nylonObject.toString();
        }
        return new NylonString(s.toCharArray());
    }

    @Override
    public NylonObject concatenate(NylonObject object, Stack<NylonObject> stack) {
        this.addAll(object.toArray(stack));
        return this;
    }

    @Override
    public NylonObject subtract(NylonObject object, Stack<NylonObject> stack) {
        this.removeAll(object.toArray(stack));
        return this;
    }

    @Override
    public NylonObject multiply(NylonObject object, Stack<NylonObject> stack) {
        Vector<NylonObject> v = (Vector<NylonObject>) this.value.clone();
        for (long i = 0; i < object.toLong(stack); i++) {
            this.addAll(v);
        }
        return this;
    }

    @Override
    public String toString() {
        String s = "";
        for (NylonObject nylonObject : this) {
            s += nylonObject + "\n";
        }
        return s;
    }

    @Override
    public NylonObject clone() {
        //noinspection unchecked
        return new NylonArray((Collection<NylonObject>) this.value.clone());
    }
}