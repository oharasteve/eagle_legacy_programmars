// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.RPGFree
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using RPGFree_Assignment = com.eagle.programmar.RPGFree.Statements.RPGFree_Assignment;
	using RPGFree_Control = com.eagle.programmar.RPGFree.Statements.RPGFree_Control;
	using RPGFree_Declare = com.eagle.programmar.RPGFree.Statements.RPGFree_Declare;
	using RPGFree_Display = com.eagle.programmar.RPGFree.Statements.RPGFree_Display;
	using RPGFree_Return = com.eagle.programmar.RPGFree.Statements.RPGFree_Return;
	using RPGFree_Comment = com.eagle.programmar.RPGFree.Terminals.RPGFree_Comment;
	using RPGFree_Keyword = com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class RPGFree_Program : AbstractLanguage
	{
		public const string RPGFree = "RPG_Free";

		public RPGFree_Program() : base(RPGFree, new RPGFree_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "Unknown";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) RPGFree_Free free;
		public RPGFree_Free free;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<RPGFree_Item> items;
		public TokenList<RPGFree_Item> items;

		public class RPGFree_Free : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword FREE = new com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword("**FREE");
			public RPGFree_Keyword FREE = new RPGFree_Keyword("**FREE");
		}

		public class RPGFree_Item : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPGFree_Comment XXcomment;
			public RPGFree_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPGFree_Control XXctlOpt;
			public RPGFree_Control XXctlOpt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPGFree_Declare XXdeclare;
			public RPGFree_Declare XXdeclare;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPGFree_Assignment XXassign;
			public RPGFree_Assignment XXassign;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPGFree_Display XXdisplay;
			public RPGFree_Display XXdisplay;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPGFree_Return XXreturn;
			public RPGFree_Return XXreturn;
		}
	}

}
