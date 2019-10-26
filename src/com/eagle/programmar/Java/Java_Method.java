// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Java_Statement.Java_StatementBlock;
import com.eagle.programmar.Java.Java_Type.Java_GenericType;
import com.eagle.programmar.Java.Symbols.Java_Current_Class_Reference;
import com.eagle.programmar.Java.Symbols.Java_Method_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Method extends TokenSequence implements EagleScopeInterface, AbstractMethod
{
	private EagleScope _scope = new EagleScope(Java_Syntax.isCaseSensitive);
	
	public @OPT @BLANKLINE TokenList<Java_Comment> comments;
	public @OPT TokenList<Java_MethodModifier> modifiers;
	public @OPT Java_GenericType genericType;
	public Java_Type jtype;
	public Java_Method_Definition methodName;
	public @NOSPACE Java_ParameterList parameters;
	public @OPT TokenList<Java_EmptyBrackets> brackets;
	public @OPT Java_MethodDefault methodDefault;
	public @OPT Java_MethodThrows jthrows;
	public @OPT Java_Comment comment;
	public Java_MethodBody body;
	
	public static class Java_EmptyBrackets extends TokenSequence
	{
		public PunctuationLeftBracket leftBracket;
		public PunctuationRightBracket rightBracket;
	}
	
	public static class Java_MethodDefault extends TokenSequence
	{
		public Java_Keyword DEFAULT = new Java_Keyword("default");
		public @OPT Java_Expression expr;
	}
	
	public static class Java_MethodModifier extends TokenChooser
	{
		public @FIRST @NEWLINE Java_Comment comment;
		public @CHOICE Java_KeywordChoice modifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
		public @CHOICE Java_Annotation annotation;
	}
	
	public static class Java_MethodThrows extends TokenSequence
	{
		public Java_Keyword jthrows = new Java_Keyword("throws");
		public SeparatedList<Java_Variable,PunctuationComma> jclass;
	}
	
	public static class Java_MethodBody extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon semicolon;
		
		public @CHOICE static class Java_MethodImplementation extends TokenSequence
		{
			public @OPT @NEWLINE TokenList<Java_Comment> comment1;
			public Java_StatementBlock block;
			public @OPT TokenList<Java_Comment> comment2;
			public @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon2;
		}
	}
	
	public static class Java_Constructor extends TokenSequence
	{
		public @OPT @BLANKLINE TokenList<Java_Annotation> annotation;
		public @OPT TokenList<Java_MethodModifier> modifiers;
		public Java_Current_Class_Reference constructorName;
		public @NOSPACE Java_ParameterList parameters;
		public @OPT Java_MethodThrows jthrows;
		public @OPT Java_Comment comment;
		public Java_MethodBody body;
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
