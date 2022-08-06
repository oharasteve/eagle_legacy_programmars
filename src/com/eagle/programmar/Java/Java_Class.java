// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Java.Java_Method.Java_Constructor;
import com.eagle.programmar.Java.Java_Type.Java_GenericType;
import com.eagle.programmar.Java.Symbols.Java_Class_Definition;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Class extends TokenSequence implements EagleRunnable, EagleScopeInterface, AbstractClass
{
	public @S(10) @OPT @BLANKLINE Java_ClassModifierList modifierList;
	public @S(20) @OPT @CURIOUS("Extra at sign") Java_Punctuation atSign = new Java_Punctuation('@');
	public @S(30) Java_KeywordChoice classOrInterface = new Java_KeywordChoice("class", "interface");
	public @S(40) Java_Class_Definition className;
	public @S(50) @OPT Java_GenericType genericType;
	public @S(60) @OPT Java_ClassExtends jextends;
	public @S(70) @OPT Java_ClassImplements jimplements;
	public @S(80) @OPT TokenList<Java_Comment> comments2;
	public @S(90) @INDENT PunctuationLeftBrace leftBrace;
	public @S(100) @OPT TokenList<Java_ClassElement> elements;
	public @S(110) @OUTDENT PunctuationRightBrace rightBrace;
	public @S(120) @OPT @NEWLINE TokenList<Java_Comment> comments3;
	public @S(130) @CURIOUS("Extra semicolon") @OPT PunctuationSemicolon semicolon;
	
	public static class Java_ClassModifierList extends TokenSequence
	{
		public @S(10) TokenList<Java_ClassModifier> modifiers;
	}
	
	public static class Java_ClassModifier extends TokenChooser
	{
		public @FIRST @NEWLINE Java_Comment comment;
		public @CHOICE Java_Annotation annotation;
		public @CHOICE Java_KeywordChoice modifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
	}

	public static class Java_ClassExtends extends TokenSequence
	{
		public @S(10) Java_Keyword EXTENDS = new Java_Keyword("extends");
		public @S(20) SeparatedList<Java_Identifier_Reference,PunctuationPeriod> classNames;
		public @S(30) @OPT Java_GenericType genericType;
		public @S(40) @OPT TokenList<Java_MoreExtends> moreExtend;
		
		public static class Java_MoreExtends extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) SeparatedList<Java_Identifier_Reference,PunctuationPeriod> classNames;
			public @S(30) @OPT Java_GenericType genericType;
		}
	}
	
	public static class Java_ClassImplements extends TokenSequence
	{
		public @S(10) Java_Keyword IMPLEMENTS = new Java_Keyword("implements");
		public @S(20) SeparatedList<Java_Identifier_Reference,PunctuationPeriod> classNames;
		public @S(30) @OPT Java_GenericType genericType;
		public @S(40) @OPT TokenList<Java_MoreImplements> moreImpl;
		
		public static class Java_MoreImplements extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) @OPT Java_Comment comment;
			public @S(30) SeparatedList<Java_Identifier_Reference,PunctuationPeriod> classNames;
			public @S(40) @OPT Java_GenericType genericType;
		}
	}
	
	public static class Java_ClassElement extends TokenChooser
	{
		public @FIRST @NEWLINE Java_Comment comment;
		public @CHOICE @NEWLINE Java_Method jmethod;
		public @FIRST @NEWLINE Java_Constructor constructor;
		public @CHOICE @CURIOUS(value = "Extra semicolon") PunctuationSemicolon semicolon;
		
		public @CHOICE static class Java_StaticStatement extends TokenSequence implements EagleRunnable
		{
			public @S(10) @OPT Java_Keyword PRIVATE = new Java_Keyword("private");
			public @S(20) @OPT Java_Annotation annotation;
			public @S(30) @OPT Java_Keyword STATIC = new Java_Keyword("static");
			public @S(40) Java_Statement statement;
			
			@Override
			public void interpret(EagleInterpreter interpreter)
			{
				interpreter.tryToInterpret(statement.getWhich());
			}
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (Java_ClassElement element : elements._elements)
		{
			interpreter.tryToInterpret(element.getWhich());
		}
	}
	
	private EagleScope _scope = new EagleScope(this, Java_Syntax.isCaseSensitive);
	
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
