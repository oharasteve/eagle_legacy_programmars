// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Data extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT @NEWLINE TokenList<Java_Annotation> annotation1;
	public @S(20) @OPT TokenList<Java_DataModifier> modifiers;
	public @S(30) @OPT TokenList<Java_Annotation> annotation2;
	public @S(40) Java_Type jtype;
	public @S(50) Java_Variable_Definition id;
	public @S(60) @OPT TokenList<Java_DataSubscript> subscripts;
	public @S(70) @OPT Java_DataInitialValue initialValue;
	public @S(80) @OPT TokenList<Java_MoreIdentifiers> moreIds;
	public @S(90) @NOSPACE PunctuationSemicolon semicolon;
	public @S(100) @OPT TokenList<Java_Comment> comments;

	public static class Java_DataSubscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
	}

	public static class Java_DataModifier extends TokenSequence
	{
		public @S(10) Java_KeywordChoice modifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
	}

	public static class Java_DataInitialValue extends TokenSequence implements EagleRunnable
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Java_Expression expression;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(expression);
			interpreter.pushEagleValue(value);
		}
	}

	public static class Java_MoreIdentifiers extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Java_Variable_Definition id;
		public @S(30) @OPT PunctuationLeftBracket leftBracket;
		public @S(40) @OPT PunctuationRightBracket rightBracket;
		public @S(50) @OPT Java_DataInitialValue initialValue;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(initialValue);
		interpreter.setSymbol(id.getFileName(), id.getStartLine(), id.getStartChar(), id.toString(),
				value);
	}
}
