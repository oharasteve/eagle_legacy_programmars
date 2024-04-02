// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Symbols.Powershell_Field_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Comment;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Powershell_Dictionary extends PrimaryOperator
{
	public @S(10) @OPT Powershell_Punctuation at = new Powershell_Punctuation("@");
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) @OPT Powershell_DictionaryValues expressions;
	public @S(40) PunctuationRightBrace rightBrace;
	
	public static class Powershell_DictionaryValues extends TokenSequence
	{
		public @S(10) @OPT Powershell_EndOfLine eoln1;
		public @S(20) Powershell_Field field;
		public @S(30) PunctuationEquals equals;
		public @S(40) Powershell_Expression expr;
		public @S(50) @OPT TokenList<Powershell_MoreDictExpressions> more;
		public @S(60) @OPT Powershell_Comment comment;
		public @S(70) @OPT Powershell_EndOfLine eoln2;
		
		public static class Powershell_Field extends TokenChooser
		{
			public @CHOICE Powershell_Field_Reference field;
			public @CHOICE Powershell_Literal literal;
		}
		public static class Powershell_MoreDictExpressions extends TokenSequence
		{
			public @S(10) @OPT PunctuationSemicolon semicolon;
			public @S(20) @OPT Powershell_Comment comment;
			public @S(30) @OPT Powershell_EndOfLine eoln;
			public @S(40) Powershell_Field field;
			public @S(50) PunctuationEquals equals;
			public @S(60) Powershell_Expression expr;
		}
	}
}
