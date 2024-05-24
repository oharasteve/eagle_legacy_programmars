// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution;

import com.eagle.programmar.MSSolution.Terminals.MSSolution_Comment;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Integer;
import com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class MSSolution_Header extends TokenSequence
{
	public @S(10) MSSolution_HeaderMicrosoft microsoft;
	public @S(20) @OPT MSSolution_CommentLine comment;
	public @S(30) @OPT MSSolution_HeaderVisual visual;
	public @S(40) @OPT MSSolution_HeaderMinimum minimum;

	public static class MSSolution_CommentLine extends TokenSequence
	{
		public @S(10) MSSolution_Comment comment;
		public @S(20) MSSolution_EndOfLine eoln;
	}

	public static class MSSolution_HeaderMicrosoft extends TokenSequence
	{
		public @S(10) MSSolution_Keyword MICROSOFT = new MSSolution_Keyword("Microsoft");
		public @S(20) MSSolution_Keyword VISUAL = new MSSolution_Keyword("Visual");
		public @S(30) MSSolution_Keyword STUDIO = new MSSolution_Keyword("Studio");
		public @S(40) MSSolution_Keyword SOLUTION = new MSSolution_Keyword("Solution");
		public @S(50) MSSolution_Keyword FILE = new MSSolution_Keyword("File");
		public @S(60) PunctuationComma comma;
		public @S(70) MSSolution_Keyword FORMAT = new MSSolution_Keyword("Format");
		public @S(80) MSSolution_Keyword VERSION = new MSSolution_Keyword("Version");
		public @S(90) SeparatedList<MSSolution_Integer, PunctuationPeriod> version;
		public @S(100) MSSolution_EndOfLine eoln;
	}

	public static class MSSolution_HeaderVisual extends TokenSequence
	{
		public @S(10) MSSolution_Keyword VISUALSTUDIOVERSION = new MSSolution_Keyword("VisualStudioVersion");
		public @S(20) PunctuationEquals equals;
		public @S(30) SeparatedList<MSSolution_Integer, PunctuationPeriod> version;
		public @S(40) MSSolution_EndOfLine eoln;
	}

	public static class MSSolution_HeaderMinimum extends TokenSequence
	{
		public @S(10) MSSolution_Keyword MINIMUMVISUALSTUDIOVERSION = new MSSolution_Keyword(
				"MinimumVisualStudioVersion");
		public @S(20) PunctuationEquals equals;
		public @S(30) SeparatedList<MSSolution_Integer, PunctuationPeriod> version;
		public @S(40) MSSolution_EndOfLine eoln;
	}
}
