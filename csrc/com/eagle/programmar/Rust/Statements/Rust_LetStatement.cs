// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

namespace com.eagle.programmar.Rust.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Type = com.eagle.programmar.Rust.Rust_Type;
	using Rust_Variable = com.eagle.programmar.Rust.Rust_Variable;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_LetStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("statements.html#let-statements") @NEWLINE Rust_Keyword LET = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("let");
		public @DOC("statements.html#let-statements") Rust_Keyword LET = new Rust_Keyword("let");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Rust_Keyword MUT = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("mut");
		public @OPT Rust_Keyword MUT = new Rust_Keyword("mut");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Rust_Variable var;
		public Rust_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Rust_ColonType colonType;
		public @OPT Rust_ColonType colonType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Rust_DataInitialize init;
		public @OPT Rust_DataInitialize init;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT @NOSPACE PunctuationSemicolon semicolon;
		public @OPT PunctuationSemicolon semicolon;

		public static class Rust_DataInitialize extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Rust_Expression expr;
			public Rust_Expression expr;
		}

		public static class Rust_ColonType extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Rust_Type type;
			public Rust_Type type;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			if (init != null && init.isPresent())
			{
				string id = var.var.getValue();
				EagleValue val = interpreter.getEagleValue(init.expr);
				interpreter.setSymbol(var, id, val);
			}
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (init != null && init.isPresent())
			{
				// See if the Definition has some assignments in the metrics file
				EagleGenerator.TypeEnum typ = transformer.findAssignMetric(var);
				AbstractType newType = generator.transformType(typ, null, null);

				AbstractExpression initial = transformer.transformExpression(generator, init.expr);

				string name = var.var.getValue();
				AbstractStatement stmt = generator.newDataDeclaration(false, name, null, newType, initial, this);
				return stmt;
			}

			return null;
		}

		public static Rust_LetStatement newDataDeclaration(bool isStatic, string name, Rust_Expression size, Rust_Type typ, Rust_Expression initial, AbstractToken source)
		{
			if (typ == null)
			{
				throw new Exception("Can't create data without a type, for " + name);
			}

			if (name.equalsIgnoreCase("true") || name.equalsIgnoreCase("false"))
			{
				// Sorry, cannot redefine true or false
				return null;
			}

			Rust_LetStatement letStmt = new Rust_LetStatement();
			letStmt.MUT.setPresent(true);
			letStmt.semicolon = new PunctuationSemicolon();
			letStmt.semicolon.setPresent(true);

			// Set data name, value and type
			letStmt.var = Rust_Variable.generateVariable(name);

			letStmt.colonType = new Rust_ColonType();
			letStmt.colonType.colon = new PunctuationColon();
			letStmt.colonType.type = typ;
			letStmt.colonType.setPresent(true);

			if (initial != null)
			{
				letStmt.init = new Rust_DataInitialize();
				letStmt.init.equals = new PunctuationEquals();
				letStmt.init.expr = initial;
				letStmt.init.setPresent(true);
			}

			letStmt.setTransformationSource(source);
			return letStmt;
		}
	}

}
