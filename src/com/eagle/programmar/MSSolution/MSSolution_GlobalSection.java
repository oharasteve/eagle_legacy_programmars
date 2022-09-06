// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_GUID;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Number;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class MSSolution_GlobalSection extends TokenSequence
{
	public @S(10) MSSolution_Keyword GLOBALSECTION = new MSSolution_Keyword("GlobalSection");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) MSSolution_KeywordChoice CONFIGURATION = new MSSolution_KeywordChoice(
			"ExtensibilityAddIns",
			"ExtensibilityGlobals",
			"ProjectConfiguration",
			"SolutionConfiguration");
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationEquals equals;
	public @S(60) MSSolution_KeywordChoice PREPOST = new MSSolution_KeywordChoice("preSolution", "postSolution");
	public @S(70) MSSolution_EndOfLine eoln1;
	
	public @S(80) @OPT TokenList<MSSolution_GlobalSectionEntry> sections;
	
	public @S(90) MSSolution_Keyword ENDGLOBALSECTION = new MSSolution_Keyword("EndGlobalSection");
	public @S(100) MSSolution_EndOfLine eoln2;
	
	public static class MSSolution_GlobalSectionEntry extends TokenSequence
	{
		public @S(10) MSSolution_GlobalSectionEntryValue left;
		public @S(20) PunctuationEquals equals;
		public @S(30) @OPT MSSolution_GlobalSectionEntryValue right;
		public @S(40) MSSolution_EndOfLine eoln1;
		
		public static class MSSolution_GlobalSectionEntryValue extends TokenChooser
		{
			public @CHOICE MSSolution_KeywordChoice debug = new MSSolution_KeywordChoice(
					"Debug",
					"Release",
					"XCSharpProjectsDisabled");
			
			public @CHOICE static class MSSolution_GlobalSectionEntryGuid extends TokenSequence
			{
				public @S(10) PunctuationLeftBrace leftBrace1;
				public @S(20) MSSolution_GUID guid1;
				public @S(30) PunctuationRightBrace rightBrace1;
				public @S(40) PunctuationPeriod dot1;
				public @S(50) MSSolution_KeywordChoice debug = new MSSolution_KeywordChoice("Debug", "Release");
				public @S(60) PunctuationPeriod dot2;
				public @S(70) MSSolution_KeywordChoice build = new MSSolution_KeywordChoice("ActiveCfg", "Build");
				public @S(80) @OPT PunctuationPeriod dot3;
				public @S(90) @OPT MSSolution_Number number;
			}
			
			public @CHOICE static class MSSolution_GlobalSectionEntryNet extends TokenSequence
			{
				public @S(10) MSSolution_KeywordChoice release = new MSSolution_KeywordChoice("Debug", "Release");
				public @S(20) MSSolution_Punctuation bar = new MSSolution_Punctuation("|");
				public @S(30) PunctuationPeriod dot4;
				public @S(40) MSSolution_Keyword NET = new MSSolution_Keyword("NET");
			}
		}
	}
}
