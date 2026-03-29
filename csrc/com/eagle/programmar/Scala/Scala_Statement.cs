// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala
{
	using Scala_Assignment = com.eagle.programmar.Scala.Statements.Scala_Assignment;
	using Scala_BlockStatement = com.eagle.programmar.Scala.Statements.Scala_BlockStatement;
	using Scala_BreakStatement = com.eagle.programmar.Scala.Statements.Scala_BreakStatement;
	using Scala_BreakableStatement = com.eagle.programmar.Scala.Statements.Scala_BreakableStatement;
	using Scala_ExpressionStatement = com.eagle.programmar.Scala.Statements.Scala_ExpressionStatement;
	using Scala_ForStatement = com.eagle.programmar.Scala.Statements.Scala_ForStatement;
	using Scala_Function = com.eagle.programmar.Scala.Statements.Scala_Function;
	using Scala_IfStatement = com.eagle.programmar.Scala.Statements.Scala_IfStatement;
	using Scala_PrintLnStatement = com.eagle.programmar.Scala.Statements.Scala_PrintLnStatement;
	using Scala_ReturnStatement = com.eagle.programmar.Scala.Statements.Scala_ReturnStatement;
	using Scala_ValStatement = com.eagle.programmar.Scala.Statements.Scala_ValStatement;
	using Scala_VarStatement = com.eagle.programmar.Scala.Statements.Scala_VarStatement;
	using Scala_WhileStatement = com.eagle.programmar.Scala.Statements.Scala_WhileStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Scala_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_Assignment XXassignment;
		public Scala_Assignment XXassignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_BlockStatement XXblockStatement;
		public Scala_BlockStatement XXblockStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_BreakStatement XXbreakStatement;
		public Scala_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_BreakableStatement XXbreakableStatement;
		public Scala_BreakableStatement XXbreakableStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_CommentEoln XXcomment;
		public Scala_CommentEoln XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_VarStatement XXvar;
		public Scala_VarStatement XXvar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_ValStatement XXval;
		public Scala_ValStatement XXval;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_ForStatement XXforStatement;
		public Scala_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_Function XXfunction;
		public Scala_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_IfStatement XXifStatement;
		public Scala_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_PrintLnStatement XXprintStatement;
		public Scala_PrintLnStatement XXprintStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_ReturnStatement XXreturnStatement;
		public Scala_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_WhileStatement XXwhileStatement;
		public Scala_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Scala_ExpressionStatement XXexpressionStatement;
		public Scala_ExpressionStatement XXexpressionStatement;
	}

}
