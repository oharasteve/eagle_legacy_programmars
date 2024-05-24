// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Function.Javascript_FunctionImplementation;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Javascript_ObjectLiteral extends PrimaryOperator
{
	// Don't use @INDENT here. Messes up 'return' statements that return an object
	// literal.
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) SeparatedList<Javascript_ObjectLiteralItem, PunctuationComma> items;
	public @S(30) @OPT PunctuationComma comma;
	public @S(40) @OPT TokenList<Javascript_Comment> comments;
	public @S(50) PunctuationRightBrace rightBrace;

	public static class Javascript_ObjectLiteralItem extends TokenChooser
	{
		public @CHOICE static class Javascript_ObjectFunction extends TokenSequence
		{
			public @S(10) @OPT TokenList<Javascript_Comment> comments;
			public @S(20) @OPT Javascript_Keyword STATIC = new Javascript_Keyword("static");
			public @S(30) @OPT Javascript_KeywordChoice prefix = new Javascript_KeywordChoice("get", "set");
			public @S(40) Javascript_FunctionImplementation function;
		}

		public @LAST static class Javascript_ObjecLiteraltData extends TokenSequence
		{
			public @S(10) Javascript_ObjectFieldName name;
			public @S(20) @OPT Javascript_ObjectFieldValue value;

			public static class Javascript_ObjectFieldName extends TokenChooser
			{
				public @CHOICE Javascript_Number number;
				public @CHOICE Javascript_Literal literal;
				public @CHOICE Javascript_Field_Definition field;
			}

			public static class Javascript_ObjectFieldValue extends TokenSequence
			{
				public @S(10) PunctuationColon colon;
				public @S(20) Javascript_Expression expr;
			}
		}
	}
}
