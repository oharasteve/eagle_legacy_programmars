// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.generate.TypeEnum;
import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Types.C_TypeLongLong;
import com.eagle.programmar.C.Types.C_TypePrimitive;
import com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
import com.eagle.programmar.C.Types.C_TypeShortUnsignedInt;
import com.eagle.programmar.C.Types.C_TypeSimpleUnion;
import com.eagle.programmar.C.Types.C_TypeStruct;
import com.eagle.programmar.C.Types.C_TypeUnion;
import com.eagle.programmar.C.Types.C_TypeUserDefined;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_Type extends TokenSequence implements AbstractType
{
	public @S(10) @OPT TokenList<C_TypeModifier> modifiers;
	public @S(20) @OPT TokenList<C_NamespaceType> namespaces;
	public @S(30) C_TypeBase base;
	public @S(40) @OPT C_Generic generic;
	public @S(50) @OPT C_TypeFunction function;
	public @S(60) @OPT C_Keyword CONST = new C_Keyword("const");
	public @S(70) @OPT TokenList<C_TypeStar> afterStars;

	public static class C_TypeModifier extends TokenChooser
	{
		public @CHOICE C_KeywordChoice XXEXTENSION = new C_KeywordChoice(
				"__extension__", "const", "volatile", "register", "static");
	}

	public static class C_NamespaceType extends TokenSequence
	{
		public @S(10) C_Identifier_Reference namespace;
		public @S(20) C_Punctuation colonColon = new C_Punctuation("::");
	}

	public static class C_TypeBase extends TokenChooser
	{
		public @CHOICE C_Enum XXenumeration;
		public @LAST C_Keyword XXUNSIGNED = new C_Keyword("unsigned"); // All by itself is ok too

		public @FIRST C_TypeShortUnsignedInt XXtypeShortUnsigned; // This one isn't handled by C_TypePrimitive
		public @CHOICE C_TypeUnion XXtypeUnion;
		public @CHOICE C_TypeStruct XXtypeStruct;
		public @CHOICE C_TypeLongLong XXtypeLongLong;
		public @CHOICE C_TypePrimitive XXtypePrimitive;
		public @CHOICE C_TypeUserDefined XXtypeUsedDefined;
		public @LAST C_TypeSimpleUnion XXtypeSimpleUnion;
	}

	public static class C_TypeFunction extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) PunctuationStar star;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) C_Function_ParameterDefs params;
	}

	// Return TypeEnum.STRING for "char *" for example
	public TypeEnum findType()
	{
		AbstractToken which1 = base.getWhich();
		if (which1 instanceof C_TypePrimitive)
		{
			C_TypePrimitive prim = (C_TypePrimitive) which1;

			int numStars = 0;
			if (prim.stars != null && prim.stars.size() > 0)
			{
				for (C_TypeStar nextStar : prim.stars._elements)
				{
					if (nextStar.starAmpersand.getValue().equals("*"))
					{
						numStars++;
					}
				}
			}

			String whichPrim = prim.primitive.getValue();
			if (numStars == 0)
			{
				switch (whichPrim)
				{
				case "int":
					return TypeEnum.INTEGER;
				case "double":
					return TypeEnum.DOUBLE;
				case "bool":
					return TypeEnum.BOOLEAN;
				case "void":
					return TypeEnum.VOID;
				case "char":
					return TypeEnum.CHAR;
				}
			}
			if (numStars == 1)
			{
				switch (whichPrim)
				{
				case "char":
					return TypeEnum.STRING;
				}
			}
		}
		return TypeEnum.OTHER;
	}
}
