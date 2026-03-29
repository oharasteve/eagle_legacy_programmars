// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

namespace com.eagle.programmar.Python
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Python_AssertStatement = com.eagle.programmar.Python.Statements.Python_AssertStatement;
	using Python_Assignment = com.eagle.programmar.Python.Statements.Python_Assignment;
	using Python_AwaitStatement = com.eagle.programmar.Python.Statements.Python_AwaitStatement;
	using Python_BreakStatement = com.eagle.programmar.Python.Statements.Python_BreakStatement;
	using Python_ClassDeclaration = com.eagle.programmar.Python.Statements.Python_ClassDeclaration;
	using Python_ContinueStatement = com.eagle.programmar.Python.Statements.Python_ContinueStatement;
	using Python_DeleteStatement = com.eagle.programmar.Python.Statements.Python_DeleteStatement;
	using Python_ExecStatement = com.eagle.programmar.Python.Statements.Python_ExecStatement;
	using Python_ExpressionStatement = com.eagle.programmar.Python.Statements.Python_ExpressionStatement;
	using Python_ForStatement = com.eagle.programmar.Python.Statements.Python_ForStatement;
	using Python_FromStatement = com.eagle.programmar.Python.Statements.Python_FromStatement;
	using Python_Function = com.eagle.programmar.Python.Statements.Python_Function;
	using Python_GlobalStatement = com.eagle.programmar.Python.Statements.Python_GlobalStatement;
	using Python_IfStatement = com.eagle.programmar.Python.Statements.Python_IfStatement;
	using Python_ImportStatement = com.eagle.programmar.Python.Statements.Python_ImportStatement;
	using Python_MatchStatement = com.eagle.programmar.Python.Statements.Python_MatchStatement;
	using Python_PassStatement = com.eagle.programmar.Python.Statements.Python_PassStatement;
	using Python_QuitStatement = com.eagle.programmar.Python.Statements.Python_QuitStatement;
	using Python_RaiseStatement = com.eagle.programmar.Python.Statements.Python_RaiseStatement;
	using Python_ReturnStatement = com.eagle.programmar.Python.Statements.Python_ReturnStatement;
	using Python_MultilineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
	using Python_SameLineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_SameLineStatement;
	using Python_TryStatement = com.eagle.programmar.Python.Statements.Python_TryStatement;
	using Python_TypeDeclaration = com.eagle.programmar.Python.Statements.Python_TypeDeclaration;
	using Python_WhileStatement = com.eagle.programmar.Python.Statements.Python_WhileStatement;
	using Python_WithStatement = com.eagle.programmar.Python.Statements.Python_WithStatement;
	using Python_YieldStatement = com.eagle.programmar.Python.Statements.Python_YieldStatement;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using Python_StartOfLine = com.eagle.programmar.Python.Terminals.Python_StartOfLine;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Python_ComplexStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE Python_StartOfLine soln;
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Python_StatementOrComment statementOrComment;
		public Python_StatementOrComment statementOrComment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @CURIOUS("Extra comma") com.eagle.tokens.punctuation.PunctuationComma comma;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Python_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.Python.Terminals.Python_EndOfLine> eoln;
		public  OPT;

		public class Python_StatementOrComment : TokenChooser
		{
			// Only needed for Transformation. Look at Python_StatementBlock.java
			// Why? This is needed by addStatements() but there must be a better way.
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP Python_MultilineStatement XXmultiStatement;
			public Python_MultilineStatement XXmultiStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Python_Comment XXcomment;
			public Python_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_SameLineStatement XXstatements;
			public Python_SameLineStatement XXstatements;
		}

		public class Python_Statement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Assignment XXassignment;
			public Python_Assignment XXassignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_AssertStatement XXassertStatement;
			public Python_AssertStatement XXassertStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_AwaitStatement XXawaitStatement;
			public Python_AwaitStatement XXawaitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_BreakStatement XXbreakStatement;
			public Python_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_ClassDeclaration XXclassDeclaration;
			public Python_ClassDeclaration XXclassDeclaration;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_ContinueStatement XXcontinueStatement;
			public Python_ContinueStatement XXcontinueStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Data XXdataDeclaration;
			public Python_Data XXdataDeclaration;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_DeleteStatement XXdelStatement;
			public Python_DeleteStatement XXdelStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_ExecStatement XXexecStatement;
			public Python_ExecStatement XXexecStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_ForStatement XXforStatement;
			public Python_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_FromStatement XXfromStatement;
			public Python_FromStatement XXfromStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Function XXfunctionDefinition;
			public Python_Function XXfunctionDefinition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_GlobalStatement XXglobalStatement;
			public Python_GlobalStatement XXglobalStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_IfStatement XXifStatement;
			public Python_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_ImportStatement XXimportStatement;
			public Python_ImportStatement XXimportStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_MatchStatement XXmatchStatement;
			public Python_MatchStatement XXmatchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_PassStatement XXpassStatement;
			public Python_PassStatement XXpassStatement;
			// public @CHOICE Python_PrintStatement XXprintStatement; // Added in by
			// Python2_Program
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_QuitStatement XXquitStatement;
			public Python_QuitStatement XXquitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_RaiseStatement XXraiseStatement;
			public Python_RaiseStatement XXraiseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_ReturnStatement XXreturnStatement;
			public Python_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_TryStatement XXtryStatement;
			public Python_TryStatement XXtryStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_TypeDeclaration XXtypeDeclaration;
			public Python_TypeDeclaration XXtypeDeclaration;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_WhileStatement XXwhileStatement;
			public Python_WhileStatement XXwhileStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_WithStatement XXwithStatement;
			public Python_WithStatement XXwithStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_YieldStatement XXyieldStatement;
			public Python_YieldStatement XXyieldStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Python_ExpressionStatement XXexpression;
			public Python_ExpressionStatement XXexpression; // Avoid conflict with 'for' statement
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			return interpreter.tryToInterpret(statementOrComment);
		}
	}
}
