package com.zzc.core.groovy.impl;

import org.springframework.stereotype.Component;

import com.zzc.core.groovy.IScript;

/**
 * 系统脚本
 */
@Component
public class ScriptImpl implements IScript {
	public String getCurrentUserId() {
		return "1";
	}
}