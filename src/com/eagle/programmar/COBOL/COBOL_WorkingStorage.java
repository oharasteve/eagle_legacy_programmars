// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.COBOL;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleTransformer;

public class COBOL_WorkingStorage extends TokenSequence implements EagleRunnable
{
	public @S(10) COBOL_Keyword WORKINGSTORAGE = new COBOL_Keyword("WORKING-STORAGE");
	public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
	public @S(30) PunctuationPeriod dot;
	public @S(40) TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;

	public static class COBOL_CopyOrDataDeclaration extends TokenChooser
	{
		public @CHOICE COBOL_Copy_Directive XXcopyBook;
		public @CHOICE COBOL_DataDeclaration XXdeclaration;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_CopyOrDataDeclaration decl : dataDeclarations._elements)
		{
			interpreter.tryToInterpret(decl);
		}
	}

	public void transform(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		for (COBOL_CopyOrDataDeclaration decl : dataDeclarations._elements)
		{
			AbstractToken which = decl.getWhich();
			if (which instanceof COBOL_DataDeclaration)
			{
				COBOL_DataDeclaration data = (COBOL_DataDeclaration) which;
				AbstractStatement stmt= data.transformData(transformer, generator);
				if (stmt != null)
				{
					generator.addStatement(stmt, this);
				}
			}
		}
	}
}
