// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import java.util.ArrayList;
import com.eagle.tokens.TokenList;
import com.eagle.programmar.Javascript.Javascript_Subscript;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.TypeScript.TS_TypeArguments;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Function;
import com.eagle.programmar.Javascript.Javascript_FunctionBody;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_FunctionParameter;
import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_FunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_Variable functionName;
	public @S(15) @OPT @NOSPACE TS_TypeArguments typeArguments;
	public @S(20) Javascript_ParenthesizedExpression arguments;
	public @S(30) @OPT @NOSPACE TokenList<Javascript_ParenthesizedExpression> moreCalls; // twice(f)(3) (Oct 2026)

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Javascript_Variable variable = this.functionName;
		if (variable.firstId.getWhich() instanceof Javascript_Identifier_Reference)
		{
			Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) variable.firstId.getWhich();
			String name = id.getValue();
			ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);
			ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
			int numArgs = arguments.expressions.getPrimaryCount();
			for (int i = 0; i < numArgs; i++)
			{
				Javascript_Expression expr = arguments.expressions.getPrimaryElement(i);
				args.add(transformer.transformExpression(generator, expr));
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, types, this);
		}
		throw new RuntimeException("Can't handle: " + this);
	}

	/** Calls what a variable names, with this bound when the name is a property; the receiver, when given, is what the first name belongs to. */
	public static EagleValue invoke(JsRuntime rt, Javascript_Variable functionName, Javascript_ParenthesizedExpression arguments, EagleValue receiver, AbstractToken site)
	{
		java.util.List<EagleValue> args = rt.args(arguments == null ? null : arguments.expressions);
		AbstractToken last = Javascript_Variable.lastQualifier(functionName);
		if (last == null)
		{
			String name = Javascript_Variable.firstName(functionName);
			if (receiver != null) return rt.callMethod(receiver, name, args, site);
			if ("super".equals(name)) { rt.superCall(args, site); return JsValues.undefined(); }
			return rt.call(rt.read(name), JsValues.undefined(), args, site);
		}
		EagleValue target = Javascript_Variable.evaluate(rt, functionName, receiver, 1);
		if (last instanceof Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField)
		{
			Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField field = (Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField) last;
			if ("?.".equals(field.dot.getValue()) && JsValues.isNullish(target)) return JsValues.undefined();
			return rt.callMethod(target, field.id.getValue(), args, site);
		}
		if (last instanceof Javascript_Subscript)
		{
			EagleValue f = rt.getIndex(target, rt.eval(((Javascript_Subscript) last).expr), false);
			return rt.call(f, target, args, site);
		}
		EagleValue f = Javascript_Variable.evaluate(rt, functionName, receiver, 0);
		return rt.call(f, JsValues.undefined(), args, site);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		EagleValue result = invoke(rt, functionName, arguments, null, this);
		if (JsRuntime.has(moreCalls))
			for (Javascript_ParenthesizedExpression more : moreCalls._elements) result = rt.call(result, JsValues.undefined(), rt.args(more.expressions), this);
		interpreter.pushEagleValue(result);
	}
}
