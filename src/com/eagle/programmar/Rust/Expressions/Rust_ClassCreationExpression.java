// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 28, 2026

package com.eagle.programmar.Rust.Expressions;

import java.util.ArrayList;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_ClassCreationExpression extends PrimaryOperator
{
	public @S(10) Rust_Keyword NEW = new Rust_Keyword("new");
	public @S(20) Rust_Type rstype;
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @NOSPACE @OPT TokenList<Rust_Comment> comments;
	public @S(50) @NOSPACE @OPT SeparatedList<Rust_Expression, PunctuationComma> argList;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

//	@Override
//	public AbstractExpression transformExpression(EagleTransformer transformer,
//			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
//	{
//		String className = "Unknown";
//		AbstractType type = generator.transformType(TypeEnum.OTHER, className, null);
//
//		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
//		if (this.argList != null && this.argList.isPresent())
//		{
//			for (int i = 0; i < argList.size(); i++)
//			{
//				args.add(transformer.transformExpression(generator, argList.getPrimaryElement(i)));
//			}
//		}
//
//		return generator.newClassCreation(type, args, this);
//	}

	public static Rust_Expression generateCreation(Rust_Type type,
			ArrayList<Rust_Expression> args, AbstractToken source)
	{
		Rust_ClassCreationExpression creat = new Rust_ClassCreationExpression();
		creat.rstype = type;
		creat.leftParen = new PunctuationLeftParen();
		if (args != null && args.size() > 0)
		{
			creat.argList = new SeparatedList<Rust_Expression, PunctuationComma>();
			for (int i = 0; i < args.size(); i++)
			{
				if (i > 0) creat.argList.addSecondaryElement(new PunctuationComma());
				creat.argList.addPrimaryElement(args.get(i));
			}
			creat.argList.setPresent(true);
		}
		creat.rightParen = new PunctuationRightParen();

		creat.setTransformationSource(source);
		return Rust_Generator.wrapExpression(creat);
	}
}
