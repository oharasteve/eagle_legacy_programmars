// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 25, 2013

package com.eagle.programmar.COBOL;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_DataSection;
import com.eagle.programmar.COBOL.COBOL_IdentificationDivision.COBOL_IdentificationPresent;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Program_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public abstract class COBOL_Program_Complete extends COBOL_Program
		implements AbstractFunction, EagleRunnable, EagleTransformableProgram
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

	public @S(100) @OPT TokenList<COBOL_Program_Fixed_Format> nestedPrograms;

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

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;

	public COBOL_Program_Complete(String name, COBOL_Syntax syntax)
	{
		super(name, syntax);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		COBOL_Program_Definition id = null;
		if (identificationDiv != null && identificationDiv.isPresent())
		{
			AbstractToken which = identificationDiv.header.getWhich();
			if (!(which instanceof COBOL_IdentificationPresent))
			{
				throw new RuntimeException("Program Id missing: " + which);
			}
			COBOL_IdentificationPresent present = (COBOL_IdentificationPresent) which;
			if (present.programId != null && present.programId.isPresent())
			{
				id = present.programId.programDef;
			}
		}
		if (id != null)
		{
			if (_callMetrics == null)
			{
				_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
			}
			if (_argumentsMetrics == null)
			{
				_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
			}
		}

		// Pass 1 : Collect all the variables in Working Storage
		collectDataVariables(interpreter);

		// Pass 2 : Collect all the paragraph names
		collectParagraphNames(interpreter);

		// Pass 3 : Collect all the subprograms
		collectSubprograms(interpreter);
		
		// Pass 4 -- now run it
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
	
	private void collectSubprograms(EagleInterpreter interpreter)
	{
		if (nestedPrograms != null && nestedPrograms.isPresent())
		{
			for (COBOL_Program_Complete subProg : nestedPrograms._elements)
			{
				if (subProg.identificationDiv != null && subProg.identificationDiv.isPresent())
				{
					AbstractToken which = subProg.identificationDiv.header.getWhich();
					if (!(which instanceof COBOL_IdentificationPresent))
					{
						throw new RuntimeException("Program Id missing: " + which);
					}
					COBOL_IdentificationPresent present = (COBOL_IdentificationPresent) which;
					if (present.programId != null && present.programId.isPresent())
					{
						COBOL_Program_Definition id = present.programId.programDef;
						// System.out.println("****** Found subprogram named " + id);
						interpreter.addFunction(id.getValue(), subProg);
					}
				}
			}
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (nestedPrograms != null && nestedPrograms.size() > 0)
		{
			for (COBOL_Program_Fixed_Format subProg : nestedPrograms._elements)
			{
				subProg.transformSubProgram(transformer, generator);
			}
		}
		
		if (dataDiv != null && dataDiv.isPresent())
		{
			dataDiv.transform(transformer, generator);
		}
		
		boolean skipGoBack = false;		// Only needed in SubPrograms
		procedureDiv.transform(skipGoBack, transformer, generator);
		return generator.getTransformedProgram();
	}

	public void transformSubProgram(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (dataDiv != null && dataDiv.isPresent())
		{
			dataDiv.transform(transformer, generator);
		}

		AbstractToken which0 = identificationDiv.header.getWhich();
		if (!(which0 instanceof COBOL_IdentificationPresent))
		{
			throw new RuntimeException("SubProgram Id missing: " + which0);
		}
		COBOL_IdentificationPresent idPresent = (COBOL_IdentificationPresent) which0;
		String funcName = null;
		if (idPresent.programId != null && idPresent.programId.isPresent())
		{
			COBOL_Program_Definition id = idPresent.programId.programDef;
			funcName = id.getValue();
		}

		// Collect parameters for the subprogram
		// If exactly ONE is assigned inside the function, that becomes the return type
		COBOL_LinkageSection linkage = null;
		for (COBOL_DataSection section : dataDiv.sections._elements)
		{
			AbstractToken which1 = section.getWhich();
			if (which1 instanceof COBOL_LinkageSection)
			{
				linkage = (COBOL_LinkageSection) which1;
				linkage.collectParameters(funcName, generator);
				break;
			}
		}
		if (linkage == null)
		{
			throw new RuntimeException("Linkage section is required for subprogram " + funcName);
		}
		
		// Need to create the function header
		generator.addMethod(linkage.retType, funcName, idPresent);
		for (int i = 0; i < linkage.paramTypes.size(); i++)
		{
			generator.addMethodParameter(linkage.paramTypes.get(i), linkage.paramNames.get(i));
		}
		
		// Very tricky to convert a call-by-reference to a return value
		if (linkage.retName != null)
		{
			AbstractStatement declareRet = generator.newDataDeclaration(StaticEnum.NONE,
					linkage.retName, null, linkage.retType, null, null);
			generator.addStatement(declareRet, null);
		}
		
		// Very tricky to convert a call-by-reference to a return value
		if (linkage.retName != null)
		{
			generator.newDataDeclaration(StaticEnum.NONE,
					linkage.retName, null, linkage.retType, null, null);
		}
		
		boolean skipGoBacks = (linkage.retName != null);
		{
			procedureDiv.transform(skipGoBacks, transformer, generator);
		}

		// Very tricky to convert a call-by-reference to a return value
		if (linkage.retName != null)
		{
			AbstractExpression retExpr = generator.newVariableExpression(linkage.retName,
					SubscriptEnum.FIRST_IS_ONE, null, null);
			AbstractStatement retStmt = generator.newReturnStatement(retExpr, null);
			generator.addStatement(retStmt, null);
		}
	}
}
