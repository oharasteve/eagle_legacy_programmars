// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

namespace com.eagle.programmar.Gupta
{
	using Gupta_Call_Statement = com.eagle.programmar.Gupta.Statements.Gupta_Call_Statement;
	using Gupta_Comment_Statement = com.eagle.programmar.Gupta.Statements.Gupta_Comment_Statement;
	using Gupta_If_Statement = com.eagle.programmar.Gupta.Statements.Gupta_If_Statement;
	using Gupta_Return_Statement = com.eagle.programmar.Gupta.Statements.Gupta_Return_Statement;
	using Gupta_Set_Statement = com.eagle.programmar.Gupta.Statements.Gupta_Set_Statement;
	using Gupta_While_Statement = com.eagle.programmar.Gupta.Statements.Gupta_While_Statement;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Gupta_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Gupta_Call_Statement XXcallStatement;
		public Gupta_Call_Statement XXcallStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Gupta_Comment_Statement XXcommentStatement;
		public Gupta_Comment_Statement XXcommentStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Gupta_If_Statement XXifStatement;
		public Gupta_If_Statement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Gupta_Return_Statement XXreturnStatement;
		public Gupta_Return_Statement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Gupta_Set_Statement XXsetStatement;
		public Gupta_Set_Statement XXsetStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Gupta_While_Statement XXwhileStatement;
		public Gupta_While_Statement XXwhileStatement;
	}

}
