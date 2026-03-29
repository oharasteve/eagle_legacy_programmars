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
	using Gupta_Variable_Declaration = com.eagle.programmar.Gupta.Gupta_Variable_Declaration;
	using Gupta_Keyword = com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
	using TokenList = com.eagle.tokens.TokenList;

	public class Gupta_Constants : Gupta_Declaration
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Constants = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Constants");
		public Gupta_Keyword Constants = new Gupta_Keyword("Constants");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Gupta_System_Constants systemConstants;
		public Gupta_System_Constants systemConstants;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Gupta_User_Constants userConstants;
		public Gupta_User_Constants userConstants;

		public class Gupta_System_Constants : Gupta_Declaration
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword System = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("System");
			public Gupta_Keyword System = new Gupta_Keyword("System");
		}

		public class Gupta_User_Constants : Gupta_Declaration
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword User = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("User");
			public Gupta_Keyword User = new Gupta_Keyword("User");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Gupta.Gupta_Variable_Declaration> constants;
			public TokenList<Gupta_Variable_Declaration> constants;
		}
	}

}
