// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleDouble = com.eagle.math.EagleDouble;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Operator1Metrics = com.eagle.metrics.Operator1Metrics;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Algol68_Expression = com.eagle.programmar.Algol68.Algol68_Expression;
	using Algol68_Format = com.eagle.programmar.Algol68.Terminals.Algol68_Format;
	using Algol68_Keyword = com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
	using Algol68_Punctuation = com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_PrintfStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword PRINTF = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("PRINTF");
		public Algol68_Keyword PRINTF = new Algol68_Keyword("PRINTF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation doubleLeftParen = new com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation("((");
		public Algol68_Punctuation doubleLeftParen = new Algol68_Punctuation("((");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Algol68.Terminals.Algol68_Format format;
		public Algol68_Format format;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationComma comma;
		public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Algol68.Algol68_Expression expr;
		public Algol68_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation doubleRightParen = new com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation("))");
		public Algol68_Punctuation doubleRightParen = new Algol68_Punctuation("))");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT PunctuationSemicolon semicolon;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP Operator1Metrics _metrics = null;
		private Operator1Metrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new Operator1Metrics(interpreter._metrics, PRINTF, PRINTF.getValue());
			}

			// Note that Algol68_Format is a Literal with '$' instead of ' or "
			// Hence dd instead of $dd$ below
			string fmt = interpreter.getStrValue(format);
			EagleValue val = interpreter.getEagleValue(expr);

			if (val is EagleInteger)
			{
				int? ival = Convert.ToInt32(val.forceIntegerValue());
				switch (fmt)
				{
				case "d":
					Console.Write("{0,1:D}", ival);
					break;
				case "dd":
					Console.Write("{0,2:D}", ival);
					break;
				case "ddd":
					Console.Write("{0,3:D}", ival);
					break;
				case "dddd":
					Console.Write("{0,4:D}", ival);
					break;
				default:
					throw new Exception("Unable to printf " + ival + " using $" + fmt + "$");
				}
			}

			if (val is EagleDouble)
			{
				double dval = val.forceDoubleValue();
				switch (fmt)
				{
				case "dd.d":
					Console.Write("{0,4:F1}", Convert.ToDouble(dval));
					break;
				default:
					throw new Exception("Unable to printf " + dval + " using $" + fmt + "$");
				}
			}
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Operator1Metrics.Oper1Types metric = transformer.findOperator1Metric(PRINTF);
			EagleGenerator.TypeEnum type = null;
			if (metric != null)
			{
				type = metric._type1;
			}

			string fmt = format.getValue();
			int width = 0;
			int decimals = 0;
			switch (fmt)
			{
			case "$d$":
				width = 1;
				break;
			case "$dd$":
				width = 2;
				break;
			case "$ddd$":
				width = 3;
				break;
			case "$dddd$":
				width = 4;
				break;
			case "$dd.d$":
				decimals = 1;
				break;
			default:
				throw new Exception("Unable to printf " + expr + " using " + fmt);
			}
			AbstractExpression numExpr = transformer.transformExpression(generator, expr);
			AbstractExpression line;
			if (decimals == 0)
			{
				line = generator.newFormatNumber(numExpr, width, this);
			}
			else
			{
				line = generator.newFormatDecimal(numExpr, decimals, this);
			}
			return generator.newPrintStatement(line, type, false, false, this);
		}
	}

}
