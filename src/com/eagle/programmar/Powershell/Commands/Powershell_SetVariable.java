// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 3, 2026

package com.eagle.programmar.Powershell.Commands;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Symbols.Powershell_Variable_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Powershell_SetVariable extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) Powershell_KeywordChoice SET = new Powershell_KeywordChoice("SET-VARIABLE", "SET");
	public @S(20) @OPT Powershell_SetOption setOption;
	public @S(30) Powershell_Variable_Reference var;
	public @S(40) Powershell_Expression expr;

	public static class Powershell_SetOption extends TokenSequence
	{
		public @S(10) PunctuationHyphen dash;
		public @S(20) Powershell_Keyword OPTION = new Powershell_Keyword("Option");
		public @S(30) Powershell_KeywordChoice CONST = new Powershell_KeywordChoice("Constant");
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue newValue = interpreter.getEagleValue(expr);
		interpreter.setSymbol(var, var.getValue(), newValue);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression newValue = transformer.transformExpression(generator, expr);
		String varName = var.getValue();
		
		AbstractType varType = null;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findAllAssignments();
		for (AssignMetrics met : asgMetrics)
		{
			if (met._symbolName.equals(varName))
			{
				TypeEnum typE = met.uniqueType();
				if (typE != TypeEnum.VOID)
				{
					varType = generator.transformType(typE, null, this);
				}
				break;
			}
		}

		return generator.newDataDeclaration(StaticEnum.CONST, varName, null, varType, newValue, this);
	}
}
