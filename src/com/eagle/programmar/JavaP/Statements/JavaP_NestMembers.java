// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 23, 2020

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class JavaP_NestMembers extends TokenSequence
{
	public @S(10) JavaP_Keyword NESTMEMBERS = new JavaP_Keyword("NestMembers");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_EndOfLine eoln1;
	public @S(40) TokenList<JavaP_NestMember> members;

	public static class JavaP_NestMember extends TokenSequence
	{
		public @S(10) SeparatedList<JavaP_Identifier, PunctuationSlash> className;
		public @S(20) JavaP_EndOfLine eoln2;
	}
}
