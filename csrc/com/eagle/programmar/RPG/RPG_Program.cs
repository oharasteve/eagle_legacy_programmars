// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.RPG
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleOverrideManager = com.eagle.parsers.EagleOverrideManager;
	using RPG_Copy_Directive = com.eagle.programmar.RPG.Directives.RPG_Copy_Directive;
	using RPG_Eject_Directive = com.eagle.programmar.RPG.Directives.RPG_Eject_Directive;
	using RPG_Space_Directive = com.eagle.programmar.RPG.Directives.RPG_Space_Directive;
	using RPG_Title_Directive = com.eagle.programmar.RPG.Directives.RPG_Title_Directive;
	using RPG_C_Calculation_Specification = com.eagle.programmar.RPG.Specifications.RPG_C_Calculation_Specification;
	using RPG_C_Calculation_Specification_III = com.eagle.programmar.RPG.Specifications.RPG_C_Calculation_Specification.RPG_C_Calculation_Specification_III;
	using RPG_C_Calculation_Specification_IV = com.eagle.programmar.RPG.Specifications.RPG_C_Calculation_Specification.RPG_C_Calculation_Specification_IV;
	using RPG_D_Data_Specification = com.eagle.programmar.RPG.Specifications.RPG_D_Data_Specification;
	using RPG_D_Data_Specification_III = com.eagle.programmar.RPG.Specifications.RPG_D_Data_Specification.RPG_D_Data_Specification_III;
	using RPG_D_Data_Specification_IV = com.eagle.programmar.RPG.Specifications.RPG_D_Data_Specification.RPG_D_Data_Specification_IV;
	using RPG_E_Extension_Specification = com.eagle.programmar.RPG.Specifications.RPG_E_Extension_Specification;
	using RPG_F_File_Specification = com.eagle.programmar.RPG.Specifications.RPG_F_File_Specification;
	using RPG_F_File_Specification_III = com.eagle.programmar.RPG.Specifications.RPG_F_File_Specification.RPG_F_File_Specification_III;
	using RPG_F_File_Specification_IV = com.eagle.programmar.RPG.Specifications.RPG_F_File_Specification.RPG_F_File_Specification_IV;
	using RPG_H_Header_Specification = com.eagle.programmar.RPG.Specifications.RPG_H_Header_Specification;
	using RPG_H_Header_Specification_III = com.eagle.programmar.RPG.Specifications.RPG_H_Header_Specification.RPG_H_Header_Specification_III;
	using RPG_H_Header_Specification_IV = com.eagle.programmar.RPG.Specifications.RPG_H_Header_Specification.RPG_H_Header_Specification_IV;
	using RPG_I_Input_Specification = com.eagle.programmar.RPG.Specifications.RPG_I_Input_Specification;
	using RPG_L_LineCounter_Specification = com.eagle.programmar.RPG.Specifications.RPG_L_LineCounter_Specification;
	using RPG_O_Output_Specification = com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification;
	using RPG_O_Output_Specification_III = com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification_III;
	using RPG_O_Output_Specification_IV = com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification_IV;
	using RPG_U_AutoReport_Specification = com.eagle.programmar.RPG.Specifications.RPG_U_AutoReport_Specification;
	using RPG_Comment = com.eagle.programmar.RPG.Terminals.RPG_Comment;
	using RPG_EndOfLine = com.eagle.programmar.RPG.Terminals.RPG_EndOfLine;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public abstract class RPG_Program : AbstractLanguage
	{
		public RPG_Program(string name) : base(name, new RPG_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "Unknown";
			}
		}

		public class RPG_Item : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT RPG_Spec_or_Directive specOrDirective;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_EndOfLine eoln;
			public RPG_EndOfLine eoln;
		}

		public class RPG_Spec_or_Directive : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_Comment XXcomment;
			public RPG_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_Title_Directive XXtitleDirective;
			public RPG_Title_Directive XXtitleDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_Eject_Directive XXejectDirective;
			public RPG_Eject_Directive XXejectDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_Space_Directive XXspaceDirective;
			public RPG_Space_Directive XXspaceDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_Copy_Directive XXcopyDirective;
			public RPG_Copy_Directive XXcopyDirective;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_D_Data_Specification XXdSpec;
			public RPG_D_Data_Specification XXdSpec;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_U_AutoReport_Specification XXuSpec;
			public RPG_U_AutoReport_Specification XXuSpec;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_H_Header_Specification XXhSpec;
			public RPG_H_Header_Specification XXhSpec;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_F_File_Specification XXfSpec;
			public RPG_F_File_Specification XXfSpec;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_E_Extension_Specification XXeSpec;
			public RPG_E_Extension_Specification XXeSpec;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_L_LineCounter_Specification XXlSpec;
			public RPG_L_LineCounter_Specification XXlSpec;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_I_Input_Specification XXiSpec;
			public RPG_I_Input_Specification XXiSpec;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_C_Calculation_Specification XXcSpec;
			public RPG_C_Calculation_Specification XXcSpec;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_O_Output_Specification XXoSpec;
			public RPG_O_Output_Specification XXoSpec;
		}

		// Components of an RPG Program
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<RPG_Item> items;
		public TokenList<RPG_Item> items;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<RPG_CTDATA> ctDataItems;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<RPG_CommentEOLN> comments;
		public  OPT;

		public class RPG_CommentEOLN : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Comment comment;
			public RPG_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_EndOfLine eoln;
			public RPG_EndOfLine eoln;
		}

		public class RPG_III_Program : RPG_Program
		{
			public const string RPGIII = "RPG_III";

			public RPG_III_Program() : base(RPGIII)
			{
			}

			public override void findLanguageOverrides(EagleOverrideManager overrider)
			{
				overrider.@override(typeof(RPG_C_Calculation_Specification), typeof(RPG_C_Calculation_Specification.RPG_C_Calculation_Specification_III));
				overrider.@override(typeof(RPG_D_Data_Specification), typeof(RPG_D_Data_Specification.RPG_D_Data_Specification_III)); // Not available in
																										// RPG III
				overrider.@override(typeof(RPG_F_File_Specification), typeof(RPG_F_File_Specification.RPG_F_File_Specification_III));
				overrider.@override(typeof(RPG_H_Header_Specification), typeof(RPG_H_Header_Specification.RPG_H_Header_Specification_III));
				overrider.@override(typeof(RPG_O_Output_Specification), typeof(RPG_O_Output_Specification_III));
			}
		}

		public class RPG_IV_Program : RPG_Program
		{
			public const string RPGIV = "RPG_IV";

			public RPG_IV_Program() : base(RPGIV)
			{
			}

			public override void findLanguageOverrides(EagleOverrideManager overrider)
			{
				overrider.@override(typeof(RPG_C_Calculation_Specification), typeof(RPG_C_Calculation_Specification.RPG_C_Calculation_Specification_IV));
				overrider.@override(typeof(RPG_D_Data_Specification), typeof(RPG_D_Data_Specification.RPG_D_Data_Specification_IV));
				overrider.@override(typeof(RPG_F_File_Specification), typeof(RPG_F_File_Specification.RPG_F_File_Specification_IV));
				overrider.@override(typeof(RPG_H_Header_Specification), typeof(RPG_H_Header_Specification.RPG_H_Header_Specification_IV));
				overrider.@override(typeof(RPG_O_Output_Specification), typeof(RPG_O_Output_Specification_IV));
			}
		}
	}

}
