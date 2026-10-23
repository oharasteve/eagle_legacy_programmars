// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 1, 2022

package com.eagle.programmar.Rust;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Rust_Variable extends TokenSequence
		implements AbstractVariable, EagleRunnable
{
	public @S(10) Rust_Identifier_Reference var;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(var.getValue());
		interpreter.pushEagleValue(value);
	}

	public static Rust_Variable generateVariable(String name)
	{
		Rust_Variable var = new Rust_Variable();
		var.var = new Rust_Identifier_Reference();
		var.var.setValue(rustName(name));
		return var;
	}

	/** The name a variable has in the generated Rust; definitions and references must agree. */
	public static String rustName(String name)
	{
		// Rust does not like single letter variable names
		return name.length() == 1 ? name + name : name;
	}
}
