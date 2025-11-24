// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_PerformStatement extends COBOL_AbstractStatement
		implements EagleRunnableWithResult, EagleTransformableStatement
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

	public static class COBOL_Paragraph_or_Section_Thru extends TokenSequence
	{
		public @S(10) COBOL_KeywordChoice THRU = new COBOL_KeywordChoice("THRU", "THROUGH");
		public @S(20) COBOL_Identifier_Reference performEndParagraph;
	}

	public static class COBOL_PerformWhat extends TokenChooser
	{
		public @CHOICE COBOL_PerformParagraph XXperformParagraph;
		public @CHOICE COBOL_PerformInline XXperformInline;

		public @LAST static class COBOL_PerformNothing extends TokenSequence
		{
			public @S(10) TokenList<COBOL_PerformClause> clauseList;
		}

		public @FIRST static class COBOL_PerformTimes extends TokenSequence
		{
			public @S(10) COBOL_Identifier_Reference performStartParagraph;
			public @S(20) @OPT COBOL_Paragraph_or_Section_Thru performThrough;
			public @S(30) COBOL_Expression times;
			public @S(40) COBOL_Keyword TIMES = new COBOL_Keyword("TIMES");
		}

		public @CHOICE static class COBOL_PerformTimesInline extends TokenSequence
		{
			public @S(10) COBOL_Number times;
			public @S(20) COBOL_Keyword TIMES = new COBOL_Keyword("TIMES");
			public @S(30) TokenList<COBOL_StatementOrComment> statements;
		}
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (testWhen != null && testWhen.isPresent())
		{
			throw new RuntimeException("Can't handle PERFORM TEST yet");
		}

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		if (what.getWhich() instanceof COBOL_PerformParagraph)
		{
			COBOL_PerformParagraph para = (COBOL_PerformParagraph) what.getWhich();
			result = para.interpretStatement(interpreter);
		}
		else if (what.getWhich() instanceof COBOL_PerformInline)
		{
			COBOL_PerformInline inline = (COBOL_PerformInline) what.getWhich();
			result = inline.interpretStatement(interpreter);
		}
		else
		{
			throw new RuntimeException("Can only handle simple PERFORMs right now, not: " + what.getWhich());
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		if (what.getWhich() instanceof COBOL_PerformParagraph)
		{
			COBOL_PerformParagraph para = (COBOL_PerformParagraph) what.getWhich();
			return para.transformStatement(transformer, generator);
		}
		if (what.getWhich() instanceof COBOL_PerformInline)
		{
			COBOL_PerformInline inline = (COBOL_PerformInline) what.getWhich();
			return inline.transformStatement(transformer, generator);
		}
		throw new RuntimeException("Unable to handle PERFORM: " + this);
	}
}
