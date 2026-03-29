// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

namespace com.eagle.programmar.Powershell.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Powershell_Element = com.eagle.programmar.Powershell.Powershell_Element;
	using Powershell_EndOfLine = com.eagle.programmar.Powershell.Powershell_EndOfLine;
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_Variable = com.eagle.programmar.Powershell.Powershell_Variable;
	using Powershell_PostIncrementExpression = com.eagle.programmar.Powershell.Expressions.Powershell_PostIncrementExpression;
	using Powershell_PreIncrementExpression = com.eagle.programmar.Powershell.Expressions.Powershell_PreIncrementExpression;
	using Powershell_Relational_Expression = com.eagle.programmar.Powershell.Expressions.Powershell_Relational_Expression;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Powershell_ForStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("chapter-08?view=powershell-5.1#843-the-for-statement") com.eagle.programmar.Powershell.Terminals.Powershell_Keyword FOR = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("For");
		public @DOC("chapter-08?view=powershell-5.1#843-the-for-statement") Powershell_Keyword FOR = new Powershell_Keyword("For");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Powershell_Variable var;
		public Powershell_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Powershell.Powershell_Expression init;
		public Powershell_Expression init;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
		public PunctuationSemicolon semicolon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Powershell.Powershell_Expression stopCondition;
		public Powershell_Expression stopCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
		public PunctuationSemicolon semicolon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Powershell.Powershell_Expression iterate;
		public Powershell_Expression iterate;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT Powershell_EndOfLine eoln;
		public @OPT Powershell_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) com.eagle.tokens.TokenList<com.eagle.programmar.Powershell.Powershell_Element> stmts;
		public TokenList<Powershell_Element> stmts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			int start = interpreter.getIntValue(init);
			interpreter.setSymbol(this, var.id.getValue(), new EagleInteger(start));

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				bool keepGoing = interpreter.getBoolValue(stopCondition);
				if (!keepGoing)
				{
					break;
				}
				metric.iterate();

				foreach (Powershell_Element stmt in stmts._elements)
				{
					result = interpreter.tryToInterpret(stmt.element);
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

				interpreter.tryToInterpret(iterate);
			}

			// Have to guess to see if it was backwards
			bool backwards = guessDirection(stopCondition, iterate);

			_metrics.competedLoop(metric, backwards);
			return result;
		}

		private static bool guessDirection(Powershell_Expression testExpr, Powershell_Expression incrExpr)
		{
			AbstractToken which1 = incrExpr.getWhich();
			if (which1 is Powershell_PostIncrementExpression)
			{
				Powershell_PostIncrementExpression post = (Powershell_PostIncrementExpression) which1;
				return post.@operator.getValue().Equals("--");
			}
			if (which1 is Powershell_PreIncrementExpression)
			{
				Powershell_PreIncrementExpression pre = (Powershell_PreIncrementExpression) which1;
				return pre.@operator.getValue().Equals("--");
			}

			AbstractToken which2 = testExpr.getWhich();
			if (which2 is Powershell_Relational_Expression)
			{
				Powershell_Relational_Expression rel = (Powershell_Relational_Expression) which2;
				string oper = rel.@operator.getValue().ToLower();
				if (oper.Equals("-gt") || oper.Equals("-ge"))
				{
					return true;
				}
			}

			return false; // Just don't know :(
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string newName = Powershell_Variable.repairName(var.id.getValue());

			AbstractExpression fromExpr = transformer.transformExpression(generator, init);
			AbstractExpression asgExpr = generator.newAssignmentExpression(newName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpr, null);

			AbstractExpression termExpr = transformer.transformExpression(generator, stopCondition);
			AbstractExpression delta = transformer.transformExpression(generator, iterate);

			List<AbstractStatement> newActions = new List<AbstractStatement>();
			foreach (Powershell_Element stmt in stmts._elements)
			{
				AbstractStatement newAction = transformer.transformStatement1(generator, stmt.element.getWhich());
				newActions.Add(newAction);
			}
			return generator.newForLoopStatement(asgExpr, termExpr, delta, newActions, this);
		}
	}

}
