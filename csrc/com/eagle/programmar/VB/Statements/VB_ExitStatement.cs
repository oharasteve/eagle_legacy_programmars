// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

namespace com.eagle.programmar.VB.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class VB_ExitStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("statements/exit-statement") com.eagle.programmar.VB.Terminals.VB_Keyword EXIT = new com.eagle.programmar.VB.Terminals.VB_Keyword("exit");
		public @DOC("statements/exit-statement") VB_Keyword EXIT = new VB_Keyword("exit");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Terminals.VB_KeywordChoice FOR = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("do", "for", "function", "sub");
		public VB_KeywordChoice FOR = new VB_KeywordChoice("do", "for", "function", "sub");

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			switch (FOR.ToString())
			{
			case "do":
			case "for":
				return Eagle_Statement_Result.BREAK;
			case "function":
			case "sub":
				return Eagle_Statement_Result.RETURN;
			}

			throw new Exception("Cannot handle exit " + FOR + " yet.");
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			return generator.newBreakStatement(EXIT);
		}
	}

}
