// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Java_Method.Java_MethodModifier;
import com.eagle.programmar.Java.Statements.Java_AssertStatement;
import com.eagle.programmar.Java.Statements.Java_BreakStatement;
import com.eagle.programmar.Java.Statements.Java_ContinueStatement;
import com.eagle.programmar.Java.Statements.Java_DoStatement;
import com.eagle.programmar.Java.Statements.Java_ExpressionStatement;
import com.eagle.programmar.Java.Statements.Java_ForStatement;
import com.eagle.programmar.Java.Statements.Java_IfStatement;
import com.eagle.programmar.Java.Statements.Java_ReturnStatement;
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
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
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
	public @CHOICE Java_Data jdata;
	public @CHOICE Java_Class jclass;
	public @CHOICE Java_Enum jenum;

	public @CHOICE @CURIOUS("Empty statement") PunctuationSemicolon emptyStatement;

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

	public @CHOICE static class Java_StatementBlock extends TokenSequence implements EagleScopeInterface
	{
		public @S(10) @OPT Java_Label label;
		public @S(20) @INDENT PunctuationLeftBrace leftBrace;
		public @S(30) @OPT TokenList<Java_StatementOrComment> statements;
		public @S(40) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon1;
		public @S(50) @OUTDENT PunctuationRightBrace rightBrace;

		private EagleScope _scope = new EagleScope(this, Java_Syntax.isCaseSensitive);

		@Override
		public EagleScope getScope()
		{
			return _scope;
		}

//		@Override
//		public void setScope(EagleScope scope)
//		{
//			_scope = scope;
//		}
	}

	public @CHOICE Java_AssertStatement assertStatement;
	public @CHOICE Java_BreakStatement breakStatement;
	public @CHOICE Java_ContinueStatement continueStatement;
	public @CHOICE Java_DoStatement doStatement;
	public @CHOICE Java_ForStatement forStatement;
	public @CHOICE Java_IfStatement ifStatement;
	public @CHOICE Java_ReturnStatement returnStatement;
	public @CHOICE Java_SuperStatement superStatement;
	public @CHOICE Java_SwitchStatement switchStatement;
	public @CHOICE Java_SynchronizedStatement synchronizedStatement;
	public @CHOICE Java_ThrowStatement throwStatement;
	public @CHOICE Java_TryStatement tryStatement;
	public @CHOICE Java_WhileStatement whileStatement;

	// Do this one last, just because it is so slow
	public @LAST Java_ExpressionStatement assignmentStatement;

	// public @LAST Java_UnparsedStatement unparsed;
}
