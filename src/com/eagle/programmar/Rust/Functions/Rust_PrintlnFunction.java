// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Format;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

// This works in rextester:
//   fn main() {
//     let ok = 33 as i32;
//     println!("{}", "Tests passed = ".to_string() + &ok.to_string() + " of 34");
//   }
// prints "Tests passed = 33 of 34"

public class Rust_PrintlnFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_KeywordChoice PRINTLN = new Rust_KeywordChoice("print", "println");
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
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(PRINTLN);
		AbstractExpression value = Rust_Format.compile(transformer, generator, argList, metrics);
		return generator.newPrintFunction1(value, TypeEnum.STRING, true, false, this);
	}

	public static Rust_Expression generatePrintFunc1(Rust_Expression line, TypeEnum type,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		Rust_PrintlnFunction print = new Rust_PrintlnFunction();
		print.leftParen = new PunctuationLeftParen();
		print.rightParen = new PunctuationRightParen();
		print.argList = new SeparatedList<Rust_Expression, PunctuationComma>();
		print.PRINTLN.setValue(newLine ? "println" : "print");
		
		// Simple case -> println!("str");
		if (line != null)
		{
			AbstractToken which = line.getWhich();
			if (which instanceof Rust_Literal)
			{
				print.argList.addPrimaryElement(line);
			}
			else if (which instanceof Rust_FormatFunction)
			{
				// println!(format!("{stuff}",args)) is silly
				// Remove the format! and transfer its args to println!
				Rust_FormatFunction fmtFunc = (Rust_FormatFunction) which;
				print.argList.addPrimaryElement(fmtFunc.argList.first());
				int numArgs = fmtFunc.argList.getPrimaryCount();
				for (int i = 1; i < numArgs; i++)
				{
					print.argList.addSecondaryElement(new PunctuationComma());
					print.argList.addPrimaryElement(fmtFunc.argList.getPrimaryElement(i));
				}
			}
			else
			{
				Rust_Expression braces = Rust_Literal.generateLiteralExpression("{}", null);
				print.argList.addPrimaryElement(braces);
				print.argList.addSecondaryElement(new PunctuationComma());
				print.argList.addPrimaryElement(line);
			}
		}
		
		print.setTransformationSource(source);
		return Rust_Generator.wrapExpression(print);
	}

	public static Rust_Expression generatePrintFunc(ArrayList<Rust_Expression> pieces,
			ArrayList<TypeEnum> types, boolean newLine, boolean toErr, AbstractToken source)
	{
		Rust_PrintlnFunction print = new Rust_PrintlnFunction();
		print.leftParen = new PunctuationLeftParen();
		print.rightParen = new PunctuationRightParen();
		print.argList = new SeparatedList<Rust_Expression, PunctuationComma>();
		print.PRINTLN.setValue(newLine ? "println" : "print");

		StringBuffer bracesStr = new StringBuffer();
		for (int i = 0; i < pieces.size(); i++)
		{
			bracesStr.append("{}");
		}
		Rust_Expression braces = Rust_Literal.generateLiteralExpression(bracesStr.toString(), null);
		print.argList.addPrimaryElement(braces);
		
		for (int i = 0; i < pieces.size(); i++)
		{
			print.argList.addSecondaryElement(new PunctuationComma());
			print.argList.addPrimaryElement(pieces.get(i));
		}
		
		print.setTransformationSource(source);
		return Rust_Generator.wrapExpression(print);
	}
}
