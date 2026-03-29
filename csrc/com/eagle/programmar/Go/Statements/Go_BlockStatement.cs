// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

namespace com.eagle.programmar.Go.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Go_Statement = com.eagle.programmar.Go.Go_Statement;
	using Go_EOLN = com.eagle.programmar.Go.Terminals.Go_EOLN;
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

	public class Go_BlockStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Terminals.Go_EOLN eoln1;
		public Go_EOLN eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Go.Go_Statement> statements;
		public TokenList<Go_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Go_EOLN eoln2;
		public  OPT;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Go_Statement stmt in statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
			return result;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();
			foreach (Go_Statement statement in statements._elements)
			{
				List<AbstractStatement> stmts = transformer.transformStatement(generator, statement.getWhich());
				if (stmts != null)
				{
					foreach (AbstractStatement stmt in stmts)
					{
						result.Add(stmt);
					}
				}
			}

			return generator.newBlockStatement(result, this);
		}

		public static List<AbstractStatement> collectStatements(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Go_Statement statement)
		{
			// Lots of extra work here to avoid duplicated braces; {{stmts}} is not nice.
			List<AbstractStatement> newStmts;
			if (statement.getWhich() is Go_BlockStatement)
			{
				Go_BlockStatement block = (Go_BlockStatement) statement.getWhich();
				newStmts = new List<AbstractStatement>();
				foreach (Go_Statement blockStmt in block.statements._elements)
				{
					List<AbstractStatement> oneStmt = transformer.transformStatement(generator, blockStmt.getWhich());
					if (oneStmt != null)
					{
						foreach (AbstractStatement newStmt in oneStmt)
						{
							newStmts.Add(newStmt);
						}
					}
				}
			}
			else
			{
				// Rare case I think, def fn = stmt, with no braces
				newStmts = transformer.transformStatement(generator, statement.getWhich());
			}
			return newStmts;
		}
	}

}
