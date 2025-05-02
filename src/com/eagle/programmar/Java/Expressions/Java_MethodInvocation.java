// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.Expressions.Eagle_Generate_MethodInvocation;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_ArgumentList.Java_MoreArguments;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Method;
import com.eagle.programmar.Java.Java_Method.Java_MethodImplementation;
import com.eagle.programmar.Java.Java_Method.Java_MethodType;
import com.eagle.programmar.Java.Java_ParameterList;
import com.eagle.programmar.Java.Java_ParameterList.Java_MethodParameter;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_MethodInvocation extends PrimaryOperator
		implements EagleRunnableWithResult, EagleTransformableExpression,
				Eagle_Generate_MethodInvocation<Java_Expression, Java_Variable>
{
	public @S(10) Java_Variable methodName;
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE @OPT Java_ArgumentList argList;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		AbstractToken token = methodName.firstId.getWhich();
		if (token instanceof Java_Identifier_Reference)
		{
			// Look it up
			String name = ((Java_Identifier_Reference) token).getValue();
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new RuntimeException("Unable to find a method named " + name);
			}
			
			Java_Method meth = (Java_Method) fn;
			Java_ParameterList parameters = null;
			AbstractToken which = meth.typeAndName.getWhich();
			if (which instanceof Java_MethodType)
			{
				Java_MethodType methodType = (Java_MethodType) which;
				parameters = methodType.parameters;
			}

			// Make sure the function args match up
			int argCount = 0;
			if (argList != null)
			{
				if (argList.arg != null && argList.arg.isPresent()) argCount = 1;
				if (argList.moreArgs != null && argList.moreArgs.isPresent()) argCount += argList.moreArgs.size();
			}

			int paramCount = parameters.params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, meth);

			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, this, name);
			}
			ArrayList<String> argTypes = new ArrayList<String>();

			// Now assign all the parameters
			if (argCount > 0)
			{
				Java_Expression expr = argList.arg;
				for (int i = 0; i < argCount; i++)
				{
					if (i > 0)
					{
						expr = argList.moreArgs._elements.get(i-1).arg;
					}
					Java_MethodParameter param = parameters.params.getPrimaryElement(i);
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param.id, param.id.getValue(), val);
					argTypes.add(val.typeName());
				}
			}
			_metrics.called(argTypes);

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			AbstractToken body = meth.body.getWhich();
			if (body instanceof Java_MethodImplementation)
			{
				Java_MethodImplementation impl = (Java_MethodImplementation) body;
				result = interpreter.tryToInterpret(impl.block);
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			meth._metrics.addCallFrom(this, elapsedTime);

			// Now remove all those parameters
			interpreter.completedFunction(name, meth);
		}
		else
		{
			throw new RuntimeException("Unable to call method " + methodName);
		}
		return result;
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		Java_Variable variable = this.methodName;
		if (variable.firstId.getWhich() instanceof Java_Identifier_Reference)
		{
			Java_Identifier_Reference id = (Java_Identifier_Reference) variable.firstId.getWhich();
			ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
			if (this.argList != null && this.argList.isPresent())
			{
				args.add(transformer.transformExpression(generator, this.argList.arg));
				if (this.argList.moreArgs != null && this.argList.moreArgs.isPresent())
				{
					for (Java_MoreArguments arg : this.argList.moreArgs._elements)
					{
						args.add(transformer.transformExpression(generator, arg.arg));
					}
				}
			}
			
			Java_Variable var = Java_Variable.newVariable(id.getValue());
			return generator.newMethodInvocation(var, args, this);
		}
		throw new RuntimeException("Can't handle: " + this);
	}

	@Override
	public Java_Expression generateInvocation(Java_Variable var,
			ArrayList<Java_Expression> args, AbstractToken source)
	{
		this.methodName = new Java_Variable();
		this.methodName.firstId = var.firstId;
		this.leftParen = new PunctuationLeftParen();
		this.argList = Java_ArgumentList.createArgumentList(args);
		if (args != null) this.argList.setPresent(true);
		this.rightParen = new PunctuationRightParen();

		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
