// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2026

package com.eagle.programmar.Haskell;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Haskell.Statements.Haskell_DoStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_ExpressionStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_Function;
import com.eagle.programmar.Haskell.Statements.Haskell_FunctionCall;
import com.eagle.programmar.Haskell.Statements.Haskell_IfStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_ImportStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_LetStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_MapMStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_PutStrStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_ReturnStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_StatementBlock.Haskell_MultilineStatement;
import com.eagle.programmar.Haskell.Statements.Haskell_StatementBlock.Haskell_SameLineStatement;
import com.eagle.programmar.Haskell.Terminals.Haskell_Comment;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_StartOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Haskell_ComplexStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @NEWLINE Haskell_StartOfLine soln;
	public @S(20) Haskell_StatementOrComment statementOrComment;
	public @S(30) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
	public @S(40) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	public @S(50) @OPT Haskell_Comment comment;
	public @S(60) @OPT TokenList<Haskell_EndOfLine> eoln;

	public static class Haskell_StatementOrComment extends TokenChooser
	{
		// Only needed for Transformation. Look at Haskell_StatementBlock.java
		// Why? This is needed by addStatements() but there must be a better way.
		public @SKIP Haskell_MultilineStatement XXmultiStatement;

		public @FIRST Haskell_Comment XXcomment;
		public @CHOICE Haskell_SameLineStatement XXstatements;
	}

	public static class Haskell_Statement extends TokenChooser
	{
		public @CHOICE Haskell_FunctionCall XXassignment;
		public @CHOICE Haskell_DoStatement XXdoStatement;
		public @CHOICE Haskell_Function XXfunctionDefinition;
		public @CHOICE Haskell_IfStatement XXifStatement;
		public @CHOICE Haskell_ImportStatement XXimportStatement;
		public @CHOICE Haskell_LetStatement XXletStatement;
		public @CHOICE Haskell_MapMStatement XXmapMStatement;
		public @CHOICE Haskell_PutStrStatement XXputStrState,emt;
		public @CHOICE Haskell_ReturnStatement XXreturnStatement;

		public @LAST Haskell_ExpressionStatement XXexpression; // Avoid conflict with 'for' statement
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return interpreter.tryToInterpret(statementOrComment);
	}
}