// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Rust_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
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

	public @S(10) TokenList<Rust_TopElement> elements;

	public static class Rust_TopElement extends TokenChooser
	{
		public @CHOICE Rust_Comment XXcomment;
		public @CHOICE Rust_Function XXfunction;
		public @CHOICE Rust_Module XXmodule;
		public @CHOICE Rust_Use XXuse;

		public @LAST Rust_Statement XXstatement;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Rust_TopElement elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Rust_Function)
			{
				Rust_Function fn = (Rust_Function) which;
				interpreter.addFunction(fn.id.getValue(), fn);
			}
		}

		// Second pass, execute the program
		for (Rust_TopElement elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			interpreter.tryToInterpret(which);
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// First pass, transform all the Function definitions
		for (Rust_TopElement topElt : elements._elements)
		{
			AbstractToken which = topElt.getWhich();
			if (which instanceof Rust_Function)
			{
				Rust_Function func = (Rust_Function) which;
				func.transformFunction(transformer, generator);
			}
		}

		// Second pass, transform all the data and logic
		for (Rust_TopElement topElt : elements._elements)
		{
			AbstractToken which = topElt.getWhich();
			if (!(which instanceof Rust_Function))
			{
				Collection<AbstractStatement> newStmts = transformer.transformStatement(
						generator, which);
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, topElt);
					}
				}
			}
		}

		// Not needed for C# or Java, but Python needs this
		generator.addCallToMain();

		return generator.getTransfomedProgram();
	}
	
	public void addComment(Rust_Comment comm)
	{
		Rust_TopElement topElt = new Rust_TopElement();
		topElt.setWhich(comm);
		elements.addToken(topElt);
	}

	public void addTopElement(Rust_TopElement elt)
	{
		elements.addToken(elt);
	}
}