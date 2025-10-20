// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
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
import com.eagle.programmar.Python.Statements.Python_Function;
import com.eagle.programmar.Python.Statements.Python_GlobalStatement;
import com.eagle.programmar.Python.Statements.Python_IfStatement;
import com.eagle.programmar.Python.Statements.Python_ImportStatement;
import com.eagle.programmar.Python.Statements.Python_MatchStatement;
import com.eagle.programmar.Python.Statements.Python_PassStatement;
import com.eagle.programmar.Python.Statements.Python_QuitStatement;
import com.eagle.programmar.Python.Statements.Python_RaiseStatement;
import com.eagle.programmar.Python.Statements.Python_ReturnStatement;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_SameLineStatement;
import com.eagle.programmar.Python.Statements.Python_TryStatement;
import com.eagle.programmar.Python.Statements.Python_TypeDeclaration;
import com.eagle.programmar.Python.Statements.Python_WhileStatement;
import com.eagle.programmar.Python.Statements.Python_WithStatement;
import com.eagle.programmar.Python.Statements.Python_YieldStatement;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Python_ComplexStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @NEWLINE Python_StartOfLine soln;
	public @S(20) Python_StatementOrComment statementOrComment;
	public @S(30) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
	public @S(40) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	public @S(50) @OPT Python_Comment comment;
	public @S(60) @OPT TokenList<Python_EndOfLine> eoln;
 
	public static class Python_StatementOrComment extends TokenChooser
	{
		// Only needed for Transformation. Look at Python_StatementBlock.java
		// Why? This is needed by addStatements() but there must be a better way.
		public @SKIP Python_MultilineStatement XXmultiStatement;

		public @FIRST Python_Comment XXcomment;
		public @CHOICE Python_SameLineStatement XXstatements;
	}
	
	public static class Python_Statement extends TokenChooser
	{
		public @CHOICE Python_Assignment XXassignment;
		public @CHOICE Python_AssertStatement XXassertStatement;
		public @CHOICE Python_AwaitStatement XXawaitStatement;
		public @CHOICE Python_BreakStatement XXbreakStatement;
		public @CHOICE Python_ClassDeclaration XXclassDeclaration;
		public @CHOICE Python_ContinueStatement XXcontinueStatement;
		public @CHOICE Python_Data XXdataDeclaration;
		public @CHOICE Python_DeleteStatement XXdelStatement;
		public @CHOICE Python_ExecStatement XXexecStatement;
		public @CHOICE Python_ForStatement XXforStatement;
		public @CHOICE Python_FromStatement XXfromStatement;
		public @CHOICE Python_Function XXfunctionDefinition;
		public @CHOICE Python_GlobalStatement XXglobalStatement;
		public @CHOICE Python_IfStatement XXifStatement;
		public @CHOICE Python_ImportStatement XXimportStatement;
		public @CHOICE Python_MatchStatement XXmatchStatement;
		public @CHOICE Python_PassStatement XXpassStatement;
		// public @CHOICE Python_PrintStatement XXprintStatement;  // Added in by Python2_Program
		public @CHOICE Python_QuitStatement XXquitStatement;
		public @CHOICE Python_RaiseStatement XXraiseStatement;
		public @CHOICE Python_ReturnStatement XXreturnStatement;
		public @CHOICE Python_TryStatement XXtryStatement;
		public @CHOICE Python_TypeDeclaration XXtypeDeclaration;
		public @CHOICE Python_WhileStatement XXwhileStatement;
		public @CHOICE Python_WithStatement XXwithStatement;
		public @CHOICE Python_YieldStatement XXyieldStatement;

		public @LAST Python_ExpressionStatement XXexpression; // Avoid conflict with 'for' statement
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return interpreter.tryToInterpret(statementOrComment);
	}
}