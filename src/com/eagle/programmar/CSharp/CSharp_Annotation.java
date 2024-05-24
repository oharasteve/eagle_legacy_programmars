// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_Annotation extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) CSharp_AnnotationItem item;
	public @S(30) @OPT TokenList<CSharp_MoreAnnotations> more;
	public @S(40) PunctuationRightBracket rightBracket;

	public static class CSharp_AnnotationItem extends TokenSequence
	{
		public @S(10) @OPT CSharp_AnnotionGlobal global;
		public @S(20) @OPT CSharp_AnnotionAssembly assembly;
		public @S(30) @OPT CSharp_Keyword GLOBAL = new CSharp_Keyword("global");
		public @S(40) @OPT CSharp_Punctuation colon2 = new CSharp_Punctuation("::");
		public @S(50) SeparatedList<CSharp_Identifier, PunctuationPeriod> ids;
		public @S(60) @OPT CSharp_AnnotationParams params;
	}

	public static class CSharp_AnnotionGlobal extends TokenSequence
	{
		public @S(10) CSharp_Keyword GLOBAL = new CSharp_Keyword("global");
		public @S(20) CSharp_Punctuation colon2 = new CSharp_Punctuation("::");
	}

	public static class CSharp_AnnotionAssembly extends TokenSequence
	{
		public @S(10) CSharp_KeywordChoice ASSEMBLY = new CSharp_KeywordChoice("assembly", "return");
		public @S(20) PunctuationColon colon;
	}

	public static class CSharp_AnnotationParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT CSharp_ArgumentList argList;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class CSharp_MoreAnnotations extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) CSharp_AnnotationItem item;
	}
}
