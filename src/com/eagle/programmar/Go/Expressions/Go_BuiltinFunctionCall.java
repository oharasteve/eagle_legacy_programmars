// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Format;
import com.eagle.programmar.Go.Expressions.Go_BuiltinFunctionCall.Go_BuiltinFunc.Go_BuiltinTwoNames;
import com.eagle.programmar.Go.Terminals.Go_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Go_BuiltinFunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Go_BuiltinFunc funcName;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) SeparatedList<Go_Expression, PunctuationComma> arguments;
	public @S(60) PunctuationRightParen rightParen;

	public static class Go_BuiltinFunc extends TokenChooser
	{
		public @CHOICE Go_KeywordChoice LEN = new Go_KeywordChoice("len");
		
		public @CHOICE static class Go_BuiltinTwoNames extends TokenSequence
		{
			public @S(10) Go_KeywordChoice name1 = new Go_KeywordChoice("fmt");
			public @S(20) PunctuationPeriod dot;
			public @S(30) Go_KeywordChoice name2 = new Go_KeywordChoice("Printf");
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name;
		if (funcName.getWhich() instanceof Go_BuiltinTwoNames)
		{
			Go_BuiltinTwoNames built = (Go_BuiltinTwoNames) funcName.getWhich();
			name = built.name1.getValue() + "." + built.name2.getValue();
		}
		else
		{
			Go_KeywordChoice built = (Go_KeywordChoice) funcName.getWhich();
			name = built.getValue();
		}
		
		switch (name.toString())
		{
		case "fmt.Printf":
			String formatted = Go_Format.format(interpreter, arguments);
			if (formatted.endsWith("\\n"))
			{
				formatted = formatted.substring(0, formatted.length()-2);
			}
			System.out.println(formatted);
			return;
		case "len":
			String str = interpreter.getStrValue(arguments.first());
			interpreter.pushInt(str.length());
			return;
		}

		throw new RuntimeException("Can't handle BuiltIn's other than Printf: " + name);
	}
}
