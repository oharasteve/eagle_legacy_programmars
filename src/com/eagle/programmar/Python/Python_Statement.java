// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.Python.Python_Statement.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Statements.Python_AssertStatement;
import com.eagle.programmar.Python.Statements.Python_Assignment;
import com.eagle.programmar.Python.Statements.Python_AwaitStatement;
import com.eagle.programmar.Python.Statements.Python_BreakStatement;
import com.eagle.programmar.Python.Statements.Python_ClassDeclaration;
import com.eagle.programmar.Python.Statements.Python_ContinueStatement;
import com.eagle.programmar.Python.Statements.Python_DeleteStatement;
import com.eagle.programmar.Python.Statements.Python_ExecStatement;
import com.eagle.programmar.Python.Statements.Python_ExpressionStatement;
import com.eagle.programmar.Python.Statements.Python_ForStatement;
import com.eagle.programmar.Python.Statements.Python_FromStatement;
import com.eagle.programmar.Python.Statements.Python_FunctionDefinition;
import com.eagle.programmar.Python.Statements.Python_GlobalStatement;
import com.eagle.programmar.Python.Statements.Python_IfStatement;
import com.eagle.programmar.Python.Statements.Python_ImportStatement;
import com.eagle.programmar.Python.Statements.Python_PassStatement;
import com.eagle.programmar.Python.Statements.Python_RaiseStatement;
import com.eagle.programmar.Python.Statements.Python_ReturnStatement;
import com.eagle.programmar.Python.Statements.Python_TryStatement;
import com.eagle.programmar.Python.Statements.Python_TypeDeclaration;
import com.eagle.programmar.Python.Statements.Python_WhileStatement;
import com.eagle.programmar.Python.Statements.Python_WithStatement;
import com.eagle.programmar.Python.Statements.Python_YieldStatement;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Python_Statement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @OPT @NEWLINE Python_StartOfLine soln;
	public @S(20) Python_StatementOrComment statementOrComment;
	public @S(30) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
	public @S(40) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	public @S(50) @OPT Python_Comment comment;
	public @S(60) @OPT Python_EndOfLine eoln;

	public static class Python_StatementOrComment extends TokenChooser
	{
		// Only needed for Transformation. Look at createStatementBlock in
		// Generate_Python_Statement
		public @SKIP Python_MultilineStatement multiStatement;

		public @FIRST Python_CommentList comments;
		public @CHOICE Python_Statement_List statements;
		public @CHOICE Python_EndOfLine eoln;
	}

	public static class Python_StatementBlock extends TokenChooser
	{
		public @CHOICE Python_Punctuation dots = new Python_Punctuation("...");

		public @CHOICE static class Python_SingleLineStatement extends TokenSequence implements EagleRunnableWithResult
		{
			public @S(10) SeparatedList<Python_Simple_Statement, PunctuationSemicolon> statements;
			public @S(20) @OPT Python_Comment comment;
			public @S(30) @OPT Python_EndOfLine eoln;

			@Override
			public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
			{
				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				for (int i = 0; i < statements.getPrimaryCount(); i++)
				{
					Python_Simple_Statement stmt = statements.getPrimaryElement(i);
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
				return result;
			}
		}

		public @CHOICE static class Python_MultilineStatement extends TokenSequence implements EagleRunnableWithResult
		{
			public @S(10) @OPT Python_Comment comment;
			public @S(20) Python_EndOfLine eoln;
			public @S(30) TokenList<Python_Statement> statements;

			@Override
			public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
			{
				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				for (Python_Statement stmt : statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
				return result;
			}
		}
	}
	
	public static class Python_Statement_List extends TokenSequence implements EagleRunnableWithResult
	{
		// This StartOfLine should be removed. But it breaks lots of Python
		// Such as $GitDir/Eagle/eagle_legacy_browser/pages/viewer.py
		public @S(10) @NEWLINE Python_StartOfLine soln = new Python_StartOfLine();
		public @S(20) SeparatedList<Python_Simple_Statement, Python_Statement_Separator> statements;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			return interpreter.tryToInterpret(statements.first());
		}
	}

	public static class Python_Statement_Separator extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon semicolon;
		public @CHOICE @CURIOUS("Comma instead of a semicolon") PunctuationComma comma;
	}

	public static class Python_Simple_Statement extends TokenChooser
	{
		public @CHOICE Python_Assignment assignment;
		public @CHOICE Python_AssertStatement assertStatement;
		public @CHOICE Python_AwaitStatement awaitStatement;
		public @CHOICE Python_BreakStatement breakStatement;
		public @CHOICE Python_ClassDeclaration classDeclaration;
		public @CHOICE Python_ContinueStatement continueStatement;
		public @CHOICE Python_DeleteStatement delStatement;
		public @CHOICE Python_ExecStatement execStatement;
		public @CHOICE Python_ForStatement forStatement;
		public @CHOICE Python_FromStatement fromStatement;
		public @CHOICE Python_FunctionDefinition functionDefinition;
		public @CHOICE Python_GlobalStatement globalStatement;
		public @CHOICE Python_IfStatement ifStatement;
		public @CHOICE Python_ImportStatement importStatement;
		public @CHOICE Python_PassStatement passStatement;
		public @CHOICE Python_RaiseStatement raiseStatement;
		public @CHOICE Python_ReturnStatement returnStatement;
		public @CHOICE Python_TryStatement tryStatement;
		public @CHOICE Python_TypeDeclaration typeDeclaration;
		public @CHOICE Python_WhileStatement whileStatement;
		public @CHOICE Python_WithStatement withStatement;
		public @CHOICE Python_YieldStatement yieldStatement;

		public @LAST Python_ExpressionStatement expression; // Avoid conflict with 'for' statement
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return interpreter.tryToInterpret(statementOrComment);
	}
}