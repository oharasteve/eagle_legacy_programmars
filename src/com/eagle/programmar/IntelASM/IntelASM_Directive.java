// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.IntelASM.Directives.IntelASM_CpuDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_DefineDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_EquDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_GlobalDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_IncludeDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_ListDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_MacroDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_SectionDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_TitleDirective;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IntelASM_Directive extends TokenSequence
{
	public @S(10) IntelASM_DirectiveList directive;
	public @S(20) IntelASM_EndOfLine eoln;

	public static class IntelASM_DirectiveList extends TokenChooser
	{
		public @CHOICE IntelASM_CpuDirective XXcpuDirective;
		public @CHOICE IntelASM_DefineDirective XXdefineDirective;
		public @CHOICE IntelASM_EquDirective XXequDirective;
		public @CHOICE IntelASM_GlobalDirective XXglobalDirective;
		public @CHOICE IntelASM_IncludeDirective XXincludeDirective;
		public @CHOICE IntelASM_ListDirective XXlistDirective;
		public @CHOICE IntelASM_MacroDirective XXmacroDirective;
		public @CHOICE IntelASM_SectionDirective XXsectionDirective;
		public @CHOICE IntelASM_TitleDirective XXtitleInstruction;
	}
}
