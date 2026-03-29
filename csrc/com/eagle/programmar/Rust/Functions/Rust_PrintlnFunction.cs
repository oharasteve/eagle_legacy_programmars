// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

namespace com.eagle.programmar.Rust.Functions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Format = com.eagle.programmar.Rust.Rust_Format;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Variable = com.eagle.programmar.Rust.Rust_Variable;
	using Rust_AdditiveExpression = com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
	using Rust_MethodInvocation = com.eagle.programmar.Rust.Expressions.Rust_MethodInvocation;
	using Rust_Identifier_Reference = com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
	using Rust_KeywordChoice = com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
	using Rust_Literal = com.eagle.programmar.Rust.Terminals.Rust_Literal;
	using Rust_Punctuation = com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	// This works in rextester:
	//   fn main() {
	//     let ok = 33 as i32;
	//     println!("{}", String::from("") + "Tests passed = " + &ok.to_string() + " of 34");
	//   }
	// prints "Tests passed = 33 of 34"

	public class Rust_PrintlnFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice PRINTLN = new com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice("print", "println");
		public Rust_KeywordChoice PRINTLN = new Rust_KeywordChoice("print", "println");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Rust_Punctuation bang = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("!");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE SeparatedList<com.eagle.programmar.Rust.Rust_Expression, com.eagle.tokens.punctuation.PunctuationComma> argList;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT @NOSPACE PunctuationSemicolon semicolon;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, PRINTLN.getValue(), PRINTLN);
			}
			string result = Rust_Format.format(interpreter, argList, _metrics);
			Console.WriteLine(result);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(PRINTLN);
			AbstractExpression value = Rust_Format.compile(transformer, generator, argList, metrics);
			return generator.newPrintFunction(value, EagleGenerator.TypeEnum.STRING, true, false, this);
		}

		public static Rust_Expression generatePrintFunc(Rust_Expression line, EagleGenerator.TypeEnum type, bool newLine, bool toErr, AbstractToken source)
		{
			Rust_PrintlnFunction print = new Rust_PrintlnFunction();
			print.leftParen = new PunctuationLeftParen();
			print.rightParen = new PunctuationRightParen();
			print.argList = new SeparatedList<Rust_Expression, PunctuationComma>();

			print.PRINTLN.setValue(newLine ? "println" : "print");

			// Simple case -> println!("str");
			if (line != null)
			{
				if (line.getWhich() is Rust_Literal)
				{
					print.argList.addPrimaryElement(line);
				}
				else
				{
					Rust_Expression braces = Rust_Literal.generateLiteralExpression("{}", null);
					print.argList.addPrimaryElement(braces);
					print.argList.addSecondaryElement(new PunctuationComma());

					Rust_Identifier_Reference clsName = new Rust_Identifier_Reference();
					clsName.setValue("String");
					Rust_Variable fromVar = Rust_Variable.generateVariable("from");
					List<Rust_Expression> args = new List<Rust_Expression>();
					Rust_Expression blank = Rust_Literal.generateLiteralExpression("", null);
					args.Add(blank);
					Rust_Expression invokeExpr = Rust_MethodInvocation.generateInvocation(clsName, fromVar, args, source);

					Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.STRING, type);
					Rust_Expression plusExpr = Rust_AdditiveExpression.generateAdditive(types, invokeExpr, EagleGenerator.AdditiveEnum.PLUS, line, source);
					print.argList.addPrimaryElement(plusExpr);
				}
			}

			print.setTransformationSource(source);
			return Rust_Generator.wrapExpression(print);
		}
	}

}
