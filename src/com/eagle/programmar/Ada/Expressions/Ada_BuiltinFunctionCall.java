// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ada_BuiltinFunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Ada_KeywordChoice func = new Ada_KeywordChoice("Append", "Length", "Slice", "To_Unbounded_String");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Ada_Expression, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (func.getValue())
		{
		case "Append":
			if (args.getPrimaryCount() != 2)
			{
				throw new RuntimeException("Append requires exactly 2 arguments");
			}
			Ada_VariableExpression varExpr = (Ada_VariableExpression) args.getPrimaryElement(0).getWhich();
			Ada_Expression expr = args.getPrimaryElement(1);
			Ada_Identifier_Reference id = varExpr.variable.vars.first();
			EagleValue var = interpreter.findSymbol(id.toString());
			String val = interpreter.getStrValue(expr);
			EagleString v = new EagleString(var.forceStringValue() + val);
			interpreter.setSymbol(varExpr, id.getValue(), v);
			break;
		case "Length":
			if (args.getPrimaryCount() != 1)
			{
				throw new RuntimeException("Length requires exactly 1 argument");
			}
			Ada_Expression str1 = args.getPrimaryElement(0);
			String unb1 = interpreter.getStrValue(str1);
			interpreter.pushInt(unb1.length());
			break;
		case "Slice":
			if (args.getPrimaryCount() != 3)
			{
				throw new RuntimeException("Slice requires exactly 3 arguments");
			}
			Ada_Expression str2 = args.getPrimaryElement(0);
			String unb2 = interpreter.getStrValue(str2);
			Ada_Expression scExpr = args.getPrimaryElement(1);
			int sc = interpreter.getIntValue(scExpr) - 1;
			Ada_Expression ecExpr = args.getPrimaryElement(2);
			int ec = interpreter.getIntValue(ecExpr);
			interpreter.pushStr(unb2.substring(sc, ec));
			break;
		case "To_Unbounded_String":
			if (args.getPrimaryCount() != 1)
			{
				throw new RuntimeException("To_Unbounded_String requires exactly 1 argument");
			}
			Ada_Expression str3 = args.getPrimaryElement(0);
			String unb3 = interpreter.getStrValue(str3);
			interpreter.pushStr(unb3);
			break;
		default:
			throw new RuntimeException("Unable to handle " + func.getValue());
		}
	}
}
