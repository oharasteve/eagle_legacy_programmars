// Copyright Eagle Legacy Modernization, 2010-date
// Generator words for constructs EagleGenerator does not name yet (Oct 2026)

package com.eagle.programmar.Generate;

import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

/**
 * The words a modern source language needs from a generator beyond EagleGenerator's: a
 * conditional expression first; function values, collections and objects to follow. A generator
 * that speaks them implements this; a transform asks with {@link #of} and stops by name when the
 * target does not. Kept beside the generators so EagleGenerator itself is unchanged; it can move
 * into EagleGenerator whenever that is wanted.
 */
public interface ModernGenerator<Stmt extends AbstractStatement, Expr extends AbstractExpression,
		Var extends AbstractVariable, Type extends AbstractType>
{
	/** cond ? whenTrue : whenFalse */
	Expr newConditionalExpression(Expr cond, Expr whenTrue, Expr whenFalse, AbstractToken source);

	/**
	 * A function value. Parameter types may be null where the profile saw none. Exactly one of
	 * body and exprBody is given. escapes: the value outlives the scope it was made in (it is
	 * returned), so it must own what it captures.
	 */
	Expr newClosure(java.util.ArrayList<String> names, java.util.ArrayList<Type> types,
			java.util.ArrayList<Stmt> body, Expr exprBody, boolean escapes, AbstractToken source);

	/** A variable that holds a function value; its type is the target's to infer. */
	Stmt newFunctionValueDeclaration(String name, Expr initial, AbstractToken source);

	/** The type of a function value with these parameter types and result (null: none). */
	Type newFunctionType(java.util.ArrayList<Type> params, Type returns, AbstractToken source);

	/** The generator as a ModernGenerator, or a stop that names the construct and the target. */
	@SuppressWarnings("unchecked")
	static ModernGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> of(Object generator, String construct)
	{
		if (generator instanceof ModernGenerator)
		{
			return (ModernGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType>) generator;
		}
		throw new RuntimeException("The " + generator.getClass().getSimpleName() + " has no word for " + construct + " yet");
	}
}
