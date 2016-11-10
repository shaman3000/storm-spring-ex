package com.shubin.services;

import javax.script.*;
import java.util.Map;
import java.util.Map.Entry;

public class ExpressionEvaluatorService {
	
	private ScriptEngine engine;  

	public ExpressionEvaluatorService() {

	}

	public ExpressionEvaluatorService(String engineName) {
		ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByName(engineName);
	}

	public void runScript(String script) throws Exception {
		this.runScript(script, null);
	}
	
	public void runScript(String script, Map<String, Object> contextObjects) throws Exception {
		ScriptContext context = new SimpleScriptContext();
		if (contextObjects != null) {
			Bindings bnd = context.getBindings(ScriptContext.ENGINE_SCOPE);
			for (Entry<String, Object> entry : contextObjects.entrySet()) 
				bnd.put(entry.getKey(), entry.getValue());
		}	
		engine.eval(script, context);
	}



}
