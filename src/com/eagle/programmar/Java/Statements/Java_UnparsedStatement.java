// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2014

package com.eagle.programmar.Java.Statements;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_HexNumber;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.UnparsedElement;

public class Java_UnparsedStatement extends UnparsedElement
{
	private static EagleSyntax SYNTAX = new Java_Syntax();
	static String[] KEYWORDS = SYNTAX.allReservedWords();
	static String[] PUNCTS = new String[] {
			"+", "-", "*", "/", ".", ",", "?", ":", "<", "=", ">", "(", ")", "[", "]", "&", "|"
	};

	@Override
	public @SKIP TokenList<? extends AbstractToken> unparsedPieces()
	{
		return elements;
	}

	public TokenList<Java_UnparsedElement> elements;

	public static class Java_UnparsedElement extends TokenChooser
	{
		public @CHOICE Java_Identifier_Reference XXid;
		public @CHOICE Java_PunctuationChoice XXpunct = new Java_PunctuationChoice(PUNCTS);
		public @CHOICE Java_Literal XXliteral;
		public @CHOICE Java_Number XXnumber;
		public @CHOICE Java_HexNumber XXhex;
		public @CHOICE Java_Comment XXcomment;
		public @CHOICE Java_KeywordChoice XXkeyword = new Java_KeywordChoice(KEYWORDS);
	}
}
