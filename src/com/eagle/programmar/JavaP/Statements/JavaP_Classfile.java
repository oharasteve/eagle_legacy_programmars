// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class JavaP_Classfile extends TokenSequence
{
	public @S(10) JavaP_Keyword CLASSFILE = new JavaP_Keyword("Classfile");
	public @S(20) JavaP_QualifiedName filename;
	public @S(30) JavaP_EndOfLine eoln;
	public @S(40) @OPT JavaP_LastModified lastModified;
	public @S(50) @OPT JavaP_MD5Checksum checksum;

	public static class JavaP_LastModified extends TokenSequence
	{
		public @S(10) JavaP_Keyword LAST = new JavaP_Keyword("Last");
		public @S(20) JavaP_Keyword MODIFIED = new JavaP_Keyword("modified");
		public @S(30) JavaP_KeywordChoice MONTH = new JavaP_KeywordChoice("Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		public @S(40) JavaP_Number day;
		public @S(50) PunctuationComma comma;
		public @S(60) JavaP_Number year;
		public @S(70) PunctuationSemicolon semicolon;
		public @S(80) JavaP_Keyword SIZE = new JavaP_Keyword("size");
		public @S(90) JavaP_Number bytes;
		public @S(100) JavaP_Keyword BYTES = new JavaP_Keyword("bytes");
		public @S(110) JavaP_EndOfLine eoln;
	}

	public static class JavaP_MD5Checksum extends TokenSequence
	{
		public @S(10) JavaP_KeywordChoice MD5 = new JavaP_KeywordChoice("MD5", "SHA-256");
		public @S(20) JavaP_Keyword CHECKSUM = new JavaP_Keyword("checksum");
		public @S(30) JavaP_HexNoPrefix hex;
		public @S(40) JavaP_EndOfLine eoln;
	}
}
