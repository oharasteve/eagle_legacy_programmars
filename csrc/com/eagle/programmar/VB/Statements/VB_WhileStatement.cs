// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2024

namespace com.eagle.programmar.VB.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using VB_Element = com.eagle.programmar.VB.VB_Element;
	using VB_Statement = com.eagle.programmar.VB.VB_Element.VB_Statement;
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_EndOfLine = com.eagle.programmar.VB.Terminals.VB_EndOfLine;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class VB_WhileStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("statements/while-end-while-statement") com.eagle.programmar.VB.Terminals.VB_Keyword WHILE1 = new com.eagle.programmar.VB.Terminals.VB_Keyword("While");
		public @DOC("statements/while-end-while-statement") VB_Keyword WHILE1 = new VB_Keyword("While");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.VB_Expression condition;
		public VB_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Terminals.VB_EndOfLine eoln;
		public VB_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.VB.VB_Element> actions;
		public TokenList<VB_Element> actions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.VB.Terminals.VB_KeywordChoice WEND = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("Wend", "End");
		public VB_KeywordChoice WEND = new VB_KeywordChoice("Wend", "End");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT VB_Keyword WHILE2 = new com.eagle.programmar.VB.Terminals.VB_Keyword("While");
		public @OPT VB_Keyword WHILE2 = new VB_Keyword("While");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, WHILE1);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				bool cond = interpreter.getBoolValue(condition);
				if (!cond)
				{
					break;
				}

				metric.iterate();

				foreach (VB_Element stmt in actions._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}

				if (result == Eagle_Statement_Result.BREAK)
				{
					metric.broke();
					result = Eagle_Statement_Result.NORMAL;
					break;
				}
				else if (result == Eagle_Statement_Result.CONTINUE)
				{
					metric.continued();
					result = Eagle_Statement_Result.NORMAL;
				}
				else if (result == Eagle_Statement_Result.RETURN)
				{
					break;
				}
			}

			_metrics.competedLoop(metric, false);
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);
			List<AbstractStatement> whileTrue = new List<AbstractStatement>();

			foreach (VB_Element statement in actions._elements)
			{
				for (int i = 0; i < statement.baseStatements.getPrimaryCount(); i++)
				{
					VB_Element.VB_Statement baseStatement = statement.baseStatements.getPrimaryElement(i);
					foreach (AbstractStatement stmt in transformer.transformStatement(generator, baseStatement.getWhich()))
					{
						whileTrue.Add(stmt);
					}
				}
			}

			AbstractStatement stmt = generator.newWhileStatement(cond, whileTrue, this);
			return stmt;
		}
	}

}
