// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Element;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_TryStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("js_try_catch.asp") Javascript_Keyword TRY = new Javascript_Keyword("try");
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) @OPT TokenList<Javascript_StatementOrComment> statements;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT Javascript_CatchBlock catchBlock;
	public @S(60) @OPT Javascript_FinallyBlock finallyBlock;

	public static class Javascript_CatchBlock extends TokenSequence
	{
		public @S(10) Javascript_Keyword CATCH = new Javascript_Keyword("catch");
		public @S(20) @OPT Javascript_CatchParameter parameter; // optional since ES2019 (Oct 2026, shane branch)
		public @S(50) Javascript_Element catchStatement;

		public static class Javascript_CatchParameter extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) Javascript_Variable_Definition id;
			public @S(30) PunctuationRightParen rightParen;
		}
	}

	public static class Javascript_FinallyBlock extends TokenSequence
	{
		public @S(10) Javascript_Keyword FINALLY = new Javascript_Keyword("finally");
		public @S(20) Javascript_Element finallyStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		try
		{
			result = rt.runStatements(statements);
		}
		catch (RuntimeException ex)
		{
			if (catchBlock == null || !catchBlock.isPresent()) throw ex;
			EagleValue thrown = ex instanceof JsValues.JsThrow ? ((JsValues.JsThrow) ex).value : JsValues.error(ex.getMessage() == null ? ex.toString() : ex.getMessage());
			if (catchBlock.parameter != null && catchBlock.parameter.isPresent())
				rt.declare(catchBlock.parameter.id, catchBlock.parameter.id.getValue(), thrown);
			result = interpreter.tryToInterpret(catchBlock.catchStatement);
		}
		finally
		{
			if (finallyBlock != null && finallyBlock.isPresent()) interpreter.tryToInterpret(finallyBlock.finallyStatement);
		}
		return result;
	}
}
