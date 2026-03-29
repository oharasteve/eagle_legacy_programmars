// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

namespace com.eagle.programmar.Eaglish.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eaglish_Expression = com.eagle.programmar.Eaglish.Eaglish_Expression;
	using Eaglish_Keyword = com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
	using Eaglish_KeywordChoice = com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_ConditionStringMatch : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Eaglish_Expression left = new com.eagle.programmar.Eaglish.Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice matchOperator = new com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice("ENDS_WITH", "STARTS_WITH");
		public Eaglish_KeywordChoice matchOperator = new Eaglish_KeywordChoice("ENDS_WITH", "STARTS_WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Eaglish.Eaglish_Expression right = new com.eagle.programmar.Eaglish.Eaglish_Expression(this, AllowedPrecedence.HIGHER);
		public Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Eaglish_Condition_MatchAt atClause;
		public  OPT;

		public class Eaglish_Condition_MatchAt : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword AT = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("AT");
			public Eaglish_Keyword AT = new Eaglish_Keyword("AT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Eaglish_Expression position;
			public Eaglish_Expression position;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string leftStr = interpreter.getStrValue(left);
			string rightStr = interpreter.getStrValue(right);
			string oper = matchOperator.getValue();

			int sc = 0;
			if (atClause != null && atClause.isPresent())
			{
				sc = interpreter.getIntValue(atClause.position);
			}

			switch (oper)
			{
			case "ENDS_WITH":
				interpreter.pushBool(leftStr.EndsWith(rightStr, StringComparison.Ordinal));
				return;
			case "STARTS_WITH":
				interpreter.pushBool(leftStr.StartsWith(rightStr, sc));
				return;
			default:
				throw new Exception("Unable to handle " + oper);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			string oper = matchOperator.ToString();

			AbstractExpression scExpr = null;
			if (atClause != null && atClause.isPresent())
			{
				scExpr = transformer.transformExpression(generator, atClause.position);
			}

			switch (oper.ToUpper())
			{
			case "ENDS_WITH":
				return generator.newEndsWithFunction(leftExpr, rightExpr, this);
			case "STARTS_WITH":
				return generator.newStartsWithFunction(leftExpr, rightExpr, scExpr, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
			default:
				throw new Exception("Unable to handle " + oper);
			}
		}
	}
}
