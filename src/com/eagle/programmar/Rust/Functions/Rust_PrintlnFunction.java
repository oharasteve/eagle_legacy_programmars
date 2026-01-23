// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Format;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
import com.eagle.programmar.Rust.Expressions.Rust_MethodInvocation;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

// This works in rextester:
//   fn main() {
//     let ok = 33 as i32;
//     println!("{}", String::from("") + "Tests passed = " + &ok.to_string() + " of 34");
//   }
// prints "Tests passed = 33 of 34"

public class Rust_PrintlnFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Keyword PRINTLN = new Rust_Keyword("println");
	public @S(20) @NOSPACE Rust_Punctuation bang = new Rust_Punctuation("!");
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @NOSPACE SeparatedList<Rust_Expression, PunctuationComma> argList;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
	public @S(60) @OPT @NOSPACE PunctuationSemicolon semicolon;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, PRINTLN.getValue(), PRINTLN);
		}
		String result = Rust_Format.format(interpreter, argList, _metrics);
		System.out.println(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		ArrayList<String> metrics = transformer.findArgumentsMetric(PRINTLN);
		AbstractExpression value = Rust_Format.transform(transformer, generator, argList, metrics);
		return generator.newPrintFunction(value, true, false, this);
	}

	public Rust_Expression generatePrintFunc(Rust_Expression line, boolean newLine,
			boolean toErr, AbstractToken source)
	{
		leftParen = new PunctuationLeftParen();
		rightParen = new PunctuationRightParen();
		argList = new SeparatedList<Rust_Expression, PunctuationComma>();
		
		// Simple case -> println!("str");
		if (line.getWhich() instanceof Rust_Literal)
		{
			argList.addPrimaryElement(line);
		}
		else
		{
			Rust_Expression braces = Rust_Literal.generateLiteralExpression("{}", null);
			argList.addPrimaryElement(braces);
			argList.addSecondaryElement(new PunctuationComma());
	
			Rust_MethodInvocation invoke = new Rust_MethodInvocation();
			Rust_Identifier_Reference clsName = new Rust_Identifier_Reference();
			clsName.setValue("String");
			Rust_Variable fromVar = Rust_Variable.newVariable("from");
			ArrayList<Rust_Expression> args = new ArrayList<Rust_Expression>();
			Rust_Expression blank = Rust_Literal.generateLiteralExpression("", null);
			args.add(blank);
			Rust_Expression invokeExpr = invoke.generateInvocation(clsName, fromVar, args, source);
			
			Rust_AdditiveExpression plus = new Rust_AdditiveExpression();
			Oper2Types types = new Oper2Types(EagleString.STRING, EagleString.STRING);
			Rust_Expression plusExpr = plus.generateAdditive(types, invokeExpr, AdditiveEnum.PLUS, line, source);
			argList.addPrimaryElement(plusExpr);
		}
		
		setTransformationSource(source);
		return Rust_Generator.wrapExpression(this);
	}
}
