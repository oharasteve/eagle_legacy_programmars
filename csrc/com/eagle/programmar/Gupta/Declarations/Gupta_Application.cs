// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

namespace com.eagle.programmar.Gupta.Declarations
{
	using Gupta_Declaration = com.eagle.programmar.Gupta.Gupta_Declaration;
	using Gupta_CommentToEndOfLine = com.eagle.programmar.Gupta.Terminals.Gupta_CommentToEndOfLine;
	using Gupta_Keyword = com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;

	public class Gupta_Application : Gupta_Declaration
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Application = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Application");
		public Gupta_Keyword Application = new Gupta_Keyword("Application");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword description = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("description");
		public Gupta_Keyword description = new Gupta_Keyword("description");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Gupta.Terminals.Gupta_CommentToEndOfLine comment;
		public Gupta_CommentToEndOfLine comment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Gupta_Libraries libraries;
		public Gupta_Libraries libraries;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) Gupta_Global_Declarations globalDeclarations;
		public Gupta_Global_Declarations globalDeclarations;
	}

}
