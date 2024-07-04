// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Directives.CSharp_Directive;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CSharp_Namespace extends TokenSequence
{
	public @S(10) @BLANKLINE CSharp_Keyword NAMESPACE = new CSharp_Keyword("namespace");
	public @S(20) CSharp_Identifier id;
	public @S(30) @OPT TokenList<CSharp_MoreNamespaceId> moreIds;
	public @S(40) @INDENT PunctuationLeftBrace leftBrace;
	public @S(50) @OPT TokenList<CSharp_ProgramElems> elems;
	public @S(60) @OUTDENT PunctuationRightBrace rightBrace;

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

	public static class CSharp_MoreNamespaceId extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationPeriod dot;
		public @S(20) @NOSPACE CSharp_Identifier id;
	}
}
