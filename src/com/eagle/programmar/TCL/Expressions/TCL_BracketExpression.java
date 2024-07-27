// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class TCL_BracketExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) TCL_BracketWhat what;
	public @S(30) PunctuationRightBracket rightBracket;
	
	public static class TCL_BracketWhat extends TokenChooser
	{
		public @CHOICE TCL_BracketLindex XXlindex;
		public @CHOICE TCL_BracketStringLength XXstringLength;
		public @CHOICE TCL_BracketStringFirst XXstringFirst;
		public @CHOICE TCL_BracketExpr XXexpr;
		public @LAST TCL_FunctionCall XXfunctionCall;
	}
	
	public static class TCL_BracketLindex extends TokenSequence implements EagleRunnable
	{
		public @S(10) TCL_Keyword LINDEX = new TCL_Keyword("lindex");
		public @S(20) TCL_Expression arrayExpr;
		public @S(30) TCL_Expression index;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(arrayExpr);
			EagleArray array = (EagleArray) value;
			int i = interpreter.getIntValue(index);
			interpreter.pushEagleValue(array.getValue(i));
		}
	}
	
	public static class TCL_BracketStringLength extends TokenSequence implements EagleRunnable
	{
		public @S(10) TCL_Keyword STRING = new TCL_Keyword("string");
		public @S(20) TCL_Keyword LENGTH = new TCL_Keyword("length");
		public @S(30) TCL_Expression expr;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			String str = interpreter.getStrValue(expr);
			interpreter.pushInt(str.length());
		}
	}
	
	public static class TCL_BracketStringFirst extends TokenSequence implements EagleRunnable
	{
		public @S(10) TCL_Keyword STRING = new TCL_Keyword("string");
		public @S(20) TCL_Keyword FIRST = new TCL_Keyword("first");
		public @S(30) TCL_Expression string;
		public @S(40) TCL_Expression pattern;
		public @S(50) TCL_Expression start;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			String patt = interpreter.getStrValue(string);
			String str = interpreter.getStrValue(pattern);
			int sc = interpreter.getIntValue(start);
			interpreter.pushInt(str.indexOf(patt, sc));
		}
	}
	
	public static class TCL_BracketExpr extends TokenSequence implements EagleRunnable
	{
		public @S(10) TCL_Keyword EXPR = new TCL_Keyword("expr");
		public @S(20) TCL_Expression expr;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(expr);
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(what);
	}
}
