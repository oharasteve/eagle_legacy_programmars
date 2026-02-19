// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2010

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_DoWhileStatement extends TokenSequence
		implements AbstractStatement
{
	public @S(10) @NEWLINE @DOC("statements/iteration-statements") CSharp_Keyword DO = new CSharp_Keyword("do");
	public @S(20) CSharp_Statement doStatement;
	public @S(30) CSharp_Keyword WHILE = new CSharp_Keyword("while");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) CSharp_Expression condition;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) @NOSPACE PunctuationSemicolon semicolon;

	public static CSharp_Statement generateDoUntilOne(CSharp_Expression cond,
			CSharp_Statement action, AbstractToken source)
	{
		CSharp_DoWhileStatement doStmt = new CSharp_DoWhileStatement();
		doStmt.leftParen = new PunctuationLeftParen();
		doStmt.rightParen = new PunctuationRightParen();
		doStmt.semicolon = new PunctuationSemicolon();

		doStmt.doStatement = action;
		doStmt.condition = CSharp_LogicalNotExpression.generateLogicalNot(cond, source);

		doStmt.setTransformationSource(source);
		return CSharp_Generator.wrapStatement(doStmt);
	}

	public static CSharp_Statement generateDoUntilMany(CSharp_Expression cond,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		CSharp_Statement body = CSharp_StatementBlock.generateBlock(actions, source);
		return generateDoUntilOne(cond, body, source);
	}
}
