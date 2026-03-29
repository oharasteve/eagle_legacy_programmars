// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

namespace com.eagle.programmar.Python.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Python_ComplexStatement = com.eagle.programmar.Python.Python_ComplexStatement;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_MultilineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_ElseStartOfLine = com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("compound_stmts.html#the-if-statement") @NOSPACE Python_Keyword IF = new com.eagle.programmar.Python.Terminals.Python_Keyword("if");
		public @DOC("compound_stmts.html#the-if-statement") Python_Keyword IF = new Python_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Expression condition;
		public Python_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationColon colon;
		public @NOSPACE PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @PYDENT Python_StatementBlock ifThenStatements;
		public @PYDENT Python_StatementBlock ifThenStatements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Python_IfElif> ifElif;
		public @OPT TokenList<Python_IfElif> ifElif;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Python_IfElse ifElse;
		public @OPT Python_IfElse ifElse;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<com.eagle.programmar.Python.Terminals.Python_Comment> comments;
		public @OPT TokenList<Python_Comment> comments;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public static class Python_IfElif extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_EndOfLine eoln;
			public @OPT Python_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NEWLINE Python_ElseStartOfLine soln = new com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine();
			public @NEWLINE Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Python_Keyword ELIF = new com.eagle.programmar.Python.Terminals.Python_Keyword("elif");
			public @NOSPACE Python_Keyword ELIF = new Python_Keyword("elif");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Python.Python_Expression condition;
			public Python_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE PunctuationColon colon;
			public @NOSPACE PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @PYDENT Python_StatementBlock elifStatements;
			public @PYDENT Python_StatementBlock elifStatements;
		}

		public static class Python_IfElse extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_EndOfLine eoln;
			public @OPT Python_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NEWLINE Python_ElseStartOfLine soln = new com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine();
			public @NEWLINE Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Python_Keyword ELSE = new com.eagle.programmar.Python.Terminals.Python_Keyword("else");
			public @NOSPACE Python_Keyword ELSE = new Python_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationColon colon;
			public @NOSPACE PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @PYDENT Python_StatementBlock ifElseStatements;
			public @PYDENT Python_StatementBlock ifElseStatements;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			Python_StatementBlock todo = null;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));

				if (ifElif != null)
				{
					foreach (Python_IfElif elif in ifElif._elements)
					{
						_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELIF));
					}
				}

				if (ifElse != null && ifElse.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, ifElse.ELSE));
				}
			}

			bool cond1 = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond1);
			if (cond1)
			{
				todo = ifThenStatements;
			}
			else
			{
				int seq = 1;
				// Check for each 'else if'
				if (ifElif != null)
				{
					foreach (Python_IfElif elif in ifElif._elements)
					{
						bool cond2 = interpreter.getBoolValue(elif.condition);
						_metrics.get(seq).completedIf(cond2);
						seq++;
						if (cond2)
						{
							todo = elif.elifStatements;
							break;
						}
					}
				}

				// Check for 'else'
				if (todo == null)
				{
					if (ifElse != null && ifElse.isPresent())
					{
						_metrics.get(seq).completedIf(true);
						todo = ifElse.ifElseStatements;
					}
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
			AbstractExpression cond = transformer.transformExpression(generator, condition);

			if (ifElif != null && ifElif.size() > 0)
			{
				throw new Exception("if/elif is not yet implemented in Python");
			}

			List<AbstractStatement> thenParts = transformer.transformStatement(generator, ifThenStatements);

			List<AbstractStatement> elseParts = null;
			if (ifElse != null && ifElse.isPresent())
			{
				elseParts = transformer.transformStatement(generator, ifElse.ifElseStatements);
			}

			return generator.newIfStatement(cond, thenParts, elseParts, this);
		}

		public static Python_ComplexStatement generateIfElseOne(Python_Expression cond, Python_ComplexStatement thenStmt, Python_ComplexStatement elseStmt, AbstractToken source)
		{
			List<Python_ComplexStatement> thens = new List<Python_ComplexStatement>();
			thens.Add(thenStmt);

			List<Python_ComplexStatement> elses = null;
			if (elseStmt != null)
			{
				elses = new List<Python_ComplexStatement>();
				elses.Add(elseStmt);
			}

			return generateIfElseMany(cond, thens, elses, source);
		}

		public static Python_ComplexStatement generateIfElseMany(Python_Expression cond, List<Python_ComplexStatement> thenStmts, List<Python_ComplexStatement> elseSmts, AbstractToken source)
		{
			Python_IfStatement ifStmt = new Python_IfStatement();
			ifStmt.condition = cond;
			ifStmt.colon = new PunctuationColon();

			ifStmt.ifThenStatements = new Python_StatementBlock();
			Python_MultilineStatement thenMulti = new Python_MultilineStatement();
			ifStmt.ifThenStatements.setWhich(thenMulti);
			thenMulti.statements = new TokenList<Python_ComplexStatement>();
			foreach (Python_ComplexStatement stmt in thenStmts)
			{
				thenMulti.statements.addToken(stmt);
			}

			if (elseSmts != null && elseSmts.size() > 0)
			{
				ifStmt.ifElse = new Python_IfElse();
				ifStmt.ifElse.setPresent(true);
				ifStmt.ifElse.colon = new PunctuationColon();
				ifStmt.ifElse.ifElseStatements = new Python_StatementBlock();
				Python_MultilineStatement elseMulti = new Python_MultilineStatement();
				ifStmt.ifElse.ifElseStatements.setWhich(elseMulti);
				elseMulti.statements = new TokenList<Python_ComplexStatement>();
				foreach (Python_ComplexStatement stmt in elseSmts)
				{
					elseMulti.statements.addToken(stmt);
				}
			}

			ifStmt.setTransformationSource(source);
			return Python_Generator.wrapStatement(ifStmt);
		}
	}

}
