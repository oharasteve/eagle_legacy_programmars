// Copyright Eagle Legacy Modernization, 2010-date
// Closures, for the function values of other languages (Oct 2026)

package com.eagle.programmar.Rust.Expressions;

import java.util.ArrayList;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Statements.Rust_Block_Statement;
import com.eagle.programmar.Rust.Symbols.Rust_Variable_Definition;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

/** move |a: i32, b: i32| a + b, or |a: i32| { statements } */
public class Rust_Closure extends PrimaryOperator
{
	public @S(10) @OPT Rust_Keyword MOVE = new Rust_Keyword("move");
	public @S(20) Rust_Punctuation bar1 = new Rust_Punctuation("|");
	public @S(30) @NOSPACE @OPT SeparatedList<Rust_ClosureParam, PunctuationComma> params;
	public @S(40) @NOSPACE Rust_Punctuation bar2 = new Rust_Punctuation("|");
	public @S(50) Rust_ClosureBody body;

	public static class Rust_ClosureParam extends TokenSequence
	{
		public @S(10) Rust_Variable_Definition var;
		public @S(20) @OPT Rust_ClosureParamType type;
	}

	public static class Rust_ClosureParamType extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationColon colon;
		public @S(20) Rust_Type type;
	}

	public static class Rust_ClosureBody extends TokenChooser
	{
		public @CHOICE Rust_Block_Statement XXblock;
		public @CHOICE Rust_Expression XXexpr;
	}

	/** A closure over the given parameters; types may be null (Rust infers them). Exactly one of body and exprBody. */
	public static Rust_Expression generateClosure(ArrayList<String> names, ArrayList<Rust_Type> types,
			ArrayList<Rust_Statement> body, Rust_Expression exprBody, boolean escapes, AbstractToken source)
	{
		Rust_Closure closure = new Rust_Closure();
		closure.MOVE.setPresent(escapes);
		closure.params = new SeparatedList<Rust_ClosureParam, PunctuationComma>();
		closure.params.setPresent(!names.isEmpty());
		for (int i = 0; i < names.size(); i++)
		{
			if (i > 0) closure.params.addSecondaryElement(new PunctuationComma());
			Rust_ClosureParam p = new Rust_ClosureParam();
			p.var = new Rust_Variable_Definition();
			p.var.setValue(Rust_Variable.rustName(names.get(i)));
			Rust_Type t = types == null ? null : types.get(i);
			p.type = new Rust_ClosureParamType();
			p.type.setPresent(t != null);
			if (t != null)
			{
				p.type.colon = new PunctuationColon();
				p.type.type = t;
			}
			closure.params.addPrimaryElement(p);
		}
		closure.body = new Rust_ClosureBody();
		if (exprBody != null)
		{
			closure.body.setWhich(exprBody);
		}
		else
		{
			Rust_Block_Statement block = Rust_Block_Statement.emptyBlock();
			for (Rust_Statement s : body) if (s != null) block.statements.addToken(s);
			closure.body.setWhich(block);
		}
		closure.setTransformationSource(source);
		return Rust_Generator.wrapExpression(closure);
	}
}
