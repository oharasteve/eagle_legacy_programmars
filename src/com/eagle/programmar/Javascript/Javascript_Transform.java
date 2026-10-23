// Copyright Eagle Legacy Modernization, 2010-date
// Translation helpers for JavaScript's member access and method calls (Oct 2026)

package com.eagle.programmar.Javascript;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.generate.SubstringECEnum;
import com.eagle.generate.SubstringSCEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.programmar.Javascript.Javascript_Variable.Javascript_VariableQualifier;
import com.eagle.programmar.Javascript.Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformer;

/**
 * Member access and method calls, translated through the generator vocabulary the engine already
 * has (length, trim, startsWith, indexOf, substring, print). What has no translation stops with
 * its name, so a program is never translated with a qualifier silently dropped.
 */
public final class Javascript_Transform
{
	private Javascript_Transform() { }

	public static int qualifierCount(Javascript_Variable v)
	{
		return v.qualifiers == null || !v.qualifiers.isPresent() || v.qualifiers._elements == null ? 0 : v.qualifiers.size();
	}

	private static AbstractToken qualifier(Javascript_Variable v, int i)
	{
		Javascript_VariableQualifier q = v.qualifiers._elements.get(i);
		return q.getWhich();
	}

	private static String identifier(Javascript_Variable v)
	{
		AbstractToken which = v.firstId.getWhich();
		if (!(which instanceof Javascript_Identifier_Reference))
		{
			throw new RuntimeException("Cannot translate " + v.firstId + " as a variable yet");
		}
		return ((Javascript_Identifier_Reference) which).getValue();
	}

	/**
	 * The variable with only its first {@code use} qualifiers: a name, a name with one subscript,
	 * or a name with .length. Anything longer stops, naming what it met.
	 */
	public static AbstractExpression variable(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			Javascript_Variable v, int use, AbstractToken site)
	{
		String name = identifier(v);
		if (use == 0) return generator.newVariableExpression(name, SubscriptEnum.FIRST_IS_ZERO, null, site);
		AbstractToken first = qualifier(v, 0);
		AbstractExpression base;
		if (first instanceof Javascript_Subscript)
		{
			AbstractExpression index = transformer.transformExpression(generator, ((Javascript_Subscript) first).expr);
			base = generator.newVariableExpression(name, SubscriptEnum.FIRST_IS_ZERO, index, site);
		}
		else if (first instanceof Javascript_VarField && fieldName(first).equals("length") && use == 1)
		{
			return generator.newLengthFunction(generator.newVariableExpression(name, SubscriptEnum.FIRST_IS_ZERO, null, site), site);
		}
		else
		{
			throw new RuntimeException("Cannot translate " + name + describe(first) + " yet (field access on an object)");
		}
		if (use == 1) return base;
		AbstractToken second = qualifier(v, 1);
		if (use == 2 && second instanceof Javascript_VarField && fieldName(second).equals("length"))
		{
			return generator.newLengthFunction(base, site);
		}
		throw new RuntimeException("Cannot translate " + name + describe(first) + describe(second) + " yet");
	}

	private static String fieldName(AbstractToken q)
	{
		return ((Javascript_VarField) q).id.getValue();
	}

	private static String describe(AbstractToken q)
	{
		if (q instanceof Javascript_VarField) return "." + fieldName(q);
		if (q instanceof Javascript_Subscript) return "[...]";
		return " " + q.getClass().getSimpleName();
	}

	/** console.log(a, b): one line, the pieces separated by a space, as node prints them. */
	public static boolean isConsole(Javascript_Variable v)
	{
		if (qualifierCount(v) != 1 || !(v.firstId.getWhich() instanceof Javascript_Identifier_Reference)) return false;
		if (!identifier(v).equals("console")) return false;
		AbstractToken q = qualifier(v, 0);
		if (!(q instanceof Javascript_VarField)) return false;
		String m = fieldName(q);
		return m.equals("log") || m.equals("error") || m.equals("info") || m.equals("warn");
	}

	public static AbstractExpression console(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			Javascript_Variable v, ArrayList<Javascript_Expression> args, AbstractToken site)
	{
		String m = fieldName(qualifier(v, 0));
		boolean toErr = m.equals("error") || m.equals("warn");
		if (args.size() <= 1)
		{
			AbstractExpression line = args.isEmpty() ? null : transformer.transformExpression(generator, args.get(0));
			return generator.newPrintFunction1(line, TypeEnum.STRING, true, toErr, site);
		}
		ArrayList<AbstractExpression> pieces = new ArrayList<AbstractExpression>();
		ArrayList<TypeEnum> types = new ArrayList<TypeEnum>();
		for (int i = 0; i < args.size(); i++)
		{
			if (i > 0)
			{
				pieces.add(generator.newLiteralExpression(" ", site));
				types.add(TypeEnum.STRING);
			}
			pieces.add(transformer.transformExpression(generator, args.get(i)));
			types.add(TypeEnum.STRING);
		}
		return generator.newPrintFunction(pieces, types, true, toErr, site);
	}

	/** receiver.method(args) for the string methods the generators know; anything else stops by name. */
	public static AbstractExpression method(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			Javascript_Variable v, ArrayList<Javascript_Expression> args, AbstractToken site)
	{
		int n = qualifierCount(v);
		AbstractToken last = qualifier(v, n - 1);
		if (!(last instanceof Javascript_VarField))
		{
			throw new RuntimeException("Cannot translate a call through " + describe(last) + " yet");
		}
		String method = fieldName(last);
		AbstractExpression receiver = variable(transformer, generator, v, n - 1, site);
		ArrayList<AbstractExpression> a = new ArrayList<AbstractExpression>();
		for (Javascript_Expression arg : args) a.add(transformer.transformExpression(generator, arg));
		switch (method + "/" + a.size())
		{
		case "trim/0":
			// JavaScript's trim gives a new string; Rust's gives a borrowed slice, so ask for a string
			return generator.newStringFunction(TypeEnum.STRING, generator.newTrimFunction(receiver, site), site);
		case "startsWith/1":
		case "startsWith/2":
			return generator.newStartsWithFunction(receiver, a.get(0), a.size() > 1 ? a.get(1) : null, SubstringSCEnum.FIRST_CHAR_IS_ZERO, site);
		case "endsWith/1":
			return generator.newEndsWithFunction(receiver, a.get(0), site);
		case "indexOf/1":
		case "indexOf/2":
			if (n != 1) break;
			return generator.newIndexOfFunction(generator.newVariable(identifier(v)), a.get(0), a.size() > 1 ? a.get(1) : null, SubstringSCEnum.FIRST_CHAR_IS_ZERO, site);
		case "substring/1":
			return generator.newSubstringFunction(receiver, a.get(0), SubstringSCEnum.FIRST_CHAR_IS_ZERO, SubstringECEnum.TO_END, null, false, site);
		case "substring/2":
			return generator.newSubstringFunction(receiver, a.get(0), SubstringSCEnum.FIRST_CHAR_IS_ZERO, SubstringECEnum.GIVEN_EC, a.get(1), false, site);
		default:
			break;
		}
		throw new RuntimeException("Cannot translate the method ." + method + "() with " + a.size() + " argument(s) yet");
	}
}
