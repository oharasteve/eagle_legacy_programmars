// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 24, 2025

namespace com.eagle.programmar.Rust.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Statement = com.eagle.programmar.Rust.Rust_Statement;
	using AbstractToken = com.eagle.tokens.AbstractToken;
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

	public class Rust_Block_Statement : TokenSequence, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT PunctuationLeftBrace leftBrace;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Rust.Rust_Statement> statements;
		public TokenList<Rust_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OUTDENT PunctuationRightBrace rightBrace;
		public  OUTDENT;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Rust_Statement stmt in statements._elements)
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
			foreach (Rust_Statement statement in statements._elements)
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

		public static Rust_Statement generateBlock(List<Rust_Statement> stmts, AbstractToken source)
		{
			Rust_Block_Statement block = new Rust_Block_Statement();
			block.leftBrace = new PunctuationLeftBrace();
			block.rightBrace = new PunctuationRightBrace();
			block.statements = new TokenList<Rust_Statement>();
			block.statements.setPresent(true);
			foreach (Rust_Statement stmt in stmts)
			{
				block.statements.addToken(stmt);
			}
			return Rust_Generator.wrapStatement(block);
		}

		public static List<AbstractStatement> collectStatements(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Rust_Statement statement)
		{
			// Lots of extra work here to avoid duplicated braces; {{stmts}} is not nice.
			List<AbstractStatement> newStmts;
			if (statement.getWhich() is Rust_Block_Statement)
			{
				Rust_Block_Statement block = (Rust_Block_Statement) statement.getWhich();
				newStmts = new List<AbstractStatement>();
				foreach (Rust_Statement blockStmt in block.statements._elements)
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
