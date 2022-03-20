// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Javascript_Statement.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_Function extends TokenSequence
{
	public @S(10) @OPT Javascript_Keyword STATIC = new Javascript_Keyword("static");
	public @S(20) Javascript_Keyword FUNCTION = new Javascript_Keyword("function");
	public @S(30) @OPT Javascript_Function_Definition functionName;
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @OPT @NOSPACE Javascript_FunctionParameter param;
	public @S(60) @OPT @NOSPACE TokenList<Javascript_MoreParameters> moreParams;
	public @S(70) @OPT TokenList<Javascript_Comment> comments1;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;
	public @S(90) @OPT TokenList<Javascript_Comment> comments2;
	public @S(100) Javascript_FunctionBody body;
	
	public static class Javascript_FunctionParameter extends TokenChooser
	{
		public @CHOICE Javascript_Variable_Definition id;
		public @CHOICE Javascript_Punctuation dollar = new Javascript_Punctuation('$');
	}
		
	public static class Javascript_MoreParameters extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationComma comma;
		public @S(20) @OPT Javascript_Comment comment;
		public @S(30) Javascript_FunctionParameter param;
	}
	
	public static class Javascript_FunctionBody extends TokenSequence
	{
		public @S(10) @INDENT PunctuationLeftBrace leftBrace;
		public @S(20) @OPT TokenList<Javascript_StatementOrComment> statements;
		public @S(30) @OUTDENT PunctuationRightBrace rightBrace;
	}
}
