// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2022

package com.eagle.eval.Python;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Expression.Python_Additive_Expression;
import com.eagle.programmar.Python.Python_Expression.Python_And_Expression;
import com.eagle.programmar.Python.Python_Expression.Python_BuiltIn;
import com.eagle.programmar.Python.Python_Expression.Python_Function_Call;
import com.eagle.programmar.Python.Python_Expression.Python_Multiplicative_Expression;
import com.eagle.programmar.Python.Python_Expression.Python_Or_Expression;
import com.eagle.programmar.Python.Python_Expression.Python_Parens;
import com.eagle.programmar.Python.Python_Expression.Python_Power_Expression;
import com.eagle.programmar.Python.Python_Expression.Python_VariableExpression;
import com.eagle.programmar.Python.Python_List;
import com.eagle.programmar.Python.Python_Parameter_List.Python_Parameters.Python_Params;
import com.eagle.tokens.AbstractToken;

public class Eval_Python_Expression
{
	public void interpret(Python_Expression expr, EagleInterpreter interpreter)
	{
		AbstractToken which = expr.getWhich();
		
		if (which instanceof EagleRunnable)
		{
			EagleRunnable runnable = (EagleRunnable) which;
			runnable.interpret(interpreter);
		}
		else if (which instanceof Python_VariableExpression)
		{
			Python_VariableExpression var = (Python_VariableExpression) which;
			interpreter.tryToInterpret(var.variable);
		}
		else if (which instanceof Python_Function_Call)
		{
			// Assume print(expr);
			Python_Function_Call fn = (Python_Function_Call) which;
			AbstractToken what = fn.args.first().params.getWhich();
			if (! (what instanceof Python_Params)) throw new RuntimeException("Unexpected arg: " + what.toString());
			Python_Params params = (Python_Params) what;
			EagleValue result = interpreter.getEagleValue(params.expr);
			System.out.println(result.toString());
		}
		else if (which instanceof Python_Parens)
		{
			Python_Parens parens = (Python_Parens) which;
			Python_List list = parens.list;
			interpreter.tryToInterpret(list.expr);
		}
		else if (which instanceof Python_BuiltIn)
		{
			Python_BuiltIn builtin = (Python_BuiltIn) which;
			switch (builtin.builtins.toString())
			{
			case "False" :
				interpreter.pushBool(false);
				break;
			case "True" :
				interpreter.pushBool(true);
				break;
			default:
				throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + builtin.builtins);
			}
		}
		else if (which instanceof Python_Multiplicative_Expression)
		{
			Python_Multiplicative_Expression mult = (Python_Multiplicative_Expression) which;
			int leftValue = interpreter.getIntValue(mult.left);
			int rightValue = interpreter.getIntValue(mult.right);
			switch (mult.operator.toString())
			{
			case "*" :
				interpreter.pushInt(leftValue * rightValue);
				break;
			case "//" :
				interpreter.pushInt(leftValue / rightValue);
				break;
			case "%" :
				interpreter.pushInt(leftValue % rightValue);
				break;
			default:
				throw new RuntimeException("Unexpected multiplicative operator: " + mult.operator);
			}
		}
		else if (which instanceof Python_Additive_Expression)
		{
			Python_Additive_Expression add = (Python_Additive_Expression) which;
			int leftValue = interpreter.getIntValue(add.left);
			int rightValue = interpreter.getIntValue(add.right);
			switch (add.operator.toString())
			{
			case "+" :
				interpreter.pushInt(leftValue + rightValue);
				break;
			case "-" :
				interpreter.pushInt(leftValue - rightValue);
				break;
			default:
				throw new RuntimeException("Unexpected additive operator: " + add.operator);
			}
		}
		else if (which instanceof Python_Power_Expression)
		{
			Python_Power_Expression power = (Python_Power_Expression) which;
			int leftValue = interpreter.getIntValue(power.left);
			int rightValue = interpreter.getIntValue(power.right);
			interpreter.pushInt((int) Math.round(Math.pow(leftValue, rightValue)));
		}
		else if (which instanceof Python_And_Expression)
		{
			Python_And_Expression and = (Python_And_Expression) which;
			boolean leftValue = interpreter.getBoolValue(and.left);
			boolean rightValue = interpreter.getBoolValue(and.right);
			interpreter.pushBool(leftValue && rightValue);
		}
		else if (which instanceof Python_Or_Expression)
		{
			Python_Or_Expression or = (Python_Or_Expression) which;
			boolean leftValue = interpreter.getBoolValue(or.left);
			boolean rightValue = interpreter.getBoolValue(or.right);
			interpreter.pushBool(leftValue || rightValue);
		}
		else
		{
			throw new RuntimeException("Unable to evaulate expression " + (which.getClass().getName()));
		}
	}
}
