// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_Annotation extends TokenChooser
{
	public @FIRST static class Java_AnnotationCallParens extends TokenSequence
	{
		public @S(10) Java_Punctuation atSign = new Java_Punctuation('@');
		public @S(20) @NOSPACE Java_Variable var;
		public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public @S(40) @OPT @NOSPACE SeparatedList<Java_Expression, PunctuationComma> expressions;
		public @S(50) @NOSPACE PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class Java_AnnotationCallList extends TokenSequence
	{
		public @S(10) Java_Punctuation atSign = new Java_Punctuation('@');
		public @S(20) @NOSPACE SeparatedList<Java_Identifier,PunctuationPeriod> idList;
		public @S(30) @OPT TokenList<Java_Comment> comments;
	}
	
	public @CHOICE static class Java_AnnotationList extends TokenSequence
	{
		public @S(10) Java_Punctuation atSign = new Java_Punctuation('@');
		public @S(20) Java_KeywordChoice ATTR = new Java_KeywordChoice(
				"AccessoryAction",
				"Nullable",
				"PageTransition");
	}
	
	public @LAST static class Java_AnnotationSimple extends TokenSequence
	{
		public @S(10) Java_Punctuation atSign = new Java_Punctuation('@');
		public @S(20) @NOSPACE Java_Variable var;
	}
}
