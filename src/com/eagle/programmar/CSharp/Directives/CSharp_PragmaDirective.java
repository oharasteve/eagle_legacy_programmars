// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 17, 2022

package com.eagle.programmar.CSharp.Directives;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.PragmaEnum;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Expressions.CSharp_VariableExpression;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class CSharp_PragmaDirective extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @NEWLINE CSharp_Punctuation pound1 = new CSharp_Punctuation('#');
	public @S(20) CSharp_Keyword PRAGMA = new CSharp_Keyword("pragma");
	public @S(30) CSharp_Keyword WARNING = new CSharp_Keyword("warning");
	public @S(40) CSharp_KeywordChoice DISABLE = new CSharp_KeywordChoice("disable", "restore");
	public @S(50) SeparatedList<CSharp_Expression, PunctuationComma> numbers;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing important here
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Not much important here
		if (DISABLE.getValue().equals("disable"))
		{
			int nNumbers = numbers.getPrimaryCount();
			for (int i = 0; i < nNumbers; i++)
			{
				CSharp_Expression expr = numbers.getPrimaryElement(i);
				if (expr.getWhich() instanceof CSharp_VariableExpression)
				{
					CSharp_VariableExpression var = (CSharp_VariableExpression) expr.getWhich();
					AbstractToken which = var.variable.firstId.getWhich();
					if (which instanceof CSharp_Identifier_Reference)
					{
						CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) which;
						if (id.getValue().equalsIgnoreCase("CS0162"))
						{
							return generator.newPragma(PragmaEnum.IGNORE_UNREACHABLE_CODE, this);
						}
					}
				}
			}
		}
		
		return null;
	}
	
	public static CSharp_Statement generatePragma(PragmaEnum prag, AbstractToken source)
	{
		String code;
		switch (prag)
		{
		case IGNORE_UNREACHABLE_CODE:
			code = "CS0162";
			break;
		default:
			return null;
		}
		
		CSharp_PragmaDirective dir = new CSharp_PragmaDirective();
		dir.DISABLE.setValue("disable");
		dir.numbers = new SeparatedList<CSharp_Expression, PunctuationComma>();
		CSharp_Expression expr = CSharp_VariableExpression.generateVarExpr(code, SubscriptEnum.FIRST_IS_ZERO, null, source);
		dir.numbers.addPrimaryElement(expr);
		
		dir.setTransformationSource(source);
		return CSharp_Generator.wrapStatement(dir);
	}
}
