// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Directives;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSS_AtApply extends TokenSequence
{
	public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
	public @S(20) CSS_Keyword APPLY = new CSS_Keyword("apply");
	public @S(30) CSS_Punctuation dashDash = new CSS_Punctuation("--");
	public @S(40) CSS_KeywordChoice what = new CSS_KeywordChoice("diagnostics-card-icon",
			"diagnostics-caution-banner-font", "diagnostics-chart-tick-font", "diagnostics-chart-title-font",
			"diagnostics-routine-additional-message-font", "diagnostics-routine-name-font",
			"diagnostics-settings-link-font", "diagnostics-text-badge-font", "diagnostics-troubleshooting-font");
}
