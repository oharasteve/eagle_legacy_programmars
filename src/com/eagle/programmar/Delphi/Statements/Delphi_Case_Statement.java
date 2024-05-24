// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2014

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Case_Statement extends TokenSequence
{
	public @S(10) @DOC("Declarations_and_Statements_(Delphi)#Case_Statements") Delphi_Keyword CASE = new Delphi_Keyword(
			"Case");
	public @S(20) Delphi_Expression expr;
	public @S(30) Delphi_Keyword OF = new Delphi_Keyword("Of");
	public @S(40) TokenList<Delphi_CaseClause> clauses;
	public @S(50) Delphi_Keyword END = new Delphi_Keyword("End");

	public static class Delphi_CaseClause extends TokenSequence
	{
		public @S(10) Delphi_Expression expr;
		public @S(20) PunctuationColon colon;
		public @S(30) Delphi_Statement stmt;
		public @S(40) PunctuationSemicolon semicolon;
	}
}
