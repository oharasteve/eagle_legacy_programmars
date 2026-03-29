// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.FSharp.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using FSharp_Expression = com.eagle.programmar.FSharp.FSharp_Expression;
	using FSharp_Multiline_Syntax = com.eagle.programmar.FSharp.FSharp_Syntax.FSharp_Multiline_Syntax;
	using FSharp_EndOfLine = com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
	using FSharp_Punctuation = com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_BracketBars : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation leftBracketBar = new com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation("[|");
		public FSharp_Punctuation leftBracketBar = new FSharp_Punctuation("[|");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT FSharp_EndOfLine eoln;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @SYNTAX(com.eagle.programmar.FSharp.FSharp_Syntax.FSharp_Multiline_Syntax.class) com.eagle.tokens.SeparatedList<com.eagle.programmar.FSharp.FSharp_Expression, com.eagle.tokens.punctuation.PunctuationSemicolon> expressions;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation rightBarBracket = new com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation("|]");
		public FSharp_Punctuation rightBarBracket = new FSharp_Punctuation("|]");

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleArray values = new EagleArray();
			for (int i = 0; i < expressions.getPrimaryCount(); i++)
			{
				FSharp_Expression expr = expressions.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				values.addValue(val);
			}

			interpreter.pushEagleValue(values);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (expressions != null && expressions.isPresent())
			{
				List<AbstractExpression> exprs = new List<AbstractExpression>();
				for (int i = 0; i < expressions.getPrimaryCount(); i++)
				{
					FSharp_Expression expr = expressions.getPrimaryElement(i);
					exprs.Add(transformer.transformExpression(generator, expr));
				}
				return generator.newArrayExpression(exprs, this);
			}
			return null;
		}
	}

}
