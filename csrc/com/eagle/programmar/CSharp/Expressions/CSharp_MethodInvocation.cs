// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.CSharp.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using CSharp_Argument = com.eagle.programmar.CSharp.CSharp_Argument;
	using CSharp_ArgumentOut = com.eagle.programmar.CSharp.CSharp_Argument.CSharp_ArgumentOut;
	using CSharp_ArgumentList = com.eagle.programmar.CSharp.CSharp_ArgumentList;
	using CSharp_MoreArguments = com.eagle.programmar.CSharp.CSharp_ArgumentList.CSharp_MoreArguments;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Method = com.eagle.programmar.CSharp.CSharp_Method;
	using CSharp_MethodParameter = com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter;
	using CSharp_MethodImplementation = com.eagle.programmar.CSharp.CSharp_MethodImplementation;
	using CSharp_StatementOrComment = com.eagle.programmar.CSharp.CSharp_StatementOrComment;
	using CSharp_GenericType = com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
	using CSharp_Variable = com.eagle.programmar.CSharp.CSharp_Variable;
	using CSharp_Identifier_Reference = com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
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

	public class CSharp_MethodInvocation : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Variable methodName;
		public CSharp_Variable methodName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CSharp_GenericType generic;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @NOSPACE CSharp_ArgumentList argList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			AbstractToken token = methodName.firstId.getWhich();
			if (token is CSharp_Identifier_Reference)
			{
				// Look it up
				string name = ((CSharp_Identifier_Reference) token).getValue();
				AbstractFunction fn = interpreter.findFunction(name);
				if (fn == null)
				{
					throw new Exception("Unable to find a method named " + name);
				}
				CSharp_Method meth = (CSharp_Method) fn;

				// Make sure the function args match up
				int argCount = 0;
				if (argList.arg.isPresent())
				{
					argCount = 1;
				}
				if (argList.moreArgs != null && argList.moreArgs.isPresent())
				{
					argCount += argList.moreArgs.size();
				}

				int paramCount = meth.parameters.@params.getPrimaryCount();
				if (argCount != paramCount)
				{
					throw new Exception("Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
				}

				interpreter.callingFunction(name, meth);

				// Now assign all the parameters
				List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
				if (argCount > 0)
				{
					CSharp_Argument arg = argList.arg;
					for (int i = 0; i < argCount; i++)
					{
						if (i > 0)
						{
							arg = argList.moreArgs._elements.get(i - 1).arg;
						}
						CSharp_Method.CSharp_MethodParameter param = meth.parameters.@params.getPrimaryElement(i);
						AbstractToken which = arg.getWhich();
						if (which is CSharp_Argument.CSharp_ArgumentOut)
						{
							CSharp_Expression expr = ((CSharp_Argument.CSharp_ArgumentOut) which).arg;
							EagleValue val = interpreter.getEagleValue(expr);
							interpreter.setSymbol(param.id, param.id.getValue(), val);
							argTypes.Add(val.getType());
						}
					}
				}

				// Prepare to evaluate the method
				long startTime = System.nanoTime();

				// And transfer control to the method
				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				AbstractToken body = meth.body.getWhich();
				if (body is CSharp_MethodImplementation)
				{
					CSharp_MethodImplementation impl = (CSharp_MethodImplementation) body;
					foreach (CSharp_StatementOrComment stmt in impl.block.statements._elements)
					{
						result = interpreter.tryToInterpret(stmt);
						if (result != Eagle_Statement_Result.NORMAL)
						{
							break;
						}
					}
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
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			CSharp_Variable variable = this.methodName;
			if (variable.firstId.getWhich() is CSharp_Identifier_Reference)
			{
				CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) variable.firstId.getWhich();
				List<AbstractExpression> args = new List<AbstractExpression>();
				if (this.argList != null && this.argList.isPresent())
				{
					CSharp_Expression expr1 = argList.arg.getExpression();
					args.Add(transformer.transformExpression(generator, expr1));
					if (this.argList.moreArgs != null && this.argList.moreArgs.isPresent())
					{
						foreach (CSharp_ArgumentList.CSharp_MoreArguments arg in this.argList.moreArgs._elements)
						{
							CSharp_Expression expr2 = arg.arg.getExpression();
							args.Add(transformer.transformExpression(generator, expr2));
						}
					}
				}

				AbstractVariable var = generator.newVariable(id.getValue());
				return generator.newMethodInvocation(var, args, this);
			}
			throw new Exception("Can't handle: " + this);
		}

		public static CSharp_Expression generateInvocation(CSharp_Variable var, List<CSharp_Expression> args, AbstractToken source)
		{
			CSharp_MethodInvocation invok = new CSharp_MethodInvocation();
			invok.methodName = new CSharp_Variable();
			invok.methodName.firstId = var.firstId;
			invok.leftParen = new PunctuationLeftParen();
			invok.argList = CSharp_ArgumentList.createArgumentList(args);
			if (invok.argList != null)
			{
				invok.argList.setPresent(true);
			}
			invok.rightParen = new PunctuationRightParen();

			invok.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(invok);
		}
	}

}
