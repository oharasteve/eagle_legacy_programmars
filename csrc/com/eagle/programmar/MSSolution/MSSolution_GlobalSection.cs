// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

namespace com.eagle.programmar.MSSolution
{
	using MSSolution_EndOfLine = com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
	using MSSolution_GUID = com.eagle.programmar.MSSolution.Terminals.MSSolution_GUID;
	using MSSolution_Keyword = com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
	using MSSolution_KeywordChoice = com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice;
	using MSSolution_Number = com.eagle.programmar.MSSolution.Terminals.MSSolution_Number;
	using MSSolution_Punctuation = com.eagle.programmar.MSSolution.Terminals.MSSolution_Punctuation;
	using MSSolution_RestOfLine = com.eagle.programmar.MSSolution.Terminals.MSSolution_RestOfLine;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class MSSolution_GlobalSection : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword GLOBALSECTION = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("GlobalSection");
		public MSSolution_Keyword GLOBALSECTION = new MSSolution_Keyword("GlobalSection");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice CONFIGURATION = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("ExtensibilityAddIns", "ExtensibilityGlobals", "ProjectConfiguration", "ProjectConfigurationPlatforms", "SolutionConfiguration", "SolutionConfigurationPlatforms", "SolutionProperties", "TeamFoundationVersionControl");
		public MSSolution_KeywordChoice CONFIGURATION = new MSSolution_KeywordChoice("ExtensibilityAddIns", "ExtensibilityGlobals", "ProjectConfiguration", "ProjectConfigurationPlatforms", "SolutionConfiguration", "SolutionConfigurationPlatforms", "SolutionProperties", "TeamFoundationVersionControl");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice PREPOST = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("preSolution", "postSolution");
		public MSSolution_KeywordChoice PREPOST = new MSSolution_KeywordChoice("preSolution", "postSolution");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln1;
		public MSSolution_EndOfLine eoln1;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<MSSolution_GlobalSectionEntry> sections;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword ENDGLOBALSECTION = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("EndGlobalSection");
		public MSSolution_Keyword ENDGLOBALSECTION = new MSSolution_Keyword("EndGlobalSection");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln2;
		public MSSolution_EndOfLine eoln2;

		public class MSSolution_GlobalSectionEntryDebug : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice debug = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("Debug", "Release");
			public MSSolution_KeywordChoice debug = new MSSolution_KeywordChoice("Debug", "Release");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT MSSolution_GlobalSectionEntryBar anycpu;
			public  OPT;
		}

		public class MSSolution_GlobalSectionEntryBar : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Punctuation bar = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Punctuation("|");
			public MSSolution_Punctuation bar = new MSSolution_Punctuation("|");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) MSSolution_GlobalSectionEntryBarWhat what;
			public MSSolution_GlobalSectionEntryBarWhat what;

			public class MSSolution_GlobalSectionEntryBarWhat : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE MSSolution_KeywordChoice XXcpu = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice(".NET", "Win32", "x64", "x86");
				public MSSolution_KeywordChoice XXcpu = new MSSolution_KeywordChoice(".NET", "Win32", "x64", "x86");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class MSSolution_GlobalSectionEntryBarAnyCPU extends com.eagle.tokens.TokenSequence
				public class MSSolution_GlobalSectionEntryBarAnyCPU : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword ANY = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Any");
					public MSSolution_Keyword ANY = new MSSolution_Keyword("Any");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword CPU = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("CPU");
					public MSSolution_Keyword CPU = new MSSolution_Keyword("CPU");
				}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class MSSolution_GlobalSectionEntryBarMixed extends com.eagle.tokens.TokenSequence
				public class MSSolution_GlobalSectionEntryBarMixed : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword MIXED = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Mixed");
					public MSSolution_Keyword MIXED = new MSSolution_Keyword("Mixed");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword PLATFORMS = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Platforms");
					public MSSolution_Keyword PLATFORMS = new MSSolution_Keyword("Platforms");
				}
			}
		}

		public class MSSolution_GlobalSectionEntry : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class MSSolution_GlobalSectionEntryKnown extends com.eagle.tokens.TokenSequence
			public class MSSolution_GlobalSectionEntryKnown : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice HIDE = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("SccLocalPath0", "SccLocalPath1", "SccLocalPath2", "SccLocalPath3", "SccLocalPath4", "SccProjectName1", "SccProjectName2", "SccProjectName3", "SccProjectName4", "SccProjectUniqueName1", "SccProjectUniqueName2", "SccProjectUniqueName3", "SccProjectUniqueName4", "SccTeamFoundationServer");
				public MSSolution_KeywordChoice HIDE = new MSSolution_KeywordChoice("SccLocalPath0", "SccLocalPath1", "SccLocalPath2", "SccLocalPath3", "SccLocalPath4", "SccProjectName1", "SccProjectName2", "SccProjectName3", "SccProjectName4", "SccProjectUniqueName1", "SccProjectUniqueName2", "SccProjectUniqueName3", "SccProjectUniqueName4", "SccTeamFoundationServer");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.MSSolution.Terminals.MSSolution_RestOfLine restOfLine;
				public MSSolution_RestOfLine restOfLine;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln;
				public MSSolution_EndOfLine eoln;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class MSSolution_GlobalSectionEntryUnknown extends com.eagle.tokens.TokenSequence
			public class MSSolution_GlobalSectionEntryUnknown : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) MSSolution_GlobalSectionEntryLeft left;
				public MSSolution_GlobalSectionEntryLeft left;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT MSSolution_GlobalSectionEntryRight right;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln;
				public MSSolution_EndOfLine eoln;

				public class MSSolution_GlobalSectionEntryLeft : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE MSSolution_GlobalSectionEntryDebug XXdebug;
					public MSSolution_GlobalSectionEntryDebug XXdebug;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE MSSolution_KeywordChoice XXHIDE = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("HideSolutionNode", "SccProjectEnlistmentChoice1", "SccEnterpriseProvider", "SccNumberOfProjects", "SccWebProject1", "SolutionGuid", "XCSharpProjectsDisabled");
					public MSSolution_KeywordChoice XXHIDE = new MSSolution_KeywordChoice("HideSolutionNode", "SccProjectEnlistmentChoice1", "SccEnterpriseProvider", "SccNumberOfProjects", "SccWebProject1", "SolutionGuid", "XCSharpProjectsDisabled");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class MSSolution_GlobalSectionEntryGuidDebug extends com.eagle.tokens.TokenSequence
					public class MSSolution_GlobalSectionEntryGuidDebug : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace1;
						public PunctuationLeftBrace leftBrace1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.MSSolution.Terminals.MSSolution_GUID guid;
						public MSSolution_GUID guid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace1;
						public PunctuationRightBrace rightBrace1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationPeriod dot1;
						public PunctuationPeriod dot1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice debug = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("Debug", "Release");
						public MSSolution_KeywordChoice debug = new MSSolution_KeywordChoice("Debug", "Release");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT MSSolution_GlobalSectionEntryBar anycpu;
						public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationPeriod dot2;
						public PunctuationPeriod dot2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice build = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("ActiveCfg", "Build");
						public MSSolution_KeywordChoice build = new MSSolution_KeywordChoice("ActiveCfg", "Build");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT PunctuationPeriod dot3;
						public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT MSSolution_Number number;
						public  OPT;
					}
				}

				public class MSSolution_GlobalSectionEntryRight : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE MSSolution_Number XXnumber;
					public MSSolution_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE MSSolution_GlobalSectionEntryDebug XXdebug;
					public MSSolution_GlobalSectionEntryDebug XXdebug;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE MSSolution_KeywordChoice XXCONST = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("FALSE", "true");
					public MSSolution_KeywordChoice XXCONST = new MSSolution_KeywordChoice("FALSE", "true");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class MSSolution_GlobalSectionEntryNet extends com.eagle.tokens.TokenSequence
					public class MSSolution_GlobalSectionEntryNet : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice release = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("Debug", "Release");
						public MSSolution_KeywordChoice release = new MSSolution_KeywordChoice("Debug", "Release");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.MSSolution.Terminals.MSSolution_Punctuation bar = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Punctuation("|");
						public MSSolution_Punctuation bar = new MSSolution_Punctuation("|");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationPeriod dot;
						public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword NET = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("NET");
						public MSSolution_Keyword NET = new MSSolution_Keyword("NET");
					}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class MSSolution_GlobalSectionEntryGuid extends com.eagle.tokens.TokenSequence
					public class MSSolution_GlobalSectionEntryGuid : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
						public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.MSSolution.Terminals.MSSolution_GUID guid;
						public MSSolution_GUID guid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
						public PunctuationRightBrace rightBrace;
					}
				}
			}
		}
	}

}
