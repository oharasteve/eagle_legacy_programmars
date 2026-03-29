// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 3, 2025

namespace com.eagle.programmar.Java.Functions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Java_MathMinMaxFunc : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_KeywordChoice MINMAX = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice("min", "max");
		public Java_KeywordChoice MINMAX = new Java_KeywordChoice("min", "max");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE SeparatedList<com.eagle.programmar.Java.Java_Expression, com.eagle.tokens.punctuation.PunctuationComma> expressions;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			int result = interpreter.getIntValue(expressions.first());
			bool isMin = true;
			if (MINMAX.getValue().Equals("max"))
			{
				isMin = false;
			}

			for (int i = 1; i < expressions.getPrimaryCount(); i++)
			{
				int next = interpreter.getIntValue(expressions.getPrimaryElement(i));
				if (isMin)
				{
					if (next < result)
					{
						result = next;
					}
				}
				else // isMax
				{
					if (next > result)
					{
						result = next;
					}
				}
			}
			interpreter.pushInt(result);
		}

		public virtual Java_Expression generateMinMax(bool isMin, List<Java_Expression> exprs, AbstractToken source)
		{
			this.MINMAX.setValue(isMin ? "min" : "max");
			this.leftParen = new PunctuationLeftParen();
			this.expressions = new SeparatedList<Java_Expression, PunctuationComma>();
			this.expressions.addPrimaryElement(exprs[0]);
			for (int i = 1; i < exprs.Count; i++)
			{
				this.expressions.addSecondaryElement(new PunctuationComma());
				this.expressions.addPrimaryElement(exprs[i]);
			}
			this.rightParen = new PunctuationRightParen();

			this.setTransformationSource(source);
			return Java_Generator.wrapExpression(this);
		}
	}

}
