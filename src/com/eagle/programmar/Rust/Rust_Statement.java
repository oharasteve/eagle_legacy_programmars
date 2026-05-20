// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.programmar.Rust.Statements.Rust_Block_Statement;
import com.eagle.programmar.Rust.Statements.Rust_BreakStatement;
import com.eagle.programmar.Rust.Statements.Rust_ConstStatement;
import com.eagle.programmar.Rust.Statements.Rust_ExitStatement;
import com.eagle.programmar.Rust.Statements.Rust_ExpressionStatement;
import com.eagle.programmar.Rust.Statements.Rust_ForStatement;
import com.eagle.programmar.Rust.Statements.Rust_FunctionCall;
import com.eagle.programmar.Rust.Statements.Rust_IfStatement;
import com.eagle.programmar.Rust.Statements.Rust_LetStatement;
import com.eagle.programmar.Rust.Statements.Rust_MatchStatement;
import com.eagle.programmar.Rust.Statements.Rust_Pragma;
import com.eagle.programmar.Rust.Statements.Rust_ReturnStatement;
import com.eagle.programmar.Rust.Statements.Rust_WhileStatement;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Rust_Statement extends TokenChooser implements AbstractStatement
{
	public @CHOICE Rust_Comment XXcomment;

	public @CHOICE Rust_Block_Statement XXblockStatement;
	public @CHOICE Rust_ConstStatement XXdataStatement;
	public @CHOICE Rust_BreakStatement XXbreakStatement;
	public @CHOICE Rust_ExitStatement XXexitStatement;
	public @CHOICE Rust_IfStatement XXifStatement;
	public @CHOICE Rust_ForStatement XXforStatement;
	public @CHOICE Rust_LetStatement XXletStatement;
	public @CHOICE Rust_MatchStatement XXmatchStatement;
	public @CHOICE Rust_Pragma XXpragma;
	public @CHOICE Rust_ReturnStatement XXreturnStatement;
	public @CHOICE Rust_Use XXuseStatement;
	public @CHOICE Rust_WhileStatement XXwhileStatement;

	public @LAST Rust_FunctionCall XXfunctionCall;
	public @LAST Rust_ExpressionStatement XXexpressionStatement;

}
