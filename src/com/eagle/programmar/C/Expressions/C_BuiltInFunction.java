// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Format;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_BuiltInFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) C_KeywordChoice builtinFunction = new C_KeywordChoice("exit", "printf", "strcat", "strcmp", "strcpy",
			"strdup", "strlen", "strncmp");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<C_Expression, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinFunction.toString())
		{
		case "printf":
			String formatted = C_Format.format(interpreter, args);
			System.out.println(formatted);
			return;
		case "strcat":
			AbstractToken first1 = args.first().getWhich();
			if (first1 instanceof C_VariableExpression)
			{
				C_Variable var = ((C_VariableExpression) first1).variable;
				C_Identifier_Reference id = (C_Identifier_Reference) var.firstId.getWhich();
				String varName = id.getValue();
				String str = interpreter.getStrValue(args.getPrimaryElement(1));
				String oldVal = interpreter.findSymbol(varName).forceStringValue();
				EagleString val = new EagleString(oldVal + str);
				interpreter.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), varName,
						val);
				return;
			}
			break;
		case "strcmp":
			AbstractToken first2 = args.first().getWhich();
			String left = interpreter.getStrValue(first2);
			String right = interpreter.getStrValue(args.getPrimaryElement(1).getWhich());
			interpreter.pushInt(left.compareTo(right));
			return;
		case "strcpy":
			AbstractToken first3 = args.first().getWhich();
			if (first3 instanceof C_VariableExpression)
			{
				C_Variable var = ((C_VariableExpression) first3).variable;
				C_Identifier_Reference id = (C_Identifier_Reference) var.firstId.getWhich();
				String varName = id.getValue();
				String str = interpreter.getStrValue(args.getPrimaryElement(1));
				EagleString val = new EagleString(str);
				interpreter.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), varName,
						val);
				return;
			}
			break;
		case "strdup":
			AbstractToken first4 = args.first().getWhich();
			String str = interpreter.getStrValue(first4);
			interpreter.pushStr(str);
			return;
		case "strlen":
			AbstractToken first5 = args.first().getWhich();
			String string = interpreter.getStrValue(first5);
			interpreter.pushInt(string.length());
			return;
		case "strncmp":
			AbstractToken first6 = args.first().getWhich();
			String left2 = interpreter.getStrValue(first6);
			String right2 = interpreter.getStrValue(args.getPrimaryElement(1).getWhich());
			int nc = interpreter.getIntValue(args.getPrimaryElement(2).getWhich());
			if (left2.length() > nc) left2 = left2.substring(0, nc);
			if (right2.length() > nc) right2 = right2.substring(0, nc);
			interpreter.pushInt(left2.compareTo(right2));
			return;
		}

		throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtinFunction);
	}
}
