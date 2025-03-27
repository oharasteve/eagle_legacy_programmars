// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django;

import com.eagle.programmar.Django.Controls.Django_AutoEscapeControl;
import com.eagle.programmar.Django.Controls.Django_BlockControl;
import com.eagle.programmar.Django.Controls.Django_BlockTransControl;
import com.eagle.programmar.Django.Controls.Django_CommentControl;
import com.eagle.programmar.Django.Controls.Django_ExtendsControl;
import com.eagle.programmar.Django.Controls.Django_ForControl;
import com.eagle.programmar.Django.Controls.Django_FromControl;
import com.eagle.programmar.Django.Controls.Django_IfControl;
import com.eagle.programmar.Django.Controls.Django_ImportControl;
import com.eagle.programmar.Django.Controls.Django_LoadControl;
import com.eagle.programmar.Django.Controls.Django_MacroControl;
import com.eagle.programmar.Django.Controls.Django_SetControl;
import com.eagle.programmar.Django.Controls.Django_SpacelessControl;
import com.eagle.programmar.Django.Controls.Django_TransControl;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_Control extends TokenSequence
{
	public @S(10) HTML_Punctuation bracePercent = new HTML_Punctuation("{%");
	public @S(20) @OPT PunctuationHyphen dash1;
	public @S(30) Django_ControlChoices which;
	public @S(40) @OPT PunctuationHyphen dash2;
	public @S(50) HTML_Punctuation percentBrace = new HTML_Punctuation("%}");

	public static class Django_ControlChoices extends TokenChooser
	{
		public @CHOICE Django_AutoEscapeControl XXautoescapeControl;
		public @CHOICE Django_BlockControl XXblockControl;
		public @CHOICE Django_BlockTransControl XXblockTransControl;
		public @CHOICE Django_CommentControl XXcommentControl;
		public @CHOICE Django_ExtendsControl XXextendsControl;
		public @CHOICE Django_ForControl XXforControl;
		public @CHOICE Django_FromControl XXfromControl;
		public @CHOICE Django_IfControl XXifControl;
		public @CHOICE Django_ImportControl XXimportControl;
		public @CHOICE Django_LoadControl XXloadControl;
		public @CHOICE Django_MacroControl XXmacroControl;
		public @CHOICE Django_SetControl XXsetControl;
		public @CHOICE Django_SpacelessControl XXspacelessControl;
		public @CHOICE Django_TransControl XXtransControl;
	}
}
