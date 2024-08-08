// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Symbols.Ruby_Identifier_Reference;
import com.eagle.programmar.Ruby.Terminals.Ruby_KeywordChoice;
import com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ruby_MethodInvocation extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Ruby_Variable methodName;
	public @S(20) PunctuationPeriod dot;
	public @S(30) Ruby_KeywordChoice LEN = new Ruby_KeywordChoice("length", "start_with");
	public @S(40) @OPT Ruby_Punctuation question = new Ruby_Punctuation("?");
	public @S(50) @OPT Ruby_MethodArgs args;
	
	public static class Ruby_MethodArgs extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Ruby_Expression, PunctuationComma> argList;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ruby_Identifier_Reference strVar = methodName.vars.getPrimaryElement(0);
		EagleValue value = interpreter.findSymbol(strVar.getValue());
		String str;
		if (value.isArray())
		{
			EagleArray array = (EagleArray) value;
			int sub = interpreter.getIntValue(methodName.subscript.expr);
			EagleValue val = array.getValue(sub);
			str = val.forceStringValue();
		}
		else if (value.isString() && methodName.subscript != null && methodName.subscript.isPresent())
		{
			Ruby_RangeExpression range = (Ruby_RangeExpression) methodName.subscript.expr.getWhich();
			str = value.forceStringValue();
			int len = str.length();
			int sc = interpreter.getIntValue(range.left);
			int ec = interpreter.getIntValue(range.right) + 1;
			if (ec > len) ec = len;
			str = str.substring(sc, ec);
		}
		else
		{
			str = value.forceStringValue();
		}

		switch (LEN.getValue())
		{
		case "length":
			if (args == null || !args.isPresent())
			{
				interpreter.pushInt(str.length());
				return;
			}
			break;
		case "start_with":
			if (args != null && args.isPresent() && args.argList.getPrimaryCount() == 1)
			{
				if (args != null && args.isPresent() && args.argList.getPrimaryCount() == 1)
				{
					String patt = interpreter.getStrValue(args.argList.getPrimaryElement(0));
					interpreter.pushBool(str.startsWith(patt));
					return;
				}
			}
			break;
		}
		
		throw new RuntimeException("Unable to handle: " + LEN.getValue());
	}
}
