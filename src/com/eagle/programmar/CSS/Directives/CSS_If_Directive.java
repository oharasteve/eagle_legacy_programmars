// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2014

package com.eagle.programmar.CSS.Directives;

import com.eagle.programmar.CSS.CSS_Program.CSS_Entry;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CSS_If_Directive extends TokenSequence
{
	public @S(10) CSS_Punctuation lessThan = new CSS_Punctuation('<');
	public @S(20) CSS_Keyword IF = new CSS_Keyword("if");
	public @S(30) CSS_Keyword EXPR = new CSS_Keyword("expr");
	public @S(40) PunctuationEquals equals;
	public @S(50) CSS_Literal literal;
	public @S(60) CSS_Punctuation greaterThan = new CSS_Punctuation('>');
	
	public @S(70) TokenList<CSS_Entry> entries;
	
	public @S(80) CSS_EndIf endIf;
	
	public static class CSS_EndIf extends TokenSequence
	{
		public @S(10) CSS_Punctuation lessThan = new CSS_Punctuation('<');
		public @S(20) PunctuationSlash slash;
		public @S(30) CSS_Keyword IF = new CSS_Keyword("if");
		public @S(40) CSS_Punctuation greaterThan = new CSS_Punctuation('>');
	}
}
