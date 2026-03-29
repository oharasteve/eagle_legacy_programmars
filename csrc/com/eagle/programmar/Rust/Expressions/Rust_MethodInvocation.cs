// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Rust.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Function = com.eagle.programmar.Rust.Rust_Function;
	using Rust_Parameter = com.eagle.programmar.Rust.Rust_Function.Rust_Parameter;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Variable = com.eagle.programmar.Rust.Rust_Variable;
	using Rust_Identifier_Reference = com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
	using Rust_Punctuation = com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_MethodInvocation : PrimaryOperator, EagleRunnableWithResult, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Rust_MethodWhat what;
		public Rust_MethodWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE Rust_Punctuation bang = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("!");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @NOSPACE SeparatedList<com.eagle.programmar.Rust.Rust_Expression, com.eagle.tokens.punctuation.PunctuationComma> argList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public class Rust_MethodWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Rust_MethodClass XXmethodClass;
			public Rust_MethodClass XXmethodClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_Variable XXmethodName;
			public Rust_Variable XXmethodName;
		}

		public class Rust_MethodClass : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference clsName;
			public Rust_Identifier_Reference clsName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Rust_Punctuation colonColon = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("::");
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Rust_Variable methodName;
			public  NOSPACE;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			string name;
			AbstractToken which = what.getWhich();
			if (which is Rust_MethodClass)
			{
				Rust_MethodClass mthCls = (Rust_MethodClass) which;
				name = mthCls.methodName.var.getValue();
			}
			else if (which is Rust_Variable)
			{
				Rust_Variable var = (Rust_Variable) which;
				name = var.var.getValue();
			}
			else
			{
				throw new Exception("Unexpected method name: " + which);
			}

			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + name);
			}
			Rust_Function func = (Rust_Function) fn;

			// Make sure the function args match up
			int argCount = argList.getPrimaryCount();
			int paramCount = func.funcParamDefs.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			for (int i = 0; i < argCount; i++)
			{
				Rust_Expression arg = argList.getPrimaryElement(i);
				Rust_Function.Rust_Parameter param = func.funcParamDefs.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(arg);
				interpreter.setSymbol(param, param.var.getValue(), val);
				argTypes.Add(val.getType());
			}

			// Prepare to evaluate the function
			long startTime = System.nanoTime();

			// And transfer control to the function
			interpreter.callingFunction(name, func);
			Eagle_Statement_Result result = interpreter.tryToInterpret(func.block);

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(name, func);

			return result;
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Rust_Identifier_Reference id;
			AbstractToken which = what.getWhich();
			if (which is Rust_MethodClass)
			{
				Rust_MethodClass mthCls = (Rust_MethodClass) which;
				id = mthCls.methodName.var;
			}
			else if (which is Rust_Variable)
			{
				Rust_Variable var = (Rust_Variable) which;
				id = var.var;
			}
			else
			{
				throw new Exception("Unexpected method name: " + which);
			}
			string name = id.getValue();

			if (generator.isKnownMethod(name))
			{
				List<AbstractExpression> args = new List<AbstractExpression>();
				int argCount = argList.getPrimaryCount();
				for (int i = 0; i < argCount; i++)
				{
					Rust_Expression arg = argList.getPrimaryElement(i);
					AbstractExpression newArg = transformer.transformExpression(generator, arg);
					args.Add(newArg);
				}

				AbstractVariable var = generator.newVariable(name);
				return generator.newMethodInvocation(var, args, id);
			}

			// Dang. Scale uses () for both arrays and function calls
			// It is not a function, so must be an array
			AbstractExpression index = transformer.transformExpression(generator, argList.first());
			return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, index, this);
		}

		public static Rust_Expression generateInvocation(Rust_Identifier_Reference clsName, Rust_Variable var, List<Rust_Expression> args, AbstractToken source)
		{
			Rust_MethodInvocation invoke = new Rust_MethodInvocation();
			invoke.what = new Rust_MethodWhat();

			if (clsName == null)
			{
				invoke.what.setWhich(var);
			}
			else
			{
				Rust_MethodClass methCls = new Rust_MethodClass();
				methCls.clsName = clsName;
				methCls.methodName = var;
				methCls.setPresent(true);
				invoke.what.setWhich(methCls);
			}

			invoke.leftParen = new PunctuationLeftParen();
			invoke.argList = new SeparatedList<Rust_Expression, PunctuationComma>();
			invoke.rightParen = new PunctuationRightParen();

			bool first = true;
			if (args != null)
			{
				foreach (Rust_Expression arg in args)
				{
					if (first)
					{
						first = false;
					}
					else
					{
						invoke.argList.addSecondaryElement(new PunctuationComma());
					}

					invoke.argList.addPrimaryElement(arg);
				}
			}

			return Rust_Generator.wrapExpression(invoke);
		}
	}

}
