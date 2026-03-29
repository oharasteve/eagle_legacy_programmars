// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Javascript_Element = com.eagle.programmar.Javascript.Javascript_Element;
	using Javascript_Expression = com.eagle.programmar.Javascript.Javascript_Expression;
	using Javascript_Comment = com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
	using Javascript_Keyword = com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Javascript_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("js_if_else.asp") com.eagle.programmar.Javascript.Terminals.Javascript_Keyword IF = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("if");
		public @DOC("js_if_else.asp") Javascript_Keyword IF = new Javascript_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.Javascript.Javascript_Expression, com.eagle.tokens.punctuation.PunctuationComma> conditions;
		public SeparatedList<Javascript_Expression, PunctuationComma> conditions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Javascript.Terminals.Javascript_Comment> comment1;
		public @OPT TokenList<Javascript_Comment> comment1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.Javascript.Terminals.Javascript_Comment> comments2;
		public @OPT TokenList<Javascript_Comment> comments2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Javascript.Javascript_Element thenStatement;
		public Javascript_Element thenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<com.eagle.programmar.Javascript.Terminals.Javascript_Comment> comments3;
		public @OPT TokenList<Javascript_Comment> comments3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT Javascript_IfElseClause elseClause;
		public @OPT Javascript_IfElseClause elseClause;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public static class Javascript_IfElseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Terminals.Javascript_Keyword ELSE = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("else");
			public Javascript_Keyword ELSE = new Javascript_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Javascript_Comment comment;
			public @OPT Javascript_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Javascript.Javascript_Element elseStatement;
			public Javascript_Element elseStatement;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			Javascript_Element todo = null;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
				}
			}

			Javascript_Expression condition = conditions.first();
			bool cond = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond);
			if (cond)
			{
				todo = thenStatement;
			}
			else
			{
				// Check for 'else'
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.get(1).completedIf(true);
					todo = elseClause.elseStatement;
				}
			}

			if (todo != null)
			{
				result = interpreter.tryToInterpret(todo);
			}

			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, conditions.first());
			AbstractStatement thenPart = Javascript_StatementBlock.collectStatements(transformer, generator, thenStatement.statement);

			AbstractStatement elsePart = null;
			if (elseClause != null && elseClause.isPresent())
			{
				elsePart = Javascript_StatementBlock.collectStatements(transformer, generator, elseClause.elseStatement.statement);
			}

			return generator.newIfStatement1(cond, thenPart, elsePart, this);
		}
	}

}
