// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Declarations;

import com.eagle.programmar.Gupta.Gupta_Declaration;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;

public class Gupta_Global_Declarations extends Gupta_Declaration
{
	public @S(10) Gupta_Keyword Global = new Gupta_Keyword("Global");
	public @S(20) Gupta_Keyword Declarations = new Gupta_Keyword("Declarations");

	public @S(30) Gupta_Window_Defaults windowDefaults;
	public @S(40) Gupta_Formats formats;
	public @S(50) Gupta_External_Functions externalFunctions;
	public @S(60) Gupta_Constants constants;
	public @S(70) Gupta_Resources resources;
	public @S(80) Gupta_Variables variables;
	public @S(90) Gupta_Internal_Functions internalFunctions;
	public @S(100) Gupta_Named_Menus namedMenus;
	public @S(110) Gupta_Class_Definitions classDefinitions;
	public @S(120) Gupta_Application_Actions applicationActions;
}
