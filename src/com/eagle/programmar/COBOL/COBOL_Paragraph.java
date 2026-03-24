// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.COBOL;

import java.util.HashSet;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.COBOL.COBOL_ScreenSection.COBOL_ScreenDeclaration;
import com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Symbols.COBOL_Paragraph_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformer;

public class COBOL_Paragraph extends TokenSequence
		implements EagleRunnable, AbstractFunction
{
	public @S(10) @OPT TokenList<COBOL_ParagraphHeader> paragraphHeaders;
	public @S(20) TokenList<COBOL_SentenceOrComment> sentences;

	public static class COBOL_SentenceOrComment extends TokenChooser
	{
		public @CHOICE COBOL_Comment XXcomment;
		public @CHOICE COBOL_Sentence XXsentence;
		public @LAST COBOL_ScreenDeclaration XXscreen;

		public @LAST static class COBOL_DataInParagraph extends TokenSequence
		{
			public @S(10) TokenList<COBOL_CopyOrDataDeclaration> data;
		}
	}

	public static class COBOL_ParagraphHeader extends TokenSequence
	{
		public @S(10) COBOL_Paragraph_Definition paragraphName;
		public @S(20) PunctuationPeriod dot;
	}

	public @SKIP CallMetrics _callMetrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_SentenceOrComment sentence : sentences._elements)
		{
			interpreter.tryToInterpret(sentence);
		}
	}

	public void transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String paraName = "paragraph_with_no_name";
		for (COBOL_ParagraphHeader header : paragraphHeaders._elements)
		{
			paraName = COBOL_Variable.repairName(header.paragraphName.getValue());
		}
		if (!paraName.equals("main"))
		{
			generator.addMethod(null, paraName, paragraphHeaders);
		}

		findGlobalVariables(transformer, generator);
		
		for (COBOL_SentenceOrComment sentOrComm : sentences._elements)
		{
			if (sentOrComm.getWhich() instanceof COBOL_Sentence)
			{
				COBOL_Sentence sent = (COBOL_Sentence) sentOrComm.getWhich();
				sent.transform(transformer, generator);
			}
		}

		if (!paraName.equals("main"))
		{
			generator.doneMethod();
		}
	}

	private void findGlobalVariables(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Why isn't there a pointer up to the COBOL_Program at the top of the tree?
		AbstractToken parent = this.getParent();
		COBOL_Program_Complete prog = null;
		while (parent != null)
		{
			if (parent instanceof COBOL_Program_Complete)
			{
				prog = (COBOL_Program_Complete) parent;
				break;
			}
			parent = parent.getParent();
		}
		if (prog == null) return;
		
		HashSet<String> added = new HashSet<String>();
		for (AssignMetrics var : transformer._metrics.findAllAssignments())
		{
			int line = var._startingLine;
			// System.err.println("****** " + line + " " + this._currentLine + "-" + this._endLine);
			if (line >= this._currentLine && line <= this._endLine)
			{
				String varName = var._symbolName;
				if (! added.contains(varName))
				{
					AbstractStatement newStmt = generator.newGlobalVariable(varName, null);
					generator.addStatement(newStmt, null);
					added.add(varName);
				}
			}
		}
	}
}