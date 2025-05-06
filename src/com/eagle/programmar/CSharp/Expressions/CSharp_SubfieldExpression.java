// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.generate.Expressions.Eagle_Generate_Subfield;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class CSharp_SubfieldExpression extends PrecedenceOperator
		implements EagleRunnable, Eagle_Generate_Subfield<CSharp_Expression>
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @OPT CSharp_Punctuation question = new CSharp_Punctuation('?');
	public @S(30) @NOSPACE PunctuationPeriod dot;
	public @S(40) @NOSPACE CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
//		if (right.getWhich() instanceof CSharp_MethodInvocation)
//		{
//			CSharp_MethodInvocation meth = (CSharp_MethodInvocation) right.getWhich();
//			AbstractToken token = meth.methodName.firstId.getWhich();
//			if (token instanceof CSharp_Identifier_Reference)
//			{
//				CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) token;
//				if (id.getValue().equals("Substring"))
//				{
//					String leftStr = interpreter.getStrValue(left);
//					if (meth.argList.moreArgs.size() == 0)
//					{
//						token = meth.argList.arg.getWhich();
//						if (token instanceof CSharp_ArgumentOut)
//						{
//							int sc = interpreter.getIntValue(((CSharp_ArgumentOut) token).arg);
//							interpreter.pushStr(leftStr.substring(sc));
//							return;
//						}
//					}
//				}
//				
//				if (id.getValue().equals("StartsWith"))
//				{
//					String leftStr = interpreter.getStrValue(left);
//					if (meth.argList.moreArgs.size() == 0)
//					{
//						token = meth.argList.arg.getWhich();
//						if (token instanceof CSharp_ArgumentOut)
//						{
//							String patt = interpreter.getStrValue(((CSharp_ArgumentOut) token).arg);
//							interpreter.pushBool(leftStr.startsWith(patt));
//							return;
//						}
//					}
//				}
//			}
//		}
		
		throw new RuntimeException("Unable to handle subfield expression: " + right);
	}

	@Override
	public CSharp_Expression generateSubfield(CSharp_Expression leftExpr,
			CSharp_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.dot = new PunctuationPeriod();
		this.right = rightExpr;
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
