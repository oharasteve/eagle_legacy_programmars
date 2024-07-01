// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.CSharp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CSharp.Directives.CSharp_Directive;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Program extends EagleLanguage implements EagleRunnable
{
	public static final String CSHARP = "CSharp";

	public CSharp_Program()
	{
		super(CSHARP, new CSharp_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://java.sun.com/docs/books/jls/third_edition/html/";
	}

	public static final String[] MODIFIERS = new String[] {
			"abstract",
			"async",
			"const",
			"delegate",
			"event",
			"extern",
			"final",
			"internal",
			"lock",
			"override",
			"partial",
			"private",
			"protected",
			"public",
			"readonly",
			"ref",
			"sealed",
			"static",
			"unsafe",
			"virtual",
	};

	public @S(10) @OPT @NEWLINE TokenList<CSharp_Comment> comments1;
	public @S(20) @OPT @BLANKLINE TokenList<CSharp_NamespaceOrClassEntry> myClasses;

	public static class CSharp_NamespaceOrClassEntry extends TokenChooser
	{
		public @CHOICE @NEWLINE CSharp_Using importList;
		public @CHOICE @NEWLINE CSharp_Comment comment;
		public @CHOICE @NEWLINE CSharp_Namespace myNamespace;
		public @CHOICE @NEWLINE CSharp_Class elems;
		public @CHOICE @NEWLINE CSharp_Annotation annotation;
		public @CHOICE @NEWLINE CSharp_Directive directive;
	}

	public static class CSharp_Using extends TokenSequence
	{
		public @S(10) CSharp_Keyword USING = new CSharp_Keyword("using");
		public @S(20) @OPT CSharp_Keyword STATIC = new CSharp_Keyword("static");
		public @S(30) CSharp_Identifier id;
		public @S(40) @OPT TokenList<CSharp_MoreUsing> moreIds;
		public @S(50) @OPT CSharp_UsingEquals alternateName;
		public @S(60) @NOSPACE PunctuationSemicolon semicolon;

		public static class CSharp_MoreUsing extends TokenSequence
		{
			public @S(10) @NOSPACE PunctuationPeriod dot;
			public @S(20) @NOSPACE CSharp_Identifier id;
		}

		public static class CSharp_UsingEquals extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) @OPT CSharp_UsingGlobal global;
			public @S(30) SeparatedList<CSharp_Identifier, PunctuationPeriod> id;

			public static class CSharp_UsingGlobal extends TokenSequence
			{
				public @S(10) CSharp_Keyword GLOBAL = new CSharp_Keyword("global");
				public @S(20) CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
			}
		}
	}

	public static class CSharp_Namespace extends TokenSequence
	{
		public @S(10) @BLANKLINE CSharp_Keyword NAMESPACE = new CSharp_Keyword("namespace");
		public @S(20) CSharp_Identifier id;
		public @S(30) @OPT TokenList<CSharp_MoreNamespaceId> moreIds;
		public @S(40) @INDENT PunctuationLeftBrace leftBrace;
		public @S(50) @OPT TokenList<CSharp_ProgramElems> elems;
		public @S(60) @OUTDENT PunctuationRightBrace rightBrace;

		public static class CSharp_MoreNamespaceId extends TokenSequence
		{
			public @S(10) @NOSPACE PunctuationPeriod dot;
			public @S(20) @NOSPACE CSharp_Identifier id;
		}
	}

	public static class CSharp_ProgramElems extends TokenChooser
	{
		public @CHOICE @NEWLINE CSharp_Namespace myNamespace;
		public @CHOICE @NEWLINE CSharp_Using using;
		public @CHOICE @NEWLINE CSharp_Comment comment;
		public @CHOICE @NEWLINE CSharp_Class myClass;
		public @CHOICE @NEWLINE CSharp_Enum enumeration;
		public @CHOICE @NEWLINE CSharp_Method method;
		public @CHOICE @NEWLINE CSharp_Directive directive;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(myClasses.first());
	}
}
