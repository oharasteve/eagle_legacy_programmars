// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

namespace com.eagle.programmar.Java.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Label = com.eagle.programmar.Java.Java_Label;
	using Java_Statement = com.eagle.programmar.Java.Java_Statement;
	using Java_ParenthesizedExpression = com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
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

	public class Java_IfStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE Java_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("statements.html#14.9") com.eagle.programmar.Java.Terminals.Java_Keyword IF = new com.eagle.programmar.Java.Terminals.Java_Keyword("if");
		public @DOC("statements.html#14.9") Java_Keyword IF = new Java_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE Java_Expression condition;
		public @NOSPACE Java_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comment1;
		public @OPT TokenList<Java_Comment> comment1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public @NOSPACE PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comment2;
		public @OPT TokenList<Java_Comment> comment2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Java.Java_Statement thenStatement;
		public Java_Statement thenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT Java_IfElseClause elseClause;
		public @OPT Java_IfElseClause elseClause;

		public static class Java_IfElseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comment3;
			public @OPT TokenList<Java_Comment> comment3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NEWLINE Java_Keyword ELSE = new com.eagle.programmar.Java.Terminals.Java_Keyword("else");
			public @NEWLINE Java_Keyword ELSE = new Java_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Java_Comment comment;
			public @OPT Java_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Java.Java_Statement elseStatement;
			public Java_Statement elseStatement;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			Java_Statement todo = null;

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
				result = interpreter.tryToInterpret(todo.getWhich());
			}

			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);
			AbstractStatement thenPart = Java_StatementBlock.collectStatements(transformer, generator, thenStatement);

			AbstractStatement elsePart = null;
			if (elseClause != null && elseClause.isPresent())
			{
				elsePart = Java_StatementBlock.collectStatements(transformer, generator, elseClause.elseStatement);
			}

			return generator.newIfStatement1(cond, thenPart, elsePart, this);
		}

		public static Java_Statement generateIfElseOne(Java_Expression cond, Java_Statement thenStmt, Java_Statement elseStmt, AbstractToken source)
		{
			Java_IfStatement ifStmt = new Java_IfStatement();
			ifStmt.leftParen = new PunctuationLeftParen();
			ifStmt.rightParen = new PunctuationRightParen();

			AbstractToken which = cond.getWhich();
			if (which is Java_ParenthesizedExpression)
			{
				Java_ParenthesizedExpression parensExpr = (Java_ParenthesizedExpression) which;
				// Remove redundant parens
				ifStmt.condition = parensExpr.expression;
			}
			else
			{
				ifStmt.condition = cond;
			}

			ifStmt.thenStatement = thenStmt;

			if (elseStmt != null)
			{
				ifStmt.elseClause = new Java_IfElseClause();
				ifStmt.elseClause.setPresent(true);
				ifStmt.elseClause.elseStatement = elseStmt;
				ifStmt.elseClause.elseStatement.setPresent(true);
			}

			ifStmt.setTransformationSource(source);
			return Java_Generator.wrapStatement(ifStmt);
		}

		public static Java_Statement generateIfElseMany(Java_Expression cond, List<Java_Statement> thenStatements, List<Java_Statement> elseStatements, AbstractToken source)
		{
			Java_Statement thenBlock = Java_StatementBlock.generateBlock(thenStatements, source);

			Java_Statement elseBlock = null;
			if (elseStatements != null && elseStatements.size() > 0)
			{
				elseBlock = Java_StatementBlock.generateBlock(elseStatements, source);
			}

			return generateIfElseOne(cond, thenBlock, elseBlock, source);
		}
	}

}
