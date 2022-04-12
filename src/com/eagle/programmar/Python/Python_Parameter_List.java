// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 23, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Parameter_List.Python_Params.Python_MoreParams.Python_InitValue;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Parameter_List extends TokenSequence
{
	public @S(10) @NOSPACE PunctuationLeftParen leftParen;
	public @S(20) @OPT Python_Comment comment;
	public @S(30) @NOSPACE @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_Params params;
	public @S(40) @OPT Python_EndOfLine eoln;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
	
	public static class Python_Params extends TokenSequence
	{
		public @S(10) @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
		public @S(20) Python_Expression expr;
		public @S(30) @OPT Python_ParamType type;
		public @S(40) @OPT Python_InitValue initValue;
		public @S(50) @OPT TokenList<Python_MoreParams> moreParams;
		
		public static class Python_ParamType extends TokenSequence
		{
			public @S(10) PunctuationColon colon;
			public @S(20) Python_Type type;
		}
		
		public static class Python_MoreParams extends TokenSequence
		{
			public @S(10) @NOSPACE PunctuationComma comma;
			public @S(20) @OPT Python_Comment comment;
			public @S(30) @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
			public @S(40) Python_Expression expr;
			public @S(50) @OPT Python_ParamType type;
			public @S(60) @OPT Python_InitValue initValue;
			
			public static class Python_InitValue extends TokenSequence
			{
				public @S(10) PunctuationEquals equals;
				public @S(20) Python_Expression defaultValue;
			}
		}
	}
}
