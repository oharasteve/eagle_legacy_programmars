// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2010

package com.eagle.programmar.Java.Statements;

import java.util.ArrayList;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.programmar.Java.Expressions.Java_LogicalNotExpression;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_DoWhileStatement extends TokenSequence
		implements AbstractStatement, EagleScopeInterface
{
	public @S(10) @OPT @NEWLINE Java_Label label;
	public @S(20) @DOC("statements.html#14.13") Java_Keyword DO = new Java_Keyword("do");
	public @S(30) @OPT Java_Comment comment;
	public @S(40) Java_Statement doStatement;
	public @S(50) Java_Keyword WHILE = new Java_Keyword("while");
	public @S(60) PunctuationLeftParen leftParen;
	public @S(70) @NOSPACE Java_Expression condition;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;
	public @S(90) @NOSPACE PunctuationSemicolon semicolon;

	private @SKIP EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	public static Java_Statement generateDoUntilOne(Java_Expression cond,
			Java_Statement action, AbstractToken source)
	{
		Java_DoWhileStatement doStmt = new Java_DoWhileStatement();
		doStmt.leftParen = new PunctuationLeftParen();
		doStmt.rightParen = new PunctuationRightParen();
		doStmt.semicolon = new PunctuationSemicolon();

		Java_StatementBlock body = new Java_StatementBlock();
		body.statements = new TokenList<Java_StatementOrComment>();
		body.leftBrace = new PunctuationLeftBrace();
		body.rightBrace = new PunctuationRightBrace();

		doStmt.doStatement = action;

		doStmt.condition = Java_LogicalNotExpression.generateLogicalNot(cond, source);

		doStmt.setTransformationSource(source);
		return Java_Generator.wrapStatement(doStmt);
	}

	public static Java_Statement generateDoUntilMany(Java_Expression cond,
			ArrayList<Java_Statement> actions, AbstractToken source)
	{
		Java_Statement body = Java_StatementBlock.generateBlock(actions, source);
		return generateDoUntilOne(cond, body, source);
	}
}
