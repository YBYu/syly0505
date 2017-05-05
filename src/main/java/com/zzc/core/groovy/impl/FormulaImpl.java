package com.zzc.core.groovy.impl;

import org.springframework.stereotype.Component;

import com.zzc.core.groovy.IScript;

/**
 * 公式脚本
 */
@Component
public class FormulaImpl implements IScript {
	public Double add(Double a,Double b) {
		return a*b;
	}
}