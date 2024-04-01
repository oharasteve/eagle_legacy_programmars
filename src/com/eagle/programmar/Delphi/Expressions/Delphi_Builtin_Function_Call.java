// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.programmar.Delphi.Delphi_Parameter_List;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Delphi_Builtin_Function_Call extends PrimaryOperator
{
	public @S(10) Delphi_KeywordChoice name = new Delphi_KeywordChoice("Odd", "Pred", "Succ");
	public @S(20) Delphi_Parameter_List params;
}
