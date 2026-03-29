// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 24, 2024

namespace com.eagle.programmar.FSharp
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleValue = com.eagle.math.EagleValue;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using FSharp_Literal = com.eagle.programmar.FSharp.Terminals.FSharp_Literal;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_Format
	{
		// Handle %d and %s. Super simple ones only for now
		public static string format(EagleInterpreter interpreter, TokenList<FSharp_Expression> args, List<EagleGenerator.TypeEnum> argTypes)
		{
			string fmt = interpreter.getStrValue(args.first());
			fmt = fmt.replaceAll("\\\\n", "");
			if (fmt.IndexOf('%') < 0)
			{
				return fmt;
			}

			StringBuilder sb = new StringBuilder();
			int sc = 0;
			int nc = fmt.Length;
			int index = 0;
			int numArgs = args.size();
			while (sc < nc)
			{
				// Pull in a text string
				int pct = fmt.IndexOf('%', sc);
				if (pct < 0)
				{
					sb.Append(fmt.Substring(sc, nc - sc));
					break; // Done -- no more %
				}
				if (pct > sc)
				{
					sb.Append(fmt.Substring(sc, pct - sc));
				}

				// Insert a variable name (or expression)
				index++;
				if (index < numArgs)
				{
					FSharp_Expression expr = args._elements.get(index);
					EagleValue val = interpreter.getEagleValue(expr);
					string piece = val.forceStringValue();
					argTypes.Add(val.getType());
					sb.Append(piece);
				}

				// Look for the next piece
				sc = pct + 2;
			}
			return sb.ToString();
		}

		public static AbstractExpression transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, TokenList<FSharp_Expression> argList, List<EagleGenerator.TypeEnum> metrics)
		{
			Oper2Types types = null;
			if (metrics != null)
			{
				types = new Oper2Types();
				types._type1 = EagleGenerator.TypeEnum.STRING;
			}

			FSharp_Expression fmtExpr = argList.first();
			if (!(fmtExpr.getWhich() is FSharp_Literal))
			{
				throw new Exception("Format must be a literal for Printf");
			}
			FSharp_Literal lit = (FSharp_Literal) fmtExpr.getWhich();
			string fmt = lit.getValue();
			if (fmt.StartsWith("\"", StringComparison.Ordinal))
			{
				fmt = fmt.Substring(1, (fmt.Length - 1) - 1);
			}
			if (fmt.EndsWith("\\n", StringComparison.Ordinal))
			{
				fmt = fmt.Substring(0, fmt.Length - 2);
			}
			fmt = fmt.replaceAll("\\\\\"", "\\\""); int nc = fmt.Length; int sc = fmt.IndexOf("%", StringComparison.Ordinal); int pctLen = check(fmt, sc, nc); if (sc < 0 || pctLen == 0) {return generator.newLiteralExpression(fmt, fmtExpr);} int prev = 0; AbstractExpression fullExpr = null; for (int i = 1; i < argList.size(); i++)
			{
				string nextString = fmt.Substring(prev, sc - prev); if (nextString.length() > 0)
				{
					if (metrics != null) {types._type2 = EagleGenerator.TypeEnum.STRING;} AbstractExpression nextExpr = generator.newLiteralExpression(nextString, null); if (fullExpr == null) {fullExpr = nextExpr;} else {fullExpr = generator.newAppendExpression(types, fullExpr, nextExpr, null);}
				}
				if (metrics != null) {types._type2 = metrics[i - 1];} FSharp_Expression nextArg = argList._elements.get(i); AbstractExpression nextExpr = transformer.transformExpression(generator, nextArg); if (fullExpr == null) {fullExpr = nextExpr;} else {fullExpr = generator.newAppendExpression(types, fullExpr, nextExpr, null);} prev = sc + 2; sc = fmt.IndexOf("%", prev, StringComparison.Ordinal); pctLen = check(fmt, sc, nc); if (sc < 0 || pctLen == 0) break;
			}
			string lastString = fmt.Substring(prev); if (lastString.length() > 0) {AbstractExpression lastStr = generator.newLiteralExpression(lastString, null); fullExpr = generator.newAppendExpression(types, fullExpr, lastStr, null);} return fullExpr;
			}
			private static int check(string fmt, int sc, int nc)
			{
			if (sc >= 0 && sc + 1 < nc)
			{
				char nextch = fmt[sc + 1]; if (nextch == 'd' || nextch == 's') {return 1;}
			}
			return 0;
			}
			}

		}
