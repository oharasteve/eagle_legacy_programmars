// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Scala_Statement = com.eagle.programmar.Scala.Scala_Statement;
	using Scala_Syntax = com.eagle.programmar.Scala.Scala_Syntax;
	using Scala_EOLN = com.eagle.programmar.Scala.Terminals.Scala_EOLN;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
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

	public class Scala_BlockStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleScope.EagleScopeInterface, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Scala.Terminals.Scala_EOLN eoln1;
		public Scala_EOLN eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Scala.Scala_Statement> statements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Scala_EOLN eoln2;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Scala.Scala_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Scala_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Scala_Statement stmt in statements._elements)
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
			foreach (Scala_Statement statement in statements._elements)
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

		public static List<AbstractStatement> collectStatements(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Scala_Statement statement)
		{
			// Lots of extra work here to avoid duplicated braces; {{stmts}} is not nice.
			List<AbstractStatement> newStmts;
			if (statement.getWhich() is Scala_BlockStatement)
			{
				Scala_BlockStatement block = (Scala_BlockStatement) statement.getWhich();
				newStmts = new List<AbstractStatement>();
				foreach (Scala_Statement blockStmt in block.statements._elements)
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
