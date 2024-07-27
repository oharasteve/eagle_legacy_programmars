// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG;

import com.eagle.core.EagleLanguage;
import com.eagle.parsers.EagleOverrideManager;
import com.eagle.programmar.RPG.Directives.RPG_Copy_Directive;
import com.eagle.programmar.RPG.Directives.RPG_Eject_Directive;
import com.eagle.programmar.RPG.Directives.RPG_Space_Directive;
import com.eagle.programmar.RPG.Directives.RPG_Title_Directive;
import com.eagle.programmar.RPG.Specifications.RPG_C_Calculation_Specification;
import com.eagle.programmar.RPG.Specifications.RPG_C_Calculation_Specification.RPG_C_Calculation_Specification_III;
import com.eagle.programmar.RPG.Specifications.RPG_C_Calculation_Specification.RPG_C_Calculation_Specification_IV;
import com.eagle.programmar.RPG.Specifications.RPG_D_Data_Specification;
import com.eagle.programmar.RPG.Specifications.RPG_D_Data_Specification.RPG_D_Data_Specification_III;
import com.eagle.programmar.RPG.Specifications.RPG_D_Data_Specification.RPG_D_Data_Specification_IV;
import com.eagle.programmar.RPG.Specifications.RPG_E_Extension_Specification;
import com.eagle.programmar.RPG.Specifications.RPG_F_File_Specification;
import com.eagle.programmar.RPG.Specifications.RPG_F_File_Specification.RPG_F_File_Specification_III;
import com.eagle.programmar.RPG.Specifications.RPG_F_File_Specification.RPG_F_File_Specification_IV;
import com.eagle.programmar.RPG.Specifications.RPG_H_Header_Specification;
import com.eagle.programmar.RPG.Specifications.RPG_H_Header_Specification.RPG_H_Header_Specification_III;
import com.eagle.programmar.RPG.Specifications.RPG_H_Header_Specification.RPG_H_Header_Specification_IV;
import com.eagle.programmar.RPG.Specifications.RPG_I_Input_Specification;
import com.eagle.programmar.RPG.Specifications.RPG_L_LineCounter_Specification;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Spec;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification_IV;
import com.eagle.programmar.RPG.Specifications.RPG_U_AutoReport_Specification;
import com.eagle.programmar.RPG.Terminals.RPG_Comment;
import com.eagle.programmar.RPG.Terminals.RPG_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public abstract class RPG_Program extends EagleLanguage
{
	public RPG_Program(String name)
	{
		super(name, new RPG_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "Unknown";
	}

	public static class RPG_Item extends TokenSequence
	{
		public @S(10) @OPT RPG_Spec_or_Directive specOrDirective;
		public @S(20) RPG_EndOfLine eoln;
	}

	public static class RPG_Spec_or_Directive extends TokenChooser
	{
		public @CHOICE RPG_Comment XXcomment;

		public @CHOICE RPG_Title_Directive XXtitleDirective;
		public @CHOICE RPG_Eject_Directive XXejectDirective;
		public @CHOICE RPG_Space_Directive XXspaceDirective;
		public @CHOICE RPG_Copy_Directive XXcopyDirective;

		public @CHOICE RPG_D_Data_Specification XXdSpec;
		public @CHOICE RPG_U_AutoReport_Specification XXuSpec;
		public @CHOICE RPG_H_Header_Specification XXhSpec;
		public @CHOICE RPG_F_File_Specification XXfSpec;
		public @CHOICE RPG_E_Extension_Specification XXeSpec;
		public @CHOICE RPG_L_LineCounter_Specification XXlSpec;
		public @CHOICE RPG_I_Input_Specification XXiSpec;
		public @CHOICE RPG_C_Calculation_Specification XXcSpec;
		public @CHOICE RPG_O_Output_Specification XXoSpec;
	}

	// Components of an RPG Program
	public @S(10) TokenList<RPG_Item> items;

	public static class RPG_III_Program extends RPG_Program
	{
		public static final String RPGIII = "RPG_III";

		public RPG_III_Program()
		{
			super(RPGIII);
		}

		@Override
		public void findLanguageOverrides(EagleOverrideManager overrider)
		{
			overrider.override(RPG_C_Calculation_Specification.class, RPG_C_Calculation_Specification_III.class);
			overrider.override(RPG_D_Data_Specification.class, RPG_D_Data_Specification_III.class); // Not available in
																									// RPG III
			overrider.override(RPG_F_File_Specification.class, RPG_F_File_Specification_III.class);
			overrider.override(RPG_H_Header_Specification.class, RPG_H_Header_Specification_III.class);
			overrider.override(RPG_O_Output_Specification.class, RPG_O_Output_Specification_III.class);
		}
	}

	public static class RPG_IV_Program extends RPG_Program
	{
		public static final String RPGIV = "RPG_IV";

		public RPG_IV_Program()
		{
			super(RPGIV);
		}

		@Override
		public void findLanguageOverrides(EagleOverrideManager overrider)
		{
			overrider.override(RPG_C_Calculation_Specification.class, RPG_C_Calculation_Specification_IV.class);
			overrider.override(RPG_D_Data_Specification.class, RPG_D_Data_Specification_IV.class);
			overrider.override(RPG_F_File_Specification.class, RPG_F_File_Specification_IV.class);
			overrider.override(RPG_H_Header_Specification.class, RPG_H_Header_Specification_IV.class);
			overrider.override(RPG_O_Output_Specification.class, RPG_O_Output_Specification_IV.class);
		}
	}
}
