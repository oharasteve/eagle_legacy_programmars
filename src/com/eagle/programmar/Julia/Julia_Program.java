// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Julia;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.Julia.Statements.Julia_Function;
import com.eagle.programmar.Julia.Terminals.Julia_Comment;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Julia_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String JULIA = "Julia";

	public Julia_Program()
	{
		super(JULIA, new Julia_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.julialang.org/en/v1/";
	}

	public @S(10) TokenList<Julia_Element> elements;

	public static class Julia_Element extends TokenChooser
	{
		public @CHOICE Julia_CommentEoln XXcomment;
		public @CHOICE Julia_Statement XXstmt;
	}

	public static class Julia_CommentEoln extends TokenSequence
			implements EagleRunnable
	{
		public @S(10) Julia_Comment comment;
		public @S(20) Julia_EOLN eoln;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			// Nothing to do here
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Julia_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Julia_Statement)
			{
				Julia_Statement stmt = (Julia_Statement) which;
				which = stmt.getWhich();
				if (which instanceof Julia_Function)
				{
					Julia_Function fn = (Julia_Function) which;
					interpreter.addFunction(fn.id.getValue(), fn);
				}
			}
		}

		// Second pass, execute the program
		for (Julia_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Julia_Statement)
			{
				Julia_Statement stmt = (Julia_Statement) which;
				interpreter.tryToInterpret(stmt);
			}
		}
	}
	
	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator generator)
	{
		// First pass, transform all the Function definitions
		for (Julia_Element stmt : elements._elements)
		{
			AbstractToken which1 = stmt.getWhich();
			if (which1 instanceof Julia_Function)
			{
				Julia_Function func = (Julia_Function) which1;
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

				AbstractExpression initExpr = null;
				if (typE == TypeEnum.STRING_HASH)
				{
					// Need to create an empty hashmap
					initExpr = generator.newClassCreation(abstrType, null, this);
				}
				
				//System.err.println("****** Found var " + met._symbolName);
				AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName,
						null, abstrType, initExpr, this);
				generator.addStatement(dataStmt, this);
			}
		}
		
		// Second pass, transform all the data and logic
		for (Julia_Element elt : elements._elements)
		{
			AbstractToken which2 = elt.getWhich();
			if (which2 instanceof Julia_Statement)
			{
				Julia_Statement stmt = (Julia_Statement) which2;
				Collection<AbstractStatement> newStmts = transformer.transformStatement(
						generator, stmt.getWhich());
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, stmt);
					}
				}
			}
		}
		
		return generator.getTransfomedProgram();
	}
}