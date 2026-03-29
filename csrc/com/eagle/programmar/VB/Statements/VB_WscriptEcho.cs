// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 22, 2012

namespace com.eagle.programmar.VB.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class VB_WscriptEcho : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_Keyword WSCRIPT = new com.eagle.programmar.VB.Terminals.VB_Keyword("wscript");
		public VB_Keyword WSCRIPT = new VB_Keyword("wscript");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Terminals.VB_KeywordChoice ECHO = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("echo", "quit");
		public VB_KeywordChoice ECHO = new VB_KeywordChoice("echo", "quit");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.VB.VB_Expression expr;
		public VB_Expression expr;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			EagleValue result = interpreter.getEagleValue(expr);
			switch (ECHO.ToString().ToLower())
			{
			case "echo":
				Console.WriteLine(result.ToString());
				return Eagle_Statement_Result.NORMAL;
			case "quit":
				interpreter._exitCode = result.forceIntegerValue();
				return Eagle_Statement_Result.BREAK;
			}
			throw new Exception("Unable to handle " + ECHO.ToString());
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			switch (ECHO.ToString().ToLower())
			{
			case "echo":
				AbstractExpression line = transformer.transformExpression(generator, expr);
				return generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, true, false, this);
			case "quit":
				AbstractExpression code = transformer.transformExpression(generator, expr);
				return generator.newExitStatement(code, this);
			}
			throw new Exception("Unable to handle " + ECHO.ToString());
		}
	}

}
