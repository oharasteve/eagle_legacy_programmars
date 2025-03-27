// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

package com.eagle.programmar.Javascript.Statements;

import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Javascript.Javascript_Syntax;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_RegularExpression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.UnparsedElement;

public class Javascript_UnparsedStatement extends UnparsedElement
{
	private static EagleSyntax SYNTAX = new Javascript_Syntax();
	static String[] KEYWORDS = SYNTAX.allReservedWords();
	static String[] PUNCTS = new String[] {
			"+", "-", "*", "/", ".", ",", "?", ":", "=", ">", "(", ")", "[", "]", "&", "|"
	};

	@Override
	public @SKIP TokenList<? extends AbstractToken> unparsedPieces()
	{
		return elements;
	}

	public TokenList<Javascript_UnparsedElement> elements;

	public static class Javascript_UnparsedElement extends TokenChooser
	{
		public @CHOICE Javascript_Identifier_Reference XXid;
		public @LAST Javascript_PunctuationChoice XXpunct = new Javascript_PunctuationChoice(PUNCTS);
		public @CHOICE Javascript_Literal XXliteral;
		public @CHOICE Javascript_Number XXnumber;
		public @CHOICE Javascript_Comment XXcomment;
		public @CHOICE Javascript_RegularExpression XXregex;
		public @CHOICE Javascript_KeywordChoice XXkeyword = new Javascript_KeywordChoice(KEYWORDS);
	}
}
