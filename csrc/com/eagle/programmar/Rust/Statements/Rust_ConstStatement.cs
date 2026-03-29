// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 1, 2022

namespace com.eagle.programmar.Rust.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Type = com.eagle.programmar.Rust.Rust_Type;
	using Rust_Variable_Definition = com.eagle.programmar.Rust.Symbols.Rust_Variable_Definition;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using Rust_KeywordChoice = com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
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

	public class Rust_ConstStatement : TokenSequence, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE Rust_Keyword PUB = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("pub");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("items/static-items.html") com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice STATIC = new com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice("const", "static");
		public @DOC("items/static-items.html") Rust_KeywordChoice STATIC = new Rust_KeywordChoice("const", "static");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Symbols.Rust_Variable_Definition var;
		public Rust_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationColon colon = new com.eagle.tokens.punctuation.PunctuationColon();
		public PunctuationColon colon = new PunctuationColon();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Rust.Rust_Type type;
		public Rust_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Rust_Data_Initial init;
		public @OPT Rust_Data_Initial init;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE PunctuationSemicolon semicolon;
		public @NOSPACE PunctuationSemicolon semicolon;

		public static class Rust_Data_Initial extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Rust_Expression expr;
			public Rust_Expression expr;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			if (init.isPresent())
			{
				EagleValue val = interpreter.getEagleValue(init.expr);
				interpreter.setSymbol(var, var.getValue(), val);
			}
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// See if the Definition has some assignments in the metrics file
			EagleGenerator.TypeEnum typ = transformer.findAssignMetric(var);
			AbstractType newType = generator.transformType(typ, null, null);

			AbstractExpression initial = null;
			if (init != null && init.isPresent())
			{
				initial = transformer.transformExpression(generator, init.expr);
			}

			string name = var.getValue();
			AbstractStatement stmt = generator.newDataDeclaration(false, name, null, newType, initial, this);
			return stmt;
		}

		public static Rust_ConstStatement newDataDeclaration(bool isStatic, string name, Rust_Expression size, Rust_Type type, Rust_Expression initial, AbstractToken source)
		{
			if (type == null)
			{
				throw new Exception("Can't create data without a type, for " + name);
			}

			Rust_ConstStatement data = new Rust_ConstStatement();
			data.semicolon = new PunctuationSemicolon();

			// Set data name and type
			data.var = new Rust_Variable_Definition();
			data.var.setValue(name);
			data.type = type;

			if (isStatic)
			{
				data.STATIC.setValue("static");
			}

			// Set the initial value, if any
			if (initial != null)
			{
				Rust_Data_Initial init = new Rust_Data_Initial();
				init.setPresent(true);
				init.equals = new PunctuationEquals();
				init.expr = initial;
				data.init = init;
				data.init.setPresent(true);
			}

			data.setTransformationSource(source);
			return data;
		}
	}

}
