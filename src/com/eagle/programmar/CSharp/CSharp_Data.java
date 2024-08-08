// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Data extends TokenSequence implements EagleRunnable
{
	public @S(10) @NEWLINE CSharp_DataBeforeSemicolon dataBody;
	public @S(20) @NOSPACE PunctuationSemicolon semicolon;
	public @S(30) @OPT TokenList<CSharp_Comment> comments;

	public static class CSharp_DataBeforeSemicolon extends TokenSequence implements EagleRunnable
	{
		public @S(10) @OPT TokenList<CSharp_Annotation> annotation1;
		public @S(20) @OPT TokenList<CSharp_DataModifier> modifiers;
		public @S(30) @OPT TokenList<CSharp_Annotation> annotation2;
		public @S(40) CSharp_Type jtype;
		public @S(50) CSharp_Variable_Definition id;
		public @S(60) @OPT PunctuationLeftBracket leftBracket;
		public @S(70) @OPT PunctuationRightBracket rightBracket;
		public @S(80) @OPT CSharp_DataInitialValue initialValue;
		public @S(90) @OPT TokenList<CSharp_MoreIdentifiers> moreIds;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(initialValue.expression);
			interpreter.setSymbol(id.getFileName(), id.getStartLine(), id.getStartChar(), id.toString(),
					value);
		}
	}

	public static class CSharp_DataModifier extends TokenSequence
	{
		public @S(10) CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
	}

	public static class CSharp_DataInitialValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) CSharp_Expression expression;
	}

	public static class CSharp_MoreIdentifiers extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) CSharp_Variable_Definition id;
		public @S(30) @OPT PunctuationLeftBracket leftBracket;
		public @S(40) @OPT PunctuationRightBracket rightBracket;
		public @S(50) @OPT CSharp_DataInitialValue initialValue;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(dataBody);
	}
}
