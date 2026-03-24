// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Fortran.Fortran_Type;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Definition;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Fortran_Data extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatementList
{
	public @S(10) Fortran_Type type;
	public @S(20) Fortran_Punctuation colobColon = new Fortran_Punctuation("::");
	public @S(30) SeparatedList<Fortran_Variable_Definition, PunctuationComma> variables;
	public @S(40) Fortran_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Could create an empty variable here
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		AbstractType newType = Fortran_Type.findType(generator, type);
		
		for (int i = 0; i < variables.getPrimaryCount(); i++)
		{
			// No initial values on data lines like INTEGER and CHARACTER
			Fortran_Variable_Definition varDef = variables.getPrimaryElement(i);
			
			// Skip declarations of Functions
			if (transformer.findCallTo(varDef.getValue()))
			{
				continue;
			}
			
			// Skip declaration of function/subroutine parameters
			// Fortran puts the parameters in the middle of the local variables
			boolean skipVariable = false;
			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent instanceof Fortran_Function)
				{
					Fortran_Function func = (Fortran_Function) parent;
					if (func.parameters != null && func.parameters.isPresent())
					{
						int numParams = func.parameters.getPrimaryCount();
						for (int j = 0; j < numParams; j++)
						{
							Fortran_Variable_Reference ref = func.parameters.getPrimaryElement(j);
							if (ref.getValue().equals(varDef.getValue()))
							{
								skipVariable = true;
								break;
							}
						}
					}
					break;
				}
				else if (parent instanceof Fortran_Subroutine)
				{
					Fortran_Subroutine sub = (Fortran_Subroutine) parent;
					if (sub.parameters != null && sub.parameters.isPresent())
					{
						int numParams = sub.parameters.getPrimaryCount();
						for (int j = 0; j < numParams; j++)
						{
							Fortran_Variable_Reference ref = sub.parameters.getPrimaryElement(j);
							if (ref.getValue().equals(varDef.getValue()))
							{
								skipVariable = true;
								break;
							}
						}
					}
					break;
				}
				parent = parent.getParent();
			}
			
			if (! skipVariable)
			{
				AbstractStatement stmt = generator.newDataDeclaration(false, varDef.getValue(),
						null, newType, null, varDef);
				result.add(stmt);
			}
		}
		return result;
	}
}
