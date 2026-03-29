// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2024

namespace com.eagle.programmar.Rust
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Rust_Literal = com.eagle.programmar.Rust.Terminals.Rust_Literal;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_Format
	{
		public static string format(EagleInterpreter interpreter, SeparatedList<Rust_Expression, PunctuationComma> argList, ArgumentsMetrics metrics)
		{
			string fmt = interpreter.getStrValue(argList.first());
			if (fmt.IndexOf("{}", StringComparison.Ordinal) < 0)
			{
				return fmt;
			}

			StringBuilder sb = new StringBuilder();
			int sc = 0;
			int nc = fmt.Length;
			int index = 0;
			int numArgs = argList.getPrimaryCount();
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			while (sc < nc)
			{
				// Pull in a text string
				int nxt = fmt.IndexOf("{}", sc, StringComparison.Ordinal);
				if (nxt < 0)
				{
					sb.Append(fmt.Substring(sc, nc - sc));
					break; // Done -- no more {}
				}
				if (nxt > sc)
				{
					sb.Append(fmt.Substring(sc, nxt - sc));
				}

				// Insert a variable name (or expression)
				index++;
				if (index < numArgs)
				{
					Rust_Expression expr = argList.getPrimaryElement(index);
					EagleValue val = interpreter.getEagleValue(expr);
					string piece = val.forceStringValue();
					argTypes.Add(val.getType());
					sb.Append(piece);
				}

				// Look for the next piece
				sc = nxt + 2;
			}

			metrics.calledWith(argTypes);
			return sb.ToString();
		}

		public static AbstractExpression compile(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, SeparatedList<Rust_Expression, PunctuationComma> argList, List<EagleGenerator.TypeEnum> metrics)
		{
			Oper2Types types = null;
			if (metrics != null)
			{
				types = new Oper2Types();
				types._type1 = EagleGenerator.TypeEnum.STRING;
			}

			Rust_Expression fmtExpr = argList.first();
			if (!(fmtExpr.getWhich() is Rust_Literal))
			{
				throw new Exception("Format must be a literal for print!");
			}
			Rust_Literal lit = (Rust_Literal) fmtExpr.getWhich();
			string fmt = lit.getValue();
			if (fmt.StartsWith("\"", StringComparison.Ordinal))
			{
				fmt = fmt.Substring(1, (fmt.Length - 1) - 1).replaceAll("\\\\\"", "\"");
				}
				int sc = fmt.IndexOf("{}", StringComparison.Ordinal); if (sc < 0) {return generator.newLiteralExpression(fmt, fmtExpr);} int prev = 0; AbstractExpression fullExpr = null; for (int i = 1; i < argList.getPrimaryCount(); i++)
				{
				string nextString = fmt.Substring(prev, sc - prev); if (nextString.length() > 0)
				{
					if (metrics != null) {types._type2 = EagleGenerator.TypeEnum.STRING;} AbstractExpression nextExpr = generator.newLiteralExpression(nextString, null); if (fullExpr == null) {fullExpr = nextExpr;} else {fullExpr = generator.newAppendExpression(types, fullExpr, nextExpr, null);}
				}
				if (metrics != null) {types._type2 = metrics[i - 1];} Rust_Expression nextArg = argList.getPrimaryElement(i); AbstractExpression nextExpr = transformer.transformExpression(generator, nextArg); if (fullExpr == null) {fullExpr = nextExpr;} else {fullExpr = generator.newAppendExpression(types, fullExpr, nextExpr, null);} prev = sc + 2; sc = fmt.IndexOf("{}", prev, StringComparison.Ordinal); if (sc < 0) break;
				}
				string lastString = fmt.Substring(prev); if (lastString.length() > 0) {AbstractExpression lastStr = generator.newLiteralExpression(lastString, null); fullExpr = generator.newAppendExpression(types, fullExpr, lastStr, null);} return fullExpr;
				}
				}

			}
