// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_GUID;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Number;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Punctuation;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_RestOfLine;
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
	public @S(30) MSSolution_KeywordChoice CONFIGURATION = new MSSolution_KeywordChoice("ExtensibilityAddIns",
			"ExtensibilityGlobals", "ProjectConfiguration", "ProjectConfigurationPlatforms", "SolutionConfiguration",
			"SolutionConfigurationPlatforms", "SolutionProperties", "TeamFoundationVersionControl");
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationEquals equals;
	public @S(60) MSSolution_KeywordChoice PREPOST = new MSSolution_KeywordChoice("preSolution", "postSolution");
	public @S(70) MSSolution_EndOfLine eoln1;

	public @S(80) @OPT TokenList<MSSolution_GlobalSectionEntry> sections;

	public @S(90) MSSolution_Keyword ENDGLOBALSECTION = new MSSolution_Keyword("EndGlobalSection");
	public @S(100) MSSolution_EndOfLine eoln2;

	public static class MSSolution_GlobalSectionEntryDebug extends TokenSequence
	{
		public @S(10) MSSolution_KeywordChoice debug = new MSSolution_KeywordChoice("Debug", "Release");
		public @S(20) @OPT MSSolution_GlobalSectionEntryBar anycpu;
	}

	public static class MSSolution_GlobalSectionEntryBar extends TokenSequence
	{
		public @S(10) MSSolution_Punctuation bar = new MSSolution_Punctuation("|");
		public @S(20) MSSolution_GlobalSectionEntryBarWhat what;

		public static class MSSolution_GlobalSectionEntryBarWhat extends TokenChooser
		{
			public @CHOICE MSSolution_KeywordChoice acpu = new MSSolution_KeywordChoice(".NET", "Win32", "x64", "x86");

			public @CHOICE static class MSSolution_GlobalSectionEntryBarAnyCPU extends TokenSequence
			{
				public @S(10) MSSolution_Keyword ANY = new MSSolution_Keyword("Any");
				public @S(20) MSSolution_Keyword CPU = new MSSolution_Keyword("CPU");
			}

			public @CHOICE static class MSSolution_GlobalSectionEntryBarMixed extends TokenSequence
			{
				public @S(10) MSSolution_Keyword MIXED = new MSSolution_Keyword("Mixed");
				public @S(20) MSSolution_Keyword PLATFORMS = new MSSolution_Keyword("Platforms");
			}
		}
	}

	public static class MSSolution_GlobalSectionEntry extends TokenChooser
	{
		public @CHOICE static class MSSolution_GlobalSectionEntryKnown extends TokenSequence
		{
			public @S(10) MSSolution_KeywordChoice HIDE = new MSSolution_KeywordChoice("SccLocalPath0", "SccLocalPath1",
					"SccLocalPath2", "SccLocalPath3", "SccLocalPath4", "SccProjectName1", "SccProjectName2",
					"SccProjectName3", "SccProjectName4", "SccProjectUniqueName1", "SccProjectUniqueName2",
					"SccProjectUniqueName3", "SccProjectUniqueName4", "SccTeamFoundationServer");
			public @S(20) PunctuationEquals equals;
			public @S(30) MSSolution_RestOfLine restOfLine;
			public @S(40) MSSolution_EndOfLine eoln;
		}

		public @CHOICE static class MSSolution_GlobalSectionEntryUnknown extends TokenSequence
		{
			public @S(10) MSSolution_GlobalSectionEntryLeft left;
			public @S(20) PunctuationEquals equals;
			public @S(30) @OPT MSSolution_GlobalSectionEntryRight right;
			public @S(40) MSSolution_EndOfLine eoln;

			public static class MSSolution_GlobalSectionEntryLeft extends TokenChooser
			{
				public @CHOICE MSSolution_GlobalSectionEntryDebug debug;

				public @CHOICE MSSolution_KeywordChoice HIDE = new MSSolution_KeywordChoice("HideSolutionNode",
						"SccProjectEnlistmentChoice1", "SccEnterpriseProvider", "SccNumberOfProjects", "SccWebProject1",
						"SolutionGuid", "XCSharpProjectsDisabled");

				public @CHOICE static class MSSolution_GlobalSectionEntryGuidDebug extends TokenSequence
				{
					public @S(10) PunctuationLeftBrace leftBrace1;
					public @S(20) MSSolution_GUID guid;
					public @S(30) PunctuationRightBrace rightBrace1;
					public @S(40) PunctuationPeriod dot1;
					public @S(50) MSSolution_KeywordChoice debug = new MSSolution_KeywordChoice("Debug", "Release");
					public @S(60) @OPT MSSolution_GlobalSectionEntryBar anycpu;
					public @S(70) PunctuationPeriod dot2;
					public @S(80) MSSolution_KeywordChoice build = new MSSolution_KeywordChoice("ActiveCfg", "Build");
					public @S(90) @OPT PunctuationPeriod dot3;
					public @S(100) @OPT MSSolution_Number number;
				}
			}

			public static class MSSolution_GlobalSectionEntryRight extends TokenChooser
			{
				public @CHOICE MSSolution_Number number;
				public @CHOICE MSSolution_GlobalSectionEntryDebug debug;

				public @CHOICE MSSolution_KeywordChoice CONST = new MSSolution_KeywordChoice("FALSE", "true");

				public @CHOICE static class MSSolution_GlobalSectionEntryNet extends TokenSequence
				{
					public @S(10) MSSolution_KeywordChoice release = new MSSolution_KeywordChoice("Debug", "Release");
					public @S(20) MSSolution_Punctuation bar = new MSSolution_Punctuation("|");
					public @S(30) PunctuationPeriod dot;
					public @S(40) MSSolution_Keyword NET = new MSSolution_Keyword("NET");
				}

				public @CHOICE static class MSSolution_GlobalSectionEntryGuid extends TokenSequence
				{
					public @S(10) PunctuationLeftBrace leftBrace;
					public @S(20) MSSolution_GUID guid;
					public @S(30) PunctuationRightBrace rightBrace;
				}
			}
		}
	}
}
