// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using Java_ArgumentList = com.eagle.programmar.Java.Java_ArgumentList;
	using Java_MoreArguments = com.eagle.programmar.Java.Java_ArgumentList.Java_MoreArguments;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Method = com.eagle.programmar.Java.Java_Method;
	using Java_MethodImplementation = com.eagle.programmar.Java.Java_Method.Java_MethodImplementation;
	using Java_MethodType = com.eagle.programmar.Java.Java_Method.Java_MethodType;
	using Java_ParameterList = com.eagle.programmar.Java.Java_ParameterList;
	using Java_MethodParameter = com.eagle.programmar.Java.Java_ParameterList.Java_MethodParameter;
	using Java_Variable = com.eagle.programmar.Java.Java_Variable;
	using Java_Identifier_Reference = com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_MethodInvocation : PrimaryOperator, EagleRunnableWithResult, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Variable methodName;
		public Java_Variable methodName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE @OPT Java_ArgumentList argList;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			AbstractToken token = methodName.firstId.getWhich();
			if (token is Java_Identifier_Reference)
			{
				// Look it up
				string name = ((Java_Identifier_Reference) token).getValue();
				AbstractFunction fn = interpreter.findFunction(name);
				if (fn == null)
				{
					throw new Exception("Unable to find a method named " + name);
				}

				Java_Method meth = (Java_Method) fn;
				Java_ParameterList parameters = null;
				AbstractToken which = meth.typeAndName.getWhich();
				if (which is Java_Method.Java_MethodType)
				{
					Java_Method.Java_MethodType methodType = (Java_Method.Java_MethodType) which;
					parameters = methodType.parameters;
				}

				// Make sure the function args match up
				int argCount = 0;
				if (argList != null)
				{
					if (argList.arg != null && argList.arg.getWhich() != null)
					{
						argCount = 1;
					}
					if (argList.moreArgs != null && argList.moreArgs.isPresent())
					{
						argCount += argList.moreArgs.size();
					}
				}

				int paramCount = parameters.@params.getPrimaryCount();
				if (argCount != paramCount)
				{
					throw new Exception("Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
				}

				interpreter.callingFunction(name, meth);

				// Now assign all the parameters
				List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
				if (argCount > 0)
				{
					Java_Expression expr = argList.arg;
					for (int i = 0; i < argCount; i++)
					{
						if (i > 0)
						{
							expr = argList.moreArgs._elements.get(i - 1).arg;
						}
						Java_ParameterList.Java_MethodParameter param = parameters.@params.getPrimaryElement(i);
						EagleValue val = interpreter.getEagleValue(expr);
						interpreter.setSymbol(param.id, param.id.getValue(), val);
						argTypes.Add(val.getType());
					}
				}

				// Prepare to evaluate the method
				long startTime = System.nanoTime();

				// And transfer control to the method
				AbstractToken body = meth.body.getWhich();
				if (body is Java_Method.Java_MethodImplementation)
				{
					Java_Method.Java_MethodImplementation impl = (Java_Method.Java_MethodImplementation) body;
					result = interpreter.tryToInterpret(impl.block);
				}

				// The result was already put on the runtime stack
				long elapsedTime = System.nanoTime() - startTime;
				meth._callMetrics.addCallFrom(this, elapsedTime);
				meth._argumentsMetrics.calledWith(argTypes);

				// Now remove all those parameters
				interpreter.completedFunction(name, meth);
			}
			else
			{
				throw new Exception("Unable to call method " + methodName);
			}
			return result;
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Java_Variable variable = this.methodName;
			if (variable.firstId.getWhich() is Java_Identifier_Reference)
			{
				Java_Identifier_Reference id = (Java_Identifier_Reference) variable.firstId.getWhich();
				List<AbstractExpression> args = new List<AbstractExpression>();
				if (this.argList != null && this.argList.isPresent())
				{
					args.Add(transformer.transformExpression(generator, this.argList.arg));
					if (this.argList.moreArgs != null && this.argList.moreArgs.isPresent())
					{
						foreach (Java_ArgumentList.Java_MoreArguments arg in this.argList.moreArgs._elements)
						{
							args.Add(transformer.transformExpression(generator, arg.arg));
						}
					}
				}

				AbstractVariable var = generator.newVariable(id.getValue());
				return generator.newMethodInvocation(var, args, this);
			}
			throw new Exception("Can't handle: " + this);
		}

		public static Java_Expression generateInvocation(Java_Variable var, List<Java_Expression> args, AbstractToken source)
		{
			Java_MethodInvocation invoke = new Java_MethodInvocation();
			invoke.methodName = new Java_Variable();
			invoke.methodName.firstId = var.firstId;
			invoke.leftParen = new PunctuationLeftParen();
			invoke.argList = Java_ArgumentList.createArgumentList(args);
			if (invoke.argList != null)
			{
				invoke.argList.setPresent(true);
			}
			invoke.rightParen = new PunctuationRightParen();

			invoke.setTransformationSource(source);
			return Java_Generator.wrapExpression(invoke);
		}
	}

}
