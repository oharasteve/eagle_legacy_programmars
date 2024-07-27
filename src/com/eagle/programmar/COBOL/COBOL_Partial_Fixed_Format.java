// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 20, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_ScreenSection.COBOL_ScreenDeclaration;
import com.eagle.programmar.COBOL.COBOL_Syntax.COBOL_Fixed_Format_Syntax;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class COBOL_Partial_Fixed_Format extends COBOL_Program
{
	public static final String COBOLPartial = "COBOL_Partial_Fixed_Format";

	public COBOL_Partial_Fixed_Format()
	{
		super(COBOLPartial, new COBOL_Fixed_Format_Syntax());
	}

	public @S(10) TokenList<COBOL_PartialWhat> pieces;

	public static class COBOL_PartialWhat extends TokenChooser
	{
		public @CHOICE COBOL_Directive XXdirective;
		public @CHOICE COBOL_Comment XXcomment;
		public @CHOICE COBOL_Paragraph XXparagraph;
		public @CHOICE COBOL_Section XXsection;
		public @CHOICE COBOL_ScreenDeclaration XXscreenDeclaration;
		public @CHOICE COBOL_DataDeclaration XXdeclarations;
		public @CHOICE COBOL_FileDescriptor XXfileDescriptor;
		public @CHOICE COBOL_FileSelect XXfileSelect;
	}
}
