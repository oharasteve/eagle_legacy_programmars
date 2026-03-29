// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.CPlus
{
	using EagleOverrideManager = com.eagle.parsers.EagleOverrideManager;
	using C_Program = com.eagle.programmar.C.C_Program;
	using C_Syntax = com.eagle.programmar.C.C_Syntax;
	using C_Comment = com.eagle.programmar.C.Terminals.C_Comment;
	using C_Literal = com.eagle.programmar.C.Terminals.C_Literal;
	using CMacro_StatementOrComment = com.eagle.programmar.CMacro.CMacro_StatementOrComment;
	using CMacro_Syntax = com.eagle.programmar.CMacro.CMacro_Syntax;
	using CPlus_Literal = com.eagle.programmar.CPlus.Terminals.CPlus_Literal;
	using ObjectiveC_Class = com.eagle.programmar.ObjectiveC.ObjectiveC_Class;
	using ObjectiveC_Interface = com.eagle.programmar.ObjectiveC.ObjectiveC_Interface;
	using ObjectiveC_Protocol = com.eagle.programmar.ObjectiveC.ObjectiveC_Protocol;
	using ObjectiveC_Syntax = com.eagle.programmar.ObjectiveC.ObjectiveC_Syntax;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;

	public class CPlus_Program : C_Program
	{
		public const string CPP = "Cpp";
	//	private static C_Expression _fakeExpr = null;

		public CPlus_Program() : base(CPP, new CPlus_Syntax())
		{

	//		// Can't easily have CPlus_Expression extend C_Expression. It's that old robot leg problem.
	//		if (_fakeExpr == null)
	//		{
	//			_fakeExpr = new C_Expression();		// Prime the _operators._list structure
	//			_fakeExpr.addOperator(CPlus_Expression.CPlus_NewExpression.class);
	//			fake.addOperator(CPlus_Expression.CPlus_NamespaceGlobal.clas);
	//			fake.addOperator(CPlus_Expression.CPlus_NamespaceSub.class);
	//		}

			C_Program.addPrimitive("BOOL"); // Might be ObjectiveC only

			TokenChooser.addChoice(typeof(C_StatementOrComment), typeof(CPlus_Extern));
			TokenChooser.addChoice(typeof(C_StatementOrComment), typeof(CPlus_Constructor));
		}

		public override string DocRoot
		{
			get
			{
				return "TBD";
			}
		}

		public override void findLanguageOverrides(EagleOverrideManager overrider)
		{
			// overrider.override(C_Expression.class, CPlus_Expression.class); // Times out
			// at 60 seconds. Why?
			overrider.@override(typeof(C_Literal), typeof(CPlus_Literal));
		}

		// Step is 9 to avoid duplicate @S(10) in C_Program
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(9) @OPT TokenList<CPlus_Element> items;
		public  OPT;

		public class CPlus_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST @SYNTAX(com.eagle.programmar.C.C_Syntax.class) com.eagle.programmar.C.Terminals.C_Comment XXcomment;
			public @SYNTAX(typeof(C_Syntax)) C_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Namespace XXnamespace;
			public CPlus_Namespace XXnamespace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Class XXclassDefinition;
			public CPlus_Class XXclassDefinition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Using XXusing;
			public CPlus_Using XXusing;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Method XXmethod;
			public CPlus_Method XXmethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Extern XXextern;
			public CPlus_Extern XXextern;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.ObjectiveC.ObjectiveC_Syntax.class) com.eagle.programmar.ObjectiveC.ObjectiveC_Class XXobjCclass;
			public @SYNTAX(typeof(ObjectiveC_Syntax)) ObjectiveC_Class XXobjCclass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.ObjectiveC.ObjectiveC_Syntax.class) com.eagle.programmar.ObjectiveC.ObjectiveC_Interface XXobjCinterface;
			public @SYNTAX(typeof(ObjectiveC_Syntax)) ObjectiveC_Interface XXobjCinterface;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.ObjectiveC.ObjectiveC_Syntax.class) com.eagle.programmar.ObjectiveC.ObjectiveC_Protocol XXobjCprotocol;
			public @SYNTAX(typeof(ObjectiveC_Syntax)) ObjectiveC_Protocol XXobjCprotocol;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.CMacro.CMacro_Syntax.class) com.eagle.programmar.CMacro.CMacro_StatementOrComment XXmacro;
			public @SYNTAX(typeof(CMacro_Syntax)) CMacro_StatementOrComment XXmacro;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST @SYNTAX(com.eagle.programmar.C.C_Syntax.class) C_StatementOrComment XXstatementOrComment;
			public @SYNTAX(typeof(C_Syntax)) C_StatementOrComment XXstatementOrComment;
		}
	}

}
