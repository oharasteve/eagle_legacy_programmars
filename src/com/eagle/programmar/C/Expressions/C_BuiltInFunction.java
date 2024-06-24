// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_Expression;
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
	public @S(10) C_KeywordChoice builtinFunction = new C_KeywordChoice("printf",
			"strcat", "strcmp", "strcpy", "strdup");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<C_Expression, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtinFunction.toString())
		{
		case "printf":
			// AbstractToken fmt = args.first().getWhich();
			AbstractToken arg = args.getPrimaryElement(1).getWhich();
			String result = interpreter.getStrValue(arg);
			System.out.println(result);
			return;
		case "strcat":
			AbstractToken first1 = args.first().getWhich();
			if (first1 instanceof C_VariableExpression)
			{
				C_Variable var = ((C_VariableExpression)first1).variable;
				C_Identifier_Reference id = (C_Identifier_Reference) var.firstId.getWhich();
				String varName = id.getValue();
				String str = interpreter.getStrValue(args.getPrimaryElement(1));
				String oldVal = interpreter._symbolTable.findSymbol(varName).forceStringValue();
				EagleString val = new EagleString(oldVal + str);
				interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
						varName, val);
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
				C_Variable var = ((C_VariableExpression)first3).variable;
				C_Identifier_Reference id = (C_Identifier_Reference) var.firstId.getWhich();
				String varName = id.getValue();
				String str = interpreter.getStrValue(args.getPrimaryElement(1));
				EagleString val = new EagleString(str);
				interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
						varName, val);
				return;
			}
			break;
		case "strdup":
			AbstractToken first4 = args.first().getWhich();
			String str = interpreter.getStrValue(first4);
			interpreter.pushStr(str);
			return;
		}
		
		throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtinFunction);
	}
}
