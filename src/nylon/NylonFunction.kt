package nylon

import java.util.*
import java.util.function.Supplier

/**
 * Created by Aedan Smith.
 */

abstract class NylonFunction(val string: String, var argNum: Int = 0) {
    protected var args: ArrayList<NylonFunction> = ArrayList(argNum)

    protected abstract fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>)

    fun apply(stack: NylonStack, args: ArrayList<NylonFunction>) {
        return try {
            applyImpl(stack, args)
        } catch (e: Exception) {
            throw NylonException(e, this)
        }
    }

    fun apply(stack: NylonStack) = apply(stack, args)

    fun apply(): NylonStack {
        val stack = NylonStack()
        apply(stack)
        return stack
    }

    fun resolveArgs(argumentSupplier: Supplier<NylonFunction>) {
        while (args.size < argNum) {
            args.add(argumentSupplier.get())
        }
    }

    fun resolveNestedArgs(argumentSupplier: Supplier<NylonFunction>) {
        resolveArgs(argumentSupplier)
        args.forEach { it.resolveNestedArgs(argumentSupplier) }
    }

    fun clone(): NylonFunction {
        return object : NylonFunction(string, argNum) {
            init {
                this@NylonFunction.args.forEach { this.args.add(it.clone()) }
            }

            override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
                this@NylonFunction.applyImpl(stack, args)
            }

            override fun toString(args: ArrayList<NylonFunction>): String {
                return this@NylonFunction.toString(args)
            }
        }
    }

    open fun toString(args: ArrayList<NylonFunction>): String {
        var s = string
        args.forEach { s += "$it" }
        return s
    }

    override fun toString(): String {
        return toString(args)
    }
}

fun createProvider(nylonObject: NylonObject<*>, string: String): NylonFunction {
    return object : NylonFunction(string) {
        override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
            stack.push(nylonObject.clone())
        }
    }
}

fun concatenate(nylonFunctions: ArrayList<NylonFunction>): NylonFunction {
    return object : NylonFunction("") {
        init {
            args = nylonFunctions
        }

        override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {
            args.forEach { it.apply(stack) }
        }

        override fun toString(args: ArrayList<NylonFunction>): String {
            var s = string
            args.forEach { s += " "; s += it }
            return "{" + (if (s.isNotEmpty()) s.substring(1) else s) + "}"
        }
    }
}

fun emptyFunction(): NylonFunction = object : NylonFunction("{}") {
    override fun applyImpl(stack: NylonStack, args: ArrayList<NylonFunction>) {}
}
