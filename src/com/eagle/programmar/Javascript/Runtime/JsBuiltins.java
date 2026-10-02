// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: the built-ins a small JavaScript program expects.

package com.eagle.programmar.Javascript.Runtime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues.Builtin;
import com.eagle.programmar.Javascript.Runtime.JsValues.JsFunction;
import com.eagle.programmar.Javascript.Runtime.JsValues.JsObject;
import com.eagle.programmar.Javascript.Runtime.JsValues.JsThrow;

public final class JsBuiltins
{
	private JsBuiltins() { }

	private static JsFunction fn(String name, Builtin b) { return new JsFunction(name, b); }
	private static EagleValue a(List<EagleValue> args, int i) { return JsRuntime.arg(args, i); }
	private static String s(List<EagleValue> args, int i) { return JsValues.toText(a(args, i)); }
	private static double n(List<EagleValue> args, int i) { return JsValues.toNumber(a(args, i)); }
	private static EagleArray array(List<EagleValue> items) { EagleArray r = JsValues.newArray(); for (EagleValue v : items) r.addValue(v); return r; }

	public static void install(JsRuntime rt)
	{
		JsEnv g = rt.global;
		g.define("undefined", JsValues.undefined());
		g.define("NaN", JsValues.num(Double.NaN));
		g.define("Infinity", JsValues.num(Double.POSITIVE_INFINITY));

		JsObject console = new JsObject();
		Builtin log = (in, t, args) ->
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < args.size(); i++) { if (i > 0) sb.append(' '); sb.append(JsValues.str(args.get(i))); }
			System.out.println(sb);
			return JsValues.undefined();
		};
		console.set("log", fn("log", log));
		console.set("error", fn("error", log));
		console.set("warn", fn("warn", log));
		console.set("info", fn("info", log));
		g.define("console", console);

		JsObject object = new JsObject();
		object.set("keys", fn("keys", (in, t, args) -> { EagleValue o = a(args, 0); ArrayList<EagleValue> out = new ArrayList<EagleValue>(); if (o instanceof JsObject) for (String k : ((JsObject) o).keys()) out.add(JsValues.str(k)); else if (o.isArray()) for (int i = 0; i < JsValues.items(o).size(); i++) out.add(JsValues.str(String.valueOf(i))); return array(out); }));
		object.set("values", fn("values", (in, t, args) -> { EagleValue o = a(args, 0); ArrayList<EagleValue> out = new ArrayList<EagleValue>(); if (o instanceof JsObject) out.addAll(((JsObject) o).props.values()); return array(out); }));
		object.set("entries", fn("entries", (in, t, args) -> { EagleValue o = a(args, 0); ArrayList<EagleValue> out = new ArrayList<EagleValue>(); if (o instanceof JsObject) for (String k : ((JsObject) o).keys()) { ArrayList<EagleValue> pair = new ArrayList<EagleValue>(); pair.add(JsValues.str(k)); pair.add(((JsObject) o).get(k)); out.add(array(pair)); } return array(out); }));
		object.set("assign", fn("assign", (in, t, args) -> { EagleValue target = a(args, 0); for (int i = 1; i < args.size(); i++) if (args.get(i) instanceof JsObject && target instanceof JsObject) ((JsObject) target).props.putAll(((JsObject) args.get(i)).props); return target; }));
		object.set("freeze", fn("freeze", (in, t, args) -> a(args, 0)));
		g.define("Object", object);

		JsObject math = new JsObject();
		math.set("floor", fn("floor", (in, t, args) -> JsValues.num(Math.floor(n(args, 0)))));
		math.set("ceil", fn("ceil", (in, t, args) -> JsValues.num(Math.ceil(n(args, 0)))));
		math.set("round", fn("round", (in, t, args) -> JsValues.num(Math.round(n(args, 0)))));
		math.set("abs", fn("abs", (in, t, args) -> JsValues.num(Math.abs(n(args, 0)))));
		math.set("sqrt", fn("sqrt", (in, t, args) -> JsValues.num(Math.sqrt(n(args, 0)))));
		math.set("pow", fn("pow", (in, t, args) -> JsValues.num(Math.pow(n(args, 0), n(args, 1)))));
		math.set("max", fn("max", (in, t, args) -> { double m = Double.NEGATIVE_INFINITY; for (EagleValue v : args) m = Math.max(m, JsValues.toNumber(v)); return JsValues.num(m); }));
		math.set("min", fn("min", (in, t, args) -> { double m = Double.POSITIVE_INFINITY; for (EagleValue v : args) m = Math.min(m, JsValues.toNumber(v)); return JsValues.num(m); }));
		math.set("random", fn("random", (in, t, args) -> JsValues.num(Math.random())));
		math.set("PI", JsValues.num(Math.PI));
		g.define("Math", math);

		JsObject json = new JsObject();
		json.set("stringify", fn("stringify", (in, t, args) -> JsValues.str(stringify(a(args, 0)))));
		g.define("JSON", json);

		JsFunction number = fn("Number", (in, t, args) -> JsValues.num(n(args, 0)));
		number.statics.set("isInteger", fn("isInteger", (in, t, args) -> JsValues.bool(JsValues.isNumber(a(args, 0)) && n(args, 0) == Math.rint(n(args, 0)))));
		number.statics.set("parseInt", fn("parseInt", (in, t, args) -> parseInt(args)));
		number.statics.set("parseFloat", fn("parseFloat", (in, t, args) -> JsValues.num(n(args, 0))));
		g.define("Number", number);
		g.define("String", fn("String", (in, t, args) -> JsValues.str(args.isEmpty() ? "" : JsValues.toText(a(args, 0)))));
		g.define("Boolean", fn("Boolean", (in, t, args) -> JsValues.bool(JsValues.truthy(a(args, 0)))));
		g.define("parseInt", fn("parseInt", (in, t, args) -> parseInt(args)));
		g.define("parseFloat", fn("parseFloat", (in, t, args) -> JsValues.num(n(args, 0))));
		g.define("isNaN", fn("isNaN", (in, t, args) -> JsValues.bool(Double.isNaN(n(args, 0)))));

		JsFunction arrayCtor = fn("Array", (in, t, args) -> array(args));
		arrayCtor.statics.set("isArray", fn("isArray", (in, t, args) -> JsValues.bool(a(args, 0).isArray())));
		arrayCtor.statics.set("from", fn("from", (in, t, args) -> { EagleValue v = a(args, 0); if (v.isArray()) return array(JsValues.items(v)); if (v instanceof JsObject && ((JsObject) v).store != null) { ArrayList<EagleValue> out = new ArrayList<EagleValue>(); for (EagleValue[] e : ((JsObject) v).store.values()) out.add("Map".equals(((JsObject) v).className) ? pair(e[0], e[1]) : e[0]); return array(out); } if (v.isString()) { ArrayList<EagleValue> out = new ArrayList<EagleValue>(); for (char c : v.forceStringValue().toCharArray()) out.add(JsValues.str(String.valueOf(c))); return array(out); } return array(new ArrayList<EagleValue>()); }));
		g.define("Array", arrayCtor);

		JsFunction error = fn("Error", (in, t, args) -> JsValues.error(args.isEmpty() ? "" : s(args, 0)));
		error.statics.set("[[construct]]", error);
		g.define("Error", error);
		g.define("TypeError", error);
		g.define("RangeError", error);

		JsFunction map = fn("Map", (in, t, args) -> { JsObject m = new JsObject(); m.className = "Map"; m.store = new java.util.LinkedHashMap<String, EagleValue[]>(); if (!args.isEmpty() && a(args, 0).isArray()) for (EagleValue e : JsValues.items(a(args, 0))) if (e.isArray()) { List<EagleValue> kv = JsValues.items(e); m.store.put(keyOf(kv.get(0)), new EagleValue[] { kv.get(0), kv.size() > 1 ? kv.get(1) : JsValues.undefined() }); } return m; });
		map.statics.set("[[construct]]", map);
		g.define("Map", map);
		JsFunction set = fn("Set", (in, t, args) -> { JsObject m = new JsObject(); m.className = "Set"; m.store = new java.util.LinkedHashMap<String, EagleValue[]>(); if (!args.isEmpty() && a(args, 0).isArray()) for (EagleValue e : JsValues.items(a(args, 0))) m.store.put(keyOf(e), new EagleValue[] { e, e }); return m; });
		set.statics.set("[[construct]]", set);
		g.define("Set", set);
		g.define("Promise", fn("Promise", (in, t, args) -> { throw new JsThrow(JsValues.error("Promise is not supported by the interpreter")); }));
	}

	private static EagleValue pair(EagleValue k, EagleValue v) { ArrayList<EagleValue> p = new ArrayList<EagleValue>(); p.add(k); p.add(v); return array(p); }

	private static EagleValue parseInt(List<EagleValue> args)
	{
		String text = s(args, 0).trim();
		int radix = args.size() > 1 && !JsValues.isNullish(a(args, 1)) ? (int) n(args, 1) : 10;
		int i = 0;
		if (i < text.length() && (text.charAt(i) == '-' || text.charAt(i) == '+')) i++;
		while (i < text.length() && Character.digit(text.charAt(i), radix) >= 0) i++;
		String digits = text.substring(0, i);
		if (digits.isEmpty() || digits.equals("-") || digits.equals("+")) return JsValues.num(Double.NaN);
		try { return JsValues.num(Long.parseLong(digits, radix)); } catch (NumberFormatException ex) { return JsValues.num(Double.NaN); }
	}

	static String keyOf(EagleValue v) { return JsValues.typeOf(v) + ":" + (v instanceof JsObject ? System.identityHashCode(v) : JsValues.toText(v)); }

	public static String stringify(EagleValue v)
	{
		if (v == null || v instanceof JsValues.JsUndefined || v instanceof JsFunction) return "undefined";
		if (v instanceof JsValues.JsNull) return "null";
		if (v.isString()) return "\"" + v.forceStringValue().replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n") + "\"";
		if (v.isArray())
		{
			StringBuilder sb = new StringBuilder("[");
			List<EagleValue> items = JsValues.items(v);
			for (int i = 0; i < items.size(); i++) { if (i > 0) sb.append(','); String s = stringify(items.get(i)); sb.append("undefined".equals(s) ? "null" : s); }
			return sb.append(']').toString();
		}
		if (v instanceof JsObject)
		{
			StringBuilder sb = new StringBuilder("{");
			boolean first = true;
			for (String k : ((JsObject) v).keys())
			{
				String s = stringify(((JsObject) v).get(k));
				if ("undefined".equals(s)) continue;
				if (!first) sb.append(',');
				first = false;
				sb.append('"').append(k).append("\":").append(s);
			}
			return sb.append('}').toString();
		}
		return JsValues.toText(v);
	}

	// ------------------------------------------------------------ methods on values

	public static JsFunction objectMethod(JsObject o, String name)
	{
		if (o.store != null)
		{
			boolean isMap = "Map".equals(o.className);
			switch (name)
			{
			case "get": return fn(name, (in, t, args) -> { EagleValue[] e = o.store.get(keyOf(a(args, 0))); return e == null ? JsValues.undefined() : e[1]; });
			case "set": return fn(name, (in, t, args) -> { o.store.put(keyOf(a(args, 0)), new EagleValue[] { a(args, 0), a(args, 1) }); return o; });
			case "add": return fn(name, (in, t, args) -> { o.store.put(keyOf(a(args, 0)), new EagleValue[] { a(args, 0), a(args, 0) }); return o; });
			case "has": return fn(name, (in, t, args) -> JsValues.bool(o.store.containsKey(keyOf(a(args, 0)))));
			case "delete": return fn(name, (in, t, args) -> JsValues.bool(o.store.remove(keyOf(a(args, 0))) != null));
			case "clear": return fn(name, (in, t, args) -> { o.store.clear(); return JsValues.undefined(); });
			case "keys": return fn(name, (in, t, args) -> { ArrayList<EagleValue> out = new ArrayList<EagleValue>(); for (EagleValue[] e : o.store.values()) out.add(e[0]); return array(out); });
			case "values": return fn(name, (in, t, args) -> { ArrayList<EagleValue> out = new ArrayList<EagleValue>(); for (EagleValue[] e : o.store.values()) out.add(e[1]); return array(out); });
			case "entries": return fn(name, (in, t, args) -> { ArrayList<EagleValue> out = new ArrayList<EagleValue>(); for (EagleValue[] e : o.store.values()) out.add(isMap ? pair(e[0], e[1]) : pair(e[0], e[0])); return array(out); });
			case "forEach": return fn(name, (in, t, args) -> { JsRuntime rt = JsRuntime.of(in); for (EagleValue[] e : new ArrayList<EagleValue[]>(o.store.values())) { ArrayList<EagleValue> cb = new ArrayList<EagleValue>(); cb.add(e[1]); cb.add(e[0]); cb.add(o); rt.call(a(args, 0), JsValues.undefined(), cb, null); } return JsValues.undefined(); });
			default: break;
			}
		}
		switch (name)
		{
		case "hasOwnProperty": return fn(name, (in, t, args) -> JsValues.bool(o.props.containsKey(s(args, 0))));
		case "toString": return fn(name, (in, t, args) -> JsValues.str(o.forceStringValue()));
		default: return null;
		}
	}

	public static JsFunction numberMethod(String name)
	{
		switch (name)
		{
		case "toFixed": return fn(name, (in, t, args) -> JsValues.str(String.format("%." + (int) (args.isEmpty() ? 0 : n(args, 0)) + "f", JsValues.toNumber(t))));
		case "toString": return fn(name, (in, t, args) -> JsValues.str(args.isEmpty() ? JsValues.toText(t) : Long.toString((long) JsValues.toNumber(t), (int) n(args, 0))));
		default: return null;
		}
	}

	public static JsFunction stringMethod(String name)
	{
		switch (name)
		{
		case "toUpperCase": return fn(name, (in, t, args) -> JsValues.str(JsValues.toText(t).toUpperCase()));
		case "toLowerCase": return fn(name, (in, t, args) -> JsValues.str(JsValues.toText(t).toLowerCase()));
		case "trim": return fn(name, (in, t, args) -> JsValues.str(JsValues.toText(t).trim()));
		case "trimStart": return fn(name, (in, t, args) -> JsValues.str(JsValues.toText(t).replaceAll("^\\s+", "")));
		case "trimEnd": return fn(name, (in, t, args) -> JsValues.str(JsValues.toText(t).replaceAll("\\s+$", "")));
		case "indexOf": return fn(name, (in, t, args) -> JsValues.num(JsValues.toText(t).indexOf(s(args, 0), args.size() > 1 ? (int) n(args, 1) : 0)));
		case "lastIndexOf": return fn(name, (in, t, args) -> JsValues.num(JsValues.toText(t).lastIndexOf(s(args, 0))));
		case "includes": return fn(name, (in, t, args) -> JsValues.bool(JsValues.toText(t).contains(s(args, 0))));
		case "startsWith": return fn(name, (in, t, args) -> JsValues.bool(JsValues.toText(t).startsWith(s(args, 0))));
		case "endsWith": return fn(name, (in, t, args) -> JsValues.bool(JsValues.toText(t).endsWith(s(args, 0))));
		case "charAt": return fn(name, (in, t, args) -> { String x = JsValues.toText(t); int i = (int) n(args, 0); return JsValues.str(i >= 0 && i < x.length() ? String.valueOf(x.charAt(i)) : ""); });
		case "charCodeAt": return fn(name, (in, t, args) -> { String x = JsValues.toText(t); int i = args.isEmpty() ? 0 : (int) n(args, 0); return i >= 0 && i < x.length() ? JsValues.num(x.charAt(i)) : JsValues.num(Double.NaN); });
		case "slice": return fn(name, (in, t, args) -> { String x = JsValues.toText(t); int[] r = range(x.length(), args); return JsValues.str(r[0] < r[1] ? x.substring(r[0], r[1]) : ""); });
		case "substring": return fn(name, (in, t, args) -> { String x = JsValues.toText(t); int a0 = clamp((int) n(args, 0), x.length()); int a1 = args.size() > 1 ? clamp((int) n(args, 1), x.length()) : x.length(); return JsValues.str(x.substring(Math.min(a0, a1), Math.max(a0, a1))); });
		case "substr": return fn(name, (in, t, args) -> { String x = JsValues.toText(t); int a0 = clamp((int) n(args, 0), x.length()); int len = args.size() > 1 ? (int) n(args, 1) : x.length() - a0; return JsValues.str(x.substring(a0, Math.min(x.length(), a0 + Math.max(0, len)))); });
		case "split": return fn(name, (in, t, args) -> { String x = JsValues.toText(t); ArrayList<EagleValue> out = new ArrayList<EagleValue>(); if (args.isEmpty()) out.add(JsValues.str(x)); else { String sep = s(args, 0); if (sep.isEmpty()) for (char c : x.toCharArray()) out.add(JsValues.str(String.valueOf(c))); else for (String part : x.split(java.util.regex.Pattern.quote(sep), -1)) out.add(JsValues.str(part)); } return array(out); });
		case "replace": return fn(name, (in, t, args) -> { String x = JsValues.toText(t); String from = s(args, 0); int i = x.indexOf(from); return JsValues.str(i < 0 ? x : x.substring(0, i) + s(args, 1) + x.substring(i + from.length())); });
		case "replaceAll": return fn(name, (in, t, args) -> JsValues.str(JsValues.toText(t).replace(s(args, 0), s(args, 1))));
		case "repeat": return fn(name, (in, t, args) -> JsValues.str(JsValues.toText(t).repeat(Math.max(0, (int) n(args, 0)))));
		case "padStart": return fn(name, (in, t, args) -> { String x = JsValues.toText(t); String pad = args.size() > 1 ? s(args, 1) : " "; int len = (int) n(args, 0); StringBuilder sb = new StringBuilder(); while (sb.length() + x.length() < len && !pad.isEmpty()) sb.append(pad); return JsValues.str(sb.substring(0, Math.max(0, len - x.length())) + x); });
		case "padEnd": return fn(name, (in, t, args) -> { String x = JsValues.toText(t); String pad = args.size() > 1 ? s(args, 1) : " "; int len = (int) n(args, 0); StringBuilder sb = new StringBuilder(x); while (sb.length() < len && !pad.isEmpty()) sb.append(pad); return JsValues.str(sb.substring(0, Math.max(x.length(), len))); });
		case "concat": return fn(name, (in, t, args) -> { StringBuilder sb = new StringBuilder(JsValues.toText(t)); for (EagleValue v : args) sb.append(JsValues.toText(v)); return JsValues.str(sb.toString()); });
		case "toString": case "valueOf": return fn(name, (in, t, args) -> JsValues.str(JsValues.toText(t)));
		case "localeCompare": return fn(name, (in, t, args) -> JsValues.num(Integer.signum(JsValues.toText(t).compareTo(s(args, 0)))));
		default: return null;
		}
	}

	private static int clamp(int i, int len) { return Math.max(0, Math.min(i, len)); }

	private static int[] range(int len, List<EagleValue> args)
	{
		int start = args.isEmpty() || JsValues.isNullish(a(args, 0)) ? 0 : (int) n(args, 0);
		int end = args.size() < 2 || JsValues.isNullish(a(args, 1)) ? len : (int) n(args, 1);
		if (start < 0) start = Math.max(0, len + start);
		if (end < 0) end = Math.max(0, len + end);
		return new int[] { clamp(start, len), clamp(end, len) };
	}

	public static JsFunction arrayMethod(String name)
	{
		switch (name)
		{
		case "push": return fn(name, (in, t, args) -> { for (EagleValue v : args) ((EagleArray) t).addValue(v); return JsValues.num(JsValues.items(t).size()); });
		case "pop": return fn(name, (in, t, args) -> { List<EagleValue> l = JsValues.items(t); return l.isEmpty() ? JsValues.undefined() : l.remove(l.size() - 1); });
		case "shift": return fn(name, (in, t, args) -> { List<EagleValue> l = JsValues.items(t); return l.isEmpty() ? JsValues.undefined() : l.remove(0); });
		case "unshift": return fn(name, (in, t, args) -> { List<EagleValue> l = JsValues.items(t); l.addAll(0, args); return JsValues.num(l.size()); });
		case "join": return fn(name, (in, t, args) -> JsValues.str(JsValues.join((EagleArray) t, args.isEmpty() ? "," : s(args, 0))));
		case "indexOf": return fn(name, (in, t, args) -> { List<EagleValue> l = JsValues.items(t); for (int i = 0; i < l.size(); i++) if (JsValues.strictEquals(l.get(i), a(args, 0))) return JsValues.num(i); return JsValues.num(-1); });
		case "includes": return fn(name, (in, t, args) -> { for (EagleValue v : JsValues.items(t)) if (JsValues.strictEquals(v, a(args, 0))) return JsValues.bool(true); return JsValues.bool(false); });
		case "slice": return fn(name, (in, t, args) -> { List<EagleValue> l = JsValues.items(t); int[] r = range(l.size(), args); return array(r[0] < r[1] ? l.subList(r[0], r[1]) : Collections.<EagleValue>emptyList()); });
		case "splice": return fn(name, (in, t, args) -> { List<EagleValue> l = JsValues.items(t); int start = clamp((int) n(args, 0) < 0 ? l.size() + (int) n(args, 0) : (int) n(args, 0), l.size()); int count = args.size() > 1 ? clamp((int) n(args, 1), l.size() - start) : l.size() - start; ArrayList<EagleValue> removed = new ArrayList<EagleValue>(l.subList(start, start + count)); for (int i = 0; i < count; i++) l.remove(start); l.addAll(start, args.subList(Math.min(2, args.size()), args.size())); return array(removed); });
		case "concat": return fn(name, (in, t, args) -> { ArrayList<EagleValue> out = new ArrayList<EagleValue>(JsValues.items(t)); for (EagleValue v : args) { if (v.isArray()) out.addAll(JsValues.items(v)); else out.add(v); } return array(out); });
		case "reverse": return fn(name, (in, t, args) -> { Collections.reverse(JsValues.items(t)); return t; });
		case "sort": return fn(name, (in, t, args) -> { List<EagleValue> l = JsValues.items(t); JsRuntime rt = JsRuntime.of(in); final EagleValue cmp = a(args, 0); Comparator<EagleValue> c = cmp instanceof JsFunction ? (x, y) -> (int) Math.signum(JsValues.toNumber(rt.call(cmp, JsValues.undefined(), list(x, y), null))) : (x, y) -> JsValues.toText(x).compareTo(JsValues.toText(y)); Collections.sort(l, c); return t; });
		case "forEach": return fn(name, (in, t, args) -> { JsRuntime rt = JsRuntime.of(in); List<EagleValue> l = JsValues.items(t); for (int i = 0; i < l.size(); i++) rt.call(a(args, 0), JsValues.undefined(), list(l.get(i), JsValues.num(i), t), null); return JsValues.undefined(); });
		case "map": return fn(name, (in, t, args) -> { JsRuntime rt = JsRuntime.of(in); List<EagleValue> l = JsValues.items(t); ArrayList<EagleValue> out = new ArrayList<EagleValue>(); for (int i = 0; i < l.size(); i++) out.add(rt.call(a(args, 0), JsValues.undefined(), list(l.get(i), JsValues.num(i), t), null)); return array(out); });
		case "filter": return fn(name, (in, t, args) -> { JsRuntime rt = JsRuntime.of(in); List<EagleValue> l = JsValues.items(t); ArrayList<EagleValue> out = new ArrayList<EagleValue>(); for (int i = 0; i < l.size(); i++) if (JsValues.truthy(rt.call(a(args, 0), JsValues.undefined(), list(l.get(i), JsValues.num(i), t), null))) out.add(l.get(i)); return array(out); });
		case "find": return fn(name, (in, t, args) -> { JsRuntime rt = JsRuntime.of(in); List<EagleValue> l = JsValues.items(t); for (int i = 0; i < l.size(); i++) if (JsValues.truthy(rt.call(a(args, 0), JsValues.undefined(), list(l.get(i), JsValues.num(i), t), null))) return l.get(i); return JsValues.undefined(); });
		case "findIndex": return fn(name, (in, t, args) -> { JsRuntime rt = JsRuntime.of(in); List<EagleValue> l = JsValues.items(t); for (int i = 0; i < l.size(); i++) if (JsValues.truthy(rt.call(a(args, 0), JsValues.undefined(), list(l.get(i), JsValues.num(i), t), null))) return JsValues.num(i); return JsValues.num(-1); });
		case "some": return fn(name, (in, t, args) -> { JsRuntime rt = JsRuntime.of(in); List<EagleValue> l = JsValues.items(t); for (int i = 0; i < l.size(); i++) if (JsValues.truthy(rt.call(a(args, 0), JsValues.undefined(), list(l.get(i), JsValues.num(i), t), null))) return JsValues.bool(true); return JsValues.bool(false); });
		case "every": return fn(name, (in, t, args) -> { JsRuntime rt = JsRuntime.of(in); List<EagleValue> l = JsValues.items(t); for (int i = 0; i < l.size(); i++) if (!JsValues.truthy(rt.call(a(args, 0), JsValues.undefined(), list(l.get(i), JsValues.num(i), t), null))) return JsValues.bool(false); return JsValues.bool(true); });
		case "reduce": return fn(name, (in, t, args) -> { JsRuntime rt = JsRuntime.of(in); List<EagleValue> l = JsValues.items(t); int start = 0; EagleValue acc; if (args.size() > 1) acc = a(args, 1); else { if (l.isEmpty()) throw new JsThrow(JsValues.error("TypeError: Reduce of empty array with no initial value")); acc = l.get(0); start = 1; } for (int i = start; i < l.size(); i++) acc = rt.call(a(args, 0), JsValues.undefined(), list(acc, l.get(i), JsValues.num(i), t), null); return acc; });
		case "flat": return fn(name, (in, t, args) -> { ArrayList<EagleValue> out = new ArrayList<EagleValue>(); for (EagleValue v : JsValues.items(t)) { if (v.isArray()) out.addAll(JsValues.items(v)); else out.add(v); } return array(out); });
		case "flatMap": return fn(name, (in, t, args) -> { JsRuntime rt = JsRuntime.of(in); ArrayList<EagleValue> out = new ArrayList<EagleValue>(); List<EagleValue> l = JsValues.items(t); for (int i = 0; i < l.size(); i++) { EagleValue v = rt.call(a(args, 0), JsValues.undefined(), list(l.get(i), JsValues.num(i), t), null); if (v.isArray()) out.addAll(JsValues.items(v)); else out.add(v); } return array(out); });
		case "fill": return fn(name, (in, t, args) -> { List<EagleValue> l = JsValues.items(t); for (int i = 0; i < l.size(); i++) l.set(i, a(args, 0)); return t; });
		case "keys": return fn(name, (in, t, args) -> { ArrayList<EagleValue> out = new ArrayList<EagleValue>(); for (int i = 0; i < JsValues.items(t).size(); i++) out.add(JsValues.num(i)); return array(out); });
		case "entries": return fn(name, (in, t, args) -> { ArrayList<EagleValue> out = new ArrayList<EagleValue>(); List<EagleValue> l = JsValues.items(t); for (int i = 0; i < l.size(); i++) out.add(pair(JsValues.num(i), l.get(i))); return array(out); });
		case "toString": return fn(name, (in, t, args) -> JsValues.str(JsValues.join((EagleArray) t, ",")));
		default: return null;
		}
	}

	private static List<EagleValue> list(EagleValue... vs) { ArrayList<EagleValue> l = new ArrayList<EagleValue>(); for (EagleValue v : vs) l.add(v); return l; }
}
