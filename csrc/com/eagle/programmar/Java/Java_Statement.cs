// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

namespace com.eagle.programmar.Java
{
	using Java_AnnotationDefinition = com.eagle.programmar.Java.Statements.Java_AnnotationDefinition;
	using Java_AssertStatement = com.eagle.programmar.Java.Statements.Java_AssertStatement;
	using Java_BreakStatement = com.eagle.programmar.Java.Statements.Java_BreakStatement;
	using Java_ContinueStatement = com.eagle.programmar.Java.Statements.Java_ContinueStatement;
	using Java_DoWhileStatement = com.eagle.programmar.Java.Statements.Java_DoWhileStatement;
	using Java_ExitStatement = com.eagle.programmar.Java.Statements.Java_ExitStatement;
	using Java_ExpressionStatement = com.eagle.programmar.Java.Statements.Java_ExpressionStatement;
	using Java_ForEachStatement = com.eagle.programmar.Java.Statements.Java_ForEachStatement;
	using Java_ForStatement = com.eagle.programmar.Java.Statements.Java_ForStatement;
	using Java_IfStatement = com.eagle.programmar.Java.Statements.Java_IfStatement;
	using Java_ReturnStatement = com.eagle.programmar.Java.Statements.Java_ReturnStatement;
	using Java_StatementBlock = com.eagle.programmar.Java.Statements.Java_StatementBlock;
	using Java_SuperStatement = com.eagle.programmar.Java.Statements.Java_SuperStatement;
	using Java_SwitchStatement = com.eagle.programmar.Java.Statements.Java_SwitchStatement;
	using Java_SynchronizedStatement = com.eagle.programmar.Java.Statements.Java_SynchronizedStatement;
	using Java_ThrowStatement = com.eagle.programmar.Java.Statements.Java_ThrowStatement;
	using Java_TryStatement = com.eagle.programmar.Java.Statements.Java_TryStatement;
	using Java_WhileStatement = com.eagle.programmar.Java.Statements.Java_WhileStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Java_Statement : TokenChooser, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Data XXdata;
		public Java_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Class XXclass;
		public Java_Class XXclass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Enum XXenum;
		public Java_Enum XXenum;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @CURIOUS("Empty statement") com.eagle.tokens.punctuation.PunctuationSemicolon XXemptyStatement;
		public @CURIOUS("Empty statement") PunctuationSemicolon XXemptyStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_AnnotationDefinition XXannotationDefinition;
		public Java_AnnotationDefinition XXannotationDefinition;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_AssertStatement XXassertStatement;
		public Java_AssertStatement XXassertStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_BreakStatement XXbreakStatement;
		public Java_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_ContinueStatement XXcontinueStatement;
		public Java_ContinueStatement XXcontinueStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_DoWhileStatement XXdoStatement;
		public Java_DoWhileStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_ExitStatement XXexitStatement;
		public Java_ExitStatement XXexitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_ForStatement XXforStatement;
		public Java_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_ForEachStatement XXforEachStatement;
		public Java_ForEachStatement XXforEachStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_IfStatement XXifStatement;
		public Java_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_ReturnStatement XXreturnStatement;
		public Java_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_StatementBlock XXstatementBlock;
		public Java_StatementBlock XXstatementBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_SuperStatement XXsuperStatement;
		public Java_SuperStatement XXsuperStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_SwitchStatement XXswitchStatement;
		public Java_SwitchStatement XXswitchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_SynchronizedStatement XXsynchronizedStatement;
		public Java_SynchronizedStatement XXsynchronizedStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_ThrowStatement XXthrowStatement;
		public Java_ThrowStatement XXthrowStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_TryStatement XXtryStatement;
		public Java_TryStatement XXtryStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_WhileStatement XXwhileStatement;
		public Java_WhileStatement XXwhileStatement;

		// Do this one last, just because it is so slow
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Java_ExpressionStatement XXassignmentStatement;
		public Java_ExpressionStatement XXassignmentStatement;

		// public @LAST Java_UnparsedStatement XXunparsed;
	}

}
