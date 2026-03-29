// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2024

namespace com.eagle.programmar.Python
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Python_Multiline_Syntax = com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
	using Python_Additive_Expression = com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using Python_Number = com.eagle.programmar.Python.Terminals.Python_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Subscript : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_EndOfLine eoln;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @SYNTAX(com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax.class) @NOSPACE Python_SubscrExpr body;
		public @SYNTAX(typeof(Python_Multiline_Syntax)) Python_SubscrExpr body;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightBracket rightBracket;
		public @NOSPACE PunctuationRightBracket rightBracket;

		public static class Python_SubscrExpr extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_Expression subscr;
			public @OPT Python_Expression subscr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE Python_ColonSubscript subscriptStop;
			public @OPT Python_ColonSubscript subscriptStop;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @NOSPACE Python_ColonSubscript subscriptStep;
			public @OPT Python_ColonSubscript subscriptStep;
		}

		public static class Python_ColonSubscript extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_EndOfLine eoln;
			public @OPT Python_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @NOSPACE Python_Expression expr;
			public @OPT Python_Expression expr;
		}

		public static void evaluateSubscript(EagleInterpreter interpreter, EagleValue value, Python_SubscrExpr body)
		{
			if (body.subscriptStep != null && body.subscriptStep.isPresent())
			{
				throw new Exception("Cannot handle range increments yet");
			}

			if (value.isArray())
			{
				if (body.subscriptStop != null && body.subscriptStop.isPresent())
				{
					throw new Exception("Cannot handle array ranges yet");
				}
				EagleArray array = (EagleArray) value;
				int sub = interpreter.getIntValue(body.subscr);
				interpreter.pushEagleValue(array.getArrayValue().get(sub));
			}
			else
			{
				string str = value.forceStringValue();

				int start = 0;
				int stop = 0;
				if (body.subscr != null && body.subscr.isPresent())
				{
					start = interpreter.getIntValue(body.subscr);
					stop = start + 1;
				}
				if (body.subscriptStop != null && body.subscriptStop.isPresent())
				{
					stop = str.Length;
					if (body.subscriptStop.expr != null && body.subscriptStop.expr.isPresent())
					{
						stop = interpreter.getIntValue(body.subscriptStop.expr);
					}
				}
				interpreter.pushStr(str.Substring(start, stop - start));
			}
		}

		public static AbstractExpression transformSubscript(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Python_Variable var, Python_SubscrExpr body)
		{
			string name = var.var.getWhich().ToString();

			if (body.subscriptStop == null || !body.subscriptStop.isPresent())
			{
				// Just a regular array access
				AbstractExpression subExpr = transformer.transformExpression(generator, body.subscr);
				return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subExpr, body);
			}

			if (body.subscriptStep != null && body.subscriptStep.isPresent())
			{
				throw new Exception("Cannot handle subscripts with steps: " + body.subscriptStep);
			}
			AbstractExpression theStr = generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, body);

			bool hasStart = body.subscr != null && body.subscr.isPresent();
			bool hasColon = body.subscriptStop != null && body.subscriptStop.isPresent();
			bool hasStop = hasColon && body.subscriptStop.expr != null && body.subscriptStop.expr.isPresent();

			AbstractExpression startExpr = null;
			AbstractExpression stopExpr = null;
			EagleGenerator.SubstringECEnum whichEC = EagleGenerator.SubstringECEnum.GIVEN_EC_PLUS_ONE;
			if (hasStart)
			{
				startExpr = transformer.transformExpression(generator, body.subscr);
				if (hasStop)
				{
					// Case I: a[1:2]
					stopExpr = transformer.transformExpression(generator, body.subscriptStop.expr);
				}
				else if (hasColon)
				{
					// Case II: a[1:]
					stopExpr = generator.newLengthFunction(theStr, null);
				}
	//			else
	//			{
	//				// Case III: a[1]
	//				stopExpr = generator.newNumberExpression("1", null);
	//				whichEC = SubstringECEnum.GIVEN_NC;
	//			}
			}
			else if (hasStop)
			{
				// Case IV: a[:2]
				startExpr = generator.newNumberExpression("0", null);
				stopExpr = transformer.transformExpression(generator, body.subscriptStop.expr);
			}
			else
			{
				throw new Exception("Subscripts need either a start or stop (or both): " + body);
			}

			return generator.newSubstringFunction(theStr, startExpr, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, whichEC, stopExpr, true, body);
		}

		public static Python_Subscript generateExpression(AbstractExpression sc, EagleGenerator.SubstringSCEnum whichSC, EagleGenerator.SubstringECEnum whichEC, AbstractExpression ecOrnc, AbstractToken source)
		{
			Python_Subscript subscr = new Python_Subscript();
			subscr.leftBracket = new PunctuationLeftBracket();
			subscr.body = new Python_SubscrExpr();
			subscr.body.subscriptStep = null;
			subscr.rightBracket = new PunctuationRightBracket();

			switch (whichSC)
			{
			case FIRST_CHAR_IS_ZERO:
				subscr.body.subscr = (Python_Expression) sc;
				subscr.body.subscr.setPresent(true);
				break;
			case FIRST_CHAR_IS_ONE:
				Python_Expression one = Python_Number.generateNumberExpression("1", source);
				Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
				Python_Expression scMinusOne = Python_Additive_Expression.generateAdditive(types, (Python_Expression) sc, EagleGenerator.AdditiveEnum.MINUS, one, source);
				subscr.body.subscr = scMinusOne;
				subscr.body.subscr.setPresent(true);
				break;
			}

			subscr.body.subscriptStop = new Python_ColonSubscript();
			subscr.body.subscriptStop.setPresent(true);
			subscr.body.subscriptStop.colon = new PunctuationColon();
			switch (whichEC)
			{
			case GIVEN_EC:
				if (ecOrnc != null)
				{
					switch (whichSC)
					{
					case FIRST_CHAR_IS_ZERO:
						Python_Expression one = Python_Number.generateNumberExpression("1", source);
						Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
						Python_Expression ecPlusOne = Python_Additive_Expression.generateAdditive(types, (Python_Expression) ecOrnc, EagleGenerator.AdditiveEnum.PLUS, one, source);
						subscr.body.subscriptStop.expr = ecPlusOne;
						break;
					case FIRST_CHAR_IS_ONE:
						subscr.body.subscriptStop.expr = (Python_Expression) ecOrnc;
						break;
					}
					subscr.body.subscriptStop.expr.setPresent(true);
				}
				break;
			case GIVEN_EC_PLUS_ONE:
				if (ecOrnc != null)
				{
					subscr.body.subscriptStop.expr = (Python_Expression) ecOrnc;
					subscr.body.subscriptStop.expr.setPresent(true);
				}
				break;
			case GIVEN_NC:
				Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
				Python_Expression scPlusNc = Python_Additive_Expression.generateAdditive(types, subscr.body.subscr, EagleGenerator.AdditiveEnum.PLUS, (Python_Expression) ecOrnc, source);
				subscr.body.subscriptStop.expr = scPlusNc;
				subscr.body.subscriptStop.expr.setPresent(true);
				break;
			case GIVEN_NEITHER:
				break;
			}

			subscr.setTransformationSource(source);
			return subscr;
		}
	}

}
