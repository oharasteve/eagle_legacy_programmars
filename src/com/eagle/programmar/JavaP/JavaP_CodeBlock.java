// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Blocks.JavaP_CodeExceptions;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeLineNumbers;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeLocalValues;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeStackInfo;
import com.eagle.programmar.JavaP.Blocks.JavaP_StackMapTable;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_CodeBlock extends TokenSequence
{
	public @S(10) JavaP_Keyword CODE = new JavaP_Keyword("Code");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_EndOfLine eoln;

	public @S(40) @OPT TokenList<JavaP_CodeDetails> details;

	public static class JavaP_CodeDetails extends TokenChooser
	{
		public @CHOICE JavaP_CodeLocalValues XXlocalValues;
		public @CHOICE JavaP_CodeStackInfo XXstackInfo;
		public @CHOICE JavaP_CodeLineNumbers XXlineNumbers;
		public @CHOICE JavaP_StackMapTable XXstackMap;
		public @CHOICE JavaP_CodeExceptions XXcodeExceptions;
	}
}
