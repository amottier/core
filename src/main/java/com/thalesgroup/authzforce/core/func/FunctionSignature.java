package com.thalesgroup.authzforce.core.func;

import java.lang.reflect.Method;

import com.thalesgroup.authzforce.core.eval.DatatypeDef;

/**
 * First-order function signature (name, return type, arity, parameter types)
 */
public class FunctionSignature
{
	private static final IllegalArgumentException INVALID_VARARG_METHOD_PARAMETER_COUNT_EXCEPTION = new IllegalArgumentException("Invalid number of parameter types (0) for a varargs function. Such function requires at least one type for the final variable-length argument.");
	// function name
	private final String name;

	// the return type of the function
	private final DatatypeDef returnType;

	// parameter types
	private final DatatypeDef[] paramTypes;

	/**
	 * Is the last parameter specified in <code>paramTypes</code> considered as variable-length
	 * (like Java {@link Method#isVarArgs()}), i.e. taking a variable number of arguments (0 or
	 * more) of the specified paramTypes[n-1] with n the size of paramTypes). In the following
	 * examples, '...' means varargs like in Java:
	 * <p>
	 * Example 1: string-concat(string, string, string...) -> paramTypes={string, string, string},
	 * isVarargs=true
	 * </p>
	 * <p>
	 * Example 2: or(boolean...) -> paramTypes={boolean}, isVarargs=true (As you can see,
	 * isVarargs=true really means 0 or more args; indeed, the or function can take 0 parameter
	 * according to spec)
	 * </p>
	 * <p>
	 * Example 3: n-of(integer, boolean...) -> paramTypes={integer, boolean}, isVarargs=true
	 * </p>
	 * <p>
	 * Example 4: abs(integer) -> paramTypes={integer}, isVarargs=false
	 * </p>
	 * <p>
	 * Example 5: string-equal(string, string) -> paramTypes={string, string}, isVarargs=false
	 * </p>
	 * <p>
	 * Example 6: date-add-yearMonthDuration(date, yearMonthDuration) -> paramTypes={date,
	 * yearMonthDuration}, isVarargs=false
	 * </p>
	 */
	private final boolean isVarArgs;

	/**
	 * Creates function signature
	 * 
	 * @param name
	 *            function name (e.g. XACML-defined URI)
	 * 
	 * @param returnType
	 *            function's return type
	 * @param parameterTypes
	 *            function parameter types, in order of parameter declaration
	 * @param varArgs
	 *            true iff the function takes a variable number of arguments (like Java
	 *            {@link Method#isVarArgs()}, i.e. the final type in <code>paramTypes</code> can be
	 *            repeated 0 or more times to match a variable-length argument
	 *            <p>
	 *            Examples with varargs=true ('...' means varargs like in Java):
	 *            </p>
	 *            <p>
	 *            Example 1: string-concat(string, string, string...) -> paramTypes={string, string,
	 *            string}
	 *            </p>
	 *            <p>
	 *            Example 2: or(boolean...) -> paramTypes={boolean} (As you can see, isVarargs=true
	 *            really means 0 or more args; indeed, the or function can take 0 parameter
	 *            according to spec)
	 *            </p>
	 *            <p>
	 *            Example 3: n-of(integer, boolean...) -> paramTypes={integer, boolean}
	 *            </p>
	 * @throws IllegalArgumentException
	 *             if function is Varargs but not parameter specified (
	 *             {@code varArgs == true && parameterTypes.length == 0})
	 */
	public FunctionSignature(String name, DatatypeDef returnType, boolean varArgs, DatatypeDef... parameterTypes) throws IllegalArgumentException
	{
		if (varArgs && parameterTypes.length == 0)
		{
			throw INVALID_VARARG_METHOD_PARAMETER_COUNT_EXCEPTION;
		}

		this.name = name;
		this.returnType = returnType;
		this.paramTypes = parameterTypes;
		this.isVarArgs = varArgs;
	}

	/**
	 * Get function name
	 * 
	 * @return function name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get function return type
	 * 
	 * @return function return type
	 */
	public DatatypeDef getReturnType()
	{
		return returnType;
	}

	/**
	 * Get function parameter types
	 * 
	 * @return function parameter types
	 */
	public DatatypeDef[] getParameterTypes()
	{
		return paramTypes;
	}

	/**
	 * Returns {@code true} if this method was declared to take a variable number of arguments;
	 * returns {@code false} otherwise.
	 * 
	 * @return {@code true} iff this method was declared to take a variable number of arguments.
	 */
	public boolean isVarArgs()
	{
		return isVarArgs;
	}
}
