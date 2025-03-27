// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2012

package com.eagle.programmar.COBOL.Statements;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.COBOL.COBOL_Syntax.COBOL_Fixed_Format_Syntax;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.UnparsedElement;

public class COBOL_UnparsedStatement extends UnparsedElement
{
	private static EagleSyntax SYNTAX = new COBOL_Fixed_Format_Syntax(); // Any COBOL syntax works, just need reserved
																			// words
	static String[] KEYWORDS = SYNTAX.allReservedWords();
	static String[] PUNCTS = new String[] {
			"+", "-", "*", "/", ",", "(", ")"
	};

	@Override
	public @SKIP TokenList<? extends AbstractToken> unparsedPieces()
	{
		return elements;
	}

	public TokenList<COBOL_UnparsedElement> elements;

	public static class COBOL_UnparsedElement extends TokenChooser
	{
		public @CHOICE COBOL_Identifier_Reference XXid;
		public @CHOICE COBOL_PunctuationChoice XXpunct = new COBOL_PunctuationChoice(PUNCTS);
		public @CHOICE COBOL_Literal XXliteral;
		public @CHOICE COBOL_Number XXnumber;
		public @CHOICE COBOL_HexNumber XXhex;
		public @CHOICE COBOL_Comment XXcomment;
		public @CHOICE COBOL_KeywordChoice XXkeyword = new COBOL_KeywordChoice(KEYWORDS);
	}
}
