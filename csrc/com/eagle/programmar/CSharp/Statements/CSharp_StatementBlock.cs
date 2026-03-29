// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

namespace com.eagle.programmar.CSharp.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Statement = com.eagle.programmar.CSharp.CSharp_Statement;
	using CSharp_StatementOrComment = com.eagle.programmar.CSharp.CSharp_StatementOrComment;
	using CSharp_Syntax = com.eagle.programmar.CSharp.CSharp_Syntax;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
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

	public class CSharp_StatementBlock : TokenSequence, EagleRunnableWithResult, EagleScope.EagleScopeInterface, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT PunctuationLeftBrace leftBrace;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.CSharp.CSharp_StatementOrComment> statements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OUTDENT PunctuationRightBrace rightBrace;
		public  OUTDENT;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (CSharp_StatementOrComment stmt in statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
			return result;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.CSharp.CSharp_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();
			foreach (CSharp_StatementOrComment stmtOrComment in statements._elements)
			{
				if (stmtOrComment.getWhich() is CSharp_Statement)
				{
					CSharp_Statement stmt1 = (CSharp_Statement) stmtOrComment.getWhich();
					List<AbstractStatement> stmts2 = transformer.transformStatement(generator, stmt1.getWhich());
					if (stmts2 != null)
					{
						foreach (AbstractStatement stmt2 in stmts2)
						{
							result.Add(stmt2);
						}
					}
				}
			}

			return generator.newBlockStatement(result, this);
		}

		public static CSharp_Statement generateBlock(List<CSharp_Statement> stmts, AbstractToken source)
		{
			CSharp_StatementBlock blk = new CSharp_StatementBlock();
			blk.leftBrace = new PunctuationLeftBrace();
			blk.rightBrace = new PunctuationRightBrace();
			blk.statements = new TokenList<CSharp_StatementOrComment>();
			blk.statements.setPresent(true);

			foreach (CSharp_Statement stmt in stmts)
			{
				CSharp_StatementOrComment stmtComm = new CSharp_StatementOrComment();
				stmtComm.setWhich(stmt);
				blk.statements.addToken(stmtComm);

				// If the parent block gets the 'while' as the parent, line numbers in the
				// side-by-side will pick up the 'while' instead of the first statement.
				if (blk.getTransformationSource() == null)
				{
					blk.setTransformationSource(stmt.getTransformationSource());
				}
			}
			return CSharp_Generator.wrapStatement(blk);
		}

		public static AbstractStatement collectStatements(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, CSharp_Statement statement)
		{
			List<AbstractStatement> newStmts = new List<AbstractStatement>();

			if (statement.getWhich() is CSharp_StatementBlock)
			{
				CSharp_StatementBlock block = (CSharp_StatementBlock) statement.getWhich();
				foreach (CSharp_StatementOrComment stmt1 in block.statements._elements)
				{
					if (stmt1.getWhich() is CSharp_Statement)
					{
						CSharp_Statement stmt2 = (CSharp_Statement) stmt1.getWhich();
						List<AbstractStatement> stmts3 = transformer.transformStatement(generator, stmt2.getWhich());
						foreach (AbstractStatement stmt3 in stmts3)
						{
							newStmts.Add(stmt3);
						}
					}
				}
			}
			else
			{
				List<AbstractStatement> stmts4 = transformer.transformStatement(generator, statement.getWhich());
				foreach (AbstractStatement stmt4 in stmts4)
				{
					newStmts.Add(stmt4);
				}
			}

			return generator.newBlockStatement(newStmts, statement);
		}
	}

}
