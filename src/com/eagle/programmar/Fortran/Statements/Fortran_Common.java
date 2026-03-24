// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import java.util.ArrayList;
import java.util.HashSet;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Symbols.Fortran_Common_Reference;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSlash;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Fortran_Common extends TokenSequence
		implements EagleRunnable, EagleTransformableStatementList
{
	public @S(10) @DOC("6j4m0vn7v/index.html") Fortran_Keyword COMMON = new Fortran_Keyword("COMMON");
	public @S(20) PunctuationSlash slash1;
	public @S(30) Fortran_Common_Reference common;
	public @S(40) PunctuationSlash slash2;
	public @S(50) SeparatedList<Fortran_Variable_Reference, PunctuationComma> variables;
	public @S(60) Fortran_EOLN eoln;

	// Used to see if a variable is declared inside a COMMON block
	public static HashSet<String> collectCommons(ArrayList<Fortran_Statement> statements)
	{
		HashSet<String> commons = new HashSet<String>();
		for (Fortran_Statement stmt : statements)
		{
			if (stmt.getWhich() instanceof Fortran_Common)
			{
				Fortran_Common common = (Fortran_Common) stmt.getWhich();
				int numCommons = common.variables.getPrimaryCount();
				for (int i = 0; i < numCommons; i++)
				{
					Fortran_Variable_Reference ref = common.variables.getPrimaryElement(i);
					commons.add(ref.getValue());
				}
			}
		}
		return commons;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> newStmts = new ArrayList<AbstractStatement>();
		int numCommons = variables.getPrimaryCount();
		for (int i = 0; i < numCommons; i++)
		{
			Fortran_Variable_Reference ref = variables.getPrimaryElement(i);
			AbstractStatement newStmt = generator.newGlobalVariable(ref.getValue(), this);
			newStmts.add(newStmt);
		}
		return newStmts;
	}
}
