// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using FSharp_SingleOrMultiLineStatement = com.eagle.programmar.FSharp.FSharp_Element.FSharp_SingleOrMultiLineStatement;
	using FSharp_Expression = com.eagle.programmar.FSharp.FSharp_Expression;
	using FSharp_Keyword = com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
	using FSharp_StartOfLine = com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("conditional-expressions-if-then-else") com.eagle.programmar.FSharp.Terminals.FSharp_Keyword IF = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("if");
		public @DOC("conditional-expressions-if-then-else") FSharp_Keyword IF = new FSharp_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.FSharp_Expression condition;
		public FSharp_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.FSharp.Terminals.FSharp_Keyword THEN = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("then");
		public FSharp_Keyword THEN = new FSharp_Keyword("then");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.FSharp.FSharp_Element.FSharp_SingleOrMultiLineStatement ifThenStatement;
		public FSharp_SingleOrMultiLineStatement ifThenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<FSharp_IfElif> ifElifs;
		public @OPT TokenList<FSharp_IfElif> ifElifs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT FSharp_IfElse ifElseBlock;
		public @OPT FSharp_IfElse ifElseBlock;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public static class FSharp_IfElif extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine soln = new com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine();
			public FSharp_StartOfLine soln = new FSharp_StartOfLine();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.Terminals.FSharp_Keyword ELIF = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("elif");
			public FSharp_Keyword ELIF = new FSharp_Keyword("elif");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.FSharp.FSharp_Expression condition;
			public FSharp_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.FSharp.FSharp_Element.FSharp_SingleOrMultiLineStatement elifStatement;
			public FSharp_SingleOrMultiLineStatement elifStatement;
		}

		public static class FSharp_IfElse extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine soln = new com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine();
			public FSharp_StartOfLine soln = new FSharp_StartOfLine();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.Terminals.FSharp_Keyword ELSE = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("else");
			public FSharp_Keyword ELSE = new FSharp_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.FSharp.FSharp_Element.FSharp_SingleOrMultiLineStatement ifElseStatement;
			public FSharp_SingleOrMultiLineStatement ifElseStatement;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			FSharp_SingleOrMultiLineStatement todo = null;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));

				if (ifElifs != null)
				{
					foreach (FSharp_IfElif elif in ifElifs._elements)
					{
						_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELIF));
					}
				}

				if (ifElseBlock != null && ifElseBlock.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, ifElseBlock.ELSE));
				}
			}

			bool cond1 = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond1);
			if (cond1)
			{
				todo = ifThenStatement;
			}
			else
			{
				int seq = 1;
				// Check for each 'else if'
				if (ifElifs != null)
				{
					foreach (FSharp_IfElif elif in ifElifs._elements)
					{
						bool cond2 = interpreter.getBoolValue(elif.condition);
						_metrics.get(seq).completedIf(cond2);
						seq++;
						if (cond2)
						{
							todo = elif.elifStatement;
							break;
						}
					}
				}

				// Check for 'else'
				if (todo == null)
				{
					if (ifElseBlock != null && ifElseBlock.isPresent())
					{
						_metrics.get(seq).completedIf(true);
						todo = ifElseBlock.ifElseStatement;
					}
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

			if (ifElifs != null && ifElifs.size() > 0)
			{
				throw new Exception("if/elif is not yet implemented in F#");
			}

			List<AbstractStatement> thenParts = transformer.transformStatement(generator, ifThenStatement.getWhich());

			List<AbstractStatement> elseParts = null;
			if (ifElseBlock != null && ifElseBlock.isPresent())
			{
				elseParts = transformer.transformStatement(generator, ifElseBlock.ifElseStatement.getWhich());
			}

			return generator.newIfStatement(cond, thenParts, elseParts, this);
		}
	}

}
