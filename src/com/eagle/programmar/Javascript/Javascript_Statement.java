// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.Javascript.Statements.Javascript_BreakStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ContinueStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_DoStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ForStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_IfStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ImportStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ReturnStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_StatementBlock;
import com.eagle.programmar.Javascript.Statements.Javascript_SwitchStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_ThrowStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_TryStatement;
import com.eagle.programmar.Javascript.Statements.Javascript_WhileStatement;
import com.eagle.programmar.Javascript.Symbols.Javascript_Label_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_Statement extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) @OPT Javascript_Label label;
	public @S(20) Javascript_RealStatement statement;

	public static class Javascript_Label extends TokenSequence
	{
		public @S(10) Javascript_Label_Definition label;
		public @S(20) PunctuationColon colon;
	}

	public static class Javascript_RealStatement extends TokenChooser
	{
		public @FIRST Javascript_StatementBlock XXblock;
		public @CHOICE Javascript_Data XXdata;
		public @CHOICE @CURIOUS("Extra semicolon") PunctuationSemicolon XXsemicolon;

		public @LAST static class Javascript_ExpressionStmt extends TokenSequence implements EagleRunnable
		{
			public @S(10) Javascript_Expression expression;
			public @S(20) @OPT TokenList<Javascript_MoreStatements> moreStatements;
			public @S(30) @OPT PunctuationSemicolon semicolon;

			public static class Javascript_MoreStatements extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) Javascript_Statement statement;
			}

			@Override
			public void interpret(EagleInterpreter interpreter)
			{
				interpreter.tryToInterpret(expression);
			}
		}

		public @CHOICE Javascript_BreakStatement XXbreakStatement;
		public @CHOICE Javascript_ContinueStatement XXcontinueStatement;
		public @CHOICE Javascript_DoStatement XXdoStatement;
		public @CHOICE Javascript_ForStatement XXforStatement;
		public @CHOICE Javascript_Function XXfunction;
		public @CHOICE Javascript_IfStatement XXifStatement;
		public @CHOICE Javascript_ImportStatement XXimportStatement;
		public @CHOICE Javascript_ReturnStatement XXreturnStatement;
		public @CHOICE Javascript_SwitchStatement XXswitchStatement;
		public @CHOICE Javascript_ThrowStatement XXthrowStatement;
		public @CHOICE Javascript_TryStatement XXtryStatement;
		public @CHOICE Javascript_WhileStatement XXwhileStatement;

		// public @LAST Javascript_UnparsedStatement XXunparsedStatement;
	}

	public static class Javascript_StatementOrComment extends TokenChooser
	{
		public @CHOICE Javascript_Statement XXstatement;
		public @CHOICE Javascript_Comment XXcomment;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return interpreter.tryToInterpret(statement);
	}
}
