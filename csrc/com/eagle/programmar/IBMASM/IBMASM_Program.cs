// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2011

namespace com.eagle.programmar.IBMASM
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using IBMASM_DC_Instruction = com.eagle.programmar.IBMASM.Data.IBMASM_DC_Instruction;
	using IBMASM_DS_Instruction = com.eagle.programmar.IBMASM.Data.IBMASM_DS_Instruction;
	using IBMASM_Comment = com.eagle.programmar.IBMASM.Terminals.IBMASM_Comment;
	using IBMASM_EndOfLine = com.eagle.programmar.IBMASM.Terminals.IBMASM_EndOfLine;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IBMASM_Program : AbstractLanguage
	{
		public const string IBMASM = "IBMASM";

		public IBMASM_Program() : base(IBMASM, new IBMASM_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://publibz.boulder.ibm.com/bookmgr_OS390/libraryserver/zosv1r7/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<IBMASM_Line> lines;
		public TokenList<IBMASM_Line> lines;

		public class IBMASM_Line : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) IBMASM_LineContents contents;
			public IBMASM_LineContents contents;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IBMASM.Terminals.IBMASM_EndOfLine eoln;
			public IBMASM_EndOfLine eoln;
		}

		public class IBMASM_LineContents : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Comment XXcomment;
			public IBMASM_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Instruction XXinstruction;
			public IBMASM_Instruction XXinstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Directive XXdirective;
			public IBMASM_Directive XXdirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_Macro XXmacro;
			public IBMASM_Macro XXmacro;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_DC_Instruction XXdefineConstant;
			public IBMASM_DC_Instruction XXdefineConstant;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IBMASM_DS_Instruction XXdeclareStorage;
			public IBMASM_DS_Instruction XXdeclareStorage;
		}
	}

}
