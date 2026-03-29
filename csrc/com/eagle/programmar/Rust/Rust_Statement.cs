// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

namespace com.eagle.programmar.Rust
{
	using Rust_Block_Statement = com.eagle.programmar.Rust.Statements.Rust_Block_Statement;
	using Rust_BreakStatement = com.eagle.programmar.Rust.Statements.Rust_BreakStatement;
	using Rust_ConstStatement = com.eagle.programmar.Rust.Statements.Rust_ConstStatement;
	using Rust_ExitStatement = com.eagle.programmar.Rust.Statements.Rust_ExitStatement;
	using Rust_ExpressionStatement = com.eagle.programmar.Rust.Statements.Rust_ExpressionStatement;
	using Rust_ForStatement = com.eagle.programmar.Rust.Statements.Rust_ForStatement;
	using Rust_FunctionCall = com.eagle.programmar.Rust.Statements.Rust_FunctionCall;
	using Rust_IfStatement = com.eagle.programmar.Rust.Statements.Rust_IfStatement;
	using Rust_LetStatement = com.eagle.programmar.Rust.Statements.Rust_LetStatement;
	using Rust_MatchStatement = com.eagle.programmar.Rust.Statements.Rust_MatchStatement;
	using Rust_ReturnStatement = com.eagle.programmar.Rust.Statements.Rust_ReturnStatement;
	using Rust_WhileStatement = com.eagle.programmar.Rust.Statements.Rust_WhileStatement;
	using Rust_Comment = com.eagle.programmar.Rust.Terminals.Rust_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Rust_Statement : TokenChooser, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_Comment XXcomment;
		public Rust_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_Block_Statement XXblockStatement;
		public Rust_Block_Statement XXblockStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_ConstStatement XXdataStatement;
		public Rust_ConstStatement XXdataStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_BreakStatement XXbreakStatement;
		public Rust_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_ExitStatement XXexitStatement;
		public Rust_ExitStatement XXexitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_IfStatement XXifStatement;
		public Rust_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_ForStatement XXforStatement;
		public Rust_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_LetStatement XXletStatement;
		public Rust_LetStatement XXletStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_MatchStatement XXmatchStatement;
		public Rust_MatchStatement XXmatchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_ReturnStatement XXreturnStatement;
		public Rust_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_Use XXuseStatement;
		public Rust_Use XXuseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_WhileStatement XXwhileStatement;
		public Rust_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Rust_FunctionCall XXfunctionCall;
		public Rust_FunctionCall XXfunctionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Rust_ExpressionStatement XXexpressionStatement;
		public Rust_ExpressionStatement XXexpressionStatement;

	}

}
