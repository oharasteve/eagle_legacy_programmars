// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C
{
	using C_Function_ParameterDefs = com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
	using C_Identifier_Reference = com.eagle.programmar.C.Symbols.C_Identifier_Reference;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using C_TypeLongLong = com.eagle.programmar.C.Types.C_TypeLongLong;
	using C_TypePrimitive = com.eagle.programmar.C.Types.C_TypePrimitive;
	using C_TypeStar = com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
	using C_TypeShortUnsignedInt = com.eagle.programmar.C.Types.C_TypeShortUnsignedInt;
	using C_TypeSimpleUnion = com.eagle.programmar.C.Types.C_TypeSimpleUnion;
	using C_TypeStruct = com.eagle.programmar.C.Types.C_TypeStruct;
	using C_TypeUnion = com.eagle.programmar.C.Types.C_TypeUnion;
	using C_TypeUserDefined = com.eagle.programmar.C.Types.C_TypeUserDefined;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class C_Type : TokenSequence, AbstractType
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<C_TypeModifier> modifiers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<C_NamespaceType> namespaces;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) C_TypeBase super;
		public C_TypeBase @base;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_Generic generic;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT C_TypeFunction function;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT C_Keyword CONST = new com.eagle.programmar.C.Terminals.C_Keyword("const");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar> afterStars;
		public  OPT;

		public class C_TypeModifier : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_KeywordChoice XXEXTENSION = new com.eagle.programmar.C.Terminals.C_KeywordChoice("__extension__", "const", "volatile", "register", "static");
			public C_KeywordChoice XXEXTENSION = new C_KeywordChoice("__extension__", "const", "volatile", "register", "static");
		}

		public class C_NamespaceType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Symbols.C_Identifier_Reference namespace;
			public C_Identifier_Reference @namespace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Punctuation colonColon = new com.eagle.programmar.C.Terminals.C_Punctuation("::");
			public C_Punctuation colonColon = new C_Punctuation("::");
		}

		public class C_TypeBase : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Enum XXenumeration;
			public C_Enum XXenumeration;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_Keyword XXUNSIGNED = new com.eagle.programmar.C.Terminals.C_Keyword("unsigned");
			public C_Keyword XXUNSIGNED = new C_Keyword("unsigned"); // All by itself is ok too

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST C_TypeShortUnsignedInt XXtypeShortUnsigned;
			public C_TypeShortUnsignedInt XXtypeShortUnsigned; // This one isn't handled by C_TypePrimitive
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_TypeUnion XXtypeUnion;
			public C_TypeUnion XXtypeUnion;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_TypeStruct XXtypeStruct;
			public C_TypeStruct XXtypeStruct;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_TypeLongLong XXtypeLongLong;
			public C_TypeLongLong XXtypeLongLong;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_TypePrimitive XXtypePrimitive;
			public C_TypePrimitive XXtypePrimitive;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_TypeUserDefined XXtypeUsedDefined;
			public C_TypeUserDefined XXtypeUsedDefined;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_TypeSimpleUnion XXtypeSimpleUnion;
			public C_TypeSimpleUnion XXtypeSimpleUnion;
		}

		public class C_TypeFunction : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationStar star;
			public PunctuationStar star;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.C_Function.C_Function_ParameterDefs params;
			public C_Function_ParameterDefs @params;
		}

		// Return TypeEnum.STRING for "char *" for example
		public virtual TypeEnum findType()
		{
			AbstractToken which1 = @base.getWhich();
			if (which1 is C_TypePrimitive)
			{
				C_TypePrimitive prim = (C_TypePrimitive) which1;

				int numStars = 0;
				if (prim.stars != null && prim.stars.size() > 0)
				{
					foreach (C_TypePrimitive.C_TypeStar nextStar in prim.stars._elements)
					{
						if (nextStar.starAmpersand.getValue().Equals("*"))
						{
							numStars++;
						}
					}
				}

				string whichPrim = prim.primitive.getValue();
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

}
