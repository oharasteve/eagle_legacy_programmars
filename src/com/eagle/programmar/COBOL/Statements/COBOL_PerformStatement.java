// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleTestable;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Sentence.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement.COBOL_PerformWhat.COBOL_PerformParagraph.COBOL_Paragraph_or_Section_Thru;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_PerformStatement extends COBOL_AbstractStatement implements EagleTestable, EagleRunnable
{
	public @S(10) @DOC("rlpsperf.htm") COBOL_Keyword PERFORM = new COBOL_Keyword("PERFORM");
	public @S(20) @OPT COBOL_PerformTestWhen testWhen;
	public @S(30) @OPT COBOL_PerformWhat what;
	public @S(40) @OPT COBOL_Keyword ENDPERFORM = new COBOL_Keyword("END-PERFORM");
	
	public static class COBOL_PerformTestWhen extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @S(20) COBOL_Keyword TEST = new COBOL_Keyword("TEST");
		public @S(30) COBOL_KeywordChoice when = new COBOL_KeywordChoice("BEFORE", "AFTER");
	}
	
	public static class COBOL_PerformWhat extends TokenChooser
	{
		public @CHOICE static class COBOL_PerformParagraph extends TokenSequence
		{
			public @S(10) COBOL_Identifier_Reference performStartParagraph;
			public @S(20) @OPT COBOL_Paragraph_or_Section_Thru performThrough;
			public @S(30) @OPT COBOL_PerformTestWhen testWhen;
			public @S(40) @OPT TokenList<COBOL_PerformClause> clauseList;
			
			public static class COBOL_Paragraph_or_Section_Thru extends TokenSequence
			{
				public @S(10) COBOL_KeywordChoice THRU = new COBOL_KeywordChoice("THRU", "THROUGH");
				public @S(20) COBOL_Identifier_Reference performEndParagraph;
			}
		}
		
		public @CHOICE static class COBOL_PerformNothing extends TokenSequence
		{
			public @S(10) TokenList<COBOL_PerformClause> clauseList;
		}
		
		public @CHOICE static class COBOL_PerformInline extends TokenSequence
		{
			public @S(10) @OPT TokenList<COBOL_PerformClause> clauseList;
			public @S(20) TokenList<COBOL_StatementOrComment> statements;
		}
		
		public @FIRST static class COBOL_PerformTimes extends TokenSequence
		{
			public @S(10) COBOL_Identifier_Reference performStartParagraph;
			public @S(20) @OPT COBOL_Paragraph_or_Section_Thru performThrough;
			public @S(30) COBOL_Expression times;
			public @S(40) COBOL_Keyword TIMES = new COBOL_Keyword("TIMES");
		}
		
		public @CHOICE static class COBOL_PerformTimesInline extends TokenSequence implements EagleRunnable
		{
			public @S(10) COBOL_Number times;
			public @S(20) COBOL_Keyword TIMES = new COBOL_Keyword("TIMES");
			public @S(30) TokenList<COBOL_StatementOrComment> statements;
			
			@Override
			public void interpret(EagleInterpreter interpreter)
			{
				int n = interpreter.getIntValue(times);
				for (int i = 0; i < n; i++)
				{
					for (COBOL_StatementOrComment stmt : statements._elements)
					{
						interpreter.tryToInterpret(stmt);
					}
				}
			}
		}
	}
	
	public static class COBOL_PerformClause extends TokenChooser
	{
		public @CHOICE static class COBOL_PerformVarying extends TokenSequence
		{
			public @S(10) COBOL_KeywordChoice varyingOrAfter = new COBOL_KeywordChoice("VARYING", "AFTER");
			public @S(20) COBOL_Modifiable_Identifier id;
			public @S(30) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
			public @S(40) COBOL_Expression from;
			public @S(50) COBOL_Keyword BY = new COBOL_Keyword("BY");
			public @S(60) COBOL_Expression by;
			public @S(70) @OPT COBOL_PerformUntil until;
		}
		
		public @CHOICE static class COBOL_PerformUntil extends TokenSequence
		{
			public @S(10) COBOL_Keyword UNTIL = new COBOL_Keyword("UNTIL");
			public @S(20) COBOL_Expression condition;
		}
	}

	@Override
	public Object[] addTests()
	{
		String pgm1 = "PERFORM 3 TIMES DISPLAY 99 END-PERFORM.";
		return new Object[] { "Perform1", null, pgm1, "99\n99\n99\n" };
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
	}
}
