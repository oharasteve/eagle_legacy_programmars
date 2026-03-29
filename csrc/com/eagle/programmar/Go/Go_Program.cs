// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2022

namespace com.eagle.programmar.Go
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Go_Data = com.eagle.programmar.Go.Statements.Go_Data;
	using Go_Function = com.eagle.programmar.Go.Statements.Go_Function;
	using Go_Import = com.eagle.programmar.Go.Statements.Go_Import;
	using Go_Package = com.eagle.programmar.Go.Statements.Go_Package;
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

	public class Go_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string GO = "Go";

		public Go_Program() : base(GO, new Go_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://go.dev/ref/spec";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Go_Element> elements;
		public TokenList<Go_Element> elements;

		public class Go_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_CommentEoln XXcomment;
			public Go_CommentEoln XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_Package XXpkg;
			public Go_Package XXpkg;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_Import XXimport;
			public Go_Import XXimport;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_Data XXdata;
			public Go_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_Function XXfunction;
			public Go_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_Statement XXstmt;
			public Go_Statement XXstmt;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Go_Element element in elements._elements)
			{
				AbstractToken which = element.getWhich();
				if (which is Go_Function)
				{
					Go_Function fn = (Go_Function) which;
					interpreter.addFunction(fn.id.getValue(), fn);
				}
			}

			// Second pass, execute the program
			foreach (Go_Element element in elements._elements)
			{
				interpreter.tryToInterpret(element);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Transform all the Function definitions and global data
			foreach (Go_Element elt in elements._elements)
			{
				AbstractToken which = elt.getWhich();
				if (which is Go_Function)
				{
					Go_Function func = (Go_Function) which;
					func.transformFunction(transformer, generator);
				}
				else
				{
					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
						{
							generator.addStatement(newStmt, elt);
						}
					}
				}
			}

			// Not needed for C# or Java, but Python needs this
			generator.addCallToMain();

			return generator.getTransfomedProgram();
		}
	}

}
