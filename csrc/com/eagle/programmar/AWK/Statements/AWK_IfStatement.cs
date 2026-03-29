// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using AWK_Action = com.eagle.programmar.AWK.AWK_Action;
	using AWK_StatementOrComment = com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
	using AWK_Expression = com.eagle.programmar.AWK.AWK_Expression;
	using AWK_Statements = com.eagle.programmar.AWK.AWK_Statements;
	using AWK_Statement = com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
	using AWK_EndOfLine = com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
	using AWK_Keyword = com.eagle.programmar.AWK.Terminals.AWK_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_IfStatement : TokenSequence, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#index-if-statement-2") com.eagle.programmar.AWK.Terminals.AWK_Keyword IF = new com.eagle.programmar.AWK.Terminals.AWK_Keyword("if");
		public @DOC("#index-if-statement-2") AWK_Keyword IF = new AWK_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.AWK.AWK_Expression condition;
		public AWK_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT AWK_EndOfLine eoln;
		public @OPT AWK_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) AWK_IfBlock blockThen;
		public AWK_IfBlock blockThen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT AWK_IfElse ifelse;
		public @OPT AWK_IfElse ifelse;

		public static class AWK_IfElse extends TokenSequence implements AbstractStatement
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.Terminals.AWK_Keyword ELSE = new com.eagle.programmar.AWK.Terminals.AWK_Keyword("else");
			public AWK_Keyword ELSE = new AWK_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT AWK_EndOfLine eoln;
			public @OPT AWK_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) AWK_IfBlock blockElse;
			public AWK_IfBlock blockElse;
		}

		public static class AWK_IfBlock extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Statements XXstmt;
			public AWK_Statements XXstmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Action XXaction;
			public AWK_Action XXaction;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			AWK_IfBlock todo;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
				if (ifelse != null && ifelse.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, ifelse.ELSE));
				}
			}

			bool cond1 = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond1);
			if (cond1)
			{
				todo = blockThen;
			}
			else
			{
				todo = null;

				// Check for 'else'
				if (ifelse != null && ifelse.isPresent())
				{
					_metrics.get(1).completedIf(true);
					todo = ifelse.blockElse;
				}
			}

			if (todo != null)
			{
				result = Eagle_Statement_Result.NORMAL;
				if (todo.getWhich() is AWK_Statements)
				{
					AWK_Statements stmts = (AWK_Statements) todo.getWhich();
					for (int i = 0; i < stmts.statements.getPrimaryCount(); i++)
					{
						AWK_Statements.AWK_Statement stmt = stmts.statements.getPrimaryElement(i);
						result = interpreter.tryToInterpret(stmt);
						if (result != Eagle_Statement_Result.NORMAL)
						{
							break;
						}
					}
				}
				else
				{
					AWK_Action action = (AWK_Action) todo.getWhich();
					foreach (AWK_Action.AWK_StatementOrComment stmt in action.statements._elements)
					{
						result = interpreter.tryToInterpret(stmt);
						if (result != Eagle_Statement_Result.NORMAL)
						{
							break;
						}
					}
				}
			}

			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);
			List<AbstractStatement> ifTrue = new List<AbstractStatement>();
			List<AbstractStatement> ifFalse = new List<AbstractStatement>();

			List<AbstractStatement> stmts1 = transformer.transformStatement(generator, blockThen.getWhich());
			if (stmts1 != null)
			{
				foreach (AbstractStatement stmt1 in stmts1)
				{
					ifTrue.Add(stmt1);
				}
			}

			if (ifelse != null && ifelse.isPresent())
			{
				List<AbstractStatement> stmts2 = transformer.transformStatement(generator, ifelse.blockElse.getWhich());
				if (stmts2 != null)
				{
					foreach (AbstractStatement stmt2 in stmts2)
					{
						ifFalse.Add(stmt2);
					}
				}
			}

			AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
			return stmt;
		}
	}

}
