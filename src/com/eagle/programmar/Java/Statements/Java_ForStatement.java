// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Annotation;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_ForStatement extends TokenChooser implements EagleScopeInterface
{
	private EagleScope _scope = new EagleScope(this, Java_Syntax.isCaseSensitive);
	
	public @CHOICE static class Java_ForLoopStatement extends TokenSequence
	{
		public @S(10) @OPT @NEWLINE Java_Label label;
		public @S(20) @DOC("statements.html#14.14") Java_Keyword FOR = new Java_Keyword("for");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) @OPT @NOSPACE Java_Annotation annotation;
		public @S(50) @OPT Java_ForInit init;
		public @S(60) @NOSPACE PunctuationSemicolon semicolon1;
		public @S(70) @OPT Java_Expression terminateCondition;
		public @S(80) @NOSPACE PunctuationSemicolon semicolon2;
		public @S(90) @OPT SeparatedList<Java_Expression,PunctuationComma> increments;
		public @S(100) @NOSPACE PunctuationRightParen rightParen;
		public @S(110) @OPT Java_Comment comment;
		public @S(120) Java_Statement action;

		public static class Java_ForInit extends TokenSequence
		{
			public @S(10) @OPT Java_Keyword FINAL = new Java_Keyword("final");
			public @S(20) SeparatedList<Java_ForWhat,PunctuationComma> what;
			
			public static class Java_ForWhat extends TokenChooser
			{
				public @CHOICE Java_Expression expr;
				
				public @FIRST static class Java_ForWithType extends TokenSequence
				{
					public @S(10) Java_Type varType;
					public @S(20) Java_Expression expr;
				}
			}
		}
	}
	
	public @CHOICE static class Java_ForCollectionStatement extends TokenSequence
	{
		public @S(10) @OPT @NEWLINE Java_Label label;
		public @S(20) Java_Keyword FOR = new Java_Keyword("for");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) @OPT Java_Keyword FINAL = new Java_Keyword("final");
		public @S(50) Java_Type varType;
		public @S(60) Java_Variable forVar;
		public @S(70) PunctuationColon colon;
		public @S(80) Java_Expression collection;
		public @S(90) PunctuationRightParen rightParen;
		public @S(100) @OPT Java_Comment comment;
		public @S(110) Java_Statement action;
	}
	
	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
//	@Override
//	public void setScope(EagleScope scope)
//	{
//		_scope = scope;
//	}
}
