// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.ObjectiveC
{
	using C_Program = com.eagle.programmar.C.C_Program;
	using C_Syntax = com.eagle.programmar.C.C_Syntax;
	using C_Comment = com.eagle.programmar.C.Terminals.C_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;

	public class ObjectiveC_Program : C_Program
	{
		public const string OBJECTIVEC = "ObjectiveC";

		public ObjectiveC_Program() : base(OBJECTIVEC, new ObjectiveC_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "TBD";
			}
		}

		// Step is 9 to avoid duplicate @S(10) in C_Program
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(9) @OPT TokenList<ObjectiveC_Element> items;
		public  OPT;

		public class ObjectiveC_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.C.C_Syntax.class) com.eagle.programmar.C.Terminals.C_Comment XXcomment;
			public @SYNTAX(typeof(C_Syntax)) C_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST @SYNTAX(com.eagle.programmar.C.C_Syntax.class) C_StatementOrComment XXstatementOrComment;
			public @SYNTAX(typeof(C_Syntax)) C_StatementOrComment XXstatementOrComment;
		}
	}

}
