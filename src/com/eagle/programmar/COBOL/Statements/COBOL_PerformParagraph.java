// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2024

package com.eagle.programmar.COBOL.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.COBOL.COBOL_Paragraph;
import com.eagle.programmar.COBOL.COBOL_Paragraph.COBOL_SentenceOrComment;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement.COBOL_Paragraph_or_Section_Thru;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement.COBOL_PerformTestWhen;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_PerformParagraph extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) COBOL_Identifier_Reference performStartParagraph;
	public @S(20) @OPT COBOL_Paragraph_or_Section_Thru performThrough;
	public @S(30) @OPT COBOL_PerformTestWhen testWhen;
	public @S(40) @OPT TokenList<COBOL_PerformClause> clauseList;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if ((performThrough != null && performThrough.isPresent()) ||
				(testWhen != null && testWhen.isPresent()))
		{
			throw new RuntimeException("Can only PERFORM one paragraph right now");
		}

		String startPara = performStartParagraph.getValue();
		if (interpreter._TRACE) System.err.println("*** Calling " + startPara);

		// Have to search for the PARAGRAPH definition
		AbstractFunction fn = interpreter.findFunction(startPara);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a Paragraph named " + startPara);
		}
		COBOL_Paragraph paragraph = (COBOL_Paragraph) fn;
		if (paragraph._metrics == null)
		{
			paragraph._metrics = new CallMetrics(interpreter._metrics, startPara, this);
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();
		
		// Evaluate the paragraph
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (COBOL_SentenceOrComment sentence : paragraph.sentences._elements)
		{
			result = interpreter.tryToInterpret(sentence);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		
		long elapsedTime = System.nanoTime() - startTime;
		paragraph._metrics.addCallFrom(this, elapsedTime);
		
		return result;
	}
}