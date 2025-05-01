// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2024

package com.eagle.programmar.Java.Statements;

import java.util.ArrayList;

import com.eagle.generate.Statements.Eagle_Generate_Block;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_StatementBlock extends TokenSequence
		implements EagleRunnableWithResult, EagleScopeInterface,
				Eagle_Generate_Block<Java_Statement>
{
	public @S(10) @OPT Java_Label label;
	public @S(20) @INDENT PunctuationLeftBrace leftBrace;
	public @S(30) @OPT TokenList<Java_StatementOrComment> statements;
	public @S(40) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon1;
	public @S(50) @OUTDENT PunctuationRightBrace rightBrace;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Java_StatementOrComment stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		return result;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
	@Override
	public Java_Statement generateBlock(ArrayList<Java_Statement> stmts,
			AbstractToken source)
	{
		this.leftBrace = new PunctuationLeftBrace();
		this.rightBrace = new PunctuationRightBrace();
		this.statements = new TokenList<Java_StatementOrComment>();
		this.statements.setPresent(true);
		for (Java_Statement stmt : stmts)
		{
			Java_StatementOrComment stmtComm = new Java_StatementOrComment();
			stmtComm.setWhich(stmt);
			stmtComm.setPresent(true);
			this.statements.addToken(stmtComm);
		}
		return Java_Generator.wrapStatement(this);
	}
}
