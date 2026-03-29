// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Template
{
	using Template_AssignmentStatement = com.eagle.programmar.Template.Statements.Template_AssignmentStatement;
	using Template_DataStatement = com.eagle.programmar.Template.Statements.Template_DataStatement;
	using Template_PrintStatement = com.eagle.programmar.Template.Statements.Template_PrintStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Template_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Template_AssignmentStatement XXassignmentStatement;
		public Template_AssignmentStatement XXassignmentStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Template_DataStatement XXdataStatement;
		public Template_DataStatement XXdataStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Template_PrintStatement XXprintStatement;
		public Template_PrintStatement XXprintStatement;
	}

}
