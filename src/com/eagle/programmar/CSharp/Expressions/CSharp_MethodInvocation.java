// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_Argument;
import com.eagle.programmar.CSharp.CSharp_Argument.CSharp_ArgumentOut;
import com.eagle.programmar.CSharp.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_ArgumentList.CSharp_MoreArguments;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Method;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter;
import com.eagle.programmar.CSharp.CSharp_MethodImplementation;
import com.eagle.programmar.CSharp.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_MethodInvocation extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Variable methodName;
	public @S(20) @OPT CSharp_GenericType generic;
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @OPT @NOSPACE CSharp_ArgumentList argList;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken token = methodName.firstId.getWhich();
		if (token instanceof CSharp_Identifier_Reference)
		{
			// Look it up
			String name = ((CSharp_Identifier_Reference) token).getValue();
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new RuntimeException("Unable to find a method named " + name);
			}
			CSharp_Method meth = (CSharp_Method) fn;

			// Make sure the function args match up
			int argCount = 0;
			if (argList.arg.isPresent()) argCount = 1;
			if (argList.moreArgs != null && argList.moreArgs.isPresent()) argCount += argList.moreArgs.size();

			int paramCount = meth.parameters.params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, meth);

			// Now assign all the parameters
			ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
			if (argCount > 0)
			{
				CSharp_Argument arg = argList.arg;
				for (int i = 0; i < argCount; i++)
				{
					if (i > 0)
					{
						arg = argList.moreArgs._elements.get(i - 1).arg;
					}
					CSharp_MethodParameter param = meth.parameters.params.getPrimaryElement(i);
					AbstractToken which = arg.getWhich();
					if (which instanceof CSharp_ArgumentOut)
					{
						CSharp_Expression expr = ((CSharp_ArgumentOut) which).arg;
						EagleValue val = interpreter.getEagleValue(expr);
						interpreter.setSymbol(param.id, param.id.getValue(), val);
						argTypes.add(val.getType());
					}
				}
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			AbstractToken body = meth.body.getWhich();
			if (body instanceof CSharp_MethodImplementation)
			{
				CSharp_MethodImplementation impl = (CSharp_MethodImplementation) body;
				for (CSharp_StatementOrComment stmt : impl.block.statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			meth._callMetrics.addCallFrom(this, elapsedTime);
			meth._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(name, meth);
		}
		else
		{
			throw new RuntimeException("Unable to call method " + methodName);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		CSharp_Variable variable = this.methodName;
		if (variable.firstId.getWhich() instanceof CSharp_Identifier_Reference)
		{
			CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) variable.firstId.getWhich();
			String name = id.getValue();
			ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
			ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);
			if (this.argList != null && this.argList.isPresent())
			{
				CSharp_Expression expr1 = argList.arg.getExpression();
				args.add(transformer.transformExpression(generator, expr1));
				if (this.argList.moreArgs != null && this.argList.moreArgs.isPresent())
				{
					for (CSharp_MoreArguments arg : this.argList.moreArgs._elements)
					{
						CSharp_Expression expr2 = arg.arg.getExpression();
						args.add(transformer.transformExpression(generator, expr2));
					}
				}
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, types, this);
		}
		throw new RuntimeException("Can't handle: " + this);
	}

	public static CSharp_Expression generateInvocation(CSharp_Variable var,
			ArrayList<CSharp_Expression> args, ArrayList<TypeEnum> types, AbstractToken source)
	{
		CSharp_MethodInvocation invok = new CSharp_MethodInvocation();
		invok.methodName = new CSharp_Variable();
		invok.methodName.firstId = var.firstId;
		invok.leftParen = new PunctuationLeftParen();
		invok.argList = CSharp_ArgumentList.createArgumentList(args);
		if (invok.argList != null)
		{
			invok.argList.setPresent(true);
		}
		invok.rightParen = new PunctuationRightParen();

		invok.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(invok);
	}
}
