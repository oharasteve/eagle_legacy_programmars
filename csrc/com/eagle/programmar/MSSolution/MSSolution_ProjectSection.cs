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
	using MSSolution_Literal = com.eagle.programmar.MSSolution.Terminals.MSSolution_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class MSSolution_ProjectSection : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword PROJECTSECTION = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("ProjectSection");
		public MSSolution_Keyword PROJECTSECTION = new MSSolution_Keyword("ProjectSection");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice PROJECTDEPENDENCIES = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("ProjectDependencies", "WebsiteProperties");
		public MSSolution_KeywordChoice PROJECTDEPENDENCIES = new MSSolution_KeywordChoice("ProjectDependencies", "WebsiteProperties");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice POSTPROJECT = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("postProject", "preProject");
		public MSSolution_KeywordChoice POSTPROJECT = new MSSolution_KeywordChoice("postProject", "preProject");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln1;
		public MSSolution_EndOfLine eoln1;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<MSSolution_ProjectSectionEntry> entry;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword ENDPROJECTSECTION = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("EndProjectSection");
		public MSSolution_Keyword ENDPROJECTSECTION = new MSSolution_Keyword("EndProjectSection");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln2;
		public MSSolution_EndOfLine eoln2;

		public class MSSolution_ProjectSectionEntry : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class MSSolution_ProjectSectionEntryProp extends com.eagle.tokens.TokenSequence
			public class MSSolution_ProjectSectionEntryProp : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) MSSolution_ProjectSectionEntryPropName prop;
				public MSSolution_ProjectSectionEntryPropName prop;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.MSSolution.Terminals.MSSolution_Literal value;
				public MSSolution_Literal value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln;
				public MSSolution_EndOfLine eoln;

				public class MSSolution_ProjectSectionEntryPropName : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE MSSolution_KeywordChoice XXparam = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("ProjectReferences", "SccAuxPath", "SccLocalPath", "SccProjectName", "SccProvider", "TargetFrameworkMoniker", "VWDDynamicPort", "VWDPort");
					public MSSolution_KeywordChoice XXparam = new MSSolution_KeywordChoice("ProjectReferences", "SccAuxPath", "SccLocalPath", "SccProjectName", "SccProvider", "TargetFrameworkMoniker", "VWDDynamicPort", "VWDPort");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class MSSolution_ProjectSectionEntryPropLongName extends com.eagle.tokens.TokenSequence
					public class MSSolution_ProjectSectionEntryPropLongName : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice DEBUG = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("Debug", "Release");
						public MSSolution_KeywordChoice DEBUG = new MSSolution_KeywordChoice("Debug", "Release");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot1;
						public PunctuationPeriod dot1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword ASPNEWCOMPILER = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("AspNetCompiler");
						public MSSolution_Keyword ASPNEWCOMPILER = new MSSolution_Keyword("AspNetCompiler");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationPeriod dot2;
						public PunctuationPeriod dot2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice name = new com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice("Debug", "FixedNames", "ForceOverwrite", "PhysicalPath", "TargetPath", "Updateable", "VirtualPath");
						public MSSolution_KeywordChoice name = new MSSolution_KeywordChoice("Debug", "FixedNames", "ForceOverwrite", "PhysicalPath", "TargetPath", "Updateable", "VirtualPath");
					}
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class MSSolution_ProjectSectionEntryGUID extends com.eagle.tokens.TokenSequence
			public class MSSolution_ProjectSectionEntryGUID : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace1;
				public PunctuationLeftBrace leftBrace1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.MSSolution.Terminals.MSSolution_GUID guid1;
				public MSSolution_GUID guid1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace1;
				public PunctuationRightBrace rightBrace1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace2;
				public PunctuationLeftBrace leftBrace2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.MSSolution.Terminals.MSSolution_GUID guid2;
				public MSSolution_GUID guid2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace2;
				public PunctuationRightBrace rightBrace2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln;
				public MSSolution_EndOfLine eoln;
			}
		}
	}

}
