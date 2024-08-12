// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class TCL_BracketStringFirst extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) TCL_Keyword STRING = new TCL_Keyword("string");
	public @S(30) TCL_Keyword FIRST = new TCL_Keyword("first");
	public @S(40) TCL_Expression string;
	public @S(50) TCL_Expression pattern;
	public @S(60) TCL_Expression start;
	public @S(70) PunctuationRightBracket rightBracket;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String patt = interpreter.getStrValue(string);
		String str = interpreter.getStrValue(pattern);
		int sc = interpreter.getIntValue(start);
		interpreter.pushInt(str.indexOf(patt, sc));
	}
}
