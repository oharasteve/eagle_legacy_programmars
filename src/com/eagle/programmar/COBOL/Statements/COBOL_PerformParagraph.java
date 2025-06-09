// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2024

package com.eagle.programmar.COBOL.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.COBOL.COBOL_Paragraph;
import com.eagle.programmar.COBOL.COBOL_Paragraph.COBOL_SentenceOrComment;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformUntil;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformVarying;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement.COBOL_Paragraph_or_Section_Thru;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement.COBOL_PerformTestWhen;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_PerformParagraph extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
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

		// Have to search for the PARAGRAPH definition
		AbstractFunction fn = interpreter.findFunction(startPara);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a Paragraph named " + startPara);
		}
		COBOL_Paragraph paragraph = (COBOL_Paragraph) fn;

		// Prepare to evaluate the function
		long startTime = System.nanoTime();
		
		// Evaluate the paragraph
		interpreter.callingFunction(startPara, null);
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (COBOL_SentenceOrComment sentence : paragraph.sentences._elements)
		{
			result = interpreter.tryToInterpret(sentence);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		
		long elapsedTime = System.nanoTime() - startTime;

		if (paragraph._callMetrics == null)
		{
			paragraph._callMetrics = new CallMetrics(interpreter._metrics, startPara,
					paragraph.paragraphHeaders.first().paragraphName);
		}
		paragraph._callMetrics.addCallFrom(this, elapsedTime);
		
		// Remove parameter values (none really)
		interpreter.completedFunction(startPara, null);

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		if (performThrough != null && performThrough.isPresent())
		{
			throw new RuntimeException("Cannot handle PERFORM multiple paragraphs yet " + this);
		}
		
		String indexVar = null;
		AbstractExpression initExpr = null;
		AbstractExpression incrExpr = null;
		AbstractExpression whileExpr = null;
		
		TokenList<COBOL_PerformClause> clauses = this.clauseList;
		if (clauses != null)
		{
			for (COBOL_PerformClause clause : clauses._elements)
			{
				AbstractToken which = clause.getWhich();
				if (which instanceof COBOL_PerformVarying)
				{
					COBOL_PerformVarying varying = (COBOL_PerformVarying) which;
					indexVar = varying.id.getValue();
					AbstractExpression fromExpr = transformer.transformExpression(generator, varying.from);
					initExpr = generator.newAssignmentExpression(indexVar, null, AssignmentEnum.EQUALS, fromExpr, which);
					AbstractExpression byExpr = transformer.transformExpression(generator, varying.by);
					incrExpr = generator.newAssignmentExpression(indexVar, null, AssignmentEnum.PLUS_EQUALS, byExpr, which);
				}
				else if (which instanceof COBOL_PerformUntil)
				{
					COBOL_PerformUntil until = (COBOL_PerformUntil) which;
					AbstractExpression untilExpr = transformer.transformExpression(generator, until.condition);
					whileExpr = generator.newNotExpression(untilExpr, which);
				}
			}
		}

		AbstractVariable para = generator.newVariable(performStartParagraph.getValue());
		AbstractExpression expr = generator.newMethodInvocation(para, null, this);
		AbstractStatement stmt = generator.newExpressionStatement(expr, this);
		
		// Four cases: both varying and while; just varying; just while; neither
		if (initExpr != null)
		{
			return generator.newForLoopStatement1(initExpr, whileExpr, incrExpr, stmt, this);
		}
		if (whileExpr == null)
		{
			whileExpr = generator.newLogicalExpression(true, this);
		}
		return generator.newWhileStatement1(whileExpr, stmt, this);
	}
}