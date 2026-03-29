// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using Fortran_Expression = com.eagle.programmar.Fortran.Fortran_Expression;
	using Fortran_Statement = com.eagle.programmar.Fortran.Fortran_Statement;
	using Fortran_Function_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference;
	using Fortran_Variable_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
	using Fortran_EOLN = com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
	using Fortran_Keyword = com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
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

	public class Fortran_CallStatement : TokenSequence, AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @DOC("6j4m0vn7p/index.html") @S(10) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword CALL = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("CALL");
		public @S(10) Fortran_Keyword CALL = new Fortran_Keyword("CALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference variable;
		public Fortran_Function_Reference variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<com.eagle.programmar.Fortran.Fortran_Expression, com.eagle.tokens.punctuation.PunctuationComma> arguments;
		public SeparatedList<Fortran_Expression, PunctuationComma> arguments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln;
		public Fortran_EOLN eoln;

		public void interpret(EagleInterpreter interpreter)
		{
			string fnName = variable.getValue();

			AbstractFunction fn = interpreter.findFunction(fnName);
			if (fn == null || !(fn is Fortran_Subroutine))
			{
				throw new Exception("Unable to find a subroutine named " + fnName);
			}
			Fortran_Subroutine sub = (Fortran_Subroutine) fn;

			// Make sure the function args match up
			int argCount = arguments.getPrimaryCount();
			int paramCount = sub.parameters.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new Exception("Subroutine " + fnName + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			for (int i = 0; i < argCount; i++)
			{
				Fortran_Expression expr = arguments.getPrimaryElement(i);
				Fortran_Variable_Reference param = sub.parameters.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.getValue(), val);
				argTypes.Add(val.getType());
			}

			// Prepare to evaluate the procedure or function
			long startTime = System.nanoTime();

			// And transfer control to the procedure or function
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Fortran_Statement stmt in sub.statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}

			long elapsedTime = System.nanoTime() - startTime;
			sub._callMetrics.addCallFrom(this, elapsedTime);
			sub._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			for (int i = 0; i < argCount; i++)
			{
				Fortran_Variable_Reference param = sub.parameters.getPrimaryElement(i);
				interpreter.removeSymbol(param.getValue());
			}
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractExpression> args = new List<AbstractExpression>();
			int argCount = arguments.getPrimaryCount();
			for (int i = 0; i < argCount; i++)
			{
				Fortran_Expression arg = arguments.getPrimaryElement(i);
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.Add(newArg);
			}

			AbstractVariable var = generator.newVariable(variable.getValue());
			AbstractExpression expr = generator.newMethodInvocation(var, args, variable);
			return generator.newExpressionStatement(expr, variable);
		}
	}

}
