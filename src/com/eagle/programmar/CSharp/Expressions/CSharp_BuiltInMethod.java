// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_BuiltInMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) CSharp_BuiltinMethodArgs right;
	
	public static class CSharp_BuiltinMethodArgs extends TokenChooser
	{
		public @CHOICE CSharp_BuiltinNoArgs XXnoArgs;
		public @CHOICE CSharp_BuiltinOneArg XXoneArg;
		public @CHOICE CSharp_BuiltinTwoArgs XXtwoArgs;
	}
	
	public static class CSharp_BuiltinNoArgs extends TokenSequence
	{
		public @S(10) CSharp_KeywordChoice builtin = new CSharp_KeywordChoice("Length");
	}
	
	public static class CSharp_BuiltinOneArg extends TokenSequence
	{
		public @S(10) CSharp_KeywordChoice builtin = new CSharp_KeywordChoice("StartsWith", "Substring");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSharp_Expression param;
		public @S(40) PunctuationRightParen rightParen;
	}
	
	public static class CSharp_BuiltinTwoArgs extends TokenSequence
	{
		public @S(10) CSharp_KeywordChoice builtin = new CSharp_KeywordChoice("Substring");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSharp_Expression param1;
		public @S(40) PunctuationComma comma;
		public @S(50) CSharp_Expression param2;
		public @S(60) PunctuationRightParen rightParen;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		String name = "";
		if (right != null && right.getWhich() instanceof CSharp_BuiltinTwoArgs)
		{
			CSharp_BuiltinTwoArgs twoArgs = (CSharp_BuiltinTwoArgs) right.getWhich();
			name = twoArgs.builtin.getValue();
			switch (name)
			{
			case "Substring":
				int sc = interpreter.getIntValue(twoArgs.param1);
				int nc = interpreter.getIntValue(twoArgs.param2);
				interpreter.pushStr(leftStr.substring(sc, sc+nc));
				return;
			}
		}
		else if (right != null && right.getWhich() instanceof CSharp_BuiltinOneArg)
		{
			CSharp_BuiltinOneArg oneArg = (CSharp_BuiltinOneArg) right.getWhich();
			name = oneArg.builtin.getValue();
			switch (name)
			{
			case "StartsWith":
				String patt = interpreter.getStrValue(oneArg.param);
				interpreter.pushBool(leftStr.startsWith(patt));
				return;
			case "Substring":
				int sc = interpreter.getIntValue(oneArg.param);
				interpreter.pushStr(leftStr.substring(sc));
				return;
			}
		}
		else if (right != null && right.getWhich() instanceof CSharp_BuiltinNoArgs)
		{
			CSharp_BuiltinNoArgs noArgs = (CSharp_BuiltinNoArgs) right.getWhich();
			name = noArgs.builtin.getValue();
			switch (name)
			{
			case "Length":
				interpreter.pushInt(leftStr.length());
				return;
			}
		}
		
		throw new RuntimeException("Can't handle BuiltIn method: " + name);
	}
}
