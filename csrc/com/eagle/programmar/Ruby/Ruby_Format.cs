// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2024

namespace com.eagle.programmar.Ruby
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;

	public class Ruby_Format
	{
		public static string format(EagleInterpreter interpreter, string fmt)
		{
			int nc = fmt.Length;
			string txt = fmt.Substring(1, (nc - 1) - 1).replaceAll("\\\\\"", "\""); if (txt.IndexOf("#{", StringComparison.Ordinal) < 0) {interpreter.pushStr(txt);} StringBuilder sb = new StringBuilder(); int sc = 0; nc = txt.Length; while (sc < nc)
			{
				int first = txt.IndexOf("#{", sc, StringComparison.Ordinal); if (first < 0) {sb.append(txt.Substring(sc, nc - sc)); break;} if (first > sc) {sb.append(txt.Substring(sc, first - sc));} int second = txt.IndexOf('}', first + 2); if (second < 0) throw new Exception("Missing } in " + txt); string var = txt.Substring(first + 2, second - (first + 2)); Ruby_Expression expr = new Ruby_Expression(); if (!interpreter._parser.parseLine(var, interpreter._lang, expr)) {throw new Exception("Unable to parse expression " + var);} string val = interpreter.getStrValue(expr); sb.append(val); sc = second + 1;
			}
			return sb.ToString();
			}
			public static AbstractExpression compile(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, string fmt, AbstractToken source)
			{
			AbstractExpression result = null; int nc = fmt.Length; string txt = fmt.Substring(1, (nc - 1) - 1).replaceAll("\\\\\"", "\""); nc = txt.Length; if (nc == 0) {return generator.newLiteralExpression("", null);} int sc = 0; while (sc < nc)
			{
				int nextInsertion = txt.IndexOf("#{", sc, StringComparison.Ordinal); int ec = nextInsertion; if (nextInsertion < 0) {ec = nc;} if (ec > sc)
				{
					AbstractExpression piece1 = generator.newLiteralExpression(txt.Substring(sc, ec - sc), null); if (result == null) {result = piece1;} else {result = generator.newAdditiveExpression(null, result, EagleGenerator.AdditiveEnum.PLUS, piece1, null);}
				}
				if (nextInsertion < 0) {break;} int endInsertion = txt.IndexOf("}", nextInsertion + 2, StringComparison.Ordinal); if (endInsertion < 0) {throw new Exception("Missing } following #{");} string var = txt.Substring(nextInsertion + 2, endInsertion - (nextInsertion + 2)); AbstractExpression varExpr = generator.newVariableExpression(var, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, null); AbstractExpression strExpr = generator.newStringFunction(null, varExpr, null); if (result == null) {result = varExpr;} else {result = generator.newAdditiveExpression(null, result, EagleGenerator.AdditiveEnum.PLUS, strExpr, null);} sc = endInsertion + 1;
			}
			return result;
			}
			}

		}
