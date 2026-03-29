// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.Delphi
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram, EagleScope.EagleScopeInterface
	{
		public const string DELPHI = "Delphi";

		public Delphi_Program() : base(DELPHI, new Delphi_Syntax())
		{
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, Delphi_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Delphi_Syntax.IS_CASE_SENSITIVE);

		public override string booleanName(bool flag)
		{
			if (flag)
			{
				return "True";
			}
			return "False";
		}

		public override string DocRoot
		{
			get
			{
				return "http://docwiki.embarcadero.com/RADStudio/en/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Delphi_Full_or_Partial fullOrPartial;
		public Delphi_Full_or_Partial fullOrPartial;

		public class Delphi_Full_or_Partial : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Delphi_Full XXfull;
			public Delphi_Full XXfull;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Delphi_Partial extends com.eagle.tokens.TokenSequence
			public class Delphi_Partial : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Delphi_Header> headers;
				public TokenList<Delphi_Header> headers;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(fullOrPartial);
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (!(fullOrPartial.getWhich() is Delphi_Full))
			{
				throw new Exception("Can only handle complete Delphi programs");
			}
			Delphi_Full full = (Delphi_Full) fullOrPartial.getWhich();
			full.transformFull(transformer, generator);
			return generator.getTransfomedProgram();
		}
	}

}
