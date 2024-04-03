// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 2, 2024

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Parameter_List.Python_InitValue;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_Params extends TokenSequence
{
	public @S(10) @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
	public @S(20) Python_Expression expr;
	public @S(30) @OPT Python_ParamType type;
	public @S(40) @OPT Python_InitValue initValue;
	public @S(50) @OPT TokenList<Python_MoreParams> moreParams;
	public @S(60) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	
	public static class Python_ParamType extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) Python_Type type;
	}
	
	public static class Python_MoreParams extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationComma comma;
		public @S(20) @OPT Python_Comment comment;
		public @S(30) Python_Param param;
		public @S(40) @OPT Python_ParamType type;
		public @S(50) @OPT Python_InitValue initValue;
		
		public static class Python_Param extends TokenChooser
		{
			public @CHOICE Python_Expression expr;

			public @CHOICE static class PythonParamStar extends TokenSequence
			{
				public @S(10) Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
				public @S(20) @OPT Python_Expression expr;
			}
		}
	}
}
