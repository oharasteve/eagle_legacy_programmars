// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2020

package com.eagle.programmar.Rust;

import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.SeparatedList;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Rust_Type extends TokenChooser implements AbstractType
{
	public @CHOICE static class Rust_TypePrimitive extends TokenSequence
	{
		public @S(10) @OPT Rust_TypePrimitiveStatic typeStatic;
		public @S(20) Rust_KeywordChoice PRIMITIVE = new Rust_KeywordChoice(
				"bool",
				"f64",
				"isize", "i32",
				"str", "&str", "String",
				"usize", "u32",
				"HashMap");

		public static class Rust_TypePrimitiveStatic extends TokenSequence
		{
			public @S(10) Rust_Punctuation ampersand = new Rust_Punctuation("&");
			public @S(20) @NOSPACE Rust_Punctuation quote = new Rust_Punctuation("'");
			public @S(30) @NOSPACE Rust_Keyword STATIC = new Rust_Keyword("static");
		}
	}

	/** impl FnMut(i32) -> i32: the type of a closure a function returns or takes (Oct 2026) */
	public @CHOICE static class Rust_TypeImplFn extends TokenSequence
	{
		public @S(10) Rust_Keyword IMPL = new Rust_Keyword("impl");
		public @S(20) Rust_KeywordChoice FN = new Rust_KeywordChoice("Fn", "FnMut", "FnOnce");
		public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public @S(40) @NOSPACE @OPT SeparatedList<Rust_Type, PunctuationComma> params;
		public @S(50) @NOSPACE PunctuationRightParen rightParen;
		public @S(60) @OPT Rust_TypeImplFnReturns returns;

		public static class Rust_TypeImplFnReturns extends TokenSequence
		{
			public @S(10) Rust_Punctuation arrow = new Rust_Punctuation("->");
			public @S(20) Rust_Type type;
		}
	}

	public static Rust_Type transformFunctionType(java.util.ArrayList<Rust_Type> params, Rust_Type returns)
	{
		Rust_TypeImplFn fn = new Rust_TypeImplFn();
		fn.FN.setValue("FnMut");
		fn.leftParen = new PunctuationLeftParen();
		fn.params = new SeparatedList<Rust_Type, PunctuationComma>();
		fn.params.setPresent(!params.isEmpty());
		for (int i = 0; i < params.size(); i++)
		{
			if (i > 0) fn.params.addSecondaryElement(new PunctuationComma());
			fn.params.addPrimaryElement(params.get(i));
		}
		fn.rightParen = new PunctuationRightParen();
		fn.returns = new Rust_TypeImplFn.Rust_TypeImplFnReturns();
		fn.returns.setPresent(returns != null);
		if (returns != null) fn.returns.type = returns;
		Rust_Type type = new Rust_Type();
		type.setWhich(fn);
		return type;
	}

	public @CHOICE static class Rust_TypeArray extends TokenSequence
	{
		public @S(10) Rust_Punctuation ampersand = new Rust_Punctuation("&");
		public @S(20) @NOSPACE Rust_Punctuation quote = new Rust_Punctuation("'");
		public @S(30) @NOSPACE Rust_Keyword STATIC = new Rust_Keyword("static");
		public @S(40) PunctuationLeftBracket leftBracket;
		public @S(50) @NOSPACE Rust_Type subType;
		public @S(60) @NOSPACE PunctuationRightBracket rightBracket;
	}

//	public static class Rust_GenericType extends TokenSequence
//	{
//		public @S(10) Rust_Punctuation lessThan = new Rust_Punctuation('<');
//		public @S(20) @OPT @NOSPACE Rust_Type subType1;
//		public @S(30) @OPT TokenList<Rust_MoreTypes> moreType;
//		public @S(40) @NOSPACE Rust_Punctuation greaterThan = new Rust_Punctuation('>');
//	}
//
//	public static class Rust_MoreTypes extends TokenSequence
//	{
//		public @S(10) PunctuationComma comma;
//		public @S(20) Rust_Type subType2;
//	}

	public static AbstractType findType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, String typeName)
	{
		TypeEnum newType;
		switch (typeName)
		{
		case "bool":
			newType = TypeEnum.BOOLEAN;
			break;
		case "i32":
		case "isize":
		case "u32":
		case "usize":
			newType = TypeEnum.INTEGER;
			break;
		case "f64":
			newType = TypeEnum.DOUBLE;
			break;
		case "str":
		case "String":
			newType = TypeEnum.STRING;
			break;
		case "HashMap":
			newType = TypeEnum.HASH;
			break;
		default:
			newType = TypeEnum.OTHER;
			break;
		}
		return generator.transformType(newType, null, null);
	}
	
	// Convert "i32" to a Rust_Type representing an integer
	public static Rust_Type newPrimitiveType(String name)
	{
		Rust_Type type = new Rust_Type();
		Rust_TypePrimitive primitive = new Rust_TypePrimitive();
		primitive.PRIMITIVE.setValue(name);
		type.setWhich(primitive);
		return type;
	}

	public static Rust_Type transformType(TypeEnum type,
			String typeName, AbstractToken source)
	{
		if (type == null)
		{
			return null;
		}

		switch (type)
		{
		case BOOLEAN:
			return newPrimitiveType("bool");
		case INTEGER:
			return newPrimitiveType("i32");
		case DOUBLE:
			return newPrimitiveType("f64");
		case CHAR:
		case STRING:
			return newPrimitiveType("String");
		case ARRAY:
			return transformTypeArray(TypeEnum.STRING);
		case HASH:
			return newPrimitiveType("HashMap");
		case VOID:
			return null;
		default:
			throw new RuntimeException("Can't transform type: " + type);
		}
	}
	
	public static Rust_Type transformTypeArray(TypeEnum type)
	{
		Rust_TypeArray array = new Rust_TypeArray();
		array.leftBracket = new PunctuationLeftBracket();
		array.rightBracket = new PunctuationRightBracket();
		
		Rust_TypePrimitive prim = new Rust_TypePrimitive();
		prim.typeStatic = new Rust_TypePrimitive.Rust_TypePrimitiveStatic();
		prim.typeStatic.setPresent(true);
		prim.PRIMITIVE.setValue("str");
		prim.setPresent(true);
		array.subType = new Rust_Type();
		array.subType.setWhich(prim);

		Rust_Type newType = new Rust_Type();
		newType.setWhich(array);
		return newType;
	}
}