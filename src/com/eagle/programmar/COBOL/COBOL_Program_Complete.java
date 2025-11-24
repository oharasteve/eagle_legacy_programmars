// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 25, 2013

package com.eagle.programmar.COBOL;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_DataSection;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public abstract class COBOL_Program_Complete extends COBOL_Program
		implements EagleRunnable, EagleTransformableProgram, EagleScopeInterface
{
	// Components of a complete COBOL Program
	public @S(10) @OPT TokenList<COBOL_Comment> comments1;
	public @S(20) @OPT TokenList<COBOL_Directive> directives;
	public @S(30) @OPT TokenList<COBOL_Comment> comments2;
	public @S(40) @OPT COBOL_SpecialNames specialNames;
	public @S(50) @OPT COBOL_IdentificationDivision identificationDiv;
	public @S(60) @OPT COBOL_EnvironmentDivision environmentDiv;
	public @S(70) @OPT TokenList<COBOL_Comment> comments3;
	public @S(80) @OPT COBOL_DataDivision dataDiv;
	public @S(90) COBOL_ProcedureDivision procedureDiv;

	public @S(100) @OPT TokenList<COBOL_Program_Free_Format> nestedPrograms;

	public @S(110) @OPT COBOL_EndProgram endProgram;

	public static class COBOL_EndProgram extends TokenSequence
	{
		public @S(10) COBOL_Keyword END = new COBOL_Keyword("END");
		public @S(20) COBOL_Keyword PROGRAM = new COBOL_Keyword("PROGRAM");
		public @S(30) COBOL_Identifier_Reference programId;
		public @S(40) PunctuationPeriod dot;
	}

	private @SKIP EagleScope _scope = new EagleScope(this, COBOL_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	public COBOL_Program_Complete(String name, COBOL_Syntax syntax)
	{
		super(name, syntax);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Pass 1 : Collect all the variables in Working Storage
		collectDataVariables(interpreter);

		// Pass 2 : Collect all the paragraph names
		collectParagraphNames(interpreter);

		// Pass 3 -- now run it
		interpreter.callingFunction("main", this);
		interpreter.tryToInterpret(procedureDiv);
		interpreter.completedFunction("main", this);
	}

	private void collectDataVariables(EagleInterpreter interpreter)
	{
		for (COBOL_DataSection section : dataDiv.sections._elements)
		{
			interpreter.tryToInterpret(section);
		}
	}

	private void collectParagraphNames(EagleInterpreter interpreter)
	{
		for (COBOL_Section section : procedureDiv.sections._elements)
		{
			for (COBOL_Paragraph paragraph : section.paragraphs._elements)
			{
				if (paragraph.paragraphHeaders != null &&
						paragraph.paragraphHeaders._elements != null &&
						paragraph.paragraphHeaders._elements.size() > 0)
				{
					String paragraphName = paragraph.paragraphHeaders._elements.get(0).paragraphName.getValue();
					interpreter.addFunction(paragraphName, paragraph);
				}
			}
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer,
			EagleGenerator generator)
	{
		if (dataDiv != null && dataDiv.isPresent())
		{
			dataDiv.transform(transformer, generator);
		}
		procedureDiv.transform(transformer, generator);
		return generator.getTransfomedProgram();
	}
}
