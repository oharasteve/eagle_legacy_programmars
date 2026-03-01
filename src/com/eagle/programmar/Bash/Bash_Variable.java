// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Bash_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT Bash_Punctuation dollar = new Bash_Punctuation("$");
	public @S(20) Bash_Identifier_Reference id;
	public @S(30) @OPT Bash_DoubleSubscript scnc;
	public @S(40) @OPT Bash_Subscript subscript;

	public static class Bash_DoubleSubscript extends TokenSequence
	{
		public @S(10) PunctuationColon colon1;
		public @S(20) Bash_Number sc;
		public @S(30) PunctuationColon colon2;
		public @S(40) Bash_Number nc;
	}

	public static class Bash_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Bash_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(id.getValue());
		
		// Look for ${str:2:3} which means substring with sc=2 and nc=3
		if (value.isString() && scnc != null && scnc.isPresent())
		{
			int scVal = Integer.parseInt(scnc.sc.getValue());
			int ncVal = Integer.parseInt(scnc.nc.getValue());
			String strVal = value.forceStringValue();
			if (scVal >= 0 && ncVal >= 0 && scVal + ncVal <= strVal.length())
			{
				strVal = strVal.substring(scVal, scVal + ncVal);
			}
			interpreter.pushStr(strVal);
		}
		else if (subscript != null && subscript.isPresent())
		{
			EagleArray array = (EagleArray) value;
			int sub = interpreter.getIntValue(subscript.expr);
			EagleValue val = array.getValue(sub);
			interpreter.pushEagleValue(val);
		}
		else
		{
			interpreter.pushEagleValue(value);
		}
	}
}
