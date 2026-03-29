// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.IntelASM
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using IntelASM_CpuDirective = com.eagle.programmar.IntelASM.Directives.IntelASM_CpuDirective;
	using IntelASM_DefineDirective = com.eagle.programmar.IntelASM.Directives.IntelASM_DefineDirective;
	using IntelASM_EquDirective = com.eagle.programmar.IntelASM.Directives.IntelASM_EquDirective;
	using IntelASM_GlobalDirective = com.eagle.programmar.IntelASM.Directives.IntelASM_GlobalDirective;
	using IntelASM_IncludeDirective = com.eagle.programmar.IntelASM.Directives.IntelASM_IncludeDirective;
	using IntelASM_ListDirective = com.eagle.programmar.IntelASM.Directives.IntelASM_ListDirective;
	using IntelASM_MacroDirective = com.eagle.programmar.IntelASM.Directives.IntelASM_MacroDirective;
	using IntelASM_SectionDirective = com.eagle.programmar.IntelASM.Directives.IntelASM_SectionDirective;
	using IntelASM_TitleDirective = com.eagle.programmar.IntelASM.Directives.IntelASM_TitleDirective;
	using IntelASM_EndOfLine = com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_Directive : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) IntelASM_DirectiveList directive;
		public IntelASM_DirectiveList directive;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine eoln;
		public IntelASM_EndOfLine eoln;

		public class IntelASM_DirectiveList : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_CpuDirective XXcpuDirective;
			public IntelASM_CpuDirective XXcpuDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_DefineDirective XXdefineDirective;
			public IntelASM_DefineDirective XXdefineDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_EquDirective XXequDirective;
			public IntelASM_EquDirective XXequDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_GlobalDirective XXglobalDirective;
			public IntelASM_GlobalDirective XXglobalDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_IncludeDirective XXincludeDirective;
			public IntelASM_IncludeDirective XXincludeDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_ListDirective XXlistDirective;
			public IntelASM_ListDirective XXlistDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_MacroDirective XXmacroDirective;
			public IntelASM_MacroDirective XXmacroDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_SectionDirective XXsectionDirective;
			public IntelASM_SectionDirective XXsectionDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_TitleDirective XXtitleInstruction;
			public IntelASM_TitleDirective XXtitleInstruction;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(directive);
		}
	}

}
