// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Rust_Program extends EagleLanguage implements EagleRunnable
{
	public static final String RUST = "Rust";

	public Rust_Program()
	{
		super(RUST, new Rust_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://doc.rust-lang.org/reference/";
	}

	public @S(10) TokenList<Rust_Element> elements;

	public static class Rust_Element extends TokenChooser
	{
		public @CHOICE Rust_Comment XXcomment;
		public @CHOICE Rust_Function XXfunction;
		public @CHOICE Rust_Module XXmodule;
		public @CHOICE Rust_Data XXdata;
		public @CHOICE Rust_Use XXuse;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Rust_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Rust_Function)
			{
				Rust_Function fn = (Rust_Function) which;
				interpreter._functionList.put(fn.id.getValue(), fn);
				if (interpreter._TRACE)
				{
					System.err.println("*** Found Rust function " + fn.id.getValue());
				}
			}
		}

		// Second pass, execute the program
		for (Rust_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			interpreter.tryToInterpret(which);
		}
	}
}