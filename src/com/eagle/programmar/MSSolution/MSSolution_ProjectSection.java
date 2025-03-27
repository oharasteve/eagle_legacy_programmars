// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_GUID;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_KeywordChoice;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class MSSolution_ProjectSection extends TokenSequence
{
	public @S(10) MSSolution_Keyword PROJECTSECTION = new MSSolution_Keyword("ProjectSection");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) MSSolution_KeywordChoice PROJECTDEPENDENCIES = new MSSolution_KeywordChoice("ProjectDependencies",
			"WebsiteProperties");
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationEquals equals;
	public @S(60) MSSolution_KeywordChoice POSTPROJECT = new MSSolution_KeywordChoice("postProject", "preProject");
	public @S(70) MSSolution_EndOfLine eoln1;

	public @S(80) @OPT TokenList<MSSolution_ProjectSectionEntry> entry;

	public @S(90) MSSolution_Keyword ENDPROJECTSECTION = new MSSolution_Keyword("EndProjectSection");
	public @S(100) MSSolution_EndOfLine eoln2;

	public static class MSSolution_ProjectSectionEntry extends TokenChooser
	{
		public @CHOICE static class MSSolution_ProjectSectionEntryProp extends TokenSequence
		{
			public @S(10) MSSolution_ProjectSectionEntryPropName prop;
			public @S(20) PunctuationEquals equals;
			public @S(30) MSSolution_Literal value;
			public @S(40) MSSolution_EndOfLine eoln;

			public static class MSSolution_ProjectSectionEntryPropName extends TokenChooser
			{
				public @CHOICE MSSolution_KeywordChoice XXparam = new MSSolution_KeywordChoice(
						"ProjectReferences", "SccAuxPath", "SccLocalPath", "SccProjectName",
						"SccProvider", "TargetFrameworkMoniker", "VWDDynamicPort", "VWDPort");

				public @CHOICE static class MSSolution_ProjectSectionEntryPropLongName extends TokenSequence
				{
					public @S(10) MSSolution_KeywordChoice DEBUG = new MSSolution_KeywordChoice("Debug", "Release");
					public @S(20) PunctuationPeriod dot1;
					public @S(30) MSSolution_Keyword ASPNEWCOMPILER = new MSSolution_Keyword("AspNetCompiler");
					public @S(40) PunctuationPeriod dot2;
					public @S(50) MSSolution_KeywordChoice name = new MSSolution_KeywordChoice("Debug", "FixedNames",
							"ForceOverwrite", "PhysicalPath", "TargetPath", "Updateable", "VirtualPath");
				}
			}
		}

		public @CHOICE static class MSSolution_ProjectSectionEntryGUID extends TokenSequence
		{
			public @S(10) PunctuationLeftBrace leftBrace1;
			public @S(20) MSSolution_GUID guid1;
			public @S(30) PunctuationRightBrace rightBrace1;
			public @S(40) PunctuationEquals equals;
			public @S(50) PunctuationLeftBrace leftBrace2;
			public @S(60) MSSolution_GUID guid2;
			public @S(70) PunctuationRightBrace rightBrace2;
			public @S(80) MSSolution_EndOfLine eoln;
		}
	}
}
