// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

namespace com.eagle.programmar.CSharp
{
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CSharp_Argument : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSharp_ArgumentRef extends com.eagle.tokens.TokenSequence
		public class CSharp_ArgumentRef : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword REF = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("ref");
			public CSharp_Keyword REF = new CSharp_Keyword("ref");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CSharp_Expression arg;
			public CSharp_Expression arg;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSharp_ArgumentOutType extends com.eagle.tokens.TokenSequence
		public class CSharp_ArgumentOutType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword OUT = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("out");
			public CSharp_Keyword OUT = new CSharp_Keyword("out");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CSharp_Type type;
			public CSharp_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CSharp_Expression arg;
			public CSharp_Expression arg;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class CSharp_ArgumentOut extends com.eagle.tokens.TokenSequence
		public class CSharp_ArgumentOut : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CSharp_Keyword OUT = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("out");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CSharp_Expression arg;
			public CSharp_Expression arg;
		}

		public virtual CSharp_Expression Expression
		{
			get
			{
				AbstractToken which = this.getWhich();
				if (which is CSharp_ArgumentOut)
				{
					CSharp_ArgumentOut arg1 = (CSharp_ArgumentOut) which;
					return arg1.arg;
				}
				if (which is CSharp_ArgumentOutType)
				{
					CSharp_ArgumentOutType arg2 = (CSharp_ArgumentOutType) which;
					return arg2.arg;
				}
				if (which is CSharp_ArgumentRef)
				{
					CSharp_ArgumentRef arg3 = (CSharp_ArgumentRef) which;
					return arg3.arg;
				}
				throw new Exception("Unable to process: " + which);
			}
		}
	}

}
