package com.eagle.programmar.Java.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_StatementBlock extends TokenSequence implements EagleRunnableWithResult //  EagleScopeInterface
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
	
//	private EagleScope _scope = new EagleScope(this, Java_Syntax.isCaseSensitive);
//
//	@Override
//	public EagleScope getScope()
//	{
//		return _scope;
//	}

//	@Override
//	public void setScope(EagleScope scope)
//	{
//		_scope = scope;
//	}
}
