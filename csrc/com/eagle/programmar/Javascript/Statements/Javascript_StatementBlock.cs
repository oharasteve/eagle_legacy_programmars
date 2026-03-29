// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

namespace com.eagle.programmar.Javascript.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Javascript_Label = com.eagle.programmar.Javascript.Javascript_Element.Javascript_Label;
	using Javascript_StatementOrComment = com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
	using Javascript_Statement = com.eagle.programmar.Javascript.Javascript_Statement;
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

	public class Javascript_StatementBlock : TokenSequence, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Javascript_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @INDENT PunctuationLeftBrace leftBrace;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment> statements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OUTDENT PunctuationRightBrace rightBrace;
		public  OUTDENT;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Javascript_StatementOrComment stmt in statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
			return result;
		}

		public static AbstractStatement collectStatements(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Javascript_Statement statement)
		{
			List<AbstractStatement> newStmts = new List<AbstractStatement>();

			if (statement.getWhich() is Javascript_StatementBlock)
			{
				Javascript_StatementBlock block = (Javascript_StatementBlock) statement.getWhich();
				foreach (Javascript_StatementOrComment stmt1 in block.statements._elements)
				{
					if (stmt1.getWhich() is Javascript_Statement)
					{
						Javascript_Statement stmt2 = (Javascript_Statement) stmt1.getWhich();
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

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();
			foreach (Javascript_StatementOrComment stmtOrComment in statements._elements)
			{
				if (stmtOrComment.getWhich() is Javascript_Statement)
				{
					Javascript_Statement stmt1 = (Javascript_Statement) stmtOrComment.getWhich();
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
	}

}
