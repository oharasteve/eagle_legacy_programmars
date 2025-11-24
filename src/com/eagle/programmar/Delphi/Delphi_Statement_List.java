// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformer;

public class Delphi_Statement_List extends TokenSequence
{
	public @S(10) @OPT TokenList<Delphi_Comment> comments1;
	public @S(20) Delphi_Statement stmt;
	public @S(30) @OPT TokenList<Delphi_NextStatement> stmts;
	public @S(40) @OPT TokenList<Delphi_Comment> comments2;
	public @S(50) @OPT PunctuationSemicolon semicolon;
	public @S(60) @OPT TokenList<Delphi_Comment> comments3;

	public static class Delphi_NextStatement extends TokenSequence
	{
		public @S(10) PunctuationSemicolon semicolon;
		public @S(20) @OPT TokenList<Delphi_Comment> comments4;
		public @S(30) Delphi_Statement stmt;
		public @S(40) @OPT TokenList<Delphi_Comment> comments5;
	}

	// It looks odd to leave extra { } around the program / function / procedure
	// implementation
	public void transformRemoveBeginEnd(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractStatement newStmt = transformer.transformStatement1(generator, this.stmt.getWhich());
		generator.addStatement(newStmt, this);
		if (this.stmts != null && this.stmts.size() > 0)
		{
			for (Delphi_NextStatement nextStmt : this.stmts._elements)
			{
				newStmt = transformer.transformStatement1(generator, nextStmt.stmt.getWhich());
				generator.addStatement(newStmt, nextStmt);
			}
		}
	}
}
