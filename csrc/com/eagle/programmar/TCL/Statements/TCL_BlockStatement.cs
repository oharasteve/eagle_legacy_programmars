// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

namespace com.eagle.programmar.TCL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using TCL_Element = com.eagle.programmar.TCL.TCL_Element;
	using TCL_Statement = com.eagle.programmar.TCL.TCL_Element.TCL_Statement;
	using TCL_Variable = com.eagle.programmar.TCL.TCL_Variable;
	using TCL_EndOfLine = com.eagle.programmar.TCL.Terminals.TCL_EndOfLine;
	using TCL_Keyword = com.eagle.programmar.TCL.Terminals.TCL_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_BlockStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TCL_EndOfLine endOfLine;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TCL_GlobalVariables globals;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.TCL.TCL_Element> statements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

		public class TCL_GlobalVariables : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.TCL.Terminals.TCL_Keyword GLOBAL = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("global");
			public TCL_Keyword GLOBAL = new TCL_Keyword("global");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.TCL.TCL_Variable> vars;
			public TokenList<TCL_Variable> vars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.TCL.Terminals.TCL_EndOfLine endOfLine;
			public TCL_EndOfLine endOfLine;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (TCL_Element element in statements._elements)
			{
				result = interpreter.tryToInterpret(element);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
			return result;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> stmts = new List<AbstractStatement>();
			if (statements != null && statements.isPresent())
			{
				foreach (TCL_Element element in statements._elements)
				{
					int nstmts = element.statements.getPrimaryCount();
					for (int i = 0; i < nstmts; i++)
					{
						TCL_Element.TCL_Statement stmt = element.statements.getPrimaryElement(i);
						List<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
						if (newStmts != null)
						{
							foreach (AbstractStatement newStmt in newStmts)
							{
								stmts.Add(newStmt);
							}
						}
					}
				}
			}

			return generator.newBlockStatement(stmts, this);
		}
	}

}
