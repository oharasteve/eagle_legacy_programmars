// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Sentence.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_EvaluateStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpseval.htm") COBOL_Keyword EVALUATE = new COBOL_Keyword("EVALUATE");
	public @S(20) COBOL_EvaluateWhat key;
	public @S(30) @OPT TokenList<COBOL_Comment> comments;
	public @S(40) @OPT TokenList<COBOL_EvaluateAlsoClause> alsos;
	public @S(50) TokenList<COBOL_EvaluateWhenClause> whens;
	public @S(60) @OPT COBOL_Keyword ENDEVALUATE = new COBOL_Keyword("END-EVALUATE");
	
	public static class COBOL_EvaluateWhat extends TokenChooser
	{
		public @CHOICE COBOL_Identifier_Reference id;
		public @FIRST COBOL_Keyword OTHER = new COBOL_Keyword("OTHER");
		
		// Careful -- Condition has to precede Expression here.
		public @CHOICE static class COBOL_EvaluateCondition extends TokenSequence
		{
			public @S(10) COBOL_Expression cond;
		}

		public @CHOICE static class COBOL_EvaluateExpression extends TokenSequence
		{
			public @S(10) COBOL_Expression expr;
			public @S(20) @OPT COBOL_EvaluateThru thru;
			
			public static class COBOL_EvaluateThru extends TokenSequence
			{
				public @S(10) COBOL_Keyword THRU = new COBOL_Keyword("THRU");
				public @S(20) COBOL_Expression expr;
			}
		}
	}

	public static class COBOL_EvaluateAlsoClause extends TokenSequence
	{
		public @S(10) COBOL_Keyword ALSO = new COBOL_Keyword("ALSO");
		public @S(20) COBOL_EvaluateWhat value;
	}

	public static class COBOL_EvaluateWhenClause extends TokenSequence
	{
		public @S(10) COBOL_Keyword WHEN = new COBOL_Keyword("WHEN");
		public @S(20) COBOL_EvaluateWhat value;
		public @S(30) @OPT TokenList<COBOL_EvaluateAlsoClause> alsos;
		public @S(40) @OPT TokenList<COBOL_StatementOrComment> statements; 
	}
}
