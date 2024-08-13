// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Java_Method.Java_MethodModifier;
import com.eagle.programmar.Java.Statements.Java_AssertStatement;
import com.eagle.programmar.Java.Statements.Java_BreakStatement;
import com.eagle.programmar.Java.Statements.Java_ContinueStatement;
import com.eagle.programmar.Java.Statements.Java_DoStatement;
import com.eagle.programmar.Java.Statements.Java_ExpressionStatement;
import com.eagle.programmar.Java.Statements.Java_ForEachStatement;
import com.eagle.programmar.Java.Statements.Java_ForStatement;
import com.eagle.programmar.Java.Statements.Java_IfStatement;
import com.eagle.programmar.Java.Statements.Java_PrintStatement;
import com.eagle.programmar.Java.Statements.Java_ReturnStatement;
import com.eagle.programmar.Java.Statements.Java_StatementBlock;
import com.eagle.programmar.Java.Statements.Java_SuperStatement;
import com.eagle.programmar.Java.Statements.Java_SwitchStatement;
import com.eagle.programmar.Java.Statements.Java_SynchronizedStatement;
import com.eagle.programmar.Java.Statements.Java_ThrowStatement;
import com.eagle.programmar.Java.Statements.Java_TryStatement;
import com.eagle.programmar.Java.Statements.Java_WhileStatement;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Statement extends TokenChooser implements AbstractStatement
{
	public @CHOICE Java_Data XXdata;
	public @CHOICE Java_Class XXclass;
	public @CHOICE Java_Enum XXenum;
 
	public @CHOICE @CURIOUS("Empty statement") PunctuationSemicolon XXemptyStatement;

	public @CHOICE static class Java_AnnotationDefinition extends TokenSequence
	{
		public @S(10) @OPT Java_Annotation annotation;
		public @S(20) TokenList<Java_MethodModifier> modifiers;
		public @S(30) Java_Punctuation atSign = new Java_Punctuation('@');
		public @S(40) Java_Keyword INTERFACE = new Java_Keyword("interface");
		public @S(50) Java_Identifier id;
		public @S(60) PunctuationLeftBrace leftBrace;
		public @S(70) @OPT TokenList<Java_Comment> comments;
		public @S(80) @OPT Java_AnnotationParameter parameter;
		public @S(90) PunctuationRightBrace rightBrace;

		public static class Java_AnnotationParameter extends TokenSequence
		{
			public @S(10) Java_Type type;
			public @S(20) Java_Identifier id;
			public @S(30) PunctuationLeftParen leftParen;
			public @S(40) PunctuationRightParen rightParen;
			public @S(50) PunctuationSemicolon semicolon;
		}
	}

	public @CHOICE Java_AssertStatement XXassertStatement;
	public @CHOICE Java_BreakStatement XXbreakStatement;
	public @CHOICE Java_ContinueStatement XXcontinueStatement;
	public @CHOICE Java_DoStatement XXdoStatement;
	public @CHOICE Java_ForStatement XXforStatement;
	public @CHOICE Java_ForEachStatement XXforEachStatement;
	public @CHOICE Java_IfStatement XXifStatement;
	public @CHOICE Java_PrintStatement XXprintStatement;
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
