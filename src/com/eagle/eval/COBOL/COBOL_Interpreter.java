// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 23, 2012

package com.eagle.eval.COBOL;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Paragraph;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Paragraph.COBOL_SentenceOrComment;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Section;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Sentence;
import com.eagle.programmar.COBOL.COBOL_Program_Complete;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_StatementOrComment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;

public class COBOL_Interpreter extends EagleInterpreter
{
	public COBOL_Interpreter(ParserManager parser, EagleSymbolTable symbolTable)
	{
		super(parser, symbolTable);
	}

	@Override
	protected void evaluateExpression(AbstractExpression abs_expr)
	{
		COBOL_Expression expr = (COBOL_Expression) abs_expr;
		AbstractToken which = expr.getWhich();
		
		if (which instanceof EagleRunnable)
		{
			EagleRunnable runnable = (EagleRunnable) which;
			runnable.interpret(this);
		}
		else
		{
			throw new RuntimeException("Unable to evaulate expression " + (which.getClass().getName()));
		}
	}

	public void execute(COBOL_Program_Complete pgm)
	{
		//COBOL_Resolve_References.resolveReferences(pgm);
		
		COBOL_ProcedureDivision procDiv = pgm.procedureDiv;
		COBOL_Section firstSection = procDiv.sections.first();
		COBOL_Paragraph firstParagraph = firstSection.paragraphs.first();
		for (COBOL_SentenceOrComment sentenceItem : firstParagraph.sentences._elements)
		{
			AbstractToken whichSentence = sentenceItem.getWhich();
			if (whichSentence instanceof COBOL_Sentence)
			{
				COBOL_Sentence sentence = (COBOL_Sentence) whichSentence;
				for (COBOL_StatementOrComment statementItem : sentence.statements._elements)
				{
					AbstractToken whichStmtItem = statementItem.getWhich();
					if (whichStmtItem instanceof COBOL_Statement)
					{
						COBOL_Statement stmt = (COBOL_Statement) whichStmtItem;
						AbstractToken whichStmt = stmt.getWhich();
						if (whichStmt instanceof EagleRunnable)
						{
							EagleRunnable runnable = (EagleRunnable) whichStmt;
							runnable.interpret(this);
						}
						else throw new RuntimeException("Don't know how to interpret statement: " + whichStmt);
					}
				}
			}
		}
	}
}
