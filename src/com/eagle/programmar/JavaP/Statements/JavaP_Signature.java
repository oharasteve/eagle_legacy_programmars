// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 5, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.JavaP_Value;
import com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class JavaP_Signature extends TokenSequence
{
	public @S(10) JavaP_Keyword SIGNATURE = new JavaP_Keyword("Signature");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_SignatureChoice choice;
	public @S(40) JavaP_EndOfLine eoln;

	public static class JavaP_SignatureChoice extends TokenChooser
	{
		public @CHOICE static class JavaP_SignatureLength extends TokenSequence
		{
			public @S(10) JavaP_Keyword LENGTH = new JavaP_Keyword("length");
			public @S(20) PunctuationEquals equals;
			public @S(30) JavaP_HexNumber hex;
			public @S(40) JavaP_EndOfLine eoln;
			public @S(50) TokenList<JavaP_HexNoPrefix> numbers;
		}

		public @CHOICE static class JavaP_SignatureNoLength extends TokenSequence
		{
			public @S(10) JavaP_Value value;
			public @S(20) @OPT JavaP_Comment comment;
		}
	}
}
