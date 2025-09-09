// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2022

package com.eagle.programmar.Go;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.Go.Statements.Go_Data;
import com.eagle.programmar.Go.Statements.Go_Function;
import com.eagle.programmar.Go.Statements.Go_Import;
import com.eagle.programmar.Go.Statements.Go_Package;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Go_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String GO = "Go";

	public Go_Program()
	{
		super(GO, new Go_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://go.dev/ref/spec";
	}

	public @S(10) TokenList<Go_Element> elements;

	public static class Go_Element extends TokenChooser
	{
		public @CHOICE Go_CommentEoln XXcomment;
		public @CHOICE Go_Package XXpkg;
		public @CHOICE Go_Import XXimport;
		public @CHOICE Go_Data XXdata;
		public @CHOICE Go_Function XXfunction;
		public @CHOICE Go_Statement XXstmt;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Go_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof Go_Function)
			{
				Go_Function fn = (Go_Function) which;
				interpreter.addFunction(fn.id.getValue(), fn);
			}
		}

		// Second pass, execute the program
		for (Go_Element element : elements._elements)
		{
			interpreter.tryToInterpret(element);
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator generator)
	{
		// First pass, transform all the Function definitions
		for (Go_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Go_Function)
			{
				Go_Function func = (Go_Function) which;
				func.transformFunction(transformer, generator);
			}
		}
		
		// Are there any global variables we need to declare?
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			TypeEnum typE = met.uniqueType();
			if (typE != TypeEnum.VOID)
			{
				AbstractType abstrType = generator.transformType(typE, null, this);

				// System.err.println("****** Found var " + met._symbolName);
				AbstractStatement dataStmt = generator.newDataDeclaration(met._symbolName,
						null, abstrType, null, this);
				generator.addStatement(dataStmt, this);
			}
		}

		// Second pass, transform all the data and logic
		for (Go_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (! (which instanceof Go_Function))
			{
				Collection<AbstractStatement> newStmts = transformer.transformStatement(
						generator, which);
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, elt);
					}
				}
			}
		}
		
		// Not needed for C# or Java, but Python needs this
		generator.addCallToMain();

		return generator.getTransfomedProgram();
	}
}
