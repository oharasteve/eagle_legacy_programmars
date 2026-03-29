// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 20, 2012

namespace com.eagle.programmar.COBOL
{
	using COBOL_ScreenDeclaration = com.eagle.programmar.COBOL.COBOL_ScreenSection.COBOL_ScreenDeclaration;
	using COBOL_Fixed_Format_Syntax = com.eagle.programmar.COBOL.COBOL_Syntax.COBOL_Fixed_Format_Syntax;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;

	public class COBOL_Partial_Fixed_Format : COBOL_Program
	{
		public const string COBOLPartial = "COBOL_Partial_Fixed_Format";

		public COBOL_Partial_Fixed_Format() : base(COBOLPartial, new COBOL_Fixed_Format_Syntax())
		{
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<COBOL_PartialWhat> pieces;
		public TokenList<COBOL_PartialWhat> pieces;

		public class COBOL_PartialWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Directive XXdirective;
			public COBOL_Directive XXdirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Comment XXcomment;
			public COBOL_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Paragraph XXparagraph;
			public COBOL_Paragraph XXparagraph;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Section XXsection;
			public COBOL_Section XXsection;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenDeclaration XXscreenDeclaration;
			public COBOL_ScreenDeclaration XXscreenDeclaration;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_DataDeclaration XXdeclarations;
			public COBOL_DataDeclaration XXdeclarations;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_FileDescriptor XXfileDescriptor;
			public COBOL_FileDescriptor XXfileDescriptor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_FileSelect XXfileSelect;
			public COBOL_FileSelect XXfileSelect;
		}
	}

}
