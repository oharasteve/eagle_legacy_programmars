// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_MultiplyStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsmult.htm") COBOL_Keyword MULTIPLY = new COBOL_Keyword("MULTIPLY");
	public @S(20) COBOL_MultiplyType type;
	public @S(30) @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");
	public @S(40) @OPT COBOL_MultiplyOnSizeError onError;

	public static class COBOL_MultiplyType extends TokenChooser
	{
		public @CHOICE static class COBOL_MultiplyNoGiving extends TokenSequence
		{
			// This seems backwards. MULTIPLY -1 BY X. Oh well.
			// Look at https://www.ibm.com/support/knowledgecenter/SS6SG3_6.3.0/lr/ref/rlpsmult.html
			public @S(10) COBOL_Expression expression;
			public @S(20) COBOL_Keyword BY = new COBOL_Keyword("BY");
			public @S(30) COBOL_Variable var;
		}

		public @FIRST static class COBOL_MultiplyWithGiving extends TokenSequence
		{
			public @S(10) COBOL_Expression expr;
			public @S(20) COBOL_Keyword BY = new COBOL_Keyword("BY");
			public @S(30) COBOL_Expression expression;
			public @S(40) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
			public @S(50) COBOL_Variable var;
			public @S(60) @OPT TokenList<COBOL_MultiplyMoreVars> moreVars;
			
			public static class COBOL_MultiplyMoreVars extends TokenSequence
			{
				public @S(10) @OPT PunctuationComma comma;
				public @S(20) COBOL_Variable var;
			}
		}
	}
	
	public static class COBOL_MultiplyOnSizeError extends TokenSequence
	{
		public @S(10) COBOL_Keyword ON = new COBOL_Keyword("ON");
		public @S(20) COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
		public @S(30) COBOL_Keyword ERROR = new COBOL_Keyword("ERROR");
		public @S(40) TokenList<COBOL_Statement> actions;
	}
}
