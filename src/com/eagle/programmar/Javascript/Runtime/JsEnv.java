// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: a lexical environment, the thing a closure keeps.

package com.eagle.programmar.Javascript.Runtime;

import java.util.HashMap;

import com.eagle.math.EagleValue;

public final class JsEnv
{
	public final JsEnv parent;
	private final HashMap<String, EagleValue> vars = new HashMap<String, EagleValue>();

	public JsEnv(JsEnv parent) { this.parent = parent; }

	public void define(String name, EagleValue value) { vars.put(name, JsValues.of(value)); }

	public boolean hasLocal(String name) { return vars.containsKey(name); }

	/** The environment that holds the name, or null. */
	public JsEnv holder(String name)
	{
		for (JsEnv e = this; e != null; e = e.parent) if (e.vars.containsKey(name)) return e;
		return null;
	}

	public EagleValue lookup(String name)
	{
		JsEnv h = holder(name);
		return h == null ? null : h.vars.get(name);
	}

	/** Assigns where the name lives; a name nobody declared lands in the outermost environment. */
	public void assign(String name, EagleValue value)
	{
		JsEnv h = holder(name);
		if (h == null) { h = this; while (h.parent != null) h = h.parent; }
		h.vars.put(name, JsValues.of(value));
	}
}
