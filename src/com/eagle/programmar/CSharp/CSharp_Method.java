// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Statement.CSharp_StatementBlock;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
import com.eagle.programmar.CSharp.Symbols.CSharp_Method_Definition;
import com.eagle.programmar.CSharp.Symbols.CSharp_Type_Definition;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Method extends TokenSequence implements AbstractMethod
{
	public @S(10) @OPT @NEWLINE TokenList<CSharp_Comment> comment;
	public @S(20) @OPT TokenList<CSharp_Annotation> annotation;
	public @S(30) @OPT @NEWLINE TokenList<CSharp_MethodModifiers> modifiers;
	public @S(40) @OPT TokenList<CSharp_Comment> comment2;
	public @S(50) CSharp_Type returnType;
	public @S(60) @OPT CSharp_Keyword GLOBAL = new CSharp_Keyword("global");
	public @S(70) @OPT CSharp_Punctuation colon2 = new CSharp_Punctuation("::");
	public @S(80) CSharp_Method_Definition methodName;
	public @S(90) @OPT CSharp_GenericType generic;
	public @S(100) @OPT CSharp_MethodParameters parameters;
	public @S(110) @OPT TokenList<CSharp_MethodWhere> where;
	public @S(120) @NEWLINE CSharp_MethodBody body;
	public @S(130) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;

	public static class CSharp_MethodParameters extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationLeftParen leftParen;
		public @S(20) @OPT @NOSPACE CSharp_MethodParameter param;
		public @S(30) @OPT TokenList<CSharp_MoreParameters> moreParams;
		public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public @S(50) @OPT CSharp_Comment comment3;
	}
	
	public static class CSharp_MethodModifiers extends TokenSequence
	{
		public @S(10) CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
	}
	
	public static class CSharp_MethodParameter extends TokenSequence
	{
		public @S(10) @OPT CSharp_Annotation annotation;
		public @S(20) @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice("ref", "out", "this", "params");
		public @S(30) CSharp_Type cstype;
		public @S(40) CSharp_Variable_Definition id;
		public @S(50) @OPT CSharp_Punctuation emptySubscript = new CSharp_Punctuation("[]");
		public @S(60) @OPT CSharp_MethodParamDefault defValue;
		
		public static class CSharp_MethodParamDefault extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) CSharp_Expression value;
		}
	}
	
	public static class CSharp_MoreParameters extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) CSharp_MethodParameter param;
	}
	
	public static class CSharp_MethodWhere extends TokenSequence
	{
		public @S(10) CSharp_Keyword WHERE = new CSharp_Keyword("where");
		public @S(20) CSharp_Type_Definition id;
		public @S(30) PunctuationColon colon;
		public @S(40) CSharp_Type type;
	}
	
	public static class CSharp_MethodBody extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon semicolon;
		
		public @CHOICE static class CSharp_MethodImplementation extends TokenSequence
		{
			public @S(10) @OPT TokenList<CSharp_Comment> comment1;
			public @S(20) @NOSPACE CSharp_StatementBlock block;
			public @S(30) @OPT TokenList<CSharp_Comment> comment2;
			public @S(40) @OPT @CURIOUS(value = "Extra semicolon") PunctuationSemicolon semicolon2;
		}
		
		public @CHOICE static class CSharp_MethodLambda extends TokenSequence
		{
			public @S(10) CSharp_Punctuation equalsGreater = new CSharp_Punctuation("=>");
			public @S(20) @OPT CSharp_Keyword REF = new CSharp_Keyword("ref");
			public @S(30) CSharp_Expression returnValue;
		}
	}
}
