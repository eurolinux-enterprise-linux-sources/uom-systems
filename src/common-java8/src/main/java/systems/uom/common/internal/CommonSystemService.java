/*
 * Unit Systems
 * Copyright (c) 2005-2017, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package systems.uom.common.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.measure.spi.SystemOfUnits;
import javax.measure.spi.SystemOfUnitsService;

import systems.uom.common.CGS;
import systems.uom.common.Imperial;
import systems.uom.common.USCustomary;
import tec.uom.lib.common.function.IntPrioritySupplier;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.7, March 4, 2017
 */
public class CommonSystemService implements SystemOfUnitsService,
	IntPrioritySupplier {
    static final int PRIO = 100;
    private static final String DEFAULT_SYSTEM_NAME = "USCustomary";

    private final Map<String, SystemOfUnits> souMap = new HashMap<>();
    private final Map<String, String> aliases = new HashMap<>();

    public CommonSystemService() {
	souMap.put("Imperial", Imperial.getInstance());
	souMap.put(DEFAULT_SYSTEM_NAME, USCustomary.getInstance());
	souMap.put("CGS", CGS.getInstance());
	aliases.put("US", DEFAULT_SYSTEM_NAME);
	aliases.put("Centimetre–gram–second", "CGS");
    }

    public Collection<SystemOfUnits> getAvailableSystemsOfUnits() {
	return souMap.values();
    }

    @Override
    public SystemOfUnits getSystemOfUnits() {
	return getSystemOfUnits(DEFAULT_SYSTEM_NAME); 
	// We assume US Customary as the more common system here
    }

    @Override
    public SystemOfUnits getSystemOfUnits(String name) {
	String alias = aliases.get(name);
	if (alias != null && alias.length() > 0) {
	    return souMap.get(alias);
	}
	return souMap.get(name);
    }

    @Override
    public int getPriority() {
	return PRIO;
    }
}
