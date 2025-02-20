// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Rust.Statements.Rust_AssignmentStatement;
import com.eagle.programmar.Rust.Statements.Rust_BreakStatement;
import com.eagle.programmar.Rust.Statements.Rust_ForStatement;
import com.eagle.programmar.Rust.Statements.Rust_FunctionCall;
import com.eagle.programmar.Rust.Statements.Rust_IfStatement;
import com.eagle.programmar.Rust.Statements.Rust_LetStatement;
import com.eagle.programmar.Rust.Statements.Rust_MatchStatement;
import com.eagle.programmar.Rust.Statements.Rust_PrintlnStatement;
import com.eagle.programmar.Rust.Statements.Rust_ReturnStatement;
import com.eagle.programmar.Rust.Statements.Rust_WhileStatement;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Rust_Statement extends TokenChooser implements AbstractStatement
{
	public @CHOICE Rust_Comment XXcomment;

	public @CHOICE Rust_BreakStatement XXbreakStatement;
	public @CHOICE Rust_IfStatement XXifStatement;
	public @CHOICE Rust_ForStatement XXforStatement;
	public @CHOICE Rust_LetStatement XXletStatement;
	public @CHOICE Rust_MatchStatement XXmatchStatement;
	public @CHOICE Rust_PrintlnStatement XXprintlnStatement;
	public @CHOICE Rust_ReturnStatement XXreturnStatement;
	public @CHOICE Rust_Use XXuseStatement;
	public @CHOICE Rust_WhileStatement XXwhileStatement;

	public @LAST Rust_AssignmentStatement XXassignmentStatement;
	public @LAST Rust_FunctionCall XXfunctionCall;
	public @LAST Rust_Expression XXexpressionStatement;

	public @CHOICE static class Rust_Block_Statement extends TokenSequence implements EagleRunnableWithResult
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) TokenList<Rust_Statement> statements;
		public @S(30) PunctuationRightBrace rightBrace;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (Rust_Statement stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
			return result;
		}
	}
}
