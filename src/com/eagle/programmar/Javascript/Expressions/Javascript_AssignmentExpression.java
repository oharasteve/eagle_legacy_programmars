// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.generate.AssignmentEnum;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Subscript;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Javascript_Variable.Javascript_VariableQualifier;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_AssignmentExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_Expression var = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice(
			"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
	public @S(30) Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AssignmentEnum asg;
		switch (operator.getValue())
		{
		case "=":
			asg = AssignmentEnum.EQUALS;
			break;
		case "+=":
			asg = AssignmentEnum.PLUS_EQUALS;
			break;
		case "-=":
			asg = AssignmentEnum.MINUS_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
		}

		if (!(var.getWhich() instanceof Javascript_VariableExpression))
		{
			throw new RuntimeException("Can only assign variables");
		}
		Javascript_VariableExpression variableExpr = (Javascript_VariableExpression) var.getWhich();
		Javascript_Variable theVar = variableExpr.variable;

		AbstractExpression newSub = null;
		if (theVar.qualifiers != null && theVar.qualifiers.size() == 1)
		{
			Javascript_VariableQualifier qual = theVar.qualifiers.first();
			if (qual.getWhich() instanceof Javascript_Subscript)
			{
				Javascript_Subscript sub = (Javascript_Subscript) qual.getWhich();
				newSub = transformer.transformExpression(generator, sub.expr);
			}
		}

		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractToken which = theVar.firstId.getWhich();
		if (!(which instanceof Javascript_Identifier_Reference))
		{
			throw new RuntimeException("Have to assign to a regular variable");
		}
		Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) which;

		AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, newSub, asg, value, this);
		return asgExpr;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		String op = operator.getValue();
		AbstractToken target = var.getWhich();
		if (target instanceof Javascript_Subfield || target instanceof Javascript_SubfieldKeyword)
		{
			// receiver.member = value, where the receiver is any expression (this, a call, ...)
			EagleValue receiver;
			String member;
			if (target instanceof Javascript_Subfield)
			{
				Javascript_Subfield sub = (Javascript_Subfield) target;
				receiver = rt.eval(sub.left);
				AbstractToken right = sub.right.getWhich();
				if (!(right instanceof Javascript_VariableExpression)) throw new RuntimeException("Cannot assign to " + right.getClass().getSimpleName());
				Javascript_Variable rv = ((Javascript_VariableExpression) right).variable;
				AbstractToken rlast = Javascript_Variable.lastQualifier(rv);
				if (rlast != null) { receiver = Javascript_Variable.evaluate(rt, rv, receiver, 1); member = rlast instanceof Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField ? ((Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField) rlast).id.getValue() : null;
					if (member == null) { EagleValue key = rt.eval(((Javascript_Subscript) rlast).expr); EagleValue value = "=".equals(op) ? rt.eval(expr) : combine(op.substring(0, op.length() - 1), rt.getIndex(receiver, key, false), rt.eval(expr)); rt.setIndex(receiver, key, value); interpreter.pushEagleValue(value); return; } }
				else member = Javascript_Variable.firstName(rv);
			}
			else
			{
				Javascript_SubfieldKeyword sub = (Javascript_SubfieldKeyword) target;
				receiver = rt.eval(sub.left);
				member = sub.field.getValue();
			}
			EagleValue value = "=".equals(op) ? rt.eval(expr) : combine(op.substring(0, op.length() - 1), rt.getProperty(receiver, member, false), rt.eval(expr));
			rt.setProperty(receiver, member, value);
			interpreter.pushEagleValue(value);
			return;
		}
		if (!(target instanceof Javascript_VariableExpression))
			throw new RuntimeException("Cannot assign to " + target.getClass().getSimpleName());
		Javascript_Variable v = ((Javascript_VariableExpression) target).variable;
		EagleValue value;
		if ("=".equals(op)) value = rt.eval(expr);
		else
		{
			EagleValue current = Javascript_Variable.evaluate(rt, v, null, 0);
			if ("??=".equals(op)) value = JsValues.isNullish(current) ? rt.eval(expr) : current;
			else if ("||=".equals(op)) value = JsValues.truthy(current) ? current : rt.eval(expr);
			else if ("&&=".equals(op)) value = JsValues.truthy(current) ? rt.eval(expr) : current;
			else value = combine(op.substring(0, op.length() - 1), current, rt.eval(expr));
		}
		AbstractToken last = Javascript_Variable.lastQualifier(v);
		if (last == null)
		{
			rt.assign(v, Javascript_Variable.firstName(v), value);
		}
		else
		{
			EagleValue receiver = Javascript_Variable.evaluate(rt, v, null, 1);
			if (last instanceof Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField)
				rt.setProperty(receiver, ((Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField) last).id.getValue(), value);
			else if (last instanceof Javascript_Subscript)
				rt.setIndex(receiver, rt.eval(((Javascript_Subscript) last).expr), value);
			else throw new RuntimeException("Cannot assign through " + last.getClass().getSimpleName());
		}
		interpreter.pushEagleValue(value);
	}

	/** a op b for the arithmetic operators, with JavaScript's string rule for +. */
	public static EagleValue combine(String op, EagleValue a, EagleValue b)
	{
		if ("+".equals(op) && (a.isString() || b.isString() || a instanceof JsValues.JsObject || b instanceof JsValues.JsObject || a.isArray() || b.isArray()))
			return JsValues.str(JsValues.toText(a) + JsValues.toText(b));
		double x = JsValues.toNumber(a), y = JsValues.toNumber(b);
		switch (op)
		{
		case "+": return JsValues.num(x + y);
		case "-": return JsValues.num(x - y);
		case "*": return JsValues.num(x * y);
		case "/": return JsValues.num(x / y);
		case "%": return JsValues.num(x % y);
		case "**": return JsValues.num(Math.pow(x, y));
		case "<<": return JsValues.num((int) x << (int) y);
		case ">>": return JsValues.num((int) x >> (int) y);
		case ">>>": return JsValues.num((int) x >>> (int) y);
		case "&": return JsValues.num((int) x & (int) y);
		case "|": return JsValues.num((int) x | (int) y);
		case "^": return JsValues.num((int) x ^ (int) y);
		default: throw new RuntimeException("Unexpected operator: " + op);
		}
	}
}
