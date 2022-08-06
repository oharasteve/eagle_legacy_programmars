// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_Syntax;
import com.eagle.programmar.CSharp.CSharp_Statement.CSharp_StatementBlock.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_TryStatement extends TokenSequence implements EagleScopeInterface
{
	public @S(10) @DOC("statements.html#14.20") CSharp_Keyword TRY = new CSharp_Keyword("try");
	public @S(20) @INDENT PunctuationLeftBrace leftBrace;
	public @S(30) TokenList<CSharp_StatementOrComment> statements;
	public @S(40) @OUTDENT PunctuationRightBrace rightBrace;
	public @S(50) @OPT TokenList<CSharp_Comment> comments;
	public @S(60) @OPT TokenList<CSharp_CatchBlock> catchBlocks;
	public @S(70) @OPT CSharp_FinallyBlock finallyBlock;
	
	public static class CSharp_CatchBlock extends TokenSequence
	{
		public @S(10) CSharp_Keyword CATCH = new CSharp_Keyword("catch");
		public @S(20) @OPT CSharp_CatchWhat catchWhat;
		public @S(30) CSharp_Statement catchStatement;
		
		public static class CSharp_CatchWhat extends TokenSequence
		{
			public @S(10) @NOSPACE PunctuationLeftParen leftParen;
			public @S(20) @NOSPACE CSharp_Type cstype;
			public @S(30) @OPT CSharp_Identifier id;
			public @S(40) @NOSPACE PunctuationRightParen rightParen;
		}
	}
	
	public static class CSharp_FinallyBlock extends TokenSequence
	{
		public @S(10) CSharp_Keyword FINALLY = new CSharp_Keyword("finally");
		public @S(20) CSharp_Statement finallyStatement;
	}
	
	private EagleScope _scope = new EagleScope(this, CSharp_Syntax.isCaseSensitive);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
}
