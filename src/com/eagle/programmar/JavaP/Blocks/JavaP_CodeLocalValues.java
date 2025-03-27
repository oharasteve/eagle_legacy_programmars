// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

package com.eagle.programmar.JavaP.Blocks;

import com.eagle.programmar.JavaP.JavaP_Value;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class JavaP_CodeLocalValues extends TokenSequence
{
	public @S(10) JavaP_KeywordChoice LOCALVARIABLES = new JavaP_KeywordChoice("LocalVariableTable",
			"LocalVariableTypeTable");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_CodeLocalHeaderChoice header;

	public static class JavaP_CodeLocalHeaderChoice extends TokenChooser
	{
		public @CHOICE static class JavaP_CodeLocalHeader extends TokenSequence
		{
			public @S(10) JavaP_EndOfLine eoln1;
			public @S(20) JavaP_Keyword START = new JavaP_Keyword("Start");
			public @S(30) JavaP_Keyword LENGTH = new JavaP_Keyword("Length");
			public @S(40) JavaP_Keyword SLOT = new JavaP_Keyword("Slot");
			public @S(50) JavaP_Keyword NAME = new JavaP_Keyword("Name");
			public @S(60) JavaP_Keyword SIGNATURE = new JavaP_Keyword("Signature");
			public @S(70) JavaP_EndOfLine eoln2;

			public @S(80) @OPT TokenList<JavaP_CodeLocalEntry> entries;

			public static class JavaP_CodeLocalEntry extends TokenSequence
			{
				public @S(10) JavaP_Number start;
				public @S(20) JavaP_Number length;
				public @S(30) JavaP_Number slot;
				public @S(40) JavaP_QualifiedName name;
				public @S(50) JavaP_Value value;
				public @S(60) JavaP_EndOfLine eoln;
			}
		}

		public @CHOICE static class JavaP_CodeLocalLength extends TokenSequence
		{
			public @S(10) JavaP_Keyword LENGTH = new JavaP_Keyword("length");
			public @S(20) PunctuationEquals equals;
			public @S(30) JavaP_HexNumber len;
			public @S(40) TokenList<JavaP_CodeLocalHexes> hexes;

			public static class JavaP_CodeLocalHexes extends TokenChooser
			{
				public @CHOICE JavaP_HexNoPrefix XXhex;
				public @CHOICE JavaP_EndOfLine XXeoln;
			}
		}
	}
}
