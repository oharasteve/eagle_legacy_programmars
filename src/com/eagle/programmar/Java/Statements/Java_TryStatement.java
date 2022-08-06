// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Data.Java_DataInitialValue;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_Statement.Java_StatementBlock.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_TryStatement extends TokenSequence implements EagleScopeInterface
{
	public @S(10) @OPT @NEWLINE Java_Label label;
	public @S(20) @DOC("statements.html#14.20") Java_Keyword TRY = new Java_Keyword("try");
	public @S(30) @OPT Java_TryResources resources;
	public @S(40) @INDENT PunctuationLeftBrace leftBrace;
	public @S(50) @OPT TokenList<Java_StatementOrComment> statements;
	public @S(60) @OUTDENT PunctuationRightBrace rightBrace;
	public @S(70) @OPT TokenList<Java_Comment> comments;
	public @S(80) @OPT TokenList<Java_CatchBlock> catchBlocks;
	public @S(90) @OPT Java_FinallyBlock finallyBlock;
	
	public static class Java_CatchBlock extends TokenSequence
	{
		public @S(10) @NEWLINE Java_Keyword CATCH = new Java_Keyword("catch");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT Java_Keyword FINAL = new Java_Keyword("final");
		public @S(40) @NOSPACE Java_Type jtype;
		public @S(50) @OPT TokenList<Java_MoreExceptions> more;
		public @S(60) Java_Identifier id;
		public @S(70) @NOSPACE PunctuationRightParen rightParen;
		public @S(80) Java_Statement catchStatement;
		
		public static class Java_MoreExceptions extends TokenSequence
		{
			public @S(10) Java_Punctuation vertBar = new Java_Punctuation('|');
			public @S(20) Java_Type jtype;
		}
	}
	
	public static class Java_FinallyBlock extends TokenSequence
	{
		public @S(10) @NEWLINE Java_Keyword FINALLY = new Java_Keyword("finally");
		public @S(20) Java_Statement finallyStatement;
	}
	
	public static class Java_TryResources extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Java_TryResource resource;
		public @S(30) @OPT TokenList<Java_TryMoreResources> more;
		public @S(40) PunctuationRightParen rightParen;
		
		public static class Java_TryResource extends TokenSequence
		{
			public @S(10) @OPT Java_Keyword FINAL = new Java_Keyword("final");
			public @S(20) Java_Type jtype;
			public @S(30) Java_Variable_Definition id;
			public @S(40) Java_DataInitialValue initialValue;
		}
		
		public static class Java_TryMoreResources extends TokenSequence
		{
			public @S(10) PunctuationSemicolon semicolon;
			public @S(20) @OPT Java_TryResource resource;
		}
	}
	
	private EagleScope _scope = new EagleScope(this, Java_Syntax.isCaseSensitive);
	
	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
}
