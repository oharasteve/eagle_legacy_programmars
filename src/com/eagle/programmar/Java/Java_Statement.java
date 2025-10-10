// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Statements.Java_AnnotationDefinition;
import com.eagle.programmar.Java.Statements.Java_AssertStatement;
import com.eagle.programmar.Java.Statements.Java_BreakStatement;
import com.eagle.programmar.Java.Statements.Java_ContinueStatement;
import com.eagle.programmar.Java.Statements.Java_DoWhileStatement;
import com.eagle.programmar.Java.Statements.Java_ExitStatement;
import com.eagle.programmar.Java.Statements.Java_ExpressionStatement;
import com.eagle.programmar.Java.Statements.Java_ForEachStatement;
import com.eagle.programmar.Java.Statements.Java_ForStatement;
import com.eagle.programmar.Java.Statements.Java_IfStatement;
import com.eagle.programmar.Java.Statements.Java_ReturnStatement;
import com.eagle.programmar.Java.Statements.Java_StatementBlock;
import com.eagle.programmar.Java.Statements.Java_SuperStatement;
import com.eagle.programmar.Java.Statements.Java_SwitchStatement;
import com.eagle.programmar.Java.Statements.Java_SynchronizedStatement;
import com.eagle.programmar.Java.Statements.Java_ThrowStatement;
import com.eagle.programmar.Java.Statements.Java_TryStatement;
import com.eagle.programmar.Java.Statements.Java_WhileStatement;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Statement extends TokenChooser implements AbstractStatement
{
	public @CHOICE Java_Data XXdata;
	public @CHOICE Java_Class XXclass;
	public @CHOICE Java_Enum XXenum;
 
	public @CHOICE @CURIOUS("Empty statement") PunctuationSemicolon XXemptyStatement;

	public @CHOICE Java_AnnotationDefinition XXannotationDefinition;

	public @CHOICE Java_AssertStatement XXassertStatement;
	public @CHOICE Java_BreakStatement XXbreakStatement;
	public @CHOICE Java_ContinueStatement XXcontinueStatement;
	public @CHOICE Java_DoWhileStatement XXdoStatement;
	public @CHOICE Java_ExitStatement XXexitStatement;
	public @CHOICE Java_ForStatement XXforStatement;
	public @CHOICE Java_ForEachStatement XXforEachStatement;
	public @CHOICE Java_IfStatement XXifStatement;
	public @CHOICE Java_ReturnStatement XXreturnStatement;
	public @CHOICE Java_StatementBlock XXstatementBlock;
	public @CHOICE Java_SuperStatement XXsuperStatement;
	public @CHOICE Java_SwitchStatement XXswitchStatement;
	public @CHOICE Java_SynchronizedStatement XXsynchronizedStatement;
	public @CHOICE Java_ThrowStatement XXthrowStatement;
	public @CHOICE Java_TryStatement XXtryStatement;
	public @CHOICE Java_WhileStatement XXwhileStatement;

	// Do this one last, just because it is so slow
	public @LAST Java_ExpressionStatement XXassignmentStatement;

	// public @LAST Java_UnparsedStatement XXunparsed;
}
