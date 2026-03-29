// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

namespace com.eagle.programmar.CSharp.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Statement = com.eagle.programmar.CSharp.CSharp_Statement;
	using CSharp_ParenthesizedExpression = com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
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

	public class CSharp_IfStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE @DOC("statements/selection-statements") com.eagle.programmar.CSharp.Terminals.CSharp_Keyword IF = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("if");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE CSharp_Expression condition;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comments1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.CSharp.CSharp_Statement thenStatement;
		public CSharp_Statement thenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT CSharp_IfElseClause elseClause;
		public  OPT;

		public class CSharp_IfElseClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comments2;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NEWLINE CSharp_Keyword ELSE = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("else");
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comments3;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CSharp.CSharp_Statement elseStatement;
			public CSharp_Statement elseStatement;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			CSharp_Statement todo = null;

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
				result = interpreter.tryToInterpret(todo);
			}

			return result;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);
			AbstractStatement thenPart = CSharp_StatementBlock.collectStatements(transformer, generator, thenStatement);

			AbstractStatement elsePart = null;
			if (elseClause != null && elseClause.isPresent())
			{
				elsePart = CSharp_StatementBlock.collectStatements(transformer, generator, elseClause.elseStatement);
			}

			return generator.newIfStatement1(cond, thenPart, elsePart, this);
		}

		public static CSharp_Statement generateIfElseOne(CSharp_Expression cond, CSharp_Statement thenStmt, CSharp_Statement elseStmt, AbstractToken source)
		{
			CSharp_IfStatement ifStmt = new CSharp_IfStatement();
			ifStmt.leftParen = new PunctuationLeftParen();
			ifStmt.rightParen = new PunctuationRightParen();

			AbstractToken which = cond.getWhich();
			if (which is CSharp_ParenthesizedExpression)
			{
				CSharp_ParenthesizedExpression parensExpr = (CSharp_ParenthesizedExpression) which;
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
				ifStmt.elseClause = new CSharp_IfElseClause();
				ifStmt.elseClause.setPresent(true);
				ifStmt.elseClause.elseStatement = elseStmt;
				ifStmt.elseClause.elseStatement.setPresent(true);
			}

			ifStmt.setTransformationSource(source);
			return CSharp_Generator.wrapStatement(ifStmt);
		}

		public static CSharp_Statement generateIfElseMany(CSharp_Expression cond, List<CSharp_Statement> thenStatements, List<CSharp_Statement> elseStatements, AbstractToken source)
		{
			CSharp_Statement blockThen = CSharp_StatementBlock.generateBlock(thenStatements, source);

			CSharp_Statement blockElse = null;
			if (elseStatements != null && elseStatements.Count > 0)
			{
				blockElse = CSharp_StatementBlock.generateBlock(elseStatements, source);
			}

			return generateIfElseOne(cond, blockThen, blockElse, source);
		}
	}

}
