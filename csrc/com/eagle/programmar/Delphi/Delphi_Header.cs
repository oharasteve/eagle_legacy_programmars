// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

namespace com.eagle.programmar.Delphi
{
	using Delphi_Comment = com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
	using Delphi_Include = com.eagle.programmar.Delphi.Terminals.Delphi_Include;
	using Delphi_KeywordChoice = com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Header : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_KeywordChoice XXINTERFACE = new com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice("Interface", "Implementation");
		public Delphi_KeywordChoice XXINTERFACE = new Delphi_KeywordChoice("Interface", "Implementation");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Comment XXcomment;
		public Delphi_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Uses XXuses;
		public Delphi_Uses XXuses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Types XXtypes;
		public Delphi_Types XXtypes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Consts XXconsts;
		public Delphi_Consts XXconsts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Vars XXvars;
		public Delphi_Vars XXvars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Procedure XXproc;
		public Delphi_Procedure XXproc;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Function XXfunc;
		public Delphi_Function XXfunc;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Include XXinclude;
		public Delphi_Include XXinclude;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Delphi_Initialization extends com.eagle.tokens.TokenSequence
		public class Delphi_Initialization : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice INITIALIZATION = new com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice("Initialization", "Finalization");
			public Delphi_KeywordChoice INITIALIZATION = new Delphi_KeywordChoice("Initialization", "Finalization");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Delphi_Statement stmt;
			public Delphi_Statement stmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public PunctuationSemicolon semicolon;
		}

		public virtual void processHeader(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which = this.getWhich();
			if (which is Delphi_Comment)
			{
				string comment = ((Delphi_Comment) which).getValue();
				generator.addComment(comment, which);
			}
			else if (which is Delphi_Include)
			{
				string comment = ((Delphi_Include) which).getValue();
				generator.addComment(comment, which);
			}
			else if (which is Delphi_Uses)
			{
				Delphi_Uses uses = (Delphi_Uses) which;
				uses.transformUses(transformer, generator);
			}
			else if (which is Delphi_Types)
			{
				Delphi_Types types = (Delphi_Types) which;
				types.transformTypes(transformer, generator);
			}
			else if (which is Delphi_Consts)
			{
				Delphi_Consts consts = (Delphi_Consts) which;
				consts.transformConsts(transformer, generator);
			}
			else if (which is Delphi_Vars)
			{
				Delphi_Vars vars = (Delphi_Vars) which;
				vars.transformVars(transformer, generator);
			}
			else if (which is Delphi_Procedure)
			{
				Delphi_Procedure proc = (Delphi_Procedure) which;
				proc.transformProcedure(transformer, generator);
			}
			else if (which is Delphi_Function)
			{
				Delphi_Function func = (Delphi_Function) which;
				func.transformFunction(transformer, generator);
			}
			else
			{
				throw new Exception("Cannot handle " + which + " yet.");
			}
		}
	}

}
