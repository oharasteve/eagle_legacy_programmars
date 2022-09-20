// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.COBOL_ScreenSection.COBOL_ScreenDeclaration;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Paragraph_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Section_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_ProcedureDivision extends TokenSequence
{
	public @S(10) @OPT TokenList<COBOL_Comment> comments;
	
	public @S(20) COBOL_Keyword PROCEDURE = new COBOL_Keyword("PROCEDURE");
	public @S(30) COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
	public @S(40) @OPT COBOL_ProcedureUsing using;
	public @S(50) @OPT COBOL_ProcedureChaining chaining;
	public @S(60) @OPT COBOL_Keyword WINAPI = new COBOL_Keyword("WINAPI");
	public @S(70) PunctuationPeriod dot;
	public @S(80) @OPT COBOL_Declaratives declaratives;
	public @S(90) TokenList<COBOL_Section> sections;
	public @S(100) @OPT COBOL_ParagraphHeader extraPara;
	
	public static class COBOL_ProcedureUsing extends TokenSequence
	{
		public @S(10) COBOL_Keyword USING = new COBOL_Keyword("USING");
		public @S(20) COBOL_Identifier_Reference id;
		public @S(30) @OPT TokenList<COBOL_ProcedureUsingWhat> uses;

		public static class COBOL_ProcedureUsingWhat extends TokenSequence
		{
			public @S(10) @OPT PunctuationComma comma;
			public @S(20) COBOL_Identifier_Reference id;
		}
	}

	public static class COBOL_ProcedureChaining extends TokenSequence
	{
		public @S(10) COBOL_Keyword CHAINING = new COBOL_Keyword("CHAINING");
		public @S(20) COBOL_Identifier_Reference id;
		public @S(30) @OPT TokenList<COBOL_ProcedureChainingWhat> chain;

		public static class COBOL_ProcedureChainingWhat extends TokenSequence
		{
			public @S(10) @OPT PunctuationComma comma;
			public @S(20) COBOL_Identifier_Reference id;
		}
	}

	public static class COBOL_Declaratives extends TokenSequence
	{
		public @S(10) COBOL_Keyword DECLARATIVES1 = new COBOL_Keyword("DECLARATIVES");
		public @S(20) PunctuationPeriod dot1;
		public @S(30) COBOL_Section section;
		public @S(40) COBOL_Keyword END = new COBOL_Keyword("END");
		public @S(50) COBOL_Keyword DECLARATIVES2 = new COBOL_Keyword("DECLARATIVES");
		public @S(60) PunctuationPeriod dot2;
	}
	
	public static class COBOL_Section extends TokenSequence
	{
		public @S(10) @OPT COBOL_SectionHeader sectionHeader;
		public @S(20) TokenList<COBOL_Paragraph> paragraphs;

		public static class COBOL_SectionHeader extends TokenSequence
		{
			public @S(10) COBOL_Section_Definition sectionName;
			public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
			public @S(30) @OPT COBOL_Number number;
			public @S(40) PunctuationPeriod dot;
		}
	}

	public static class COBOL_Paragraph extends TokenSequence
	{
		public @S(10) @OPT TokenList<COBOL_ParagraphHeader> paragraphHeaders;
		public @S(20) TokenList<COBOL_SentenceOrComment> sentences;

		public static class COBOL_SentenceOrComment extends TokenChooser
		{
			public @CHOICE COBOL_Comment comment;
			public @CHOICE COBOL_Sentence sentence;
			public @LAST COBOL_ScreenDeclaration screen;

			public @LAST static class COBOL_DataInParagraph extends TokenSequence
			{
				public @S(10) TokenList<COBOL_CopyOrDataDeclaration> data;
			}
		}
	}
	
	public static class COBOL_ParagraphHeader extends TokenSequence
	{
		public @S(10) COBOL_Paragraph_Definition paragraphName;
		public @S(20) PunctuationPeriod dot;
	}

	public static class COBOL_Sentence extends TokenSequence
	{
		public @S(10) TokenList<COBOL_StatementOrComment> statements;
		public @S(20) @OPT PunctuationPeriod dot1;
		public @S(30) @CURIOUS("SENTENCE: Extra dot") @OPT PunctuationPeriod dot2;
	}
}
