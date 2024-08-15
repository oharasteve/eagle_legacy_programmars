// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 2, 2024

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Parameter_List.Python_InitValue;
import com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSlash;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Python_Params extends TokenSequence
{
	public @S(10) Python_Parameter param;
	public @S(20) @OPT Python_ParamType type;
	public @S(30) @OPT Python_InitValue initValue;
	public @S(40) @OPT TokenList<Python_MoreParams> moreParams;
	public @S(50) @OPT @CURIOUS("Extra comma") PunctuationComma comma;

	public static class Python_ParamType extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) Python_Type type;
	}

	public static class Python_Parameter extends TokenChooser
	{
		public @CHOICE Python_Variable_Definition XXvar;
		public @LAST PunctuationSlash XXslash; // Means end of positional arguments
		public @LAST PunctuationStar XXstar; // Means end of positional or keyword arguments

		public @CHOICE static class PythonParamStar extends TokenSequence
		{
			public @S(10) Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
			public @S(20) Python_Variable_Definition var;
		}
	}

	public static class Python_MoreParams extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationComma comma;
		public @S(20) @OPT Python_Comment comment;
		public @S(30) Python_Parameter param;
		public @S(40) @OPT Python_ParamType type;
		public @S(50) @OPT Python_InitValue initValue;
	}
}
