// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.C.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using C_ArgumentList = com.eagle.programmar.C.C_ArgumentList;
	using C_MoreArgument = com.eagle.programmar.C.C_ArgumentList.C_MoreArgument;
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Function = com.eagle.programmar.C.C_Function;
	using C_FunctionImplementation = com.eagle.programmar.C.C_Function.C_FunctionImplementation;
	using C_FunctionRegularParameter = com.eagle.programmar.C.C_Function.C_FunctionRegularParameter;
	using C_Function_ParameterDefs = com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
	using C_Generic = com.eagle.programmar.C.C_Generic;
	using C_StatementOrComment = com.eagle.programmar.C.C_Program.C_StatementOrComment;
	using C_Variable = com.eagle.programmar.C.C_Variable;
	using C_Identifier_Reference = com.eagle.programmar.C.Symbols.C_Identifier_Reference;
	using CPlus_NamespaceList = com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
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

	public class C_FunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CPlus_NamespaceList namespace;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.C_Variable functionName;
		public C_Variable functionName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_Generic generic;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT C_ArgumentList argList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			AbstractToken token = functionName.firstId.getWhich();
			if (token is C_Identifier_Reference)
			{
				C_Identifier_Reference id = (C_Identifier_Reference) token;
				string fnName = id.getValue();

				// Look through our list of functions
				AbstractFunction fn = interpreter.findFunction(fnName);
				if (fn == null)
				{
					throw new Exception("Unable to find a function named " + fnName);
				}
				C_Function func = (C_Function) fn;

				// Count the parameters
				C_Function.C_Function_ParameterDefs @params = func.parameters;
				int expected = 0;
				if (@params.param != null && @params.param.isPresent())
				{
					C_Function.C_FunctionRegularParameter param = (C_Function.C_FunctionRegularParameter) @params.param.getWhich();
					if (param.id != null)
					{
						string paramName = param.id.getValue();
						if (!string.ReferenceEquals(paramName, null) && !paramName.Equals("void"))
						{
							// 'f(void)' is special here meaning no arguments
							expected++;
						}
					}
				}
				if (@params.moreParams != null && @params.moreParams.isPresent())
				{
					expected += @params.moreParams.size();
				}

				int actual = 0;
				if (argList != null)
				{
					if (argList.arg != null && argList.arg.getWhich() != null)
					{
						actual++;
					}
					if (argList.moreArgs != null && argList.moreArgs.isPresent())
					{
						actual += argList.moreArgs.size();
					}
				}

				if (actual != expected)
				{
					throw new Exception("Function " + fnName + ", expected params = " + expected + ", but actual args = " + actual);
				}

				interpreter.callingFunction(fnName, func);

				// Assign all the parameters
				List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
				if (argList != null)
				{
					AbstractToken arg = argList.arg.getWhich();
					C_Function.C_FunctionRegularParameter param = (C_Function.C_FunctionRegularParameter) @params.param.getWhich();
					for (int i = 0; i < actual; i++)
					{
						if (i > 0)
						{
							arg = argList.moreArgs._elements.get(i - 1).arg;
							param = (C_Function.C_FunctionRegularParameter) @params.moreParams._elements.get(i - 1).param.getWhich();
						}

						EagleValue val = interpreter.getEagleValue(arg);
						interpreter.setSymbol(param.id, param.id.getValue(), val);
						argTypes.Add(val.getType());
					}
				}

				// Evaluate the function
				long startTime = System.nanoTime();

				C_Function.C_FunctionImplementation impl = (C_Function.C_FunctionImplementation) func.body.getWhich();
				foreach (C_StatementOrComment stmt in impl.elements._elements)
				{
					Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}

				long elapsedTime = System.nanoTime() - startTime;
				func._callMetrics.addCallFrom(this, elapsedTime);
				func._argumentsMetrics.calledWith(argTypes);

				// Remove all the parameters
				interpreter.completedFunction(fnName, func);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which0 = functionName.firstId.getWhich();
			if (!(which0 is C_Identifier_Reference))
			{
				throw new Exception("Unable to handle " + which0);
			}
			C_Identifier_Reference id = (C_Identifier_Reference) which0;
			string name = id.getValue();
			List<AbstractExpression> args = new List<AbstractExpression>();

			if (argList != null && argList.isPresent())
			{
				AbstractToken which1 = argList.arg.getWhich();
				if (which1 is C_Expression)
				{
					C_Expression arg1 = (C_Expression) which1;
					AbstractExpression newArg1 = transformer.transformExpression(generator, arg1);
					args.Add(newArg1);

					if (argList.moreArgs != null)
					{
						foreach (C_ArgumentList.C_MoreArgument more in argList.moreArgs._elements)
						{
							AbstractToken which2 = more.arg.getWhich();
							if (which2 is C_Expression)
							{
								C_Expression arg2 = (C_Expression) which2;
								AbstractExpression newArg2 = transformer.transformExpression(generator, arg2);
								args.Add(newArg2);
							}
						}
					}
				}
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, id);
		}
	}

}
