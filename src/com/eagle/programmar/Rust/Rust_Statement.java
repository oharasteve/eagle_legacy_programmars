// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.programmar.Rust.Statements.Rust_AssignmentStatement;
import com.eagle.programmar.Rust.Statements.Rust_BreakStatement;
import com.eagle.programmar.Rust.Statements.Rust_ForStatement;
import com.eagle.programmar.Rust.Statements.Rust_FunctionCall;
import com.eagle.programmar.Rust.Statements.Rust_IfStatement;
import com.eagle.programmar.Rust.Statements.Rust_LetStatement;
import com.eagle.programmar.Rust.Statements.Rust_PrintlnStatement;
import com.eagle.programmar.Rust.Statements.Rust_ReturnStatement;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Rust_Statement extends TokenChooser implements AbstractStatement
{
	public @CHOICE Rust_Comment comment;

	public @CHOICE Rust_BreakStatement breakStatement;
	public @CHOICE Rust_IfStatement ifStatement;
	public @CHOICE Rust_ForStatement forStatement;
	public @CHOICE Rust_LetStatement letStatement;
	public @CHOICE Rust_PrintlnStatement printlnStatement;
	public @CHOICE Rust_ReturnStatement returnStatement;
	public @CHOICE Rust_Use useStatement;
	
	public @LAST Rust_AssignmentStatement assignmentStatement;
	public @LAST Rust_FunctionCall functionCall;
	
	public static @CHOICE class Rust_Block_Statement extends TokenSequence
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) TokenList<Rust_Statement> statements;
		public @S(30) PunctuationRightBrace rightBrace;
	}
}
