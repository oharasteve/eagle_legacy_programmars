// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.VB_Element;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Symbols.VB_Variable_Definition;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class VB_CallStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("statements/call-statement") VB_Keyword CALL = new VB_Keyword("call");
	public @S(20) VB_Identifier_Reference subName;
	public @S(30) @OPT VB_CallArguments callArguments;

	public static class VB_CallArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<VB_Expression, PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = subName.getValue();

		// Look up the subroutine
		AbstractFunction func = interpreter.findFunction(name);
		if (func == null || !(func instanceof VB_Subroutine))
		{
			throw new RuntimeException("Unable to find a subroutine named " + name);
		}
		VB_Subroutine subr = (VB_Subroutine) func;

		// Make sure the function args match up
		int argCount = 0;
		if (callArguments.arguments != null)
		{
			argCount = callArguments.arguments.getPrimaryCount();
		}
		int paramCount = subr.params.params.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Sub " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(name, subr);

		// Now assign all the parameters
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		for (int i = 0; i < argCount; i++)
		{
			VB_Expression expr = callArguments.arguments.getPrimaryElement(i);
			VB_Variable_Definition param = subr.params.params.getPrimaryElement(i).var;

			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.getValue(), val);
			argTypes.add(val.getType());
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (VB_Element stmt : subr.stmts._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		subr._callMetrics.addCallFrom(CALL, elapsedTime);
		subr._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		interpreter.completedFunction(name, subr);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		int argCount = callArguments.arguments.getPrimaryCount();
		for (int i = 0; i < argCount; i++)
		{
			VB_Expression arg = callArguments.arguments.getPrimaryElement(i);
			AbstractExpression newArg = transformer.transformExpression(generator, arg);
			args.add(newArg);
		}

		AbstractVariable var = generator.newVariable(subName.getValue());
		AbstractExpression expr = generator.newMethodInvocation(var, args, subName);
		return generator.newExpressionStatement(expr, subName);
	}
}
