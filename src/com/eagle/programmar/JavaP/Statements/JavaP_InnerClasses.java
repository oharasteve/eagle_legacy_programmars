// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
import com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class JavaP_InnerClasses extends TokenSequence
{
	public @S(10) JavaP_KeywordChoice INNERCLASS = new JavaP_KeywordChoice("InnerClass", "InnerClasses");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_EndOfLine eoln;
	
	public @S(40) TokenList<JavaP_InnerClassEntry> entries;
	
	public static class JavaP_InnerClassEntry extends TokenSequence
	{
		public @S(10) @OPT JavaP_KeywordChoice PUBLIC = new JavaP_KeywordChoice("private", "protected", "public");
		public @S(20) @OPT JavaP_Keyword STATIC = new JavaP_Keyword("static");
		public @S(30) @OPT JavaP_Keyword ABSTRACT = new JavaP_Keyword("abstract");
		public @S(40) @OPT JavaP_Keyword FINAL = new JavaP_Keyword("final");
		public @S(50) JavaP_Symbol_Reference id1;
		public @S(60) PunctuationEquals equals;
		public @S(70) JavaP_Symbol_Reference id2;
		public @S(80) JavaP_Keyword OF = new JavaP_Keyword("of");
		public @S(90) JavaP_Symbol_Reference id3;
		public @S(100) PunctuationSemicolon semicolon;
		public @S(110) JavaP_Comment comment;
		public @S(120) JavaP_EndOfLine eoln;
	}
}
