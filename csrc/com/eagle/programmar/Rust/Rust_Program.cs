// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

namespace com.eagle.programmar.Rust
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Rust_Comment = com.eagle.programmar.Rust.Terminals.Rust_Comment;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string RUST = "Rust";

		public Rust_Program() : base(RUST, new Rust_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://doc.rust-lang.org/reference/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Rust_TopElement> elements;
		public TokenList<Rust_TopElement> elements;

		public class Rust_TopElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_Comment XXcomment;
			public Rust_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_Function XXfunction;
			public Rust_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_Module XXmodule;
			public Rust_Module XXmodule;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_Use XXuse;
			public Rust_Use XXuse;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Rust_Statement XXstatement;
			public Rust_Statement XXstatement;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Rust_TopElement elt in elements._elements)
			{
				AbstractToken which = elt.getWhich();
				if (which is Rust_Function)
				{
					Rust_Function fn = (Rust_Function) which;
					interpreter.addFunction(fn.id.getValue(), fn);
				}
			}

			// Second pass, execute the program
			foreach (Rust_TopElement elt in elements._elements)
			{
				AbstractToken which = elt.getWhich();
				interpreter.tryToInterpret(which);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, transform all the Function definitions
			foreach (Rust_TopElement topElt in elements._elements)
			{
				AbstractToken which = topElt.getWhich();
				if (which is Rust_Function)
				{
					Rust_Function func = (Rust_Function) which;
					func.transformFunction(transformer, generator);
				}
			}

			// Second pass, transform all the data and logic
			foreach (Rust_TopElement topElt in elements._elements)
			{
				AbstractToken which = topElt.getWhich();
				if (!(which is Rust_Function))
				{
					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
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

		public virtual void addComment(Rust_Comment comm)
		{
			Rust_TopElement topElt = new Rust_TopElement();
			topElt.setWhich(comm);
			elements.addToken(topElt);
		}

		public virtual void addTopElement(Rust_TopElement elt)
		{
			elements.addToken(elt);
		}
	}
}
