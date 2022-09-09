// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Java_Class.Java_ClassElement;
import com.eagle.programmar.Java.Java_Class.Java_ClassImplements;
import com.eagle.programmar.Java.Java_Data.Java_DataModifier;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Enum extends TokenSequence implements EagleScopeInterface
{
	public @S(10) @OPT @NEWLINE TokenList<Java_Annotation> annotations;
	public @S(20) @OPT TokenList<Java_DataModifier> modifiers;
	public @S(30) Java_Keyword ENUM = new Java_Keyword("enum");
	public @S(40) Java_Variable_Definition id;
	public @S(50) @OPT Java_ClassImplements implement;
	public @S(60) @INDENT PunctuationLeftBrace leftBrace;
	public @S(70) @OPT Java_Comment comment1;

	public @S(80) @OPT Java_EnumConstants constants;
	public @S(90) @OPT PunctuationComma comma;
	public @S(100) @OPT TokenList<Java_Comment> comment2;
	public @S(110) @OPT Java_EnumDeclarations declarations;
	public @S(120) @OPT TokenList<Java_Comment> comment3;
	
	public @S(130) @OPT PunctuationSemicolon semicolon1;
	public @S(140) @OUTDENT PunctuationRightBrace rightBrace;
	public @S(150) @OPT TokenList<Java_Comment> comment4;
	public @S(160) @OPT @NOSPACE @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon2;
	
	public static class Java_EnumConstants extends TokenSequence
	{
		public @S(10) Java_EnumConstant constant;
		public @S(20) @OPT TokenList<Java_MoreEnumConstants> more;
		public @S(30) @OPT TokenList<Java_Comment> comments;
		
		public static class Java_MoreEnumConstants extends TokenSequence
		{
			public @S(10) @NOSPACE PunctuationComma comma;
			public @S(20) @OPT TokenList<Java_Comment> comments;
			public @S(30) Java_EnumConstant constant;
		}
	}
	
	public static class Java_EnumConstant extends TokenSequence
	{
		public @S(10) @OPT @NEWLINE TokenList<Java_Annotation> annotations;
		public @S(20) Java_Variable_Definition id;
		public @S(30) @OPT Java_EnumInitializer initializer;
		public @S(40) @OPT Java_EnumClassBody body;
		
		public static class Java_EnumClassBody extends TokenSequence
		{
			public @S(10) @INDENT PunctuationLeftBrace leftBrace;
			public @S(20) @OPT TokenList<Java_EnumClassBodyDeclaration> declarations;
			public @S(30) @OUTDENT PunctuationRightBrace rightBrace;
		}
	}
	
	public static class Java_EnumClassBodyDeclaration extends TokenChooser
	{
		public @CHOICE Java_ClassElement element;
	}

	public static class Java_EnumDeclarations extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationSemicolon semicolon;
		public @S(20) TokenList<Java_EnumClassBodyDeclaration> body;
	}
	
	public static class Java_EnumInitializer extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Java_Expression,PunctuationComma> exprs;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	private EagleScope _scope = new EagleScope(this, Java_Syntax.isCaseSensitive);
	
	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
}
