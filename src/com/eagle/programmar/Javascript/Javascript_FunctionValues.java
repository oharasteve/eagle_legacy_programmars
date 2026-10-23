// Copyright Eagle Legacy Modernization, 2010-date
// Translation of JavaScript's function values: arrows, function expressions, closures (Oct 2026)

package com.eagle.programmar.Javascript;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.programmar.Generate.ModernGenerator;
import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_FunctionParameter;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_MoreParameters;
import com.eagle.programmar.Javascript.Expressions.Javascript_VariableExpression;
import com.eagle.programmar.Javascript.Functions.Javascript_FunctionExpr;
import com.eagle.programmar.Javascript.Functions.Javascript_LambdaFunction;
import com.eagle.programmar.Javascript.Functions.Javascript_LambdaFunction.Javascript_LambdaParam;
import com.eagle.programmar.Javascript.Functions.Javascript_LambdaFunction.Javascript_LambdaParams.Javascript_LambdaManyParams;
import com.eagle.programmar.Javascript.Statements.Javascript_ReturnStatement;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.DefinitionInterface;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformer;

/**
 * Arrow functions and function expressions become the generator's closures; a variable that
 * holds one is declared with the type left to the target; a function that returns one gets a
 * function type built from what the profile saw. Every case it cannot type stops by name.
 */
public final class Javascript_FunctionValues
{
	private Javascript_FunctionValues() { }

	/** The shape of a function value: its parameter definitions and one of a block or an expression body. */
	public static final class Shape
	{
		final ArrayList<Javascript_Variable_Definition> params = new ArrayList<Javascript_Variable_Definition>();
		Javascript_FunctionBody block;
		Javascript_Expression expr;
	}

	/** The function value an expression is, or null when it is not one. */
	public static Shape shapeOf(AbstractToken token)
	{
		AbstractToken t = token instanceof Javascript_Expression ? ((Javascript_Expression) token).getWhich() : token;
		if (t instanceof Javascript_LambdaFunction) return shape((Javascript_LambdaFunction) t);
		if (t instanceof Javascript_FunctionExpr) return shape((Javascript_FunctionExpr) t);
		return null;
	}

	public static Shape shape(Javascript_LambdaFunction lambda)
	{
		if (lambda.ASYNC != null && lambda.ASYNC.isPresent()) throw new RuntimeException("Cannot translate an async arrow function yet");
		Shape s = new Shape();
		AbstractToken which = lambda.params.getWhich();
		if (which instanceof Javascript_LambdaParam) s.params.add(param((Javascript_LambdaParam) which));
		else
		{
			Javascript_LambdaManyParams many = (Javascript_LambdaManyParams) which;
			if (many.params != null && many.params.isPresent())
			{
				for (int i = 0; i < many.params.getPrimaryCount(); i++) s.params.add(param(many.params.getPrimaryElement(i)));
			}
		}
		AbstractToken body = lambda.body.getWhich();
		if (body instanceof Javascript_FunctionBody) s.block = (Javascript_FunctionBody) body;
		else s.expr = (Javascript_Expression) body;
		return s;
	}

	private static Javascript_Variable_Definition param(Javascript_LambdaParam p)
	{
		if ((p.rest != null && p.rest.isPresent()) || (p.init != null && p.init.isPresent()) || (p.optional != null && p.optional.isPresent()))
		{
			throw new RuntimeException("Cannot translate a rest, optional or default parameter of an arrow function yet");
		}
		AbstractToken name = p.param.getWhich();
		if (!(name instanceof Javascript_Variable_Definition)) throw new RuntimeException("Cannot translate a destructured arrow parameter yet");
		return (Javascript_Variable_Definition) name;
	}

	public static Shape shape(Javascript_FunctionExpr fexpr)
	{
		if (fexpr.args != null && fexpr.args.isPresent()) throw new RuntimeException("Cannot translate an immediately invoked function yet");
		Javascript_Function.Javascript_FunctionImplementation impl = fexpr.function.implementation;
		Shape s = new Shape();
		if (impl.params != null && impl.params.isPresent())
		{
			s.params.add(param(impl.params.param));
			if (impl.params.moreParams != null && impl.params.moreParams.isPresent())
			{
				for (Javascript_MoreParameters more : impl.params.moreParams._elements) s.params.add(param(more.param));
			}
		}
		s.block = impl.body;
		return s;
	}

	private static Javascript_Variable_Definition param(Javascript_FunctionParameter p)
	{
		if ((p.rest != null && p.rest.isPresent()) || (p.value != null && p.value.isPresent()))
		{
			throw new RuntimeException("Cannot translate a rest or default parameter of a function value yet");
		}
		AbstractToken name = p.paramName.getWhich();
		if (!(name instanceof Javascript_Variable_Definition)) throw new RuntimeException("Cannot translate a destructured parameter yet");
		return (Javascript_Variable_Definition) name;
	}

	// ------------------------------------------------------------------ closures

	public static AbstractExpression closure(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			Shape shape, AbstractToken site)
	{
		ModernGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> mg = ModernGenerator.of(generator, "a function value");
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<AbstractType> types = new ArrayList<AbstractType>();
		for (Javascript_Variable_Definition p : shape.params)
		{
			names.add(p.getValue());
			types.add(typeOrNull(generator, transformer.findAssignMetric(p), p));
		}
		if (shape.expr != null)
		{
			return mg.newClosure(names, types, null, transformer.transformExpression(generator, shape.expr), escapes(site), site);
		}
		ArrayList<AbstractStatement> body = new ArrayList<AbstractStatement>();
		for (Javascript_Statement s : statements(shape.block)) body.addAll(transformer.transformStatement(generator, s.getWhich()));
		return mg.newClosure(names, types, body, null, escapes(site), site);
	}

	/** A parameter's type, or null to leave it to the target when the profile has none (or it is itself a function). */
	private static AbstractType typeOrNull(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			TypeEnum t, AbstractToken site)
	{
		if (t == null || t == TypeEnum.TOKEN || t == TypeEnum.OTHER) return null;
		return generator.transformType(t, null, site);
	}

	/** Returned from where it was made: the closure must own what it captures. */
	private static boolean escapes(AbstractToken site)
	{
		for (AbstractToken t = site.getParent(); t != null; t = t.getParent())
		{
			if (t instanceof Javascript_ReturnStatement) return true;
			if (t instanceof Javascript_FunctionBody || t instanceof Javascript_Statement) return false;
		}
		return false;
	}

	static ArrayList<Javascript_Statement> statements(Javascript_FunctionBody block)
	{
		ArrayList<Javascript_Statement> out = new ArrayList<Javascript_Statement>();
		if (block == null || block.statements == null || !block.statements.isPresent()) return out;
		for (Javascript_StatementOrComment s : block.statements._elements)
		{
			if (s.getWhich() instanceof Javascript_Statement) out.add((Javascript_Statement) s.getWhich());
		}
		return out;
	}

	// ------------------------------------------------------------------ functions that return one

	/**
	 * The type of the function value a function returns, from the first `return <function value>`
	 * in its body: parameter types from the profile, the result from what the value returns.
	 * Null when the body returns no function value it can see.
	 */
	public static AbstractType returnedFunctionType(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			Javascript_FunctionBody body, AbstractToken site)
	{
		Shape returned = null;
		for (Javascript_Statement s : statements(body))
		{
			Javascript_Expression e = returnedExpression(s);
			if (e != null && (returned = shapeOf(e)) != null) break;
		}
		if (returned == null) return null;
		ModernGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> mg = ModernGenerator.of(generator, "a function type");
		ArrayList<AbstractType> params = new ArrayList<AbstractType>();
		for (Javascript_Variable_Definition p : returned.params)
		{
			AbstractType t = typeOrNull(generator, transformer.findAssignMetric(p), p);
			if (t == null) throw new RuntimeException("Cannot tell the type of parameter " + p.getValue() + " of the returned function");
			params.add(t);
		}
		Javascript_Expression result = returned.expr;
		if (result == null)
		{
			for (Javascript_Statement s : statements(returned.block))
			{
				Javascript_Expression e = returnedExpression(s);
				if (e != null) result = e;
			}
		}
		AbstractType returns = null;
		if (result != null)
		{
			TypeEnum t = typeOf(transformer, result);
			if (t == null) throw new RuntimeException("Cannot tell what the returned function gives back: " + result);
			returns = generator.transformType(t, null, site);
		}
		return mg.newFunctionType(params, returns, site);
	}

	private static Javascript_Expression returnedExpression(Javascript_Statement s)
	{
		if (!(s.getWhich() instanceof Javascript_ReturnStatement)) return null;
		Javascript_ReturnStatement r = (Javascript_ReturnStatement) s.getWhich();
		if (r.expressions == null || !r.expressions.isPresent()) return null;
		return r.expressions.getPrimaryElement(r.expressions.getPrimaryCount() - 1);
	}

	/** What the profile says an expression holds, for the simple ones: a variable, or a number. */
	private static TypeEnum typeOf(EagleTransformer transformer, Javascript_Expression e)
	{
		AbstractToken w = e.getWhich();
		if (w instanceof Javascript_VariableExpression)
		{
			Javascript_Variable v = ((Javascript_VariableExpression) w).variable;
			if (Javascript_Transform.qualifierCount(v) == 0 && v.firstId.getWhich() instanceof Javascript_Identifier_Reference)
			{
				DefinitionInterface def = ((Javascript_Identifier_Reference) v.firstId.getWhich()).searchForDefinition();
				if (def instanceof AbstractToken) return transformer.findAssignMetric((AbstractToken) def);
			}
		}
		if (w instanceof Javascript_Number) return ((Javascript_Number) w).getValue().contains(".") ? TypeEnum.DOUBLE : TypeEnum.INTEGER;
		return null;
	}

	// ------------------------------------------------------------------ declarations

	/** `const f = x => ...` or `const next = counter(10)` when the profile saw a function value: the target infers the type. */
	public static boolean holdsFunction(TypeEnum profiled, Javascript_Expression init)
	{
		if (init == null) return false;
		if (shapeOf(init) != null || profiled == TypeEnum.TOKEN) return true;
		// The profile does not type what a call returned when it was a function (counter(10))
		return (profiled == null || profiled == TypeEnum.OTHER || profiled == TypeEnum.VOID)
				&& init.getWhich() instanceof com.eagle.programmar.Javascript.Expressions.Javascript_FunctionCall;
	}
}
