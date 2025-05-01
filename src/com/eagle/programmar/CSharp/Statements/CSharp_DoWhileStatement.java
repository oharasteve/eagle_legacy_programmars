// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2010

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.generate.Statements.Eagle_Generate_DoUntil;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_DoWhileStatement extends TokenSequence
		implements AbstractStatement,
				Eagle_Generate_DoUntil<CSharp_Statement, CSharp_Expression>
{
	public @S(10) @NEWLINE @DOC("statements.html#14.13") CSharp_Keyword DO = new CSharp_Keyword("do");
	public @S(20) CSharp_Statement doStatement;
	public @S(30) CSharp_Keyword WHILE = new CSharp_Keyword("while");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) CSharp_Expression condition;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public CSharp_Statement generateDoUntil1(CSharp_Expression condition,
			CSharp_Statement action, AbstractToken source)
	{
		this.leftParen = new PunctuationLeftParen();
		this.rightParen = new PunctuationRightParen();
		this.semicolon = new PunctuationSemicolon();

		this.doStatement = action;

		CSharp_LogicalNotExpression not = new CSharp_LogicalNotExpression();
		CSharp_Expression notExpr = not.generateLogicalNot(condition, source);
		this.condition = notExpr;

		this.setTransformationSource(source);
		return CSharp_Generator.wrapStatement(this);
	}
	
	@Override
	public CSharp_Statement generateDoUntil(CSharp_Expression condition,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		CSharp_StatementBlock body = new CSharp_StatementBlock();
		body.statements = new TokenList<CSharp_StatementOrComment>();
		body.leftBrace = new PunctuationLeftBrace();
		body.rightBrace = new PunctuationRightBrace();

		CSharp_Statement stmt = new CSharp_Statement();
		this.doStatement = stmt;
		stmt.setWhich(body);

		for (CSharp_Statement action : actions)
		{
			CSharp_StatementOrComment wrapper = new CSharp_StatementOrComment();
			wrapper.setWhich(action);
			body.statements.addToken(wrapper);
		}

		CSharp_Statement action = CSharp_Generator.wrapStatement(body);
		return generateDoUntil1(condition, action, source);
	}
}
