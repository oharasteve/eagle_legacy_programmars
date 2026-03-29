// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2011

namespace com.eagle.programmar.IBMASM
{
	using IBMASM_CSECT_Directive = com.eagle.programmar.IBMASM.Directives.IBMASM_CSECT_Directive;
	using IBMASM_EJECT_Directive = com.eagle.programmar.IBMASM.Directives.IBMASM_EJECT_Directive;
	using IBMASM_END_Directive = com.eagle.programmar.IBMASM.Directives.IBMASM_END_Directive;
	using IBMASM_EQU_Directive = com.eagle.programmar.IBMASM.Directives.IBMASM_EQU_Directive;
	using IBMASM_LTORG_Directive = com.eagle.programmar.IBMASM.Directives.IBMASM_LTORG_Directive;
	using IBMASM_USING_Directive = com.eagle.programmar.IBMASM.Directives.IBMASM_USING_Directive;
	using IBMASM_Label_Definition = com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
	using IBMASM_Remark = com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
	using IBMASM_Spaces = com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IBMASM_Directive : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT IBMASM_Label_Definition label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces spaces1;
		public IBMASM_Spaces spaces1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) IBMASM_Direct directive;
		public IBMASM_Direct directive;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT IBMASM_Spaces spaces2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT IBMASM_Remark remark;
		public  OPT;

		public class IBMASM_Direct : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_CSECT_Directive XXCSECT_directive;
			public IBMASM_CSECT_Directive XXCSECT_directive;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_EJECT_Directive XXEJECT_directive;
			public IBMASM_EJECT_Directive XXEJECT_directive;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_END_Directive XXEND_Directive;
			public IBMASM_END_Directive XXEND_Directive;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_EQU_Directive XXEQU_Directive;
			public IBMASM_EQU_Directive XXEQU_Directive;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_LTORG_Directive XXLTORG_directive;
			public IBMASM_LTORG_Directive XXLTORG_directive;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_USING_Directive XXUSING_Directive;
			public IBMASM_USING_Directive XXUSING_Directive;
		}
	}

}
