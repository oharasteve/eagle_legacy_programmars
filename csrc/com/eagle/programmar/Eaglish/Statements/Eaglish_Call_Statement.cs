// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

namespace com.eagle.programmar.Eaglish.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Eaglish_Expression = com.eagle.programmar.Eaglish.Eaglish_Expression;
	using Eaglish_Statement = com.eagle.programmar.Eaglish.Eaglish_Statement;
	using Eaglish_Identifier_Reference = com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
	using Eaglish_EndOfLine = com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
	using Eaglish_Keyword = com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_Call_Statement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword CALL = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("CALL");
		public Eaglish_Keyword CALL = new Eaglish_Keyword("CALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference funcName;
		public Eaglish_Identifier_Reference funcName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Eaglish_CallParameters callParams;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln;
		public Eaglish_EndOfLine eoln;

		public class Eaglish_CallParameters : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParenn;
			public PunctuationLeftParen leftParenn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Eaglish.Eaglish_Expression, com.eagle.tokens.punctuation.PunctuationComma> args;
			public SeparatedList<Eaglish_Expression, PunctuationComma> args;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParenn;
			public PunctuationRightParen rightParenn;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// Have to search for the FUNCTION definition
			AbstractFunction fn = interpreter.findFunction(funcName.getValue());
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + funcName.getValue());
			}
			Eaglish_Function func = (Eaglish_Function) fn;

			// Count the parameters
			int expected = func.parameterStatements.size();
			int actual = callParams.args.getPrimaryCount();
			if (actual != expected)
			{
				throw new Exception("Function " + funcName + ", expected params = " + expected + ", but actual args = " + actual);
			}

			// Assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			for (int i = 0; i < actual; i++)
			{
				Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
				Eaglish_Expression arg = callParams.args.getPrimaryElement(i);
				// interpreter.tryToInterpret(arg);
				EagleValue val = interpreter.getEagleValue(arg);
				interpreter.setSymbol(param, param.param.getValue(), val);
				argTypes.Add(val.getType());
			}

			// Evaluate the function
			long startTime = System.nanoTime();
			interpreter.callingFunction(funcName.getValue(), func);
			foreach (Eaglish_Statement stmt in func.statements._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Remove all the parameters
			interpreter.completedFunction(funcName.getValue(), func);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = funcName.getValue();
			List<AbstractExpression> args = new List<AbstractExpression>();
			int argCount = callParams.args.getPrimaryCount();
			for (int i = 0; i < argCount; i++)
			{
				Eaglish_Expression arg = callParams.args.getPrimaryElement(i);
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.Add(newArg);
			}

			AbstractVariable var = generator.newVariable(name);
			AbstractExpression invocation = generator.newMethodInvocation(var, args, this);
			return generator.newExpressionStatement(invocation, CALL);
		}
	}

}
