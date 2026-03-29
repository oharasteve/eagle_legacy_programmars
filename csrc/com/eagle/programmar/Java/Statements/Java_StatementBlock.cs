// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2024

namespace com.eagle.programmar.Java.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Label = com.eagle.programmar.Java.Java_Label;
	using Java_Statement = com.eagle.programmar.Java.Java_Statement;
	using Java_StatementOrComment = com.eagle.programmar.Java.Java_StatementOrComment;
	using Java_Syntax = com.eagle.programmar.Java.Java_Syntax;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
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
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_StatementBlock : TokenSequence, EagleRunnableWithResult, EagleScope.EagleScopeInterface, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Java_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Java_Keyword STATIC = new com.eagle.programmar.Java.Terminals.Java_Keyword("static");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @INDENT PunctuationLeftBrace leftBrace;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Java.Java_StatementOrComment> statements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OUTDENT PunctuationRightBrace rightBrace;
		public  OUTDENT;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Java_StatementOrComment stmt in statements._elements)
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
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Java.Java_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

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
			foreach (Java_StatementOrComment stmtOrComment in statements._elements)
			{
				if (stmtOrComment.getWhich() is Java_Statement)
				{
					Java_Statement stmt1 = (Java_Statement) stmtOrComment.getWhich();
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

		public static Java_Statement generateBlock(List<Java_Statement> stmts, AbstractToken source)
		{
			Java_StatementBlock blk = new Java_StatementBlock();
			blk.leftBrace = new PunctuationLeftBrace();
			blk.rightBrace = new PunctuationRightBrace();
			blk.statements = new TokenList<Java_StatementOrComment>();
			blk.statements.setPresent(true);
			foreach (Java_Statement stmt in stmts)
			{
				Java_StatementOrComment stmtOrComment = new Java_StatementOrComment();
				stmtOrComment.setWhich(stmt);
				blk.statements.addToken(stmtOrComment);
			}
			return Java_Generator.wrapStatement(blk);
		}

		public static AbstractStatement collectStatements(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Java_Statement statement)
		{
			List<AbstractStatement> newStmts = new List<AbstractStatement>();

			if (statement.getWhich() is Java_StatementBlock)
			{
				Java_StatementBlock block = (Java_StatementBlock) statement.getWhich();
				foreach (Java_StatementOrComment stmt1 in block.statements._elements)
				{
					if (stmt1.getWhich() is Java_Statement)
					{
						Java_Statement stmt2 = (Java_Statement) stmt1.getWhich();
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
