// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

namespace com.eagle.programmar.CSS.Directives
{
	using CSS_Keyword = com.eagle.programmar.CSS.Terminals.CSS_Keyword;
	using CSS_KeywordChoice = com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
	using CSS_Punctuation = com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CSS_AtApply : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Punctuation at = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('@');
		public CSS_Punctuation at = new CSS_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSS.Terminals.CSS_Keyword APPLY = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("apply");
		public CSS_Keyword APPLY = new CSS_Keyword("apply");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSS.Terminals.CSS_Punctuation dashDash = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation("--");
		public CSS_Punctuation dashDash = new CSS_Punctuation("--");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice what = new com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice("diagnostics-card-icon", "diagnostics-caution-banner-font", "diagnostics-chart-tick-font", "diagnostics-chart-title-font", "diagnostics-routine-additional-message-font", "diagnostics-routine-name-font", "diagnostics-settings-link-font", "diagnostics-text-badge-font", "diagnostics-troubleshooting-font");
		public CSS_KeywordChoice what = new CSS_KeywordChoice("diagnostics-card-icon", "diagnostics-caution-banner-font", "diagnostics-chart-tick-font", "diagnostics-chart-title-font", "diagnostics-routine-additional-message-font", "diagnostics-routine-name-font", "diagnostics-settings-link-font", "diagnostics-text-badge-font", "diagnostics-troubleshooting-font");
	}

}
