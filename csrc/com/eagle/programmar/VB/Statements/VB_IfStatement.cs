// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

namespace com.eagle.programmar.VB.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using VB_Element = com.eagle.programmar.VB.VB_Element;
	using VB_Statement = com.eagle.programmar.VB.VB_Element.VB_Statement;
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_Comment = com.eagle.programmar.VB.Terminals.VB_Comment;
	using VB_EndOfLine = com.eagle.programmar.VB.Terminals.VB_EndOfLine;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
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

	public class VB_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("statements/if-then-else-statement") com.eagle.programmar.VB.Terminals.VB_Keyword IF1 = new com.eagle.programmar.VB.Terminals.VB_Keyword("if");
		public @DOC("statements/if-then-else-statement") VB_Keyword IF1 = new VB_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.VB_Expression condition;
		public VB_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Terminals.VB_Keyword THEN = new com.eagle.programmar.VB.Terminals.VB_Keyword("then");
		public VB_Keyword THEN = new VB_Keyword("then");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) VB_IfType ifType;
		public VB_IfType ifType;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public static class VB_IfType extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_IfOneLiner XXoneLiner;
			public VB_IfOneLiner XXoneLiner;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_IfMultiLiner XXmultiLiner;
			public VB_IfMultiLiner XXmultiLiner;
		}

		public static class VB_IfOneLiner extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.VB_Element.VB_Statement thenStatement;
			public VB_Element.VB_Statement thenStatement;
		}

		public static class VB_IfMultiLiner extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_EndOfLine eoln;
			public VB_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.VB.VB_Element> thenStatement;
			public TokenList<VB_Element> thenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<VB_IfElseIfClause> elseIfClause;
			public @OPT TokenList<VB_IfElseIfClause> elseIfClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT VB_IfElseClause elseClause;
			public @OPT VB_IfElseClause elseClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.VB.Terminals.VB_Keyword END = new com.eagle.programmar.VB.Terminals.VB_Keyword("end");
			public VB_Keyword END = new VB_Keyword("end");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.VB.Terminals.VB_Keyword IF2 = new com.eagle.programmar.VB.Terminals.VB_Keyword("if");
			public VB_Keyword IF2 = new VB_Keyword("if");
		}

		public static class VB_IfElseIfClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.VB.Terminals.VB_Comment> comments;
			public @OPT TokenList<VB_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Terminals.VB_Keyword ELSEIF = new com.eagle.programmar.VB.Terminals.VB_Keyword("elseif");
			public VB_Keyword ELSEIF = new VB_Keyword("elseif");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.VB_Expression condition;
			public VB_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.VB.Terminals.VB_Keyword THEN = new com.eagle.programmar.VB.Terminals.VB_Keyword("then");
			public VB_Keyword THEN = new VB_Keyword("then");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.VB.Terminals.VB_EndOfLine eoln;
			public VB_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<com.eagle.programmar.VB.VB_Element> elseIfStatement;
			public TokenList<VB_Element> elseIfStatement;
		}

		public static class VB_IfElseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.VB.Terminals.VB_Comment> comments;
			public @OPT TokenList<VB_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Terminals.VB_Keyword ELSE = new com.eagle.programmar.VB.Terminals.VB_Keyword("else");
			public VB_Keyword ELSE = new VB_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Terminals.VB_EndOfLine eoln;
			public VB_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.VB.VB_Element> elseStatement;
			public TokenList<VB_Element> elseStatement;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			TokenList<VB_Element> todo = null;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF1));

				if (ifType.getWhich() is VB_IfMultiLiner)
				{
					VB_IfMultiLiner multi = (VB_IfMultiLiner) ifType.getWhich();
					if (multi.elseIfClause != null && multi.elseIfClause.isPresent())
					{
						foreach (VB_IfElseIfClause elif in multi.elseIfClause._elements)
						{
							_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELSEIF));
						}
					}

					if (multi.elseClause != null && multi.elseClause.isPresent())
					{
						_metrics.add(new IfCondMetrics(interpreter._metrics, multi.elseClause.ELSE));
					}
				}
			}

			bool cond1 = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond1);

			if (ifType.getWhich() is VB_IfOneLiner)
			{
				VB_IfOneLiner oneLiner = (VB_IfOneLiner) ifType.getWhich();
				if (cond1)
				{
					result = interpreter.tryToInterpret(oneLiner.thenStatement);
				}
			}
			else
			{
				VB_IfMultiLiner multi = (VB_IfMultiLiner) ifType.getWhich();
				if (cond1)
				{
					todo = multi.thenStatement;
				}
				else
				{
					int seq = 1;
					// Check for each 'else if'
					if (multi.elseIfClause != null && multi.elseIfClause.isPresent())
					{
						foreach (VB_IfElseIfClause elif in multi.elseIfClause._elements)
						{
							bool cond2 = interpreter.getBoolValue(elif.condition);
							_metrics.get(seq).completedIf(cond2);
							seq++;
							if (cond2)
							{
								todo = elif.elseIfStatement;
								break;
							}
						}
					}

					// Check for 'else'
					if (todo == null)
					{
						if (multi.elseClause != null && multi.elseClause.isPresent())
						{
							_metrics.get(seq).completedIf(true);
							todo = multi.elseClause.elseStatement;
						}
					}
				}

				if (todo != null)
				{
					result = Eagle_Statement_Result.NORMAL;
					foreach (VB_Element stmt in todo._elements)
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

			AbstractToken which = ifType.getWhich();
			if (which is VB_IfOneLiner)
			{
				VB_IfOneLiner oneLiner = (VB_IfOneLiner) which;
				VB_Element statement = new VB_Element();
				statement.baseStatements = new SeparatedList<VB_Element.VB_Statement, PunctuationColon>();
				statement.baseStatements.addPrimaryElement(oneLiner.thenStatement);
				foreach (AbstractStatement stmt in transformer.transformStatement(generator, statement.baseStatements.first().getWhich()))
				{
					ifTrue.Add(stmt);
				}
			}
			else
			{
				VB_IfMultiLiner multiLiner = (VB_IfMultiLiner) which;
				foreach (VB_Element statement in multiLiner.thenStatement._elements)
				{
					for (int i = 0; i < statement.baseStatements.getPrimaryCount(); i++)
					{
						VB_Element.VB_Statement baseStatement = statement.baseStatements.getPrimaryElement(i);
						List<AbstractStatement> stmts = transformer.transformStatement(generator, baseStatement.getWhich());
						if (stmts != null)
						{
							foreach (AbstractStatement stmt in stmts)
							{
								ifTrue.Add(stmt);
							}
						}
					}
				}

				if (multiLiner.elseIfClause != null && multiLiner.elseIfClause.isPresent() && multiLiner.elseIfClause.size() > 0)
				{
					throw new Exception("Can't handle VB elseif yet");
				}

				if (multiLiner.elseClause != null && multiLiner.elseClause.isPresent())
				{
					foreach (VB_Element statement in multiLiner.elseClause.elseStatement._elements)
					{
						for (int i = 0; i < statement.baseStatements.getPrimaryCount(); i++)
						{
							VB_Element.VB_Statement baseStatement = statement.baseStatements.getPrimaryElement(i);
							foreach (AbstractStatement stmt in transformer.transformStatement(generator, baseStatement.getWhich()))
							{
								ifFalse.Add(stmt);
							}
						}
					}
				}
			}

			AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
			return stmt;
		}
	}

}
