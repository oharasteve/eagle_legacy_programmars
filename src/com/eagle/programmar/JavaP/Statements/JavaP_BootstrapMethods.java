// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_LClassName;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.programmar.JavaP.Terminals.JavaP_RestOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class JavaP_BootstrapMethods extends TokenSequence
{
	public @S(10) JavaP_Keyword BOOTSTRAP_METHODS = new JavaP_Keyword("BootstrapMethods");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_EndOfLine eoln;
	public @S(40) TokenList<JavaP_Methods> methods;

	public static class JavaP_Methods extends TokenSequence
	{
		public @S(10) JavaP_Number number;
		public @S(20) PunctuationColon colon1;
		public @S(30) JavaP_HashNumber ref_id;
		public @S(40) JavaP_Identifier id;
		public @S(50) SeparatedList<JavaP_Identifier, PunctuationSlash> ParentNames;
		public @S(60) PunctuationPeriod dot;
		public @S(70) JavaP_Identifier methodName;
		public @S(80) PunctuationColon colon2;
		public @S(90) PunctuationLeftParen leftParen;
		public @S(100) SeparatedList<JavaP_LClassName, PunctuationSemicolon> classes;
		public @S(110) PunctuationSemicolon semicolon1;
		public @S(120) PunctuationLeftBracket leftBracket;
		public @S(130) JavaP_LClassName className1;
		public @S(140) PunctuationSemicolon semicolon2;
		public @S(150) PunctuationRightParen rightParen;
		public @S(160) JavaP_LClassName className2;
		public @S(170) PunctuationSemicolon semicolon3;
		public @S(180) JavaP_EndOfLine eoln1;

		public @S(190) JavaP_Keyword METHOD = new JavaP_Keyword("Method");
		public @S(200) JavaP_Keyword ARGUMENTS = new JavaP_Keyword("arguments");
		public @S(210) PunctuationColon colon;
		public @S(220) JavaP_EndOfLine eoln2;

		public @S(230) JavaP_HashNumber arg_id;
		public @S(240) JavaP_RestOfLine restOfLine;
		public @S(250) JavaP_EndOfLine eoln3;
	}

}
