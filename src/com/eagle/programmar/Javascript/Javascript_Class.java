// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2022

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Javascript_Program.Javascript_Element;
import com.eagle.programmar.Javascript.Symbols.Javascript_Class_Definition;
import com.eagle.programmar.Javascript.Symbols.Javascript_Class_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_Class extends TokenSequence
{
	public @S(10) @OPT Javascript_Keyword EXPORT = new Javascript_Keyword("export");
	public @S(20) Javascript_Keyword CLASS = new Javascript_Keyword("class");
	public @S(30) Javascript_Class_Definition name;
	public @S(40) @OPT Javascript_ClassExtends extend;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) @OPT TokenList<Javascript_ClassElement> elements;
	public @S(70) PunctuationRightBrace rightBrace;

	public static class Javascript_ClassExtends extends TokenSequence
	{
		public @S(10) Javascript_Keyword EXTENDS = new Javascript_Keyword("extends");
		public @S(20) SeparatedList<Javascript_Class_Reference, PunctuationPeriod> name;
	}

	public static class Javascript_ClassElement extends TokenChooser
	{
		public @CHOICE Javascript_Method method;
		public @CHOICE Javascript_Element element;
	}

	public static class Javascript_Method extends TokenSequence
	{
		public @S(10) Javascript_Keyword STATIC = new Javascript_Keyword("static");
		public @S(20) @OPT Javascript_KeywordChoice GET = new Javascript_KeywordChoice("get", "set");
		public @S(30) Javascript_Function_Definition name;
		public @S(40) PunctuationLeftParen leftParen;
		public @S(50) @OPT Javascript_FunctionParameters params;
		public @S(60) PunctuationRightParen rightParen;
		public @S(70) Javascript_FunctionBody body;
	}
}
